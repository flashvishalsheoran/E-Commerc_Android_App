package devops.vishal.ecommerce.activity.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.adapter.ProductsAdapters;
import devops.vishal.ecommerce.adapter.SearchResultAdapter;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.FragmentSearchBinding;
import devops.vishal.ecommerce.models.ProductModel;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding mBindings;
    private SearchResultAdapter mSearchAdapter;
    private DatabaseReference demoRef;
    private List<ProductModel> productNames;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mBindings = FragmentSearchBinding.inflate(inflater);
        mBindings.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    String editTextResult = s.toString();
                    setSearchResultAdapter(editTextResult);
                }else{
                    mBindings.emptySearchLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        return mBindings.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        productNames = new ArrayList<>();

    }

    private void setSearchResultAdapter(String search) {

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mBindings.productRecyclerView.setLayoutManager(manager);

        mBindings.emptySearchLayout.setVisibility(View.GONE);
        mBindings.productRecyclerView.setVisibility(View.VISIBLE);

        productNames.clear();
        demoRef =  EcommerceApplication.getFirebaseDBInstance().child("products");

        demoRef.orderByChild("productName")
                .equalTo(search).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ProductModel productModel = new ProductModel();
                    productModel = postSnapshot.getValue(ProductModel.class);
                    productNames.add(productModel);
                }


                mSearchAdapter = new SearchResultAdapter(mBindings.productRecyclerView, getContext(),productNames);
                mBindings.productRecyclerView.setAdapter(mSearchAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}