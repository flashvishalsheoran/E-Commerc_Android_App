package devops.vishal.ecommerce.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.adapter.ProductsAdapters;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.ActivityKidsBinding;
import devops.vishal.ecommerce.models.ProductModel;

public class KidsActivity extends AppCompatActivity {
    private ActivityKidsBinding mBindings;
    private List<ProductModel> productModelList;
    private DatabaseReference demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindings = ActivityKidsBinding.inflate(getLayoutInflater());
        setTheme(R.style.fullScreenDesign);
        setContentView(mBindings.getRoot());

        mBindings.kRecyclerView.setLayoutManager(new LinearLayoutManager(KidsActivity.this));

        productModelList = new ArrayList<>();
        demoRef = EcommerceApplication.getFirebaseDBInstance().child("products");
        demoRef.orderByChild("productType").equalTo("Kids").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                    ProductModel productModel = new ProductModel();
                    productModel = dataSnap.getValue(ProductModel.class);
                    productModelList.add(productModel);
                }
                Collections.reverse(productModelList);
                ProductsAdapters adapter = new ProductsAdapters(KidsActivity.this, mBindings.kRecyclerView, getApplicationContext(), productModelList);
                mBindings.kRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}