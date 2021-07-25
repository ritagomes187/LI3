package PackageReviews;

public class BadReview extends Exception{

    public BadReview(){
        super();
    }

    public BadReview(String msg){
        super(msg);
    }
}
