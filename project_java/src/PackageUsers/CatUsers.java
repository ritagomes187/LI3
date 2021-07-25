package PackageUsers;

import java.util.*;

import PackageReviews.EndOfCatalogException;

/**
 * Classe Catalogo dos Users que implementa a interface ICatUsers
 */
public class CatUsers implements ICatUsers{
    /**
     * Map de interfaces IUser
     */
    private Map<String, IUser> catalogo;

    /**
     * Iterator do catálogo
     */
    private Iterator<IUser> iter;

    /**
     * Construtor por omissão do Catálogo de Users
     */
    public CatUsers(){
        this.catalogo = new HashMap<>();
        this.iter = null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        this.catalogo.values().forEach(e -> sb.append(e).append(":"));

        return sb.toString();
    }
   
    public void insere(IUser user){
        this.catalogo.put(user.getUserID(), user.clone());
    }

    public boolean procura(String id){
        return this.catalogo.containsKey(id);
    }

    public IUser getUser(String id){
        IUser u = this.catalogo.get(id);
        if(u!=null)
            u = u.clone();
        return u;
    }

    public IUser proximo() throws EndOfCatalogException{
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