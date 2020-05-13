package devops.vishal.ecommerce.activity.ui.cart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import devops.vishal.ecommerce.adapter.CartProductAdapter;
import devops.vishal.ecommerce.adapter.ProductsAdapters;
import devops.vishal.ecommerce.adapter.SearchResultAdapter;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.FragmentCartBinding;
import devops.vishal.ecommerce.models.CartModel;
import devops.vishal.ecommerce.models.ProductModel;
import devops.vishal.ecommerce.utility.Util;

public class CartFragment extends Fragment {
    private FragmentCartBinding mBindings;
    private DatabaseReference demoRef;
    private String userPhone;
    private SharedPreferences sharedPreferences;
    private List<ProductModel> productNames;
    private CartProductAdapter adapters;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBindings = FragmentCartBinding.inflate(inflater);
        View root = mBindings.getRoot();
        sharedPreferences = Util.getSharePrefrences(getContext());
        userPhone = sharedPreferences.getString("userPhone", "");

        cartProduct();

        return root;
    }

    private void cartProduct(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mBindings.productRecyclerView.setLayoutManager(manager);

        productNames = new ArrayList<ProductModel>();

        demoRef = EcommerceApplication.getFirebaseDBInstance().child("cartProduct").child(userPhone);
        demoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ProductModel productModel = new ProductModel();
                    productModel = postSnapshot.getValue(ProductModel.class);
                    productNames.add(productModel);

                }
                adapters = new CartProductAdapter(getActivity(), mBindings.productRecyclerView, getContext(), productNames);
                mBindings.productRecyclerView.setAdapter(adapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}