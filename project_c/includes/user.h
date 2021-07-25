#ifndef USER_H
#define USER_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct user *USER;

/**Funções para lidar com a struct*/
USER initUSER();

/**Funções do tipo "get" para a struct.*/
char* get_userID(USER);
char* get_name(USER);
char* get_friends(USER);

/**Setters para a struct.*/
void set_userID(USER, const char*);
void set_name(USER, const char*);
void set_friends(USER, const char*); 

/**Liberta a memória da struct.*/
void destroyUSER(USER);
char* formatUSER(const char*);


#endif