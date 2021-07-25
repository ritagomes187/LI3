package PackageUsers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserNoFriends implements IUser{

    /**
     * Para efeitos de testes de performance
     */

    private String user_id;
    private String name;

     /**
     * Construtor por omissão
     */
     public UserNoFriends(){
       this.user_id = "";
       this.name = "";
     }

    /**
     * Construtor parametrizado
     */
     public UserNoFriends (String user_id, String name){
       this.user_id = user_id;
       this.name = name;
     }

    /**
     * Construtor de cópia
     */
     public UserNoFriends(UserNoFriends c){
        this.user_id = c.getUserID();
        this.name = c.getName();
     }

     /**
     * devolve o id do user
     * @return <code>char</code> user_id
     */
     public String getUserID(){
        return this.user_id;
     }

     /**
     * devolve o nome do user
     * @return <code>char</code> name
     */
    public String getName(){
        return this.name;
    }

    /**
      * Metodo que faz uma copia do objeto receptor da mensagem.
      * @return objeto clone do objeto que recebe a mensagem.
      */
    public IUser clone() {
       return new UserNoFriends(this);
    }

    /**
     * verifica se dois users são iguais atraves do seu id
     * @return <code>true</code> se os dois users são iguais;
     *         <code>false</code> caso contrário
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass()!=this.getClass()) return false;
        User u = (User) o;
        return this.user_id.equals(u.getUserID()) &&
               this.name.equals(u.getName());
    }

    /**
     * devolve uma representação textual do objeto
     * @return <code>string</code>
     */
    public String toString(){
      StringBuilder s = new StringBuilder();
      s.append("Utilizador:").append("\nID = ").append(user_id)
                            .append("\nNome = ").append(name)
                            .append("\n");
      return s.toString();
    }

     public int hashCode(){
       return Objects.hash(user_id);
    }

     public int compareTo(IUser u){
        return this.user_id.compareTo(u.getUserID());
    }

    public static IUser parseUser(String s) throws BadUser{

        List<String> campos = Arrays.asList(s.split(";"));
        IUser u;

        if(campos.size() == 3 && campos.get(0).length() == 22)
            u = new UserNoFriends(campos.get(0), campos.get(1));
        else
            throw new BadUser();

        return u;
    }

}
