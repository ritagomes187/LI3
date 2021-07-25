package PackageReviews;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Review implements IReview{
   /** Código da Review */
   private String review_id;
   private String user_id;
   private String business_id;
   private float stars;
   private int useful;
   private int funny;
   private int cool;
   private String date;
   private String text;

    /**
    * Construtor por omissão
    */
    public Review(){
      this.review_id = "";
      this.user_id = "";
      this.business_id = "";
      this.stars = 0.f;
      this.useful = 0;
      this.funny = 0;
      this.cool = 0;
      this.date = "";
      this.text = "";
    }

   /**
    * Construtor parametrizado
    */
    public Review(String review_id, String user_id, String business_id, float stars,  
                   int useful, int funny, int cool, String date, String text){
        this.review_id = review_id;
        this.user_id = user_id;
        this.business_id = business_id;
        this.stars = stars;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.date = date;
        this.text = text;
    }
    
   /**
    * Construtor de cópia
    */
    public Review(Review c){
        this.review_id = c.getReviewID();
        this.user_id = c.getUserID();
        this.business_id = c.getBusID();
        this.stars = c.getStars();
        this.useful = c.getUseful();
        this.funny = c.getFunny();
        this.cool = c.getCool();
        this.date = c.getDate();
        this.text = c.getText();
    }
   

    public String getReviewID(){
       return this.review_id;
    }
    public String getUserID(){
        return this.user_id;
     }
     public String getBusID(){
        return this.business_id;
     }
     public float getStars(){
        return this.stars;
     }
     public int getUseful(){
        return this.useful;
     }
     public int getFunny(){
        return this.funny;
     }
     public int getCool(){
        return this.cool;
     }

     public String getDate(){
        return this.date;
     }
     public String getText(){
        return this.text;
     }
     
   
  

   /**
     * Metodo que faz uma copia do objeto receptor da mensagem.
     * @return objeto clone do objeto que recebe a mensagem.
     */
   public IReview clone() { 
      return new Review(this);
   }
    
   /**
    * verifica se duas reviews são iguais atraves do id do user, da review e do business
    * @return <code>true</code> se os dois reviews são iguais;
    *         <code>false</code> caso contrário
    */ 
   public boolean equals(Object o){
       if (o == this) return true; 
       if (o == null || o.getClass()!=this.getClass()) return false;
       Review r = (Review) o; 
       return this.review_id.equals(r.getReviewID()) && 
              this.user_id.equals(r.getUserID()) && 
              this.business_id.equals(r.getBusID()) &&
              this.stars == r.getStars() &&
              this.funny == r.getFunny() &&
              this.cool == r.getCool() &&
              this.useful == r.getUseful() &&
              this.text.equals(r.getText()) &&
              this.date.equals(r.getDate());
   }
    
   /**
    * devolve uma representação textual do objeto
    * @return <code>string</code>
    */
   
   public String toString(){
     StringBuilder s = new StringBuilder(); 
     s.append("Review:").append("\nReviewID = ").append(review_id)
                           .append("\nUserID = ").append(user_id)
                           .append("\nBusinessID = ").append(business_id)
                           .append("\nStars = ").append(stars)
                           .append("\nUseful = ").append(useful)
                           .append("\nFunny = ").append(funny)
                           .append("\nCool = ").append(cool)
                           .append("\nData = ").append(date)
                           .append("\nTexto = ").append(text)
                           .append("\n");
     return s.toString();
   }
   
   public int hashCode() {
      return Objects.hash(review_id);
   } 

   public int compareTo(IReview r){
      return this.review_id.compareTo(r.getReviewID());
   }

   public static IReview parseReview(String s) throws BadReview{

      List<String> campos = Arrays.asList(s.split(";"));
      IReview r;

      if(campos.size() == 9 && campos.get(0).length() == 22)      
         r = new Review(campos.get(0), campos.get(1), campos.get(2), Float.parseFloat(campos.get(3)),
                        Integer.parseInt(campos.get(4)), Integer.parseInt(campos.get(5)), 
                        Integer.parseInt(campos.get(6)), campos.get(7), campos.get(8));
      
      else if(campos.size() == 8 && campos.get(0).length() == 22)
      r = new Review(campos.get(0), campos.get(1), campos.get(2), Float.parseFloat(campos.get(3)),
                     Integer.parseInt(campos.get(4)), Integer.parseInt(campos.get(5)), 
                     Integer.parseInt(campos.get(6)), campos.get(7), "");
      
      else
         throw new BadReview();

      return r;
   }

}