package PackageUsers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class User implements IUser{
   /** Código do User */
   private String user_id;
   private String name;
   private String friends;

    /**
    * Construtor por omissão 
    */
    public User(){
      this.user_id = "";
      this.name = "";
      this.friends = "";
    }

   /**
    * Construtor parametrizado
    */
    public User (String user_id, String name, String friends){
      this.user_id = user_id;
      this.name = name;
      this.friends = friends;
    }
    
   /**
    * Construtor de cópia
    */
    public User(User c){
       this.user_id = c.getUserID();
       this.name = c.getName();
       this.friends = c.getFriends();
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
    * devolve os friends do user
    * @return <code>char</code> friends
    */
    public String getFriends(){
        return this.friends;
    }

   /**
     * Metodo que faz uma copia do objeto receptor da mensagem.
     * @return objeto clone do objeto que recebe a mensagem.
     */
   public IUser clone() { 
      return new User(this);
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
              this.friends.equals(u.getFriends()) &&
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
                           .append("\nAmigos = ").append(friends)
                         .append("\n");
     return s.toString();
   }
    
    public int hashCode() {
      return Objects.hash(user_id);
    } 

    public int compareTo(IUser u){
      return this.user_id.compareTo(u.getUserID());
    }

    public static IUser parseUser(String s) throws BadUser{

      List<String> campos = Arrays.asList(s.split(";"));
      IUser u;

      if(campos.size() == 3 && campos.get(0).length() == 22)      
         u = new User(campos.get(0), campos.get(1), campos.get(2));
      else
         throw new BadUser();

      return u;
   }
}