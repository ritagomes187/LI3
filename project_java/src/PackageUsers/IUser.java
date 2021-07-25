package PackageUsers;

import java.io.Serializable;

/**
 * Interface do User
 */
public interface IUser extends Comparable<IUser>, Serializable{
    
    public String getUserID();

    public String getName();
   
   

    public IUser clone();

    /**
     * Calcula o hash value do User
     * @return hash value do User
     */
    @Override
    public int hashCode();
}