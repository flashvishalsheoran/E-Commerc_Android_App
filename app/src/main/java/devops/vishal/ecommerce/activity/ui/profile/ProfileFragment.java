package devops.vishal.ecommerce.activity.ui.profile;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.databinding.FragmentProfileBinding;
import devops.vishal.ecommerce.utility.Util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ProfileFragment extends Fragment {
    private FragmentProfileBinding mBindings;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = FragmentProfileBinding.inflate(inflater);
        View rootView = mBindings.getRoot();

        SharedPreferences sharedPreferences = Util.getSharePrefrences(getContext());

        mBindings.userNameTV.setText(sharedPreferences.getString("userName",""));
        mBindings.userEmailTV.setText(sharedPreferences.getString("userEmail",""));
        mBindings.userPhoneTV.setText(sharedPreferences.getString("userPhone",""));
        return rootView;
    }
}