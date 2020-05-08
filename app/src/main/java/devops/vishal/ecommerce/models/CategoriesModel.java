package devops.vishal.ecommerce.models;

import androidx.annotation.Keep;

@Keep
public class CategoriesModel {

    private String categoryName;
    private String categoryImage;

    public CategoriesModel() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }
}
