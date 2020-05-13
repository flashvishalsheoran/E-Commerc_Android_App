package devops.vishal.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    private Button payBtn;
    private final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());

        payBtn = findViewById(R.id.payBtn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throw new RuntimeException("Test Crash");
            }
        });

    }


    public void startPayment() {
        final Activity activity = this;
        Checkout checkout = new Checkout();

        checkout.setImage(R.drawable.ic_launcher_background);

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Vishal Sheoran");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Toast.makeText(this, "Order Successfully Performed...", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onPaymentSuccess :: Order Is is :  "+paymentData.getOrderId());
        Log.e(TAG, "onPaymentSuccess :: User Phone is :  "+paymentData.getUserContact());
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Log.e(TAG, "onPaymentError :: "+s );
        Toast.makeText(this, "Error In Transaction : Error is  :: "+s ,Toast.LENGTH_SHORT).show();
    }
}
