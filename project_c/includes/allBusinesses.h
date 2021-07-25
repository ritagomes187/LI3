#ifndef ALLBUSINESS_H
#define ALLBUSINESS_H

#include <glib.h>

#include "business.h"
#include "view.h"

#define BUFFER_SIZE 1000000

typedef struct allbusinesses *ALLBUS;

ALLBUS initALLBUS();
void insertBUS(ALLBUS, BUSINESS);
void destroyALLBUS(ALLBUS);
GHashTable* getALLBUS(ALLBUS);

char* get_lixo(ALLBUS);
void set_lixo(ALLBUS, char*);

int busFile(const char *, ALLBUS);

#endif