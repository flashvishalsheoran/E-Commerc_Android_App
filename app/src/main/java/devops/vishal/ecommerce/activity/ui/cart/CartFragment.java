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

import devops.vishal.ecommerce.adapter.ProductsAdapters;
import devops.vishal.ecommerce.adapter.SearchResultAdapter;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.FragmentCartBinding;
import devops.vishal.ecommerce.models.CartModel;
import devops.vishal.ecommerce.models.ProductModel;
import devops.vishal.ecommerce.utility.Util;

public class CartFragment extends Fragment {
    private FragmentCartBinding mBindings;
    private DatabaseReference demoRef,mRef;
    private List<CartModel> productID;
    private String userPhone, productId;
    private SharedPreferences sharedPreferences;
    private List<ProductModel> productNames;
    private ProductsAdapters adapters;
    private ProductModel productModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBindings = FragmentCartBinding.inflate(inflater);
        View root = mBindings.getRoot();

        sharedPreferences = Util.getSharePrefrences(getContext());

        userPhone = sharedPreferences.getString("userPhone", "");

        cartProduct();


        for (CartModel value : productID) {
            String check = value.getProduct();
            Log.i("Value of element ", check);
        }


        return root;
    }

    private void cartProduct(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mBindings.productRecyclerView.setLayoutManager(manager);

        productID = new ArrayList<CartModel>();
        demoRef = EcommerceApplication.getFirebaseDBInstance().child("cartProduct").child(userPhone);
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CartModel cartModel = new CartModel();
                    cartModel = postSnapshot.getValue(CartModel.class);
                    productID.add(cartModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        productNames = new ArrayList<ProductModel>();



        mRef = EcommerceApplication.getFirebaseDBInstance().child("products");
        mRef.orderByChild("productId").equalTo("1001").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    productModel = postSnapshot.getValue(ProductModel.class);
                    productNames.add(productModel);
                }

                adapters = new ProductsAdapters(getActivity(), mBindings.productRecyclerView, getContext(), productNames);
                mBindings.productRecyclerView.setAdapter(adapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

}