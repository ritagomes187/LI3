#include "../includes/allReviews.h"

/* Este modulo serve para armazenar todos as reviews numa hashtable */

struct allreviews{
    GHashTable* all;
};

ALLREV initALLREV(){
    ALLREV new = malloc(sizeof *new);
    new->all = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, (GDestroyNotify) destroyREV);
    return new;
}

void insertREV(ALLREV rs, REVIEW r){
    g_hash_table_insert(rs->all, get_reviewID(r), r);
}

void destroyALLREV(ALLREV rs){
    if(!rs)
        return;
    g_hash_table_destroy(rs->all);
    free(rs);
}

GHashTable* getALLREV(ALLREV rs){
    return rs->all;
}

int revFile(const char *PATH, ALLREV rs){
    char *buffer = malloc(sizeof *buffer * BUFFER_SIZE);
    char *begin = buffer;
  
    FILE* f = fopen(PATH,"r");

    if(!f)
        return -1;

    fgets(buffer, BUFFER_SIZE, f); //ler a primeira linha
    
    while(fgets(buffer, BUFFER_SIZE, f)){
        REVIEW new = initREV();
        set_reviewID(new, strtok(buffer, ";"));
        set_userID_rev(new, strtok(NULL, ";"));
        set_busID_rev(new, strtok(NULL, ";"));
        set_stars(new, atof(strtok(NULL, ";")));
        set_useful(new, atoi(strtok(NULL, ";")));
        set_funny(new, atoi(strtok(NULL, ";")));
        set_cool(new, atoi(strtok(NULL, ";")));
        set_date(new, strtok(NULL, ";"));
        set_text(new, strtok(NULL, "\0\n\r"));
        insertREV(rs, new);
        buffer = begin;
    }
    fclose(f);
    free(buffer);
    return 0;
}