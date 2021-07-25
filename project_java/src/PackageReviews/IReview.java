package PackageReviews;

import java.io.Serializable;

/**
 * Interface das reviews
 */
public interface IReview extends Comparable<IReview>, Serializable{    
    public String getReviewID();
    public String getUserID();
    public String getBusID();
    public float getStars();
    public int getUseful();
    public int getFunny();
    public int getCool();
    public String getDate();
    public String getText();

    public IReview clone();

    /**
     * Calcula o hash value da Review
     * @return hash value da Review
     */
    @Override
    public int hashCode();
}