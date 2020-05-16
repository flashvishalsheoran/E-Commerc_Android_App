package devops.vishal.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.Random;

import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.databinding.ActivityBuyProductBinding;
import devops.vishal.ecommerce.utility.RazorPayClient;
import devops.vishal.ecommerce.utility.Util;

public class BuyProductActivity extends AppCompatActivity implements PaymentResultListener {

    private ActivityBuyProductBinding mBindings;
    private String mUserId;
    private String mProductPrice;
    private String mOrderNumber;
    private String mUserNumber;
    private final String RAZORPAY_API_KEY = "rzp_test_e1S6RIGAmZDxaF";

    private boolean isCodChecked;
    private boolean isCardChecked;
    private boolean isPayOnlineChecked;

    private String PAYMENT_MODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindings = ActivityBuyProductBinding.inflate(getLayoutInflater());
        setTheme(R.style.fullScreenDesign);
        setContentView(mBindings.getRoot());
        Checkout.preload(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mUserId = bundle.getString("USER_PHONE");
            mProductPrice = bundle.getString("TOTAL_AMOUNT");
        }



        mBindings.totalAmtTv.setText(mProductPrice);
        mBindings.addressTv.setText("Flat No.2, Golkonda Palace, Delhi");

        mBindings.codLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isCodChecked){
                    mBindings.codLayout.setBackground(getResources().getDrawable(R.drawable.red_outline_background));
                    mBindings.codTv.setTextColor(Color.parseColor("#dc143c"));

                    mBindings.payOnlineTv.setTextColor(Color.parseColor("#a8a8a8"));
                    mBindings.payOnlineLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));

                    mBindings.cardTv.setTextColor(Color.parseColor("#a8a8a8"));
                    mBindings.cardLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));

                    PAYMENT_MODE = "cash";
                    isCodChecked = true;
                    isCardChecked = false;
                    isPayOnlineChecked = false;
                } else{
                    mBindings.codLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));
                    mBindings.codTv.setTextColor(Color.parseColor("#a8a8a8"));
                    isCodChecked = false;
                }

            }
        });


        mBindings.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isCardChecked){
                    mBindings.cardLayout.setBackground(getResources().getDrawable(R.drawable.red_outline_background));
                    mBindings.cardTv.setTextColor(Color.parseColor("#dc143c"));

                    mBindings.payOnlineTv.setTextColor(Color.parseColor("#a8a8a8"));
                    mBindings.payOnlineLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));

                    mBindings.codTv.setTextColor(Color.parseColor("#a8a8a8"));
                    mBindings.codLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));

                    PAYMENT_MODE = "card";
                    isCardChecked = true;
                    isCardChecked = false;
                    isPayOnlineChecked = false;
                } else{
                    mBindings.cardLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));
                    mBindings.cardTv.setTextColor(Color.parseColor("#a8a8a8"));
                    isCardChecked = false;
                }
            }
        });


        mBindings.payOnlineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPayOnlineChecked){
                    mBindings.payOnlineLayout.setBackground(getResources().getDrawable(R.drawable.red_outline_background));
                    mBindings.payOnlineTv.setTextColor(Color.parseColor("#dc143c"));

                    mBindings.cardTv.setTextColor(Color.parseColor("#a8a8a8"));
                    mBindings.cardLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));

                    mBindings.codTv.setTextColor(Color.parseColor("#a8a8a8"));
                    mBindings.codLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));

                    PAYMENT_MODE = "online";
                    isPayOnlineChecked = true;
                    isCardChecked = false;
                    isPayOnlineChecked = false;
                } else{
                    mBindings.payOnlineLayout.setBackground(getResources().getDrawable(R.drawable.rectangle_white_backgrond));
                    mBindings.payOnlineTv.setTextColor(Color.parseColor("#a8a8a8"));
                    isPayOnlineChecked = false;
                }
            }
        });



        mBindings.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(PAYMENT_MODE)){

                    mOrderNumber = getRandomNumberString();
                    if(PAYMENT_MODE.equalsIgnoreCase("online")){
                        startPayment(mProductPrice,mOrderNumber);
                    } else{
                        Intent successIntent = new Intent(BuyProductActivity.this,OrderSuccessActivity.class);
                        successIntent.putExtra("ORDER_NUMBER",mOrderNumber);
                        successIntent.putExtra("PHONE_NUMBER",mUserNumber);
                        startActivity(new Intent(successIntent));
                        finish();
                    }

                } else{
                    Toast.makeText(BuyProductActivity.this, "Please select a payment mode first...", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    //razor pay code should be in activity
    public void startPayment(String amount, String orderId) {
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_LONG).show();
            return;
        }

        if (amount.charAt(0) == '0') {
            Toast.makeText(this, "Amount should be greater than 0", Toast.LENGTH_LONG).show();
            return;
        }

        //initiate checkout here
        final Checkout checkout = RazorPayClient.getCheckout();

        int paybleAmount = Math.round(Float.parseFloat(mProductPrice));

        //creting json object with values like amount, order number and prefill data
        JSONObject options = RazorPayClient.createJsonRequest(paybleAmount);
        RazorPayClient.addOrderId(options, "vishal.sheoran773@gmail.com",mUserNumber);

        checkout.open(BuyProductActivity.this, options);
    }




    @Override
    public void onPaymentSuccess(String s) {
        //Success Activity
        Intent successIntent = new Intent(BuyProductActivity.this,OrderSuccessActivity.class);
        successIntent.putExtra("ORDER_NUMBER",mOrderNumber);
        successIntent.putExtra("PHONE_NUMBER",mUserNumber);
        startActivity(new Intent(successIntent));
        finish();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Online payment cancelled. If any amount is deduced it will be refunded soon...", Toast.LENGTH_SHORT).show();
    }



    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
