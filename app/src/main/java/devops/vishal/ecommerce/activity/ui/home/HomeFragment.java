package devops.vishal.ecommerce.activity.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.adapter.CategoriesAdapter;
import devops.vishal.ecommerce.adapter.ProductsAdapters;
import devops.vishal.ecommerce.adapter.ViewPagerAdapter;
import devops.vishal.ecommerce.databinding.FragmentHomeBinding;
import devops.vishal.ecommerce.models.ProductModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding mFragBindings;
    private ViewPagerAdapter viewPagerAdapter;
    private CategoriesAdapter mCategoryAdapter;
    private ProductsAdapters mProductAdapter;

    private int dotscount;
    private ImageView[] dots;
    private Timer timer;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        getContext().getTheme().applyStyle(R.style.homeScreenDesign, true);
        mFragBindings = FragmentHomeBinding.inflate(inflater);
        return mFragBindings.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        viewPagerAdapter = new ViewPagerAdapter(getContext());
        mFragBindings.viewPager.setAdapter(viewPagerAdapter);

        //Setting Dots to the image Slider
        setImageDotCount();

        //Set Category adapter
        setCategoryAdapter();

        //Set Products Adapters
        setProductAdapter();



    }

    private void setProductAdapter() {

        ArrayList<ProductModel> productNames = new ArrayList<>();
        productNames.add(new ProductModel("LED Tv","Sony Onida LED","Price : 31,000"));
        productNames.add(new ProductModel("Jeans","Denim Mens LED","Price : 1,200"));
        productNames.add(new ProductModel("Shoes","Nike Jordon Shoes","Price : 5,699"));
        productNames.add(new ProductModel("Shoes","Nike Lite Series","Price : 3,000"));
        productNames.add(new ProductModel("iPhone","iPhone XR","Price : 45,000"));
        productNames.add(new ProductModel("iPhone","iPhone 11","Price : 65,000"));



        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);

        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);


        mProductAdapter = new ProductsAdapters(getContext(),productNames);

        mFragBindings.popularRecycler1.setLayoutManager(manager);
        mFragBindings.popularRecycler2.setLayoutManager(manager1);

        mFragBindings.popularRecycler1.setAdapter(mProductAdapter);
        mFragBindings.popularRecycler2.setAdapter(mProductAdapter);

    }


    //Function to set Category Adapter
    private void setCategoryAdapter() {
        ArrayList<String> categoryNames = new ArrayList<>();
        categoryNames.add("Electronics");
        categoryNames.add("Fashion");
        categoryNames.add("Shoes");
        categoryNames.add("Kids");
        categoryNames.add("Living");


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mCategoryAdapter = new CategoriesAdapter(getContext(),categoryNames);

        mFragBindings.categoriesRecyclerView.setLayoutManager(manager);
        mFragBindings.categoriesRecyclerView.setAdapter(mCategoryAdapter);


    }


    //Function to set dots to the Image Slider
    private void setImageDotCount() {

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            mFragBindings.SliderDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

        mFragBindings.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mFragBindings.viewPager.post(new Runnable(){

                    @Override
                    public void run() {
                        mFragBindings.viewPager.setCurrentItem((mFragBindings.viewPager.getCurrentItem()+1)%dots.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);

    }


    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}