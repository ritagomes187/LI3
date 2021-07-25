#include "../includes/view.h"

/* Este módulo contém o menu e a código relativo à paginação
*/

void menu(){
        printf("\033[0;33m"" \n ****************************************************************""\033[0m");
        printf("\033[1m""\n                        MENU: Selecione uma opção                    ""\033[0m");
        printf("\033[0;33m"" \n ****************************************************************""\033[0m");
        printf("\n 1-Ler ficheiros");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 2-Negocios começados por determinada letra");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 3-Informação sobre um determinado negocio");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 4-Negocios reviewed por um determinado user");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 5-Negocios com n estrelas na cidade C");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 6-Top N negocios");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 7-Utilizadores com reviews em diferentes estados");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 8-Top N negocios na respetiva categoria");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 9-Procura palavra na review");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n 10-SAIR");
        printf("\n ---------------------------------------------------------------- ");
        printf("\n> ");
        
}

/* Função que aparece depois da exibição de cada query*/
void acedeMenu(){
    printf ("\033[0;33m""*****************************************************************\n""\033[0m");
    printf ("\033[1m""            Se pretende aceder ao menu : PRIMA ENTER             \n""\033[0m");
    printf ("\033[0;33m""*****************************************************************\n""\033[0m");
    printf("\033[0;33m""> ""\033[0m");
    while(getchar()!='\n');
    clear();
}

/* Função que limpa o ecrã */
void clear(){
    printf("\e[2J\e[h");
}

/* Função que imprime mensagens*/
void printMensagem(char* string){
    printf(" %s\n", string);
}

/* Função que imprime linha*/
void printLinha(int i){
    switch(i){
        case 2:
            printf("\t       %s\t\n", SEP2_4_5);
            break;

        case 3:
            printf("\t       %s\t\n", SEP3);
            break;

        case 4:
            printf("\t       %s\t\n", SEP2_4_5);
            break;

        case 5:
            printf("\t       %s\t\n", SEP2_4_5);
            break;

        case 6:
            printf("\t       %s\t\n", SEP6_8);
            break;
        
        case 7:
            printf("\t       %s\t\n", SEP7);
            break;
        
        case 8:
            printf("\t       %s\t\n", SEP6_8);
            break;

        case 9:
            printf("\t       %s\t\n", SEP9);
            break;
        
        default:
            break;
    }
    
}

/**
 * @brief Função responsavel por erros que de loading
 * 
 */
void promptError(){
    printMensagem("Error loading SGR using default paths. Please insert some new paths.");
    printf("ENTER to continue.\n> ");
    while(getchar() != '\n');
    clear();
}


/**
 * @brief Menu da função listDivider
 * 
 * @return char 
 */
static char proxPag(){
    char ch;
    printf("\033[1m""Pagina Seguinte       (P)\n""\033[0m");
    printf("\033[1m""Pagina Anterior       (A)\n""\033[0m");
    printf("\033[1m""Voltar ao Menu        (M)\n""\033[0m");
    printf("\033[1m""Ir Para a Pagina      (E)\n""\033[0m");
    printf("\033[1m""> ""\033[0m");
    do{
        ch = toupper(getchar());
    } while((ch!='P')&&(ch!='A')&&(ch!='M')&&(ch!='E'));
    while (!getchar());
    return ch;
}

/**
 * @brief Função responsável pela paginação
 * 
 * @param l 
 * @param N 
 * @param message 
 * @param header 
 * @param q 
 */
void listDivider (GPtrArray* l, guint N, char* message, char* header, int q) {
    printf("\033[2J");
    
    int pages = N/10;
    if ((N%10)!=0) pages++;

    int j=1;
    guint k=0;
    guint t=0;
    char ch;
    int temp1=0;
    int temp2=0;
    guint n;
    
    while(j<=pages&&t<N){

    jump:
        temp1=t;
        printf("\033[1m""%s\n\n""\033[0m", message);
        printLinha(q);
        printf("\033[1m""\t       %s\n""\033[0m",header);
        for(n=0; n<10; n++, t++){
            if(k+n>=N) break;
            else{
                printLinha(q);
                printf("\t%0*d. %s\n",5, n+k+1, (char*) g_ptr_array_index(l, k+n));
            }
        }
        printLinha(q);
        temp2=k;
        k+=10;
        printf("\033[1m""\nPage <%d/%d> \n\n""\033[0m",j ,pages);
        j++;
        
        ch=proxPag();

        if(ch=='P'){
        
            if(j==pages+1){
                j=1; t=0; k=0;
                printf("You are already on the last page!\nPress C to return to the first page\n> ");
                while (getchar()!='c' && getchar()!='C');
                printf("\033[2J");
                goto jump;
            }
            else printf("\033[2J");
        }
        else if(ch=='M') break;
    
        else if(ch=='A'){

                if(j!=2){
                    printf ("\033[2J");
                    k = temp2-10; j = j-2; t = temp1-10;

                    goto jump;
                }
                else {
                    printf("You are already on the first page!\nPress C to continue\n> "); 
                    while (getchar()!='c' && getchar()!='C');
                    printf("\033[2J");
                }
            }
        else if(ch=='E'){
            printf("Escolha o numero da pagina:\n> ");
            char buf[30];
            int f;

            jump2:
                fgets(buf, 30, stdin);
                f = atoi(buf);

                if(f<1 || f>pages){
                    printf("Página Inválida. Insira uma pagina entre 1 e %d.\n",pages);
                    goto jump2;
                }
                else{
                    k=(f*10)-10;
                    j = f;
                    t = f*10;
                    printf("\033[2J");
                    goto jump;
                }
            }
        }
}

/**
 * @brief Função responsável por apresentar os resultados da query 2
 * 
 * @param p 
 */
void showQuery2(TABLE p){
    char* head = get_header(p);
    listDivider(get_lines(p),get_size(p),"Bussiness started by letter", head, 2);
    free(head);
}

/**
 * @brief Função responsável por apresentar os resultados da query 3
 * 
 * @param info 
 */
void showQuery3(TABLE info){
    char* head = get_header(info);
    listDivider(get_lines(info), get_size(info), "Info about a business", head, 3);
    free(head);
}

/**
 * @brief Função responsável por apresentar os resultados da query 4
 * 
 * @param list 
 */
void showQuery4(TABLE list){
    char* head = get_header(list);
    listDivider(get_lines(list),get_size(list),"Reviews made by this user", head, 4);
    free(head);
}

/**
 * @brief Função responsável por apresentar os resultados da query 5
 * 
 * @param avali 
 */
void showQuery5(TABLE avali){
    char* head = get_header(avali);
    listDivider(get_lines(avali),get_size(avali),"Best businesses in this city", head, 5);
    free(head);

}

/**
 * @brief Função responsável por apresentar os resultados da query 6
 * 
 * @param top 
 */
void showQuery6(TABLE top){
    char* head = get_header(top);
    listDivider(get_lines(top),get_size(top),"Top 'n' businesses", head, 6);
    free(head);
}

/**
 * @brief Função responsável por apresentar os resultados da query 7
 * 
 * @param inter 
 */
void showQuery7(TABLE inter){
    char* head = get_header(inter);
    listDivider(get_lines(inter),get_size(inter),"List of International People", head, 7);
    free(head);
}

/**
 * @brief Função responsável por apresentar os resultados da query 8
 * 
 * @param avaliac 
 */
void showQuery8(TABLE avaliac){
    char* head = get_header(avaliac);
    listDivider(get_lines(avaliac),get_size(avaliac),"Top businesses within this category", head, 8);
    free(head);
}

/**
 * @brief Função responsável por apresentar os resultados da query 9
 * 
 * @param search 
 */
void showQuery9(TABLE search){
    char* head = get_header(search);
    listDivider(get_lines(search),get_size(search),"Search for a word", head, 9);
    free(head);
}