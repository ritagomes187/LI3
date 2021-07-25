#ifndef REVIEW_H
#define REVIEW_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct review *REVIEW;

REVIEW initREV();

char* get_reviewID(REVIEW);
char* get_userID_rev(REVIEW);
char* get_busID_rev(REVIEW);
float get_stars(REVIEW);
int get_useful(REVIEW);
int get_funny(REVIEW);
int get_cool(REVIEW);
char* get_date(REVIEW);
char* get_text(REVIEW);

void set_reviewID(REVIEW, const char*);
void set_userID_rev(REVIEW, const char*);
void set_busID_rev(REVIEW, const char*);
void set_stars(REVIEW, const float);
void set_useful(REVIEW, const int);
void set_funny(REVIEW, const int);
void set_cool(REVIEW, const int);
void set_date(REVIEW, const char*);
void set_text(REVIEW, const char*);

void destroyREV(REVIEW);

char* get_reviewID_and_text(const REVIEW, const char*);

#endif //REVIEW_H