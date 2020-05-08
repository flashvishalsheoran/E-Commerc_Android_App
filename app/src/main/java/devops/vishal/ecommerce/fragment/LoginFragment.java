package devops.vishal.ecommerce.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import devops.vishal.ecommerce.databinding.FragmentLoginBinding;
import devops.vishal.ecommerce.interfaces.LoginValidatorListner;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding mFragBindings;
    private final String TAG = "LoginFragment//";
    private LoginValidatorListner mCallBack;

    private String mUserEmail;
    private String mUserPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate :: method called for Login Screen");

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallBack = (LoginValidatorListner) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragBindings = FragmentLoginBinding.inflate(getLayoutInflater());
        View rootView = mFragBindings.getRoot();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mFragBindings.fragmentLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForLoginCredentials();
            }
        });
    }

    private void checkForLoginCredentials() {

        mUserEmail = mFragBindings.loginEt.getText().toString();
        mUserPassword = mFragBindings.passwordEt.getText().toString();

        if(TextUtils.isEmpty(mUserEmail) || TextUtils.isEmpty(mUserPassword)){
            Toast.makeText(getContext(), "Please Enter Valid Email Id and Password...", Toast.LENGTH_SHORT).show();
        }
        else{
                mCallBack.checkUserCredentials(mUserEmail,mUserPassword);
        }

    }
}