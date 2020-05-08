package devops.vishal.ecommerce.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import devops.vishal.ecommerce.databinding.FragmentEntryBinding;
import devops.vishal.ecommerce.databinding.FragmentSignupBinding;
import devops.vishal.ecommerce.interfaces.LoginValidatorListner;


public class SignupFragment extends Fragment {

    private FragmentSignupBinding mFragBindings;
    private LoginValidatorListner mCallBacks;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallBacks = (LoginValidatorListner) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragBindings = FragmentSignupBinding.inflate(inflater);
        return mFragBindings.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        mFragBindings.fragmentSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigupNewUser();
            }
        });


    }

    private void sigupNewUser() {

        if(TextUtils.isEmpty(mFragBindings.userNameEt.getText().toString()) ||
                TextUtils.isEmpty(mFragBindings.emailEt.getText().toString()) ||
                TextUtils.isEmpty(mFragBindings.phoneEt.getText().toString()) ||
                TextUtils.isEmpty(mFragBindings.rePassEt.getText().toString()) ||
                TextUtils.isEmpty(mFragBindings.passEt.getText().toString())){
            Toast.makeText(getActivity(), "Please Enter all the values..", Toast.LENGTH_SHORT).show();
        }
        else{
                mCallBacks.signUpNewUser(mFragBindings.emailEt.getText().toString(),
                        mFragBindings.passEt.getText().toString(),
                        mFragBindings.phoneEt.getText().toString(),
                        mFragBindings.userNameEt.getText().toString());
        }
    }
}