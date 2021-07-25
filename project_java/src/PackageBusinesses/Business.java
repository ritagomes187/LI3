package PackageBusinesses;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Business implements IBusiness{
   /** Código do Business */

   private String business_id;
   private String name;
   private String city;
   private String state;
   private Set<String> categories;

    /**
    * Construtor por omissão
    */
    public Business(){
      this.business_id = "";
      this.name = "";
      this.city = "";
      this.state = "";
      this.categories = new TreeSet<>();
    }

   /**
    * Construtor parametrizado
    */
    public Business (String id, String name, String city, String state, Set<String> cats){
      this.business_id = id;
      this.name = name;
      this.city = city;
      this.state = state;
      this.categories = new TreeSet<>(cats);
    }
    
   /**
    * Construtor de cópia
    */
    public Business(Business c){
        this.business_id = c.getBusID();
        this.name = c.getName();
        this.city = c.getCity();
        this.state = c.getState();
        this.categories = c.getCategories();
    }
   

     public String getBusID(){
        return this.business_id;
     }
    
     public String getName(){
        return this.name;
     }
    
    public String getCity(){
        return this.city;
    }

    public String getState(){
        return this.state;
    }
    
    public Set<String> getCategories(){
        return new TreeSet<>(this.categories);
    }
    


   /**
     * Metodo que faz uma copia do objeto receptor da mensagem.
     * @return objeto clone do objeto que recebe a mensagem.
     */
    public IBusiness clone() { 
        return new Business(this);
    }
    
    /**
    * verifica se dois businesses são iguais atraves do id business
    * @return <code>true</code> se os dois Businesses são iguais;
    *         <code>false</code> caso contrário
    */ 
    public boolean equals(Object o){
        if (o == this) return true; 
        if (o == null || o.getClass()!=this.getClass()) return false;
        Business b = (Business) o; 
        return this.business_id.equals(b.getBusID()) &&
                this.categories.equals(b.getCategories()) &&
                this.city.equals(b.getCity()) &&
                this.name.equals(b.getName()) &&
                this.state.equals(b.getState());
    }
    
    /**
    * devolve uma representação textual do objeto
    * @return <code>string</code>
    */
   
    public String toString(){
        StringBuilder s = new StringBuilder(); 
        s.append("Business:").
        append("\nBusinessID = ").append(business_id).
        append("\nNome = ").append(name).
        append("\nCidade = ").append(city).
        append("\nEstado = ").append(state).
        append("\nCategorias = ");

        for(String c : this.categories){
            s.append(c).append("; ");
        }

        s.append("\n");
        return s.toString();
    }
    
    public int hashCode() {
        return Objects.hash(business_id);
    }
 
    public int compareTo(IBusiness b){
        return this.business_id.compareTo(b.getBusID());
    }

    public static IBusiness parseBusiness(String s) throws BadBusiness{

        List<String> campos = Arrays.asList(s.split(";"));
        Set<String> categories;

        if(campos.size() == 5 && campos.get(0).length() == 22)
            categories = new TreeSet<>(Arrays.asList(campos.get(4).split(",")));
        
        else if(campos.size() == 4 && campos.get(0).length() == 22)
            categories = new TreeSet<>();
        
        else
            throw new BadBusiness();


        return new Business(campos.get(0), campos.get(1), campos.get(2), campos.get(3), categories);
    }
}