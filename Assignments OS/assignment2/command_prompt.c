/*	Simple command-line kernel prompt useful for
	controlling the kernel and exploring the system interactively.


KEY WORDS
==========
CONSTANTS:	WHITESPACE, NUM_OF_COMMANDS
VARIABLES:	Command, commands, name, description, function_to_execute, number_of_arguments, arguments, command_string, command_line, command_found
FUNCTIONS:	readline, cprintf, execute_command, run_command_prompt, command_kernel_info, command_help, strcmp, strsplit, start_of_kernel, start_of_uninitialized_data_section, end_of_kernel_code_section, end_of_kernel
=====================================================================================================================================================================================================
 */

#include <inc/stdio.h>
#include <inc/string.h>
#include <inc/memlayout.h>
#include <inc/assert.h>
#include <inc/x86.h>


#include <kern/console.h>
#include <kern/command_prompt.h>
#include <kern/memory_manager.h>
#include <kern/trap.h>
#include <kern/kdebug.h>
#include <kern/user_environment.h>
#include <kern/tests.h>


//TODO:LAB3.Hands-on: declare start address variable of "My int array"

//=============================================================

//Structure for each command
struct Command
{
	char *name;
	char *description;
	// return -1 to force command prompt to exit
	int (*function_to_execute)(int number_of_arguments, char** arguments);
};

//Functions Declaration
int command_writemem(int number_of_arguments, char **arguments);
int command_readmem(int number_of_arguments, char **arguments);
int command_meminfo(int , char **);

//Lab2.Hands.On
//=============
//TODO: LAB2 Hands-on: declare the command function here


//Lab4.Hands.On
//=============
int command_show_mapping(int number_of_arguments, char **arguments);
int command_set_permission(int number_of_arguments, char **arguments);
int command_share_range(int number_of_arguments, char **arguments);

//Lab5.Examples
//=============
int command_nr(int number_of_arguments, char **arguments);
int command_ap(int , char **);
int command_fp(int , char **);

//Lab5.Hands-on
//=============
int command_asp(int, char **);
int command_cfp(int, char **);

//Lab6.Examples
//=============
int command_run(int , char **);
int command_kill(int , char **);
int command_ft(int , char **);


//Array of commands. (initialized)
struct Command commands[] =
{
		{ "help", "Display this list of commands", command_help },	//don't need arguments
		{ "kernel_info", "Display information about the kernel", command_kernel_info },	//don't need arguments
		{ "wum", "writes one byte to specific location" ,command_writemem},	//need arguments
		{ "rum", "reads one byte from specific location" ,command_readmem},	//need arguments
		{ "ver", "Print the FOS version" ,command_ver},//don't need arguments
		{ "add", "Add two integers" ,command_add},//need arguments

		//Assignment2 commands
		//====================
		{ "cns", "Create named string with the given value", command_cns},
		{ "gss", "Get the size of a previously created string", command_gss},
		{ "fss", "Find a substring in a previously created string", command_fss},
		{ "cat", "Concatenate a source string into the destination string", command_cat},

		//Assignment2.BONUS command
		//=========================
		{ "swap", "Exchange the value of 2 previously created strings", command_swap},

		//TODO: LAB2 Hands-on: add the commands here


		//LAB4: Hands-on
		{ "sm", "Lab4.HandsOn: display the mapping info for the given virtual address", command_show_mapping},
		{ "sp", "Lab4.HandsOn: set the desired permission to a given virtual address page", command_set_permission},
		{ "sr", "Lab4.HandsOn: shares the physical frames of the first virtual range with the 2nd virtual range", command_share_range},

		//LAB5: Examples
		{ "nr", "Lab5.Example: show the number of references of the physical frame" ,command_nr},
		{ "ap", "Lab5.Example: allocate one page [if not exists] in the user space at the given virtual address", command_ap},
		{ "fp", "Lab5.Example: free one page in the user space at the given virtual address", command_fp},

		//LAB5: Hands-on
		{ "asp", "Lab5.HandsOn: allocate 2 shared pages with the given virtual addresses" ,command_asp},
		{ "cfp", "Lab5.HandsOn: count the number of free pages in the given range", command_cfp},

		//LAB6: Examples
		{ "ft", "Lab6.Example: Free table", command_ft},
		{ "run", "Lab6.Example: Load and Run User Program", command_run},
		{ "kill", "Lab6.Example: Kill User Program", command_kill},

};

//Number of commands = size of the array / size of command structure
#define NUM_OF_COMMANDS (sizeof(commands)/sizeof(commands[0]))

int firstTime = 1;

//invoke the command prompt
void run_command_prompt()
{
	//CAUTION: DON'T CHANGE OR COMMENT THESE LINE======
	if (firstTime)
	{
		firstTime = 0;
		TestAssignment2();
	}
	else
	{
		cprintf("Test failed.\n");
	}
	//================================================

	char command_line[1024];

	while (1==1)
	{
		//get command line
		readline("FOS> ", command_line);

		//parse and execute the command
		if (command_line != NULL)
			if (execute_command(command_line) < 0)
				break;
	}
}

/***** Kernel command prompt command interpreter *****/

//define the white-space symbols
#define WHITESPACE "\t\r\n "

//Function to parse any command and execute it
//(simply by calling its corresponding function)
int execute_command(char *command_string)
{
	// Split the command string into whitespace-separated arguments
	int number_of_arguments;
	//allocate array of char * of size MAX_ARGUMENTS = 16 found in string.h
	char *arguments[MAX_ARGUMENTS];


	strsplit(command_string, WHITESPACE, arguments, &number_of_arguments) ;
	if (number_of_arguments == 0)
		return 0;

	// Lookup in the commands array and execute the command
	int command_found = 0;
	int i ;
	for (i = 0; i < NUM_OF_COMMANDS; i++)
	{
		if (strcmp(arguments[0], commands[i].name) == 0)
		{
			command_found = 1;
			break;
		}
	}

	if(command_found)
	{
		int return_value;
		return_value = commands[i].function_to_execute(number_of_arguments, arguments);
		return return_value;
	}
	else
	{
		//if not found, then it's unknown command
		cprintf("Unknown command '%s'\n", arguments[0]);
		return 0;
	}
}

/***** Implementations of basic kernel command prompt commands *****/
/***************************************/
/*DON'T change the following functions*/
/***************************************/
//print name and description of each command
int command_help(int number_of_arguments, char **arguments)
{
	int i;
	for (i = 0; i < NUM_OF_COMMANDS; i++)
		cprintf("%s - %s\n", commands[i].name, commands[i].description);

	cprintf("-------------------\n");

	return 0;
}

/*DON'T change this function*/
//print information about kernel addresses and kernel size
int command_kernel_info(int number_of_arguments, char **arguments )
{
	extern char start_of_kernel[], end_of_kernel_code_section[], start_of_uninitialized_data_section[], end_of_kernel[];

	cprintf("Special kernel symbols:\n");
	cprintf("  Start Address of the kernel 			%08x (virt)  %08x (phys)\n", start_of_kernel, start_of_kernel - KERNEL_BASE);
	cprintf("  End address of kernel code  			%08x (virt)  %08x (phys)\n", end_of_kernel_code_section, end_of_kernel_code_section - KERNEL_BASE);
	cprintf("  Start addr. of uninitialized data section 	%08x (virt)  %08x (phys)\n", start_of_uninitialized_data_section, start_of_uninitialized_data_section - KERNEL_BASE);
	cprintf("  End address of the kernel   			%08x (virt)  %08x (phys)\n", end_of_kernel, end_of_kernel - KERNEL_BASE);
	cprintf("Kernel executable memory footprint: %d KB\n",
			(end_of_kernel-start_of_kernel+1023)/1024);
	return 0;
}


/*DON'T change this function*/
int command_readmem(int number_of_arguments, char **arguments)
{
	unsigned int address = strtol(arguments[1], NULL, 16);
	unsigned char *ptr = (unsigned char *)(address ) ;

	cprintf("value at address %x = %c\n", ptr, *ptr);

	return 0;
}

/*DON'T change this function*/
int command_writemem(int number_of_arguments, char **arguments)
{
	unsigned int address = strtol(arguments[1], NULL, 16);
	unsigned char *ptr = (unsigned char *)(address) ;

	*ptr = arguments[2][0];

	return 0;
}

/*DON'T change this function*/
int command_meminfo(int number_of_arguments, char **arguments)
{
	cprintf("Free frames = %d\n", calculate_free_frames());
	return 0;
}

//===========================================================================
//Lab1 Examples
//=============
/*DON'T change this function*/
int command_ver(int number_of_arguments, char **arguments)
{
	cprintf("FOS version 0.1\n") ;
	return 0;
}

/*DON'T change this function*/
int command_add(int number_of_arguments, char **arguments)
{
	int n1 = strtol(arguments[1], NULL, 10);
	int n2 = strtol(arguments[2], NULL, 10);

	int res = n1 + n2 ;
	cprintf("res=%d\n", res);

	return 0;
}

//===========================================================================
//Lab2.Hands.On
//=============
//TODO: LAB2 Hands-on: write the command function here


//===========================================================================
//Lab4.Hands.On
//=============
int command_show_mapping(int number_of_arguments, char **arguments)
{
	//TODO: LAB4 Hands-on: fill this function. corresponding command name is "sm"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0 ;
}

int command_set_permission(int number_of_arguments, char **arguments)
{
	//TODO: LAB4 Hands-on: fill this function. corresponding command name is "sp"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0 ;
}

int command_share_range(int number_of_arguments, char **arguments)
{
	//TODO: LAB4 Hands-on: fill this function. corresponding command name is "sr"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0;
}

//===========================================================================
//Lab5.Examples
//==============
//[1] Number of references on the given physical address
int command_nr(int number_of_arguments, char **arguments)
{
	//TODO: LAB5 Example: fill this function. corresponding command name is "nr"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0;
}

//[2] Allocate Page: If the given user virtual address is mapped, do nothing. Else, allocate a single frame and map it to a given virtual address in the user space
int command_ap(int number_of_arguments, char **arguments)
{
	//TODO: LAB5 Example: fill this function. corresponding command name is "ap"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0 ;
}

//[3] Free Page: Un-map a single page at the given virtual address in the user space
int command_fp(int number_of_arguments, char **arguments)
{
	//TODO: LAB5 Example: fill this function. corresponding command name is "fp"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0;
}

//===========================================================================
//Lab5.Hands-on
//==============
//[1] Allocate Shared Pages
int command_asp(int number_of_arguments, char **arguments)
{
	//TODO: LAB5 Hands-on: fill this function. corresponding command name is "asp"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0;
}


//[2] Count Free Pages in Range
int command_cfp(int number_of_arguments, char **arguments)
{
	//TODO: LAB5 Hands-on: fill this function. corresponding command name is "cfp"
	//Comment the following line
	panic("Function is not implemented yet!");

	return 0;
}

//===========================================================================
//Lab6.Examples
//=============
/*DON'T change this function*/
int command_run(int number_of_arguments, char **arguments)
{
	//[1] Create and initialize a new environment for the program to be run
	struct UserProgramInfo* ptr_program_info = env_create(arguments[1]);
	if(ptr_program_info == 0) return 0;

	//[2] Run the created environment using "env_run" function
	env_run(ptr_program_info->environment);
	return 0;
}

/*DON'T change this function*/
int command_kill(int number_of_arguments, char **arguments)
{
	//[1] Get the user program info of the program (by searching in the "userPrograms" array
	struct UserProgramInfo* ptr_program_info = get_user_program_info(arguments[1]) ;
	if(ptr_program_info == 0) return 0;

	//[2] Kill its environment using "env_free" function
	env_free(ptr_program_info->environment);
	ptr_program_info->environment = NULL;
	return 0;
}

int command_ft(int number_of_arguments, char **arguments)
{
	//TODO: LAB6 Example: fill this function. corresponding command name is "ft"
	//Comment the following line

	return 0;
}
/****************************************************************/
//========================================================
/*ASSIGNMENT-2 [MAIN QUESTIONS] */
//========================================================
//Q1:Create Named String
//=========================
/*DON'T change this function*/
int command_cns(int number_of_arguments, char **arguments )
{
	//DON'T WRITE YOUR LOGIC HERE, WRITE INSIDE THE CreateString() FUNCTION
	CreateString(arguments);
	return 0;
}
/*---------------------------------------------------------*/

/*FILL this function
 * arguments[1]: string name
 * arguments[2]: string value
 * Return:
 * 		start address of the FIRST ELEMENT in the created string.
 * 		Or NULL if the string already exists.
 *
 * Example:
 * 	FOS> cns	x hello
 *
 * Create string named "x", with value "hello"

 * It should return the start address of the FIRST ELEMENT in the created array
 */



 struct cs{
	char name[1024];
	char value[1024];
	char* Address;
}strs[30];

char *mainAdd=(char *)0xF1000000;
int iterator=0;
char* CreateString(char** arguments)
{
	//for localy use in function
	char *ptr = (char*)mainAdd;
	char *FIRST_PTR=(char *)mainAdd;


	for(int i=0;i<30;i++)
		if(strcmp(strs[i].name,arguments[1])==0)
			 return NULL;
	// assign name of var
	strcpy(strs[iterator].name,arguments[1]);
	// assign value to var
	strcpy(strs[iterator].value,arguments[2]);
	// address point in first position of value in memory
	strs[iterator].Address=ptr;

	// store value in memory
	for(int b=0; b<strlen(arguments[2]) ; b++){
			*ptr = arguments[2][b];
			ptr++;
		}
		iterator++;
		*ptr='\0';
		ptr++;
		mainAdd = ptr;

return FIRST_PTR;

}

////========================================================

//Q2:Get a String Size
//==========================================
/*DON'T change this function*/
int command_gss(int number_of_arguments, char **arguments )
{
	//DON'T WRITE YOUR LOGIC HERE, WRITE INSIDE THE GetStringSize() FUNCTION
	GetStringSize(arguments) ;
	return 0;
}
/*---------------------------------------------------------*/

/*FILL this function
 * arguments[1]: string name
 * Return:
 * 		the size of the string if it exists. Or -1 if it doesn’t exist.
 */
int GetStringSize(char** arguments)
{
	int o =0;
	while(o<30){
		int IsEqual = strcmp(arguments[1], strs[o].name);
		if(IsEqual==0){
			//return length of value in memory
			cprintf("%d \n",strlen(strs[o].value));
			return strlen(strs[o].value);
		}
		o++;
	}

	return -1;
}
//========================================================

//Q3:Find a Substring
//============================
/*DON'T change this function*/
int command_fss(int number_of_arguments, char **arguments )
{
	//DON'T WRITE YOUR LOGIC HERE, WRITE INSIDE THE FindSubstring() FUNCTION
	int itemLoc = FindSubstring(arguments) ;
	if (itemLoc != -1)
	{
		cprintf("Substring %s is found @ %d\n", arguments[2] ,itemLoc) ;
	}
	else
	{
		cprintf("Substring not found\n");
	}
	return 0;
}
/*---------------------------------------------------------*/

/*FILL this function
 * arguments[1]: string name
 * arguments[2]: substring to find
 * Return:
 * 		If substring is found: return the zero-based index of its first occurrence
 *    	Otherwise: return -1

 */
int FindSubstring(char** arguments)
{
	int q =0;
	while( q<30){
		int IsEqual = strcmp(arguments[1], strs[q].name);
		  if(IsEqual==0){
			 bool flag = 0;
			 int id = -1,total = 0, begin = 0, s=0;
			 while(s<strlen(arguments[2])){
				 int b=begin;
				 //looping in original string(value)
				 while(b<strlen(strs[q].value)){
					 if(arguments[2][s]!= strs[q].value[b]){
						 if(total > 0){
							 //if sequence is broken set total to 0
							 total = 0;
							 begin = b;
							 s = -1;
							 break;
						 }
					 }
					 else{
						 total++;
						 begin = b+1;
						 if(total == strlen(arguments[2])){
							 id = b-strlen(arguments[2])+1;
							 flag = 1;
							 break;
						 } break;

					 }
					 if(total == strlen(arguments[2])){
						 id = b-strlen(arguments[2])+1;
						 flag = 1;
						 break;
					 }
					  b++;
				 }
				 s++;
			 }
			if(flag == 1) return id;

			else return -1;
		}
		 q++;
		}
	return -1 ;
}
//========================================================

//Q4: Swap Two Strings
//===========================
/*DON'T change this function*/
int command_swap(int number_of_arguments, char **arguments )
{
	//DON'T WRITE YOUR LOGIC HERE, WRITE INSIDE THE SwapStrings() FUNCTION
	SwapStrings(arguments);
	return 0;
}

/*---------------------------------------------------------*/
/*FILL this function
 * arguments[1]: name of the first string
 * arguments[2]: name of the second string to swap
 */
void SwapStrings(char** arguments)
{

	int c1 = -1;
	int c2 = -1;
	for(int i =0; i<30; i++){
		int IsEqual1=strcmp(arguments[1], strs[i].name);
		int IsEqual2=strcmp(arguments[2], strs[i].name);
		if(IsEqual1==0){
			c1 = i;
		}
		if(IsEqual2==0){
			c2 = i;
		}
	}

	if(c1 == -1 || c2 == -1){

		return;
	}

	if(strlen(strs[c1].value) != strlen(strs[c2].value)){
	cprintf("NOT EQUAL SIZE!");
	return;
	}

	char* Addr1 = (char*)strs[c1].Address;
	char* Addr2 = (char*)strs[c2].Address;

	char *ptr_1 = (char*)Addr1;
	char *ptr_2 = (char*)Addr2;

	for(int x=0; x<strlen(strs[c1].value); x++){
		*ptr_2 = strs[c1].value[x];
		*ptr_1 = strs[c2].value[x];
		ptr_2++;
		ptr_1++;

	}


}

//========================================================
/*ASSIGNMENT-2 [BONUS QUESTION] */
//========================================================
//BONUS: Concatenate Strings
//=======================
/*DON'T change this function*/
int command_cat(int number_of_arguments, char **arguments )
{
	//DON'T WRITE YOUR LOGIC HERE, WRITE INSIDE THE Concatenate() FUNCTION
	Concatenate(arguments);
	return 0;
}
/*---------------------------------------------------------*/
/*FILL this function
 * arguments[1]: string1 name
 * arguments[2]: string2 name
 */
void Concatenate(char** arguments)
{
	//TODO: Assignment2.BONUS
	//put your logic here
	//...
}
