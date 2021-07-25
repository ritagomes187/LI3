#include "../includes/controller.h"

/*Query 1. Dado o caminho para os 3 ficheiros, ler o seu
conteúdo e carregar as estruturas de dados correspondentes*/
static void query_1(SGR *sgr){
    char *user = malloc(sizeof *user * 1000);
    char *bus = malloc(sizeof *bus * 1000);
    char *rev = malloc(sizeof *rev * 1000);
    
    printMensagem("\033[2J");
    printMensagem("\033[5m" "\033[1m" "LOADING...\n" "\033[0m");
    printMensagem("\x1B[?25l");
    clear();
    
    printMensagem("Users PATH?");
    printf("> ");
    fgets(user, 1000, stdin);
    strtok(user, "\n");

    printMensagem("Businesses PATH?");
    printf("> ");
    fgets(bus, 1000, stdin);
    strtok(bus, "\n");
    
    printMensagem("Reviews PATH?");
    printf("> ");
    fgets(rev, 1000, stdin);
    strtok(rev, "\n");

    *sgr = load_sgr (user, bus, rev);
    free(user);
    free(bus);
    free(rev);
    printMensagem("\x1B[?25h");
    acedeMenu();
}


/*Query 2. Determinar a lista de nomes de negócios e o número total de negócios cujo nome
inicia por uma dada letra.*/
static void query_2(SGR sgr){
    char letra;
    TABLE res;
    printMensagem("Insira a letra");
    printf("> ");
    letra = fgetc(stdin);
    res = businesses_started_by_letter(sgr,letra);
    showQuery2(res);
    destroyTABLE(res);
    acedeMenu();
}


    


/*Query 3. Dado um id de negócio, determinar a sua informação: 
nome, cidade, estado, stars e nr total reviews.*/
static void query_3(SGR sgr){
    char buf[100];
    TABLE info;
    printMensagem("Insira um id");
    printf("> ");
    fgets(buf,100,stdin);
    strtok(buf, "\n");
    info = business_info(sgr,buf);
    showQuery3(info);
    destroyTABLE(info);
    acedeMenu();
} 



/*Query 4. Dado um id de utilizador, determinar a lista de negócios aos quais fez review. A
informação associada a cada negócio deve ser o id e o nome.*/
static void query_4(SGR sgr){
    char buf[100];
    TABLE list;
    printMensagem("Insira um id");
    printf("> ");
    fgets(buf,100,stdin);
    strtok(buf, "\n");
    list = businesses_reviewed(sgr,buf);
    showQuery4(list);
    destroyTABLE(list);
    acedeMenu();
}


/*
Query 5. Dado um número n de stars e uma cidade, determinar a lista de negócios com n
ou mais stars na dada cidade. A informação associada a cada negócio deve ser o seu id e
nome.*/
static void query_5(SGR sgr){
    float stars;
    char bufS[4];
    char buf[100];
    TABLE avali;
    printMensagem("Insira um número de stars");
    printf("> ");
    stars = atof(fgets(bufS,4,stdin));
    printMensagem("Insira a cidade");
    printf("> ");
    fgets(buf,100,stdin);
    strtok(buf, "\n");
    avali = businesses_with_stars_and_city(sgr,stars,buf);
    showQuery5(avali);
    destroyTABLE(avali);
    acedeMenu();
}

/*
Query 6. Dado um número inteiro n, determinar a lista dos top n negócios (tendo em conta
o número médio de stars) em cada cidade. A informação associada a cada negócio deve
ser o seu id, nome, e número de estrelas.*/
static void query_6(SGR sgr){
    int n;
    char topn[100];
    TABLE top;
    printMensagem("Insire o número inteiro para determinar o top");
    printf("> ");
    n = atoi(fgets(topn,100,stdin));
    top = top_businesses_by_city(sgr,n);
    showQuery6(top);
    destroyTABLE(top);
    acedeMenu();

}

/*
Query 7. Determinar a lista de ids de utilizadores e o número total de utilizadores que
tenham visitado mais de um estado, i.e. que tenham feito reviews em negócios de diferentes
estados.*/
static void query_7(SGR sgr){
    TABLE inter;
    inter = international_users(sgr);
    showQuery7(inter);
    destroyTABLE(inter);
    acedeMenu();
}

/*
Query 8. Dado um número inteiro n e uma categoria, determinar a lista dos top n negócios
(tendo em conta o número médio de stars) que pertencem a uma determinada categoria. A
informação associada a cada negócio deve ser o seu id, nome, e número de estrelas.*/
static void query_8(SGR sgr){
    int num;
    char bufS[20];
    char buf[100];
    TABLE avaliac;
    printMensagem("Insira um número inteiro");
    printf("> ");
    num = atof(fgets(bufS,20,stdin));
    printMensagem("Insira a categoria");
    printf("> ");
    fgets(buf,100,stdin);
    strtok(buf, "\n");
    avaliac= top_businesses_with_category(sgr,num,buf);
    showQuery8(avaliac);
    destroyTABLE(avaliac);
    acedeMenu();
}


/*Query 9. Dada uma palavra, determinar a lista de ids de reviews que a referem no campo
text.*/
static void query_9(SGR sgr){
    char buf[30];
    TABLE search;
    printMensagem("Insira a word");
    printf("> ");
    fgets(buf,30,stdin);
    strtok(buf, "\n");
    search = reviews_with_word(sgr,buf);
    showQuery9(search);
    destroyTABLE(search);
    acedeMenu();
}


/**
 * Função que faz a interação com o utilizador 
 **/
int startController(){
    char buf[100];
    int choice;
    clock_t start, end;
    double cpu_time_used;
    SGR sgr = load_sgr(DEF_USER, DEF_BUS, DEF_REV);
    if(!sgr){
        promptError();
        choice = 1;
        goto sgr_fail;
    }

    do{
        clear();
        menu();
        fgets(buf, 100, stdin);
        choice = atoi(buf);
        sgr_fail:
        start = clock();
        
        switch (choice){
        
            case 1:
                free_sgr(sgr);    //no caso de ser NULL, nada acontece
                query_1(&sgr);
                break;

            case 2:
                query_2(sgr);
                break;
           
            case 3:
                query_3(sgr);
                break;
            
            case 4:
                query_4(sgr);
                break;
            
            case 5:
                query_5(sgr);
                break;
            
            case 6:
                query_6(sgr);
                break;
            
            case 7:
                query_7(sgr);
                break;
            
            case 8:
                query_8(sgr);
                break;
        
            case 9:
                query_9(sgr);
                break; 

            case 10:
                break;

            default:
                printMensagem("Insira novamente a opção.");
                printf("> ");
        }

        end = clock();
        cpu_time_used = ((double)(end-start)) / CLOCKS_PER_SEC;
        printf("time to execute query %d: %lf ", choice , cpu_time_used);
        while (getchar() != '\n');
    
    } while (choice != 10);
    
    free_sgr(sgr);
    printMensagem("That's All, Folks!");
    return 0;
}
  