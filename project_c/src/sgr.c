#include "../includes/sgr.h"

/**
 * Estrutura que contem todos os users, bussinesses e reviews
 **/
struct sgr{
    ALLUSR user;
    ALLBUS bus;
    ALLREV review;
};

struct bus_info{
    float stars;
    int num_of_revs;
    char *id;
    char *nome;
};

void freeSTARS(STARS s){
    if (!s)
        return;
    free(s->id);
    free(s->nome);
    free(s);
}

SGR init_sgr(){
    SGR sgr = malloc(sizeof *sgr);
    sgr->user = initALLUSR();
    sgr->bus = initALLBUS();
    sgr->review = initALLREV();
    return sgr;
}

void free_sgr(SGR sgr){
    if (!sgr)
        return;
    destroyALLUSR(sgr->user);
    destroyALLBUS(sgr->bus);
    destroyALLREV(sgr->review);
    free(sgr);
}

/* Query 1: Dado o caminho para os 3 ficheiros, ler o seu
conteúdo e carregar as estruturas de dados correspondentes
*/
SGR load_sgr(char *users, char *business, char *reviews){
    SGR new = init_sgr();
    int i;

    i = userFile(users, new->user);
    if (i)
        goto fail;

    i = busFile(business, new->bus);
    if (i)
        goto fail;

    i = revFile(reviews, new->review);
    if (i)
        goto fail;

    return new;

fail:
    free_sgr(new);
    return NULL;
}

/* aux query 2 */
static void auxQ2(char *key, BUSINESS value, TABLE t){

    char *aux = get_name_bus(value);
    char *str_builder;
    char letter = get_chardata(t);

    if (tolower(*aux) == tolower(letter)){
        str_builder = get_busID_and_name(value);
        addLineTABLE(t, str_builder);
    }
    free(aux);
}

/*Query 2: Determinar a lista de nomes de negócios e o número total de negócios cujo nome
inicia por uma dada letra. Note que a procura não deverá ser case sensitive.*/
TABLE businesses_started_by_letter(SGR sgr, char letter){

    TABLE new = initTABLE();
    set_chardata(new, letter); //letra para a função auxiliar
    loadHeader(new, 2);

    g_hash_table_foreach(getALLBUS(sgr->bus), (GHFunc)auxQ2, new);
    sortTABLE(new);

    return new;
}

/* auxiliar usada em várias queries */
static void countSTARS(char *key, REVIEW value, GHashTable *h){
    char *busID = get_busID_rev(value);
    STARS aux_struct = (STARS) g_hash_table_lookup(h, busID);

    STARS new = malloc(sizeof *new);
    new->stars = get_stars(value);
    new->num_of_revs = 1;
    new->id = new->nome = NULL;

    if (aux_struct){
        new->stars += aux_struct->stars;
        new->num_of_revs += aux_struct->num_of_revs;
        free(aux_struct);
    }

    g_hash_table_insert(h, busID, new);
}

/* Query 3: Dado um id de negócio, determinar a sua informação: 
nome, cidade, estado, stars e nr total reviews.
*/
TABLE business_info(SGR sgr, char *business_id){ //business id = RFJWBB4LLtXZn_yXlqklxg (6 reviews)

    TABLE new = initTABLE();
    loadHeader(new, 3);

    GHashTableIter iter;
    gpointer value;
    int num_of_revs = 0;
    float stars = 0;
    g_hash_table_iter_init(&iter, getALLREV(sgr->review));

    while (g_hash_table_iter_next(&iter, NULL, &value)){
        char *busID = get_busID_rev((REVIEW) value);
        if (!(strcmp(busID, business_id))){
            num_of_revs++;
            stars += get_stars((REVIEW) value);
        }
        free(busID);
    }

    stars /= num_of_revs;

    BUSINESS bus = (BUSINESS)g_hash_table_lookup(getALLBUS(sgr->bus), business_id);
    if (bus)
        addLineTABLE(new, get_bus_info(bus, stars, num_of_revs));

    return new;
}

/* Query 4: Dado um id de utilizador, determinar a lista de negócios aos quais fez review. A
informação associada a cada negócio deve ser o id e o nome.
*/
TABLE businesses_reviewed(SGR sgr, char *user_id){ //user id = YoVfDbnISlW0f7abNQACIg (123 reviews)
    TABLE new = initTABLE();
    loadHeader(new, 4);

    GHashTableIter iter;
    gpointer value;
    g_hash_table_iter_init(&iter, getALLREV(sgr->review));

    while (g_hash_table_iter_next(&iter, NULL, &value)){
        char *usrID = get_userID_rev((REVIEW) value);

        if (!(strncmp(usrID, user_id, strlen(usrID)))){
            char* busID = get_busID_rev((REVIEW) value);
            BUSINESS bus = (BUSINESS) g_hash_table_lookup(getALLBUS(sgr->bus), busID);
            if (bus)
                addLineTABLE(new, get_busID_and_name(bus));
            free(busID);
        }
        free(usrID);
    }

    sortTABLE(new);
    return new;
}

/* aux query 5 */
static void auxQ5(char *key, STARS st, TABLE t){

    float f = get_floatdata(t);
    st->stars /= st->num_of_revs;

    if (st->stars >= f)
        addLineTABLE(t, strdup(key));
}

static void countSTARS_5(char *key, REVIEW value, GHashTable *h){
    char *busID = get_busID_rev(value);
    STARS aux_struct = (STARS)g_hash_table_lookup(h, busID);

    STARS new = malloc(sizeof *new);
    new->stars= get_stars(value);
    new->num_of_revs = 1;
    new->id = new->nome = NULL;

    if(aux_struct){
        new->stars += aux_struct->stars;
        new->num_of_revs += aux_struct->num_of_revs;
    }

    g_hash_table_insert(h, busID, new);
}
/* Query 5: Dado um número n de stars e uma cidade, determinar a lista de negócios com n
ou mais stars na dada cidade. A informação associada a cada negócio deve ser o seu id e
nome.*/
TABLE businesses_with_stars_and_city(SGR sgr, float stars, char *city){

    TABLE new = initTABLE();
    set_floatdata(new, stars);
    loadHeader(new, 5);

    GHashTable *auxHASH = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, g_free);

    g_hash_table_foreach(getALLREV(sgr->review), (GHFunc)countSTARS_5, auxHASH);
    g_hash_table_foreach(auxHASH, (GHFunc)auxQ5, new);

    char *busID, *strbuilder, *cidade;
    const unsigned int N = get_size(new);
    unsigned int count;

    for (count = 0; count < N; count++){

        busID = g_ptr_array_index(get_lines(new), 0);
        BUSINESS auxBUS = g_hash_table_lookup(getALLBUS(sgr->bus), busID);
        cidade = get_city(auxBUS);

        if (!strcmp(cidade, city)){
            strbuilder = get_busID_and_name(auxBUS);
            addLineTABLE(new, strbuilder);
        }

        g_ptr_array_remove_index(get_lines(new), 0);
        free(cidade);
    }
    g_hash_table_destroy(auxHASH);

    sortTABLE(new);
    return new;
}

static void extraINFO(char *key, STARS value, GHashTable *bus){
    value->nome = get_name_bus((BUSINESS)g_hash_table_lookup(bus, key));
    value->id = strdup(key);
    value->stars /= value->num_of_revs;
}

static void cityToIDS(char *key, BUSINESS value, GHashTable *cities){
    char *city = get_city(value);
    GPtrArray *aux = g_hash_table_lookup(cities, city);

    if (!aux){
        GPtrArray *new = g_ptr_array_new_with_free_func(g_free);
        g_ptr_array_add(new, strdup(key));
        g_hash_table_insert(cities, city, new);
    }
    else
        g_ptr_array_add(aux, strdup(key));
}

static gint sort_6(gconstpointer a, gconstpointer b){
    STARS sa = *(STARS *) a;
    STARS sb = *(STARS *) b;

    if(!sa) return -1;
    if(!sb) return 1;

    if((sa->stars - sb->stars) > 0) return 1;
    if((sa->stars - sb->stars) < 0) return -1;
    return 0;
}


static void starsByCity(char *key, GPtrArray *value, GHashTable *rating){   

    const int N = value->len;
    STARS aux;

    for (int i = 0; i < N; i++){
        aux = (STARS) g_hash_table_lookup(rating, g_ptr_array_index(value, 0));
        g_ptr_array_remove_index(value, 0);
        g_ptr_array_add(value, aux);
    }
    g_ptr_array_sort(value, (GCompareFunc)sort_6);
    g_ptr_array_set_free_func(value, (GDestroyNotify)freeSTARS);
}

static void toTABLE(char *key, GPtrArray *value, TABLE t){
    const guint len = value->len;
    const guint top = ((guint)get_intdata(t) < len) ? (guint)get_intdata(t) : len;
    char *strbuilder;
    gboolean b = FALSE;
    gboolean c = TRUE;
   
    for (guint i = len-1; i > (len-top-1); i--){
        STARS aux = (STARS)g_ptr_array_index(value, i);
        if(aux){
            if(c){
                addLineTABLE(t, format_city(key));
                c = !c;
            }
            strbuilder = get_bus_info_param(aux->id, aux->nome, aux->stars);
            addLineTABLE(t, strbuilder);
            b = !b;
        }
    }
    g_ptr_array_free(value, b);
}

/* Query 6:  Dado um número inteiro n, determinar a lista dos top n negócios (tendo em conta
o número médio de stars) em cada cidade. A informação associada a cada negócio deve
ser o seu id, nome, e número de estrelas.*/
TABLE top_businesses_by_city(SGR sgr, int top){
    TABLE new = initTABLE();
    loadHeader(new, 6);
    top = (top < 0) ? 0 : top;
    set_intdata(new, top);

    GHashTable *rating = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, NULL);
    GHashTable *cities = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, NULL);
    
    //associar a cada negócio as estrelas
    g_hash_table_foreach(getALLREV(sgr->review), (GHFunc)countSTARS, rating);

    //associar a cada 'STARS' o nome do negócio e fazer a média das stars
    g_hash_table_foreach(rating, (GHFunc)extraINFO, getALLBUS(sgr->bus));

    //associar a cada cidade uma lista de IDs de negócio
    g_hash_table_foreach(getALLBUS(sgr->bus), (GHFunc)cityToIDS, cities);
 
    //em cada cidade substitui-se cada ID pela informação de negócio correspondente
    g_hash_table_foreach(cities, (GHFunc)starsByCity, rating);

    //tomar os 'top' melhores e colocá-los na TABLE
    g_hash_table_foreach(cities, (GHFunc)toTABLE, new);

    g_hash_table_destroy(rating);
    g_hash_table_destroy(cities);
    return new;
}

/*
 aux query 7-1 */
static void auxQ7_1(char *key, REVIEW value, GHashTable *h){

    char *userID = get_userID_rev(value);
    char *busID = get_busID_rev(value);
    GPtrArray *aux = g_hash_table_lookup(h, userID);

    if (!aux){
        GPtrArray *new = g_ptr_array_new_with_free_func(g_free);
        g_ptr_array_add(new, busID);
        g_hash_table_insert(h, userID, new);
    }
    else
        g_ptr_array_add(aux, busID);
}

/*aux query 7-2 */
static void auxQ7_2(char *key, GPtrArray *value, GHashTable *bus){

    const unsigned int N = value->len;
    char *state;

    for (unsigned int i = 0; i < N; i++){
        state = get_state((BUSINESS)g_hash_table_lookup(bus, g_ptr_array_index(value, 0)));
        g_ptr_array_remove_index(value, 0);
        g_ptr_array_add(value, state);
    }
}

/*aux query 7-3 */
static void auxQ7_3(char *key, GPtrArray *value, TABLE t){

    const unsigned int N = value->len;
    unsigned int i = 0;
    unsigned int j;

    for (; i < N - 1; i++){
        for (j = i + 1; j < N; j++){
            if (strcmp(g_ptr_array_index(value, i), g_ptr_array_index(value, j))){
                addLineTABLE(t, formatUSER(key));
                goto exit;
            }
        }
    }
exit:
    g_ptr_array_free(value, TRUE);
}

/*Query 7: Determinar a lista de ids de utilizadores e o número total de utilizadores que
tenham visitado mais de um estado, i.e. que tenham feito reviews em negócios de diferentes
estados*/
TABLE international_users(SGR sgr){
    TABLE new = initTABLE();
    loadHeader(new, 7);

    GHashTable *usersNbus = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, NULL);

    //associar a cada user uma lista de IDs de reviews
    g_hash_table_foreach(getALLREV(sgr->review), (GHFunc)auxQ7_1, usersNbus);
    //associar a cada user uma lista de states
    g_hash_table_foreach(usersNbus, (GHFunc)auxQ7_2, getALLBUS(sgr->bus));
    //adicionar à TABLE os internacionais
    g_hash_table_foreach(usersNbus, (GHFunc)auxQ7_3, new);

    g_hash_table_destroy(usersNbus);
    sortTABLE(new);

    return new;
}

/* aux query 8 */
static gboolean auxQ8(char *key, STARS value, ALLBUS bs){
    
    GHashTable *allBUS = getALLBUS(bs);
    char *begin = get_categories(g_hash_table_lookup(allBUS, key));
    if(!begin)
        return 1;

    char *word = get_lixo(bs);
    char *ptr = begin;
    size_t N = strlen(word);
    int i = 1;

    while (i && (ptr = strcasestr(ptr, word))){

        if ((ptr == begin || !isalnum(*(ptr - 1))) && !isalnum(*(ptr + N))){
            value->stars /= value->num_of_revs;
            value->nome = get_name(g_hash_table_lookup(allBUS, key));
            value->id = strdup(key);
            i=0;
        }
        else
            ptr += N;
    }

    free(begin);
    return i;
}

static void toArray(char *key, STARS value, GPtrArray *arr){
    g_ptr_array_add(arr, value);
}

static gint sort_by_stars(gconstpointer a, gconstpointer b){
    STARS sa = *(STARS *) a;
    STARS sb = *(STARS *) b;

    if((sa->stars - sb->stars) > 0) return 1;
    if((sa->stars - sb->stars) < 0) return -1;
    return 0;
}

/* Query 8: Dado um número inteiro n e uma categoria, determinar a lista dos top n negócios
(tendo em conta o número médio de stars) que pertencem a uma determinada categoria. A
informação associada a cada negócio deve ser o seu id, nome, e número de estrelas.
*/
TABLE top_businesses_with_category(SGR sgr, int top, char *category){
    TABLE new = initTABLE();
    loadHeader(new, 8);

    GHashTable *auxHASH = g_hash_table_new_full(g_str_hash, g_str_equal, g_free, NULL);

    //associar a cada negócio as estrelas e filtrar as que não pertencem à categoria
    g_hash_table_foreach(getALLREV(sgr->review), (GHFunc)countSTARS, auxHASH);
    set_lixo(sgr->bus, category);
    g_hash_table_foreach_remove(auxHASH, (GHRFunc)auxQ8, sgr->bus);

    GPtrArray *array = g_ptr_array_new_with_free_func((GDestroyNotify)freeSTARS);

    //converter a hashtable num array, destruí-la e ordenar por estrelas
    g_hash_table_foreach(auxHASH, (GHFunc)toArray, array);
    g_hash_table_destroy(auxHASH);
    g_ptr_array_sort(array, (GCompareFunc)sort_by_stars);

    top = (top < 0) ? 0 : top;
    const int len = array->len;
    top = (len < top) ? len : top;
    for (int i = len-1; i > (len-top-1); i--){
        STARS aux = (STARS)g_ptr_array_index(array, i);
        addLineTABLE(new, get_bus_info_param(aux->id, aux->nome, aux->stars));
    }
    g_ptr_array_free(array, TRUE);
    return new;
}

/* aux query 9 */
static void auxQ9(char *key, REVIEW value, TABLE t){
    char *begin = get_text(value);
    char *word = get_strdata(t);
    char *ptr = begin;
    char *str_builder;
    size_t N = strlen(word);
    int i = 1;

    while (i && (ptr = strcasestr(ptr, word))){

        if ((ptr == begin || !isalnum(*(ptr - 1))) && !isalnum(*(ptr + N))){
            str_builder = get_reviewID_and_text(value, ptr);
            addLineTABLE(t, str_builder);
            i = 0;
        }
        else
            ptr += N;
    }
    free(begin);
}

/* Query 9: Dada uma palavra, determinar a lista de ids 
de reviews que a referem no campo text.*/
TABLE reviews_with_word(SGR sgr, char *word){

    TABLE new = initTABLE();
    loadHeader(new, 9);
    set_strdata(new, word);
    g_hash_table_foreach(getALLREV(sgr->review), (GHFunc)auxQ9, new);

    sortTABLE(new);
    return new;
}
