#include "../includes/allBusinesses.h"


/* Este modulo serve para armazenar todos os businesses numa hashtable */


struct allbusinesses{
   GHashTable* all;
   char *lixo;
};

ALLBUS initALLBUS(){
    ALLBUS new = malloc(sizeof *new);
    new->all = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, (GDestroyNotify) destroyBUS);
    new->lixo = NULL;
    return new;
}

void insertBUS(ALLBUS bs, BUSINESS b){
    g_hash_table_insert(bs->all, get_busID(b), b);
}

void destroyALLBUS(ALLBUS bs){
    if(!bs)
        return;
    g_hash_table_destroy(bs->all);
    free(bs);
}

char* get_lixo(ALLBUS bs){
    return bs->lixo;
}

void set_lixo(ALLBUS bs, char* lixo){
    bs->lixo = lixo;
}

GHashTable* getALLBUS(ALLBUS bs){
    return bs->all;
}

int busFile(const char *PATH, ALLBUS bs){
    char buffer[BUFFER_SIZE];

    FILE* f = fopen(PATH,"r");

    if(!f)
        return -1;

    fgets(buffer, BUFFER_SIZE, f); //ler a primeira linha

    while (fgets(buffer, BUFFER_SIZE, f)){
        BUSINESS new = initBUS();
        set_busID(new, strtok(buffer, ";"));
        set_name_bus(new, strtok(NULL, ";"));
        set_city(new, strtok(NULL, ";"));
        set_state(new, strtok(NULL, ";"));
        set_categories(new, strtok(NULL, "\0\n\r"));
        insertBUS(bs, new);
    }
    fclose(f);
    return 0;
}