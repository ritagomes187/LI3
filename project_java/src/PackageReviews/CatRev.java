package PackageReviews;

import java.util.*;

/**
 * Classe Catalogo dos Reviews que implementa a interface ICatRev
 */
public class CatRev implements ICatRev{

    /**
     * Map de interfaces IReview
     */
    private Map<String, IReview> catalogo;

    /**
     * Iterator do catálogo
     */
    private Iterator<IReview> iter;

    /**
     * Construtor por omissão do Catálogo de Reviews
     */
    public CatRev(){
        this.catalogo = new HashMap<>();
        this.iter = null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        this.catalogo.values().forEach(e -> sb.append(e).append(":"));

        return sb.toString();
    }

    public void insere(IReview rev){
        this.catalogo.put(rev.getReviewID(), rev.clone());
    }

    public boolean procura(String id){
        return this.catalogo.containsKey(id);
    }

    public IReview getReview(String id){
        IReview r = this.catalogo.get(id);
        if(r!=null)
            r = r.clone();
        return r;
    }

    public int businesses_reviewed(){
        return (int) this.catalogo.values().stream().
                                   map(IReview::getBusID).
                                   distinct().count();
    }

    public long reviewers(){
        return this.catalogo.values().stream().
                                      map(IReview::getUserID).
                                      distinct().count();
    }
    
    public int impactless(){
        int[] count = new int[1];
        this.catalogo.values().stream().
                               map(r -> r.getFunny() + r.getCool() + r.getUseful()).
                               forEach(n -> count[0] = (n==0) ? count[0]++ : count[0]);
        return count[0];
    }

    public IReview proximo() throws EndOfCatalogException{
        if(this.iter == null)
            this.iter = this.catalogo.values().iterator();

        if(iter.hasNext())
            return iter.next().clone();
        else{
            this.iter = null;
            throw new EndOfCatalogException();
        }
    }
}
