#ifndef BUSINESS_H
#define BUSINESS_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct business *BUSINESS;

BUSINESS initBUS();

char* get_busID(BUSINESS);
char* get_name_bus(BUSINESS);
char* get_city(BUSINESS);
char* get_state(BUSINESS);
char* get_categories(BUSINESS);

void set_busID(BUSINESS, const char*);
void set_name_bus(BUSINESS, const char*);
void set_city(BUSINESS, const char*);
void set_state(BUSINESS, const char*);
void set_categories(BUSINESS, const char*);

void destroyBUS(BUSINESS);

char* get_busID_and_name(const BUSINESS);
char* get_bus_info(const BUSINESS, const float, const int);
char* get_bus_info_param(const char*, const char*, const float);
char* format_city(const char*);

#endif