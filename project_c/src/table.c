#include "../includes/table.h"

struct table{
    char header[100];
    GPtrArray* lines;

    int intdata;          //dados adicionais
    char chardata;
    char* strdata;
    float floatdata;
};

TABLE initTABLE(){
    TABLE new = malloc(sizeof *new);
    new->lines = g_ptr_array_new_with_free_func(g_free);
    return new;
}

void destroyTABLE(TABLE t){
    if(!t)
        return;
    g_ptr_array_free(t->lines, TRUE);
    free(t);
}

char* get_header(TABLE t){
    return strdup(t->header);
}

GPtrArray* get_lines(TABLE t){
    return t->lines;
}

guint get_size(TABLE t){
    return t->lines->len;
}

void loadHeader(TABLE t, int query){

    switch(query){

        case 2:
            strcpy(t->header, QUERY2_4_5);
            break;

        case 3:
            strcpy(t->header, QUERY3);
            break;

        case 4:
            strcpy(t->header, QUERY2_4_5);
            break;

        case 5:
            strcpy(t->header, QUERY2_4_5);
            break;

        case 6:
            strcpy(t->header, QUERY6_8);
            break;
        
        case 7:
            strcpy(t->header, QUERY7);
            break;

        case 8:
            strcpy(t->header, QUERY6_8);
            break;

        case 9:
            strcpy(t->header, QUERY9);
            break;
        
        default:
            break;
    }
}

void addLineTABLE(TABLE t, char* s){
    g_ptr_array_add(t->lines, s);
}

static gint cmp (gconstpointer a, gconstpointer b){
    const char* arga = *((char **) a);
    const char* argb = *((char **) b);

    return strcmp(arga, argb);
}

void sortTABLE(TABLE t){
    g_ptr_array_sort(t->lines, (GCompareFunc) cmp);
}

int get_intdata(TABLE t){
    return t->intdata;
}

void set_intdata(TABLE t, int data){
    t->intdata = data;
}

char get_chardata(TABLE t){
    return t->chardata;
}

void set_chardata(TABLE t, char data){
    t->chardata = data;
}

float get_floatdata(TABLE t){
    return t->floatdata;
}

void set_floatdata(TABLE t, float f){
    t->floatdata = f;
}

char* get_strdata(TABLE t){
    return t->strdata;
}

void set_strdata(TABLE t, char* data){
    t->strdata = data;
}