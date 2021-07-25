#ifndef SGR_H
#define SGR_H

#ifndef _GNU_SOURCE
#define _GNU_SOURCE
#endif

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#include "allBusinesses.h"
#include "allReviews.h"
#include "allUsers.h"
#include "table.h"

typedef struct sgr *SGR;
typedef struct bus_info *INFO, *STARS;

/*  Default PATHs */
#define DEF_USER "input/users.csv"
#define DEF_BUS "input/businesses.csv"
#define DEF_REV "input/reviews.csv"


/**
 * @brief Inicializar a Base da dados
 * 
 * @return SGR 
 */
SGR init_sgr();

/**
 * @brief Destruição da base de dados
 * 
 * 
 * @param Estrutura a ser destruida
 */
void free_sgr(SGR sgr);

/**
 * @brief 
 * 
 * @param users (Ficheiros dos Users)
 * @param businesses (Ficheiro dos Businesses)
 * @param reviews (Ficheiro da Reviews)
 * @return SGR 
 */
/*query_1*/
SGR load_sgr(char* users, char* businesses, char* reviews);

/**
 * @brief Devolve uma tabela com trabalhos começados pela letra introduzida
 * 
 * @param sgr 
 * @param letter 
 * @return TABLE 
 */
/*query_2*/
TABLE businesses_started_by_letter(SGR sgr, char letter);

/**
 * @brief Devolve uma tabela com as informações do business
 * 
 * @param sgr 
 * @param business_id 
 * @return TABLE 
 */
/*query_3*/
TABLE business_info(SGR sgr, char* business_id);

/**
 * @brief Devolve uma tabela com as reviews de cada user
 * 
 * @param sgr 
 * @param user_id 
 * @return TABLE 
 */
/*query_4*/
TABLE businesses_reviewed(SGR sgr, char* user_id);


/**
 * @brief Devolve uma tabela com os businesses que tem determinadas estrelas e estão naquela cidade
 * 
 * @param sgr 
 * @param stars 
 * @param city 
 * @return TABLE 
 */
/*query_5*/
TABLE businesses_with_stars_and_city(SGR sgr, float stars, char* city);


/**
 * @brief Tabela que devolve os melhores business naquela cidade
 * 
 * @param sgr 
 * @param top 
 * @return TABLE 
 */
/*query_6*/
TABLE top_businesses_by_city(SGR sgr,int top);


/**
 * @brief Tabela que devolve users internacionais
 * 
 * @param sgr 
 * @return TABLE 
 */
/*query_7*/
TABLE international_users(SGR sgr);


/**
 * @brief Devolve uma tabela com o melhor negocio numa determinada categoria
 * 
 * @param sgr 
 * @param top 
 * @param category 
 * @return TABLE 
 */
/*query_8*/
TABLE top_businesses_with_category(SGR sgr, int top, char* category);


/**
 * @brief Delvode uma tabela com as reviews que contem determinada palavra.
 * 
 * @param sgr 
 * @param word 
 * @return TABLE 
 */
/*query_9*/
TABLE reviews_with_word(SGR sgr, char* word); //aqui tinha um parametro a mais

#endif
