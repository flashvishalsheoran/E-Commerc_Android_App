package devops.vishal.ecommerce.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devops.vishal.ecommerce.databinding.FragmentEntryBinding;
import devops.vishal.ecommerce.interfaces.LoginValidatorListner;
import devops.vishal.ecommerce.utility.Constants;

public class EntryFragment extends Fragment {

    private FragmentEntryBinding mFragBindings;
    private LoginValidatorListner mCallback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallback = (LoginValidatorListner) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragBindings = FragmentEntryBinding.inflate(inflater);
        View mainView = mFragBindings.getRoot();
        return mainView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mFragBindings.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.showLoginOrSignupScreen(Constants.LOGIN_ACTION);
            }
        });

        mFragBindings.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.showLoginOrSignupScreen(Constants.SIGNUP_ACTION);
            }
        });

    }
}