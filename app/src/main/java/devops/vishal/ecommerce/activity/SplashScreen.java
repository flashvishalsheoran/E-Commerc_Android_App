package devops.vishal.ecommerce.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import devops.vishal.ecommerce.R;
import devops.vishal.ecommerce.application.EcommerceApplication;
import devops.vishal.ecommerce.databinding.ActivitySplashScreenBinding;
import devops.vishal.ecommerce.fragment.EntryFragment;
import devops.vishal.ecommerce.fragment.LoginFragment;
import devops.vishal.ecommerce.fragment.SignupFragment;
import devops.vishal.ecommerce.fragment.SplashFragment;
import devops.vishal.ecommerce.interfaces.LoginValidatorListner;
import devops.vishal.ecommerce.models.UserModel;
import devops.vishal.ecommerce.utility.Constants;
import devops.vishal.ecommerce.utility.CustomProgressDialog;
import devops.vishal.ecommerce.utility.Util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity implements LoginValidatorListner {

    private ActivitySplashScreenBinding mBindings;
    private CustomProgressDialog mDialog;
    private FirebaseAuth mAuth;
    private SharedPreferences mPref;
    private Editor mEditor;
    private DatabaseReference demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Settting Up View Bindings
        mBindings = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        View rootView = mBindings.getRoot();
        setTheme(R.style.fullScreenDesign);
        setContentView(rootView);

        mDialog = new CustomProgressDialog(SplashScreen.this);
        mAuth = FirebaseAuth.getInstance();
        mPref = Util.getSharePrefrences(this);
        mEditor = mPref.edit();

        loadFragment(new SplashFragment());

        mPref = Util.getSharePrefrences(SplashScreen.this);
        mEditor = mPref.edit();

    }

    //Function to Load any Fragment
    private void loadFragment(Fragment fragment){
        FragmentTransaction transactionManager = getSupportFragmentManager().beginTransaction();
        transactionManager.replace(R.id.frameContainer , fragment).commit();
    }

    //Implemented Method to check for User Credentials
    @Override
    public void checkUserCredentials(String email, String password) {
        mDialog.show("Checking Credentials"," ");
        mDialog.setCancelable();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    demoRef = EcommerceApplication.getFirebaseDBInstance().child("Users");
                    demoRef.orderByChild("userEmail").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnaps : dataSnapshot.getChildren()){
                                UserModel userModel = new UserModel();
                                userModel = dataSnaps.getValue(UserModel.class);
                                mEditor.putString("userName",userModel.getUserName());
                                mEditor.putString("userEmail",userModel.getUserEmail());
                                mEditor.putString("userPhone",userModel.getUserPhone());
                                mEditor.putString("userUid",userModel.getUserUid());
                                mEditor.apply();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    mEditor.putBoolean(Constants.LOGIN_STATUS,true);
                    mEditor.commit();
                    mDialog.close();
                    Toast.makeText(SplashScreen.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashScreen.this, HomeScreenActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(SplashScreen.this, "No such email or password found.", Toast.LENGTH_SHORT).show();
                    mDialog.close();
                }

            }
        });
    }

    @Override
    public void showLoginOrSignupScreen(String type) {

        if(type.equals(Constants.LOGIN_ACTION)){
            loadFragment(new LoginFragment());
        }
        else {
            loadFragment(new SignupFragment());
        }

    }

    @Override
    public void signUpNewUser(String email, String pass, String phone, String name) {
        mDialog.show("Signing Up....."," Please Wait");
        mDialog.setCancelable();

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    demoRef = EcommerceApplication.getFirebaseDBInstance().child("Users").child(phone);
                    demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            UserModel userModel = new UserModel();
                            userModel.setUserName(name);
                            userModel.setUserEmail(email);
                            userModel.setUserPassword(pass);
                            userModel.setUserPhone(phone);
                            String uid = mAuth.getCurrentUser().getUid();
                            userModel.setUserUid(uid);
                            demoRef.setValue(userModel);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    mDialog.close();
                    Toast.makeText(SplashScreen.this, "Account Successfully Created...Please Login.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SplashScreen.this, "This Email Already Exist.PLease try with Different Email.", Toast.LENGTH_SHORT).show();
                    mDialog.close();
                }
            }
        });


    }

    @Override
    public void fetchValuesFromFirebase() {
        //Function to fetch values from firebase when having Splash Screen and also exit this is no internet connection is there
        //After completing the fetching just load the new fragment login fragment/check for login
        checkAleadyLoggedIn();
    }

    private void checkAleadyLoggedIn(){
        boolean isLogin = mPref.getBoolean(Constants.LOGIN_STATUS,false);

        if(!isLogin) {
            loadFragment(new EntryFragment());
        }
        else{
            startActivity(new Intent(SplashScreen.this, HomeScreenActivity.class));
            finish();
        }
    }


}