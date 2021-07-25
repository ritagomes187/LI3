#ifndef ALLUSERS_H
#define ALLUSERS_H

#include <glib.h>

#include "user.h"
#include "view.h"

#define BUFFER_SIZE 1000000

typedef struct allusers *ALLUSR;

ALLUSR initALLUSR();
void insertUSER(ALLUSR, USER);
void destroyALLUSR(ALLUSR);
GHashTable* getALLUSR(ALLUSR);

int userFile(const char *, ALLUSR);

#endif
