#ifndef ALLREVIEWS_H
#define ALLREVIEWS_H

#include <glib.h>

#include "review.h"
#include "view.h"

#define BUFFER_SIZE 1000000

typedef struct allreviews *ALLREV;

ALLREV initALLREV();
void insertREV(ALLREV, REVIEW);
void destroyALLREV(ALLREV);
GHashTable* getALLREV(ALLREV);

int revFile(const char *, ALLREV);

#endif