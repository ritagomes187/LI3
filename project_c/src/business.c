#include "../includes/business.h"

struct business{
    char* business_id;
    char* name;
    char* city;
    char* state;
    char* categories;
};

BUSINESS initBUS(){
    BUSINESS b = calloc(1, sizeof *b);
    return b;
}

char* get_busID(BUSINESS b){
    return strdup(b->business_id);
}

char* get_name_bus(BUSINESS b){
    return strdup(b->name);
}

char* get_city(BUSINESS b){
    return strdup(b->city);
}

char* get_state(BUSINESS b){
    return strdup(b->state);
}

char* get_categories(BUSINESS b){
    return strdup(b->categories);
}

void set_busID(BUSINESS b, const char* s){
    free(b->business_id);
    b->business_id = strdup(s);
}

void set_name_bus(BUSINESS b, const char* s){
    free(b->name);
    b->name = strdup(s);
}

void set_city(BUSINESS b, const char* s){
    free(b->city);
    b->city = strdup(s);
}

void set_state(BUSINESS b, const char* s){
    free(b->state);
    b->state = strdup(s);
}

void set_categories(BUSINESS b, const char* s){
    free(b->categories);
    b->categories = strdup(s);
}

void destroyBUS(BUSINESS b){
    if(!b)
        return;
    free(b->business_id);
    free(b->name);
    free(b->city);
    free(b->state);
    free(b->categories);
    free(b);
}

char* get_busID_and_name(const BUSINESS b){
    char info[256];
    snprintf(info, 256, "| %s | %-30.30s |", 
                         b->business_id, b->name
            );
    return strdup(info);
}

char* get_bus_info(const BUSINESS b, const float stars, const int num_of_rev){
    char info[512];
    snprintf(info, 512, "| %-30.30s | %-15.15s | %-5.5s | %.1f   | %0*d              |", 
                         b->name,   b->city,   b->state, stars, 4, num_of_rev
            );
    return strdup(info);
}

char* get_bus_info_param(const char* id, const char* nome, const float stars){
    char info[256];
    snprintf(info, 256, "| %s | %-30.30s | %.1f   |", 
                         id,  nome,      stars
            );
    return strdup(info);
}

char* format_city(const char* city){
    char info[256];
    snprintf(info, 256, "| \t%-56.56s |", city);
    return strdup(info);
}