package devops.vishal.ecommerce.activity.ui.cart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import devops.vishal.ecommerce.activity.BuyProductActivity;
import devops.vishal.ecommerce.adapter.CartProductAdapter;
import devops.vishal.ecommerce.adapter.ProductsAdapters;
import devops.vishal.ecommerce.adapter.SearchResultAdapter;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.FragmentCartBinding;
import devops.vishal.ecommerce.models.CartModel;
import devops.vishal.ecommerce.models.ProductModel;
import devops.vishal.ecommerce.utility.Constants;
import devops.vishal.ecommerce.utility.Util;

public class CartFragment extends Fragment {
    private FragmentCartBinding mBindings;
    private DatabaseReference demoRef;
    private String userPhone;
    private SharedPreferences sharedPreferences;
    private List<ProductModel> productNames;
    private CartProductAdapter adapters;

    private String mTotalAmount;
    private String mTotalProducts;
    private ProgressDialog mDialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBindings = FragmentCartBinding.inflate(inflater);
        View root = mBindings.getRoot();
        sharedPreferences = Util.getSharePrefrences(getContext());
        userPhone = sharedPreferences.getString("userPhone", "");
        mDialog = new ProgressDialog(getContext());

        cartProduct();

        mBindings.buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("USER_PHONE",userPhone);
                bundle.putString("TOTAL_AMOUNT",mTotalAmount);

                Intent buyIntent = new Intent(getContext(), BuyProductActivity.class);
                buyIntent.putExtras(bundle);

                startActivity(buyIntent);
            }
        });

        return root;
    }

    private void cartProduct(){

        mDialog.setMessage("Loading Cart Details");
        mDialog.setCancelable(false);
        mDialog.show();

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

                fillRecyclerView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mDialog.dismiss();
                Toast.makeText(getContext(), "Please check your internet connection...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fillRecyclerView() {

        if (productNames.size() > 0) {


        int totalProduct = 0;
        int totalAmount = 0;

        for (ProductModel models : productNames) {

            int amt = Integer.parseInt(models.getProductPrice().replace(",",""));
            totalAmount = totalAmount + amt;

            totalProduct++;
        }


        mTotalAmount = String.valueOf(totalAmount);
        mTotalProducts = String.valueOf(totalProduct);

        mBindings.noOfPdtTv.setText(mTotalProducts);
        mBindings.superOrderTotalTv.setText(mTotalAmount);

        adapters = new CartProductAdapter(getActivity(), mBindings.productRecyclerView, getContext(), productNames);
        mBindings.productRecyclerView.setAdapter(adapters);

        mDialog.dismiss();

        }  else{

            mDialog.dismiss();
            //Animation

        }



    }



}