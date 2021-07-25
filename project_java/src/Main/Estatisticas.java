package Main;

public class Estatisticas implements IEstatisticas{

    private String fn_reviews;
    private String fn_users;
    private String fn_businesses;

    private int bad_reviews;
    private int impactless;
    
    private int businesses;
    private int reviewed;
    private int not_reviewed;
    
    private long users;
    private long active_users;
    private long inactive_users;

    public Estatisticas(){
        
        this.fn_users = this.fn_reviews = this.fn_businesses = "";
        this.bad_reviews = this.impactless = this.businesses = this.reviewed = this.not_reviewed = 0;
        this.users = this.active_users = this.inactive_users = 0;
    }

    public void set_fn_reviews(String s){
        this.fn_reviews = s;
    }

    public void set_fn_users(String s){
        this.fn_users = s;
    }

    public void set_fn_businesses(String s){
        this.fn_businesses = s;
    }

    public void set_bad_reviews(int n){
        this.bad_reviews = n;
    }

    public void set_impactless(int n){
        this.impactless = n;
    }
    
    public void set_businesses(int n){
        this.businesses = n;
    }

    public void set_reviewed(int n){
        this.reviewed = n;
        this.not_reviewed = this.businesses - n;
    }

    public void set_users(long l){
        this.users = l;
    }

    public void set_active_users(long l){
        this.active_users = l;
        this.inactive_users = this.users - l;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nomes dos ficheiros: ").
           append(this.fn_businesses).append(';').
           append(this.fn_users).append(';').
           append(this.fn_reviews).append('\n').
           
           append("Reviews incorretas - sem impacto: ").
           append(this.bad_reviews).append(" - ").
           append(this.impactless).append('\n').
           
           append("Total users (ativos, inativos)").
           append(this.users).append(" (").
           append(this.active_users).append(", ").
           append(this.inactive_users).append(")\n").
           
           append("Total negócios (com reviews, sem reviews)").
           append(this.businesses).append(" (").
           append(this.reviewed).append(", ").
           append(this.not_reviewed).append(")\n");

        return sb.toString();
    }
}
