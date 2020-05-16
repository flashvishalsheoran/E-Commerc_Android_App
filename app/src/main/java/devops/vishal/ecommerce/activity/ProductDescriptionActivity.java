package devops.vishal.ecommerce.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.activity.ui.search.SearchFragment;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.ActivityProductDescriptionBinding;
import devops.vishal.ecommerce.models.CartModel;
import devops.vishal.ecommerce.models.ProductModel;
import devops.vishal.ecommerce.utility.Util;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ProductDescriptionActivity extends AppCompatActivity  {

    private ActivityProductDescriptionBinding mBindings;
    private DatabaseReference demoRef;
    private SharedPreferences sharedPreferences;
    private String userPhone, productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindings = ActivityProductDescriptionBinding.inflate(getLayoutInflater());
        setTheme(R.style.fullScreenDesign);
        setContentView(mBindings.getRoot());

        mBindings.productCategory.setText(getIntent().getStringExtra("productCategory"));
        mBindings.totalPriceTv.setText(" ₹ "+getIntent().getStringExtra("productPrice"));
        mBindings.descTV.setText(getIntent().getStringExtra("productDescription"));

        mBindings.featureTV.setText(getIntent().getStringExtra("productFeatures1")+"\n");
        mBindings.featureTV.append(getIntent().getStringExtra("productFeatures2")+"\n");
        mBindings.featureTV.append(getIntent().getStringExtra("productFeatures3")+"\n");
        mBindings.featureTV.append(getIntent().getStringExtra("productFeatures4")+"\n\n\n");

        mBindings.productNameTV.setText(getIntent().getStringExtra("productName"));
        mBindings.discountPriceTV.setText("DISCOUNT : ₹ "+getIntent().getStringExtra("productDiscountPrice"));

        mBindings.realPriceTV.setText("MPR : ₹ "+getIntent().getStringExtra("productTotalPrice"));

        mBindings.stockTv.setText("In Stock : "+getIntent().getStringExtra("productAvailable"));
        mBindings.productDiscountPercentTV.setText(getIntent().getStringExtra("productDiscountPercent")+" off");

        Glide.with(this)
                .load(getIntent().getStringExtra("productImage"))
                .into(mBindings.productImage);


        sharedPreferences  = Util.getSharePrefrences(ProductDescriptionActivity.this);
        userPhone =sharedPreferences.getString("userPhone","");


        productId = getIntent().getStringExtra("productId");
        System.out.println("aaa"+userPhone+" id"+productId);

        mBindings.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pushOfAddCart();
            }
        });

    }
    private void pushOfAddCart(){
        demoRef = EcommerceApplication.getFirebaseDBInstance().child("cartProduct")
                .child(userPhone);
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProductModel productModel = new ProductModel();
                productModel.setProductName(getIntent().getStringExtra("productName"));
                productModel.setProductCategory(getIntent().getStringExtra("productCategory"));
                productModel.setProductPrice(getIntent().getStringExtra("productPrice"));

                productModel.setProductFeatures1(getIntent().getStringExtra("productFeatures1"));
                productModel.setProductFeatures2(getIntent().getStringExtra("productFeatures2"));
                productModel.setProductFeatures3(getIntent().getStringExtra("productFeatures3"));
                productModel.setProductFeatures4(getIntent().getStringExtra("productFeatures4"));
                productModel.setProductAvailable(getIntent().getStringExtra("productAvailable"));

                productModel.setProductBrand(getIntent().getStringExtra("productBrand"));

                productModel.setProductTotalPrice(getIntent().getStringExtra("productTotalPrice"));
                productModel.setProductDiscountPercent(getIntent().getStringExtra("productDiscountPercent"));
                productModel.setProductDiscountPrice(getIntent().getStringExtra("productDiscountPrice"));
                productModel.setProductType(getIntent().getStringExtra("productType"));

                productModel.setProductDescription(getIntent().getStringExtra("productDescription"));

                productModel.setProductImage(getIntent().getStringExtra("productImage"));
                demoRef.push().setValue(productModel);



//                CartModel cartModel = new CartModel();
//                cartModel.setProduct(productId);
//                demoRef.push().setValue(cartModel);

                Toast.makeText(ProductDescriptionActivity.this,"Product Added to Cart",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Function to Load Fragment
    private void loadFragment(Fragment fragment){
        FragmentTransaction transactionManager = getSupportFragmentManager().beginTransaction();
        transactionManager.replace(R.id.frameContainer , fragment).commit();
    }



}