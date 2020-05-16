package devops.vishal.ecommerce.utility;

import android.text.TextUtils;
import com.razorpay.Checkout;
import org.json.JSONObject;
import devops.vishal.ecommerce.R;

public class RazorPayClient {

    public static Checkout getCheckout() {
        Checkout checkout = new Checkout();
        checkout.setFullScreenDisable(true);
        checkout.setImage(R.drawable.ic_box);


        String key = "rzp_test_e1S6RIGAmZDxaF";
        checkout.setKeyID(key);

        return checkout;
    }

    public static JSONObject createJsonRequest(int amoutnInPaisa) {
        /** Amount is always passed in paise
         * E.g -> 500paise = 5.0 rupee
         *
         */
        try {
            JSONObject options = new JSONObject();
            options.put("currency", "INR");
            options.put("amount", amoutnInPaisa * 100);
            //options.put("amount", 1 * 100);
            //auto capture payments payment_capture = 1
            options.put("payment_capture", 1);
            return options;
        } catch (Exception ae) {
            ae.printStackTrace();
            return null;
        }
    }


    public static JSONObject addOrderId(JSONObject options, String email, String number) {
        try {
            //razor pay doesn't allows to add name, description, prefill before creating order
            options.put("name", "SuperZop");
            options.put("description", "Order Charges");
            options.put("prefill", getPreFill(email, number));
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        return options;

    }

    public static JSONObject getPreFill(String email, String number) {
        try {
            final JSONObject preFill = new JSONObject();
            if (TextUtils.isEmpty(email)) {
                preFill.put("email", "vishal.sheoran773@gmail.com");
            } else {
                preFill.put("email", email);
            }
            preFill.put("contact", number);

            return preFill;
        } catch (Exception ae) {
            ae.printStackTrace();
            return null;
        }
    }
}
