package PackageUsers;

public class BadUser extends Exception{

    public BadUser(){
        super();
    }

    public BadUser(String msg){
        super(msg);
    }
}
