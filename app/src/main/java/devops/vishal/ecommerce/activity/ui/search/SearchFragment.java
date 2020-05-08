package devops.vishal.ecommerce.activity.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.adapter.ProductsAdapters;
import devops.vishal.ecommerce.adapter.SearchResultAdapter;
import devops.vishal.ecommerce.databinding.FragmentSearchBinding;
import devops.vishal.ecommerce.models.ProductModel;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding mBindings;
    private SearchResultAdapter mSearchAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mBindings = FragmentSearchBinding.inflate(inflater);
        return mBindings.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        setSearchResultAdapter();

    }

    private void setSearchResultAdapter() {

            mBindings.emptySearchLayout.setVisibility(View.GONE);
            mBindings.productRecyclerView.setVisibility(View.VISIBLE);

            ArrayList<ProductModel> productNames = new ArrayList<>();
            productNames.add(new ProductModel("iPhone","iPhone XR (64 gb) ","Price : 44,999"));
            productNames.add(new ProductModel("iPhone","iPhone 11 (128 gb) ","Price : 75,000"));
            productNames.add(new ProductModel("One Plus","Oneplus 7T (8gb Ram) ","Price : 49,000"));
            productNames.add(new ProductModel("Oppo","Oppo A9 Pro (4/64 gb)","Price : 15,000"));
            productNames.add(new ProductModel("Samsung","Samsung M50 (5/64 gb) ","Price : 18,000"));
            productNames.add(new ProductModel("Samsung","Samsung M20 (4/64 gb)","Price : 12,599"));


            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.VERTICAL);

            mSearchAdapter = new SearchResultAdapter(getContext(),productNames);

            mBindings.productRecyclerView.setLayoutManager(manager);
            mBindings.productRecyclerView.setAdapter(mSearchAdapter);


    }
}