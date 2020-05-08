package devops.vishal.ecommerce.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import devops.vishal.ecommerce.databinding.*;
import devops.vishal.ecommerce.interfaces.LoginValidatorListner;


public class SplashFragment extends Fragment {

    private FragmentSplashBinding mBindings;
    private LoginValidatorListner mCallbacks;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallbacks = (LoginValidatorListner) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = FragmentSplashBinding.inflate(inflater);
        return mBindings.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        mCallbacks.fetchValuesFromFirebase();
    }
}