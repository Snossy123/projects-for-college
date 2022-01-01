#ifndef FOS_KERN_MONITOR_H
#define FOS_KERN_MONITOR_H
#ifndef FOS_KERNEL
# error "This is a FOS kernel header; user programs should not #include it"
#endif

#include <inc/types.h>

// Function to activate the kernel command prompt
void run_command_prompt();
int execute_command(char *command_string);

// Declaration of functions that implement command prompt commands.
int command_help(int , char **);
int command_kernel_info(int , char **);
int command_ver(int number_of_arguments, char **arguments);
int command_add(int number_of_arguments, char **arguments);

/*ASSIGNMENT2*/
char* CreateString(char** arguments);
int GetStringSize(char** arguments);
int FindSubstring(char** arguments);
void SwapStrings(char** arguments);

int command_cns(int , char **);
int command_gss(int , char **);
int command_fss(int , char **);
int command_swap(int , char **);

/*ASSIGNMENT2 - BONUS*/
void Concatenate(char** arguments);
int command_cat(int , char **);


#endif	// !FOS_KERN_MONITOR_H
