package devops.vishal.ecommerce.utility;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Vishal 15/02/20
 */

public class CustomProgressDialog {
    private Context context;
    private ProgressDialog pDialog;


    public CustomProgressDialog(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
    }

    public void show(String title, String message){
        pDialog.setTitle(title);
        setMessage(message);
        pDialog.show();
    }

    public boolean isShowing(){ return pDialog.isShowing(); }

    public void setProgress(int value){
        pDialog.setProgress(value);
    }

    public void setMessage(String msg){
        pDialog.setMessage(msg);
    }

    public void close(){
        pDialog.dismiss();
    }

    public void setCancelable(){
        pDialog.setCancelable(false);
    }
}
