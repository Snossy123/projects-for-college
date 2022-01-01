#include <inc/memlayout.h>
#include <kern/kheap.h>
#include <kern/memory_manager.h>

//NOTE: All kernel heap allocations are multiples of PAGE_SIZE (4KB)


struct Process_data{
	int UsedPages;
	uint32 start_add;
	uint32 End_add;
};
struct Process_data processes[5000];
uint32 Ptr_to_move=KERNEL_HEAP_START;
int index_of_proc= -1 ;
uint32 ptr_MAX=KERNEL_HEAP_MAX;


void* kmalloc(unsigned int size)
{
	struct Frame_Info *ptr_frame=NULL;
	//if size (e.g. 6KB must be 8KB )
	size=ROUNDUP(size, PAGE_SIZE);
	int pages=size/PAGE_SIZE;
	if((ptr_MAX-Ptr_to_move)<size){
		return NULL;
	}
	index_of_proc=index_of_proc+1;
	processes[index_of_proc].start_add = (uint32)Ptr_to_move;
	processes[index_of_proc].End_add = (uint32)Ptr_to_move+size;
	processes[index_of_proc].UsedPages = pages;

	int i=0;
	while(i<pages)
	{

		int ret1=allocate_frame(&ptr_frame);
		if(ret1==E_NO_MEM)
			return NULL;

		int ret2=map_frame(ptr_page_directory,ptr_frame,(void*)Ptr_to_move,PERM_WRITEABLE);
		if(ret2==E_NO_MEM)
		{
			//if map doesn't complete . make sure that free frame .
			free_frame(ptr_frame);
			return NULL;
		}
		Ptr_to_move+=PAGE_SIZE;
		i++;
	}

	void* ptr_return_add=((void*)Ptr_to_move-(pages*PAGE_SIZE));

	return  ptr_return_add;

}
uint32* page_table = NULL;
void kfree(void* virtual_address)
{

	int h=0;
	int j=0;
	while(h<=index_of_proc)
	{
		if(processes[h].start_add == (uint32)virtual_address)
		{
			while(j<processes[h].UsedPages)
			{
				unmap_frame(ptr_page_directory,(void*)virtual_address);
				virtual_address+=4096;
				j++;
			}
			break;

		}
		h++;
	}
}




unsigned int kheap_virtual_address(unsigned int physical_address)
{
	//intialize i to move by it
	uint32 i=KERNEL_HEAP_START;
	//for function get_frame_info
	uint32* ptr_page_table = NULL;
	//loop to find virtual address and convert it to corresponding Physical address.
	while(i<Ptr_to_move)
		{		struct Frame_Info *fi =
				get_frame_info(ptr_page_directory, (void*)i, &ptr_page_table);
				if(fi !=NULL )
				{
					//convert to physical address
					uint32 physicalAddr =to_physical_address(fi);
					//check if physical address passed to func Equals to converted PA or not
					if(physicalAddr==physical_address)
						{
						//return corresponding virtual address.
							return i;
						}
				}
				//increament virtual address by Page size (4KB).
			i+=PAGE_SIZE;
		}
	//return 0 if physical address not mapped to virtual one.
	return 0;


}
unsigned int kheap_physical_address(unsigned int virtual_address)
{
	uint32 VA=virtual_address;
	uint32* ptr_page_table = NULL;
	struct Frame_Info *fi =
			get_frame_info(ptr_page_directory, (void*)VA, &ptr_page_table);
	if(fi==NULL )
		return 0;
	uint32 physical_add =to_physical_address(fi);
	return physical_add;
}

//this function to check if next frame is empty or not .
int Addition_Allocate(uint32 vs,unsigned int new_size,int n)
{
	int Addition = ROUNDUP(new_size,PAGE_SIZE);
	uint32 VA=processes[n].End_add;
	while(VA<vs+Addition){
		uint32 *PTR_Ptable = NULL;
		struct Frame_Info* ptr_frame_info = get_frame_info(ptr_page_directory, (void*)VA, &PTR_Ptable);
		if (ptr_frame_info != NULL)
		{
		return -1;
		}
		 VA+=4096;
	}
	return 10;
}

//re allocate and map and update values in array of struct
void *new_kmalloc(int n,void* vs,unsigned int new_size)
{
	// vs: virtual start , n:is number of index.
	int Addition = ROUNDUP(new_size,PAGE_SIZE);
	uint32 VA=processes[n].End_add;
	while( (void*)VA<vs+Addition){


			uint32 *ptr_table = NULL;
			struct Frame_Info* ptr_frame_info = get_frame_info(ptr_page_directory, (void*)VA, &ptr_table);

		//allocating
			int ret = allocate_frame(&ptr_frame_info) ;

			if (ret == E_NO_MEM)
				return NULL;
		//mapping
			int ret2 = map_frame(ptr_page_directory, ptr_frame_info, (void*)VA, PERM_WRITEABLE);
			if (ret2 == E_NO_MEM)
			{
				free_frame(ptr_frame_info) ;
				return NULL;
			}
			 VA+=4096;
	}

	//update
	processes[n].End_add=(uint32)vs+Addition;
	processes[n].UsedPages=Addition/PAGE_SIZE;

	return vs;
}

int Proc_identifier=-9;
void *krealloc(void *virtual_address, uint32 new_size)
{
	// we need to Round up and update new_size .each frame(page) in 4KB.
	new_size=ROUNDUP(new_size, PAGE_SIZE);
	if(virtual_address==NULL)
	{
		return kmalloc(new_size);;
	}
	if(new_size==0)
	{

		kfree(virtual_address);
		return NULL;
	}

	uint32* ptr_page_table = NULL;
	void* VA=(void*)virtual_address;
	int num_pag = -1;
	uint32 old_size;
	for(int i=0 ;i<=index_of_proc;i++)
		{
			if(processes[i].start_add == (uint32)VA)
			{
				num_pag = processes[i].UsedPages;
				old_size = num_pag * PAGE_SIZE;
					if(old_size >= new_size)
					{
						return VA;
					}
					Proc_identifier=i;

			}
		}

		if(Addition_Allocate(processes[Proc_identifier].start_add,new_size,Proc_identifier)==10){
			return 	new_kmalloc(Proc_identifier,(void*)processes[Proc_identifier].start_add,new_size);
		}

		else
		{
			int n_p = processes[Proc_identifier].UsedPages;
					//allocate new virtual..
			//we return it (virt address) after save content and freeing
					void* virt_address = kmalloc(new_size);
					uint32*newData=(uint32*)virt_address;

					uint32*temp_virtual=(uint32 *)virtual_address;
					int i=0;
					while(i<n_p)
					{
						//save data (content) before freeing
						// entry by entry
						for(int t=0;t<1024;t++)
						{
							 *newData=*temp_virtual;
							 newData++;
							 temp_virtual++;
						}
						i++;
					}
					//now can free. and return the new virtual address.
					kfree(virtual_address);
					return virt_address;
				}
		return NULL;
		}



