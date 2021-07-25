package PackageBusinesses;

public class BadBusiness extends Exception{
    
    public BadBusiness(){
        super();
    }

    public BadBusiness(String msg){
        super(msg);
    }
}
