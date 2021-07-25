package Main;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import Extras.*;

public interface IGestReviews extends Serializable{

    /**
     * Default businesses path
     */
    public static final String BUS_PATH = "../input/businesses.csv";

    /** 
     * Default users path 
    */ 
    public static final String USR_PATH = "../input/users.csv";

    /**
     * Default reviews path
     */
    public static final String REV_PATH = "../input/reviews.csv";

    /**
     * Leitura do ficheiro de Businesses
     * @param fn Nome do ficheiro a ler
     * @param def {@code true} se deve ser usado um PATH por omissão
     * @throws IOException
     */
    public void readBusinessesFile(String fn, boolean def) throws IOException;

    /**
     * Leitura do ficheiro de Users
     * @param fn Nome do ficheiro a ler
     * @param def {@code true} se deve ser usado um PATH por omissão
     * @throws IOException
     */
    public void readUsersFile(String fn, boolean def) throws IOException;

    /**
     * Leitura do ficheiro de Reives
     * @param fn Nome do ficheiro a ler
     * @param def {@code true} se deve ser usado um PATH por omissão
     * @throws IOException
     */
    public void readReviewFile(String fn, boolean def) throws IOException;
 
    /**
     * Query estatística 1
     * @return String contendo a informação
     */
    public String queryE1();
    
    /**
     * Lista ordenada alfabeticamente com os identificadores dos negócios 
     * nunca avaliados e o seu respetivo total
     * @return Par {@code <Set<String>, Integer>} dos ids ordenados alfabeticamente e o seu número
     */
    public Par<Set<String>, Integer> query1();

    /**
     * Dado um mês e um ano, determinar o número total de reviews
     * realizadas e o número total de users distintos que as realizaram
     * @param ano Ano das reviews a procurar
     * @param mes Mês das reviews a procurar
     * @return  A String contendo a informação
     */
    public String query2(String ano, String mes);

    /**
     * Dado um código de utilizador, determinar, para cada mês, quantas reviews fez,
     * quantos negócios distintos avaliou e que nota média atribuiu
     * @param id Identificador do utilizador
     * @return Lista das Strings contendo a informação
     */
    public List<String> query3(String id);

    /**
     * Dado o código de um negócio, determinar, mês a mês, quantas vezes foi
     * avaliado, por quantos users diferentes e a média de classificação
     * @param id Identificador do negócio
     * @return Lista das Strings contendo a informação
     */
    public List<String> query4(String id);

    /**
     * Dado o código de um utilizador, determinar a lista de nomes de negócios que mais
     * avaliou (e quantos), ordenada por ordem decrescente de quantidade e, para 
     * quantidades iguais, por ordem alfabética dos negócios
     * @param id Identificador do utilizador
     * @return Set de pares {@code <String, Integer>} nome de negócio e número de reviews feitas
     * pelo user dado
     */
    public List<String> query5(String id);

    /**
     * Determinar o conjunto dos N negócios mais avaliados (com mais reviews) em cada ano,
     * indicando o número total de distintos utilizadores que o avaliaram
     * @param n Número dos negócios mais avaliados a colecionar
     * @return Map que a cada chave ano faz corresponder um Set de Triplos
     * {@code <String, Integer, Integer>} id de negócio, número de reviews (nesse ano) e 
     * número de users distintos que o avaliaram
     */
    public List<String> query6(int n);

    /**
     * Determinar, para cada cidade, a lista dos três mais famosos negócios
     * em termos de número de reviews
     * @return {@code List<String>} contendo os ids dos negócios
     */
    public List<String> query7();

    /**
     * Dado um inteiro N, determinar os identificadores dos N utilizadores que avaliaram mais
     * negócios diferentes, indicando quantos, sendo o critério de ordenação a ordem 
     * decrescente do número de negócios
     * @param n Número dos melhores utilizadores a colecionar
     * @return
     */
    public List<String> query8(int n);

    /**
     * Dado o identificador de um negócio, determinar o conjunto dos N users que mais o avaliaram e, 
     * para cada um, qual o valor médio de classificação
     * @param id Identificador do negócio
     * @param n Número dos users que mais avaliaram a colecionar
     * @return
     */
    public List<String> query9(String id, int n);

    /**
     * Determinar para cada estado, cidade a cidade, a média de classificação de cada negócio
     * @return
     */
    public List<String> query10();   

}