package PackageReviews;

/**
 * Classe com uma Excepetion de Review não existir
 */
public class ReviewInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que cliente Não Existe
     */
    public ReviewInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Review Inexistente";
    }
}