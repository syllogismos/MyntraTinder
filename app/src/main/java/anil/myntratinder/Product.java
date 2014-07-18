package anil.myntratinder;

/**
 * Created by Anil on 7/17/2014.
 */
public class Product {
    private int mId;
    private int mIndex;
    private String name;
    private String price;
    private String imageUrl;
    private String description;

    @Override
    public String toString() {
        return name;
    }

    public Product(int mId, int mIndex, String name, String price, String imageUrl, String description) {
        this.mId = mId;
        this.mIndex = mIndex;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Product(int mId, String name, int mIndex, String price, String imageUrl) {
        this.mId = mId;
        this.name = name;
        this.mIndex = mIndex;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(int mId){
        this.mId = mId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmIndex() {
        return mIndex;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
