package devops.vishal.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.ActivityOrderSuccessBinding;
import devops.vishal.ecommerce.utility.Util;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class OrderSuccessActivity extends AppCompatActivity {

    private ActivityOrderSuccessBinding mFragmentBindings;
    private String mOrderNumber;
    private String mUserPhoneNumber;
    private AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f );
    private AlphaAnimation fadeOut = new AlphaAnimation(1.0f , 0.0f );

    private String userPhone;
    private SharedPreferences sharedPreferences;

    private DatabaseReference demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.fullScreenDesign);
        mFragmentBindings = ActivityOrderSuccessBinding.inflate(getLayoutInflater());
        setContentView(mFragmentBindings.getRoot());

        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(800);
        fadeOut.setFillAfter(true);

        sharedPreferences = Util.getSharePrefrences(this);
        userPhone = sharedPreferences.getString("userPhone", "");

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mOrderNumber = bundle.getString("ORDER_NUMBER");
            mFragmentBindings.ordernumberTv.setText("#"+mOrderNumber);
        }


        mFragmentBindings.startingAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { e.printStackTrace(); }

                startFadeInAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });


        mFragmentBindings.countinueBuyingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSuccessActivity.this,HomeScreenActivity.class));
                finish();
            }
        });


        HashMap<String, String> objDat = new HashMap<>();
        demoRef = EcommerceApplication.getFirebaseDBInstance().child("cartProduct").child(userPhone);
        demoRef.setValue(objDat);

    }






    private void startFadeInAnimation() {
        //Removing the Center Lottie Animation
        mFragmentBindings.startingAnimation.setAnimation(fadeOut);
        mFragmentBindings.startingAnimation.setVisibility(View.GONE);


        //Showing other widgets
        mFragmentBindings.successAnimation.setVisibility(View.VISIBLE);
        mFragmentBindings.successAnimation.setAnimation(fadeIn);

        mFragmentBindings.completeTv.setVisibility(View.VISIBLE);
        mFragmentBindings.completeTv.setAnimation(fadeIn);

        mFragmentBindings.purchaseTv.setVisibility(View.VISIBLE);
        mFragmentBindings.purchaseTv.setAnimation(fadeIn);

        mFragmentBindings.ordernumberText.setVisibility(View.VISIBLE);
        mFragmentBindings.ordernumberText.setAnimation(fadeIn);

        mFragmentBindings.ordernumberTv.setVisibility(View.VISIBLE);
        mFragmentBindings.ordernumberTv.setAnimation(fadeIn);

        mFragmentBindings.manageOrderButton.setVisibility(View.VISIBLE);
        mFragmentBindings.manageOrderButton.setAnimation(fadeIn);

        mFragmentBindings.countinueBuyingButton.setVisibility(View.VISIBLE);
        mFragmentBindings.countinueBuyingButton.setAnimation(fadeIn);

        mFragmentBindings.supportTxtTv.setVisibility(View.VISIBLE);
        mFragmentBindings.supportTxtTv.setAnimation(fadeIn);

    }





}
