#include <inc/lib.h>

// malloc()
//	This function use FIRST FIT strategy to allocate space in heap
//  with the given size and return void pointer to the start of the allocated space

//	To do this, we need to switch to the kernel, allocate the required space
//	in Page File then switch back to the user again.
//
//	We can use sys_allocateMem(uint32 virtual_address, uint32 size); which
//		switches to the kernel mode, calls allocateMem(struct Env* e, uint32 virtual_address, uint32 size) in
//		"memory_manager.c", then switch back to the user mode here
//	the allocateMem function is empty, make sure to implement it.

//==================================================================================//
//============================ REQUIRED FUNCTIONS ==================================//
//==================================================================================//

uint32 ptr= USER_HEAP_START;
//this iterator to point the first empty position to arr of programmes
int count = 0;

struct PAGEE{
 bool flag;
};
//create two arrays of struct
//array for processes are current in heap
//struct store start of program in heap and the number of pages that has
struct process{
 int number_of_page;
 struct PAGEE pages;
 uint32 VA_START;
};
//heap size = max number of pages in heap
int heapsize=(USER_HEAP_MAX-USER_HEAP_START)/PAGE_SIZE;
//create array to store status programmes in heap
int arr[4*1024];
int flag=0;
struct process p[4*1024];

//array for spaces(holes) are current in heap
//struct store start of fragment in heap and the number of pages that has
struct fragmentation{
 int number_of_page;
 struct PAGEE pages;
 uint32 VA_START;

}frag[4*1024];
//this iterator to point the first empty position to arr of spaces (fragmentation)
int countFrag=0;


void* malloc(uint32 size)
{

//	round up size because page mini 4kB(Page size)
 size = ROUNDUP(size,PAGE_SIZE);
// RES =  how many number of pages size has
 uint32 RES =size/PAGE_SIZE;

 uint32 y =ptr;

// check if no space in heap for this program and this 1st program add
 if(flag==0 && size > USER_HEAP_MAX-ptr)
 {

  return NULL;

 }

// check if 1st element added to heap make normal push
 if ( flag ==0 ){
//	 add program in physical memory
	  sys_allocateMem(ptr,size);

//	  increament ptr to point the 1st fragment
	  ptr += size;
//	  add program in array of programmes
	  p[count].VA_START = y;
	  p[count].number_of_page  = RES;
	  arr[count]=1;
	  count++;

//	  confirm that one program added to heap
	  if(count == 1){
//		  add fragment to array of fragment
		  frag[countFrag].VA_START = (uint32)ptr;
		  frag[countFrag].number_of_page = heapsize - RES;
		  countFrag++;
	  }

//	  display for checking that program and fragment added success
	  for(int p=0; p<countFrag; p++){
	   cprintf("VA= %x\n", frag[p].VA_START);
	   cprintf("NPages= %d\n", frag[p].number_of_page);
	  }

	  for(int pr=0; pr<count; pr++){
	   cprintf("VAprog= %x\n", p[pr].VA_START);
	   cprintf("NPprog= %d\n", p[pr].number_of_page);
	  }
//	  change flage after 1st program added to heap
	  flag=1;
//	return address for program starting in heap
	  return (void*)y;
 }
// heap has elements and fragments (holes) so i do best fit method
 else
	 {
//	 get best hole in heap
	  int mini = 1000000, z = -1;
	  for(int i =0;i<countFrag;i++)
	  {
		   if(frag[i].VA_START != (uint32)0)
		   {
			if(frag[i].number_of_page >= RES && frag[i].number_of_page < mini){
//	 current number of pages can store the program and position of it in array of holes
			 mini  = frag[i].number_of_page;
			 z = i;
			}
		   }

	  }
//	check if there is no hole valid to store prog in it
	  if(z == -1){
	   return (void*)NULL;
	  }
	  else{

	  //	  add program in array of programmes
	   p[count].VA_START = frag[z].VA_START;
	   p[count].number_of_page  = RES;
	   arr[count]=1;

	   //	 add program in physical memory
	   sys_allocateMem(p[count].VA_START,RES*PAGE_SIZE);

	   count++;

//	   update hole after add prog in it
//	   if num of pages of hole was = num of pages for prog
	   if(frag[z].number_of_page == p[count-1].number_of_page){
		frag[z].number_of_page = 0;
		frag[z].VA_START = (uint32)0;

	   }
//	   if num of pages of hole was > num of pages for prog
	   else{
		frag[z].number_of_page -= RES;
		frag[z].VA_START += (RES * PAGE_SIZE);

	   }

//	return address for program starting in heap
	   return (void*)p[count-1].VA_START;
  }



 }


 return NULL;
}

// free():
// This function frees the allocation of the given virtual_address
// To do this, we need to switch to the kernel, free the pages AND "EMPTY" PAGE TABLES
// from page file and main memory then switch back to the user again.
//
// We can use sys_freeMem(uint32 virtual_address, uint32 size); which
//  switches to the kernel mode, calls freeMem(struct Env* e, uint32 virtual_address, uint32 size) in
//  "memory_manager.c", then switch back to the user mode here
// the freeMem function is empty, make sure to implement it.

void free(void* virtual_address)
{

// round down add to point in start of program
 virtual_address = ROUNDDOWN(virtual_address,PAGE_SIZE);
// check if program i want to remove it the previos place for it in heap empty or not
 bool f = 0;
// iterate on heap programes
 for (int i =0 ; i<count ;i++)
  {
//	 if program added
   if(arr[i]==1)
   {
//	   if this is the target
    if((uint32)virtual_address == (p[i].VA_START))
    {
//    	iterate of fragments to check if found space before this program in heap
     for(int k=0; k<countFrag ; k++){
		  if(frag[k].VA_START != 0 && frag[k].VA_START + (frag[k].number_of_page * PAGE_SIZE) == (uint32)virtual_address){
	//    	  make concatenate with the previous space
		   frag[k].number_of_page += p[i].number_of_page;
		   f=1;
		   break;
		  }
     }
//     if the previos of this prog is not empty in heap
     if(!f){
//    	 create a new hole in array of frag
		  frag[countFrag].VA_START = (uint32)virtual_address;
		  frag[countFrag].number_of_page = p[i].number_of_page;
		  countFrag++;
     }
//		free program from physical heap
     sys_freeMem((uint32)virtual_address, p[i].number_of_page * PAGE_SIZE);
//     free prog from array of programs
     p[i].VA_START = 0;
     p[i].number_of_page = 0;
    }

   }

  }

}

//==================================================================================//
//================================ OTHER FUNCTIONS =================================//
//==================================================================================//

void* smalloc(char *sharedVarName, uint32 size, uint8 isWritable)
{
	panic("this function is not required...!!");
	return 0;
}

void* sget(int32 ownerEnvID, char *sharedVarName)
{
	panic("this function is not required...!!");
	return 0;
}

void sfree(void* virtual_address)
{
	panic("this function is not required...!!");
}

void *realloc(void *virtual_address, uint32 new_size)
{
	panic("this function is not required...!!");
	return 0;
}

void expand(uint32 newSize)
{
	panic("this function is not required...!!");
}
void shrink(uint32 newSize)
{
	panic("this function is not required...!!");
}

void freeHeap(void* virtual_address)
{
	panic("this function is not required...!!");
}
