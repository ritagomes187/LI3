package PackageBusinesses;

import java.io.Serializable;
import java.util.Set;

/**
 * Interface dos businesses
 */
public interface IBusiness extends Serializable, Comparable<IBusiness>{    
    public String getBusID();
    public String getName();
    public String getCity();
    public String getState();
    public Set<String> getCategories();
    
    public IBusiness clone();

    /**
     * Calcula o hash value do Business
     * @return hash value do Business
     */
    @Override
    public int hashCode();
}