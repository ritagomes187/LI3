package PackageBusinesses;

import java.util.*;

import PackageReviews.EndOfCatalogException;

/**
 * Classe Catalogo dos Businesses que implementa a interface ICatBus
 */
public class CatBus implements ICatBus{
    /**
     * Map de interfaces IBusiness
     */
    private Map<String, IBusiness> catalogo;

    /**
     * Iterator do catálogo
     */
    private Iterator<IBusiness> iter;

    /**
     * Construtor por omissão do Catálogo de Businesses
     */
    public CatBus(){
        this.catalogo = new HashMap<>();
        this.iter = null;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();

        this.catalogo.values().forEach(e -> sb.append(e).append(":"));

        return sb.toString();
    }

    public void insere(IBusiness bus){
        this.catalogo.put(bus.getBusID(), bus.clone());
    }

    public boolean procura(String id){
        return this.catalogo.containsKey(id);
    }

    public IBusiness getBusiness(String id){
        IBusiness b = this.catalogo.get(id);
        if(b!=null)
            b = b.clone();
        return b;
    }

    public IBusiness proximo() throws EndOfCatalogException{
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