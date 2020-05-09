package devops.vishal.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.databinding.ActivityProductDescriptionBinding;

import android.os.Bundle;

public class ProductDescriptionActivity extends AppCompatActivity {

    private ActivityProductDescriptionBinding mBindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindings = ActivityProductDescriptionBinding.inflate(getLayoutInflater());
        setTheme(R.style.fullScreenDesign);
        setContentView(mBindings.getRoot());



    }
}