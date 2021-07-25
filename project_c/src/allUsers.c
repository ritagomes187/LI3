#include "../includes/allUsers.h"


/* Este modulo serve para armazenar todos os users numa hashtable */

struct allusers{
    GHashTable* all;
};

ALLUSR initALLUSR(){
    ALLUSR new = malloc(sizeof *new);
    new->all = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, (GDestroyNotify) destroyUSER);
    return new;
}

void insertUSER(ALLUSR us, USER u){
    g_hash_table_insert(us->all, get_userID(u), u);
}

void destroyALLUSR(ALLUSR us){
    if(!us)
        return;
    g_hash_table_destroy(us->all);
    free(us);
}

GHashTable* getALLUSR(ALLUSR us){
    return us->all;
}

int userFile(const char *PATH, ALLUSR us){
    char buffer[BUFFER_SIZE];

    FILE* f = fopen(PATH,"r");

    if(!f)
        return -1;

    fgets(buffer, BUFFER_SIZE, f); //ler a primeira linha
    
    while(fgets(buffer, BUFFER_SIZE, f)){
        USER new = initUSER();
        set_userID(new, strtok(buffer, ";"));
        set_name(new, strtok(NULL, ";"));        //só na primeira invocação do strtok 
        set_friends(new, strtok(NULL, "\0\n\r")); //se faz referencia ao apontadador
        insertUSER(us, new);                     //chamadas subsquentes devem receber 'NULL'
    }
    fclose(f);
    return 0;
}