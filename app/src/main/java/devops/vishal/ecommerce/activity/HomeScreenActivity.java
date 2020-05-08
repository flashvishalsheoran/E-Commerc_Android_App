package devops.vishal.ecommerce.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.activity.ui.cart.CartFragment;
import devops.vishal.ecommerce.activity.ui.home.HomeFragment;
import devops.vishal.ecommerce.activity.ui.profile.ProfileFragment;
import devops.vishal.ecommerce.activity.ui.search.SearchFragment;
import devops.vishal.ecommerce.databinding.ActivityCredentialsBinding;
import devops.vishal.ecommerce.databinding.ActivityHomeScreenMainBinding;
import devops.vishal.ecommerce.interfaces.HomeScreenListner;

public class HomeScreenActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeScreenListner {

   private ActivityHomeScreenMainBinding mBindings;
   private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindings = ActivityHomeScreenMainBinding.inflate(getLayoutInflater());
        setTheme(R.style.fullScreenDesign);
        setContentView(mBindings.getRoot());

        mBindings.bottomNavView.setItemIconTintList(null);
        mBindings.bottomNavView.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                loadFragment(new HomeFragment());
                return true;
            case R.id.navigation_search:
                loadFragment(new SearchFragment());
                return true;
            case R.id.navigation_cart:
                loadFragment(new CartFragment());
                return true;
            case R.id.navigation_profile:
                loadFragment(new ProfileFragment());
                return true;
        }

        return false;
    }


    private void loadFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }

}