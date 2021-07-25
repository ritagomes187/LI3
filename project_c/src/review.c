#include "../includes/review.h"

struct review{
    char* review_id;
    char* user_id;
    char* business_id;
    float stars;
    int useful;
    int funny;
    int cool;
    char* date;
    char* text;
};

REVIEW initREV(){
    REVIEW rev = calloc(1, sizeof *rev);
    return rev;
}

char* get_reviewID(REVIEW r){
    return strdup(r->review_id);
}

char* get_userID_rev(REVIEW r){
    return strdup(r->user_id);
}

char* get_busID_rev(REVIEW r){
    return strdup(r->business_id);
}

float get_stars(REVIEW r){
    return r->stars;
}

int get_useful(REVIEW r){
    return r->useful;
}

int get_funny(REVIEW r){
    return r->funny;
}

int get_cool(REVIEW r){
    return r->cool;
}

char* get_date(REVIEW r){
    return strdup(r->date);
}

char* get_text(REVIEW r){
    return strdup(r->text);
}

void set_reviewID(REVIEW r, const char* id){
    free(r->review_id);
    r->review_id = strdup(id);
}

void set_userID_rev(REVIEW r, const char* id){
    free(r->user_id);
    r->user_id = strdup(id);
}

void set_busID_rev(REVIEW r, const char* id){
    free(r->business_id);
    r->business_id = strdup(id);
}

void set_stars(REVIEW r, const float f){
    r->stars = f;
}

void set_useful(REVIEW r, const int i){
    r->useful = i;
}

void set_funny(REVIEW r, const int i){
    r->funny = i;
}

void set_cool(REVIEW r, const int i){
    r->cool = i;
}

void set_date(REVIEW r, const char* s){
    free(r->date);
    r->date = strdup(s);
}

void set_text(REVIEW r, const char* s){
    free(r->text);
    r->text = strdup(s);
}

void destroyREV(REVIEW r){
    if(!r)
        return;
    free(r->review_id);
    free(r->user_id);
    free(r->business_id);
    free(r->date);
    free(r->text);
    free(r);
}

char* get_reviewID_and_text(const REVIEW r, const char* ptr){
    char info[256];
    snprintf(info, 256, "| %s | [...]%.25s[...] |", 
                         r->review_id, ptr);
    return strdup(info);
}