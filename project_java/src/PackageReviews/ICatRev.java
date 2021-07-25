package PackageReviews;

import java.io.Serializable;

/**
 * Interface do Catálogo de Reviews
 */
public interface ICatRev extends Serializable{

    /**
     * Função que insere um IReview
     * @param review  Interface IReview que pretendemos inserir
     */
    public void insere(IReview review);

    /**
     * Função que verifica se um IReview se encontra num catálogo
     * @param id               Chave a procurar
     * @return                  Booleano que mostra se review pertence ao catálogo ou não
     */
    public boolean procura(String id);

    /**
     *
     * Função que transforma o catálogo numa String
     * @return           String resultante da função
     */
    @Override
     public String toString();

    /**
    * Devolve o IReview, se este existir no catálogo, dada a chave
    * @param id          Chave do Review a procurar
    * @return            Review clonado
    */    
    public IReview getReview(String id);

    /**
    * Calcula o número de diferentes negócios avaliados
    * @return Número de diferentes negócios avaliados
    */
    public int businesses_reviewed();
    
    /**
     * Número de users distintos que fizeram reviews
     * @return Número de users
     */
    public long reviewers();

    /**
     * Calcula o número de reviews cujo somatório de funny, cool e useful é nulo
     * @return Número de reviews sem impacto
     */
    public int impactless();

    /**
     * A próxima entrada do catálogo a consultar
     * @return {@code IReview} clonado
     * @throws EndOfCatalogException catálogo não contém mais entradas
     */
    public IReview proximo() throws EndOfCatalogException;
}