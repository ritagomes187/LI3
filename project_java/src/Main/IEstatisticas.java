package Main;

import java.io.Serializable;

public interface IEstatisticas extends Serializable {

    public void set_fn_businesses(String s);

    public void set_fn_reviews(String s);

    public void set_fn_users(String s);

    public void set_bad_reviews(int n);
    
    public void set_impactless(int n);
    
    public void set_businesses(int n);

    public void set_reviewed(int n);

    public void set_users(long l);

    public void set_active_users(long l);
    
    /**
     * Informação estatística
     * @return String contendo a informação
     */
    public String toString();
}