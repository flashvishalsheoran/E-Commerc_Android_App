package devops.vishal.ecommerce.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {

    private String productName;
    private String productCategory;
    private String productPrice;
    private String productImage;
    private String productFeatures1;
    private String productFeatures2;
    private String productFeatures3;
    private String productFeatures4;
    private String productDiscountPrice;
    private String productDescription;
    private String productBrand;

    private String productTotalPrice;
    private String productDiscountPercent;
    private String productAvailable;
    private String productType;
    private String productPopular;
    private String productId;


    public ProductModel() {
    }

    public ProductModel(String productName, String productCategory, String productPrice, String productImage, String productFeatures1, String productFeatures2, String productFeatures3, String productFeatures4, String productDiscountPrice, String productDescription, String productBrand, String productTotalPrice, String productDiscountPercent, String productAvailable, String productType, String productPopular, String productId) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productFeatures1 = productFeatures1;
        this.productFeatures2 = productFeatures2;
        this.productFeatures3 = productFeatures3;
        this.productFeatures4 = productFeatures4;
        this.productDiscountPrice = productDiscountPrice;
        this.productDescription = productDescription;
        this.productBrand = productBrand;
        this.productTotalPrice = productTotalPrice;
        this.productDiscountPercent = productDiscountPercent;
        this.productAvailable = productAvailable;
        this.productType = productType;
        this.productPopular = productPopular;
        this.productId = productId;
    }

    protected ProductModel(Parcel in) {
        productName = in.readString();
        productCategory = in.readString();
        productPrice = in.readString();
        productImage = in.readString();
        productFeatures1 = in.readString();
        productFeatures2 = in.readString();
        productFeatures3 = in.readString();
        productFeatures4 = in.readString();
        productDiscountPrice = in.readString();
        productDescription = in.readString();
        productBrand = in.readString();
        productTotalPrice = in.readString();
        productDiscountPercent = in.readString();
        productAvailable = in.readString();
        productType = in.readString();
        productPopular = in.readString();
        productId = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductFeatures1() {
        return productFeatures1;
    }

    public void setProductFeatures1(String productFeatures1) {
        this.productFeatures1 = productFeatures1;
    }

    public String getProductFeatures2() {
        return productFeatures2;
    }

    public void setProductFeatures2(String productFeatures2) {
        this.productFeatures2 = productFeatures2;
    }

    public String getProductFeatures3() {
        return productFeatures3;
    }

    public void setProductFeatures3(String productFeatures3) {
        this.productFeatures3 = productFeatures3;
    }

    public String getProductFeatures4() {
        return productFeatures4;
    }

    public void setProductFeatures4(String productFeatures4) {
        this.productFeatures4 = productFeatures4;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(String productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getProductDiscountPercent() {
        return productDiscountPercent;
    }

    public void setProductDiscountPercent(String productDiscountPercent) {
        this.productDiscountPercent = productDiscountPercent;
    }

    public String getProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(String productAvailable) {
        this.productAvailable = productAvailable;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductPopular() {
        return productPopular;
    }

    public void setProductPopular(String productPopular) {
        this.productPopular = productPopular;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productName);
        parcel.writeString(productCategory);
        parcel.writeString(productPrice);
        parcel.writeString(productImage);
        parcel.writeString(productFeatures1);
        parcel.writeString(productFeatures2);
        parcel.writeString(productFeatures3);
        parcel.writeString(productFeatures4);
        parcel.writeString(productDiscountPrice);
        parcel.writeString(productDescription);
        parcel.writeString(productBrand);
        parcel.writeString(productTotalPrice);
        parcel.writeString(productDiscountPercent);
        parcel.writeString(productAvailable);
        parcel.writeString(productType);
        parcel.writeString(productPopular);
        parcel.writeString(productId);
    }
}
