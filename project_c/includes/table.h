#ifndef TABLE_H
#define TABLE_H

#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct table *TABLE;

#define QUERY2_4_5 "|      business_id       |              name              |"
#define QUERY3 "|              name              |      city       | state | stars | number_of_reviews |"
#define QUERY6_8 "|      business_id       |              name              | stars |"
#define QUERY7 "|        user_id         |"
#define QUERY9 "|       review_id        |                text                 |"

TABLE initTABLE();
void destroyTABLE(TABLE);
char* get_header(TABLE);
GPtrArray* get_lines(TABLE);

guint get_size(TABLE);

void loadHeader(TABLE, int);
void addLineTABLE(TABLE, char*);
void sortTABLE(TABLE);

int get_intdata(TABLE);
void set_intdata(TABLE, int);

char get_chardata(TABLE);
void set_chardata(TABLE, char);

float get_floatdata(TABLE);
void set_floatdata(TABLE, float);

char* get_strdata(TABLE);
void set_strdata(TABLE, char*);

#endif 
