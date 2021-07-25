#include "../includes/user.h"

struct user{
    char* user_id;
    char* name;
    char* friends;
};

USER initUSER(){
    USER u = calloc(1, sizeof *u);
    return u;
}

char* get_userID(USER u){
    return strdup(u->user_id);
}

char* get_name(USER u){
    return strdup(u->name);
}

char* get_friends(USER u){
    return strdup(u->friends);
}

void set_userID(USER u, const char* s){
    free(u->user_id);
    u->user_id = strdup(s);
}

void set_name(USER u, const char* s){
    free(u->name);
    u->name = strdup(s);
}

void set_friends(USER u, const char* s){
    free(u->friends);
    u->friends = strdup(s);
}

/**Libertar a memória da struct*/
void destroyUSER(USER u){
    if(!u)
        return;
    free(u->user_id);
    free(u->name);
    free(u->friends);
    free(u);
}

char* formatUSER(const char *str){
    char id[64];
    snprintf(id, 64, "| %s |", str);
    return strdup(id);
}