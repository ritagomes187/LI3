package PackageUsers;

import java.io.Serializable;

import PackageReviews.EndOfCatalogException;

/**
 * Interface do Catálogo de Users
 */
public interface ICatUsers extends Serializable{

    /**
     * Função que insere um IUser
     * @param user  Interface IUser que pretendemos inserir
     */
    public void insere(IUser user);

    /**
     * Função que verifica se um IUser se encontra num catálogo
     * @param id           Chave a procurar
     * @return                  Booleano que mostra se user pertence ao catálogo ou não
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
    * Devolve o IUser, se este existir no catálogo, dada a chave
    * @param id          Chave do User a procurar
    * @return            User clonado
    */    
    public IUser getUser(String id);

     /**
     * A próxima entrada do catálogo a consultar
     * @return {@code IUser} clonado
     * @throws EndOfCatalogException catálogo não contém mais entradas
     */
    public IUser proximo() throws EndOfCatalogException;
}