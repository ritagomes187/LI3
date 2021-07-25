#ifndef VIEW_H
#define VIEW_H

#include <stdio.h>
#include <string.h>
#include <glib.h>
#include <ctype.h>

#include "table.h"

#define SEP2_4_5 "-----------------------------------------------------------"
#define SEP3 "----------------------------------------------------------------------------------------"
#define SEP6_8 "-------------------------------------------------------------------"
#define SEP7 "--------------------------"
#define SEP9 "----------------------------------------------------------------"

/**menus e outras tools**/

void menu();
void acedeMenu();
void printLinha(int);
void clear();
void printMensagem(char* string);
void promptError();

/** Navegador*/
void listDivider (GPtrArray* l, guint N, char* message, char* header, int q);

/**funções para apresentar o resultado das queries*/
void show(TABLE, int);
void showQuery2(TABLE p);
void showQuery3(TABLE info);
void showQuery4(TABLE list);
void showQuery5(TABLE avali);
void showQuery6(TABLE top);
void showQuery7(TABLE inter);
void showQuery8(TABLE avaliac);
void showQuery9(TABLE search);

#endif
