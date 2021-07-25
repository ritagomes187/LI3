package PackageBusinesses;

import java.io.Serializable;

import PackageReviews.EndOfCatalogException;

/**
 * Interface do Catálogo de Businesses
 */
public interface ICatBus extends Serializable{
    
    /**
     *
     * Função que transforma o catálogo numa String
     * @return           String resultante da função
     */
    @Override
     public String toString();

    /**
     * Função que insere um IBusiness
     * @param bus  Interface IBusiness que pretendemos inserir
     */
    public void insere(IBusiness bus);

    /**
     * Função que verifica se um IBusiness se encontra num catálogo
     * @param id           Chave a procurar
     * @return                  Booleano que mostra se bus pertence ao catálogo ou não
     */
    public boolean procura(String id);

    /**
    * Devolve o IBusinness, se este existir no catálogo, dada a chave
    * @param id          Chave do business a procurar
    * @return            Business clonado
    */    
    public IBusiness getBusiness(String id);

    /**
     * A próxima entrada do catálogo a consultar
     * @return {@code IBusiness} clonado
     * @throws EndOfCatalogException catálogo não contém mais entradas
     */
    public IBusiness proximo() throws EndOfCatalogException;
}