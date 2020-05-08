package devops.vishal.ecommerce.application;

import android.app.Application;
import android.util.Log;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EcommerceApplication extends Application {

    private static EcommerceApplication mInstance;
    private static StorageReference mStorageRef;
    private static DatabaseReference rootRef;

    @Override
    public void onCreate() {
        super.onCreate();

        //Code to send all the crashes to the Firebase Crashlytics
        try {

            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    ex.printStackTrace();
                    Log.e("" + ex.getLocalizedMessage(), ":\n" + ex.getMessage(), ex);
                    FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
                    FirebaseCrashlytics.getInstance().log(ex.getMessage());
                    System.out.print(ex);
                }
            });
        } catch (Exception ae){
            ae.printStackTrace();
        }


        mInstance = this;
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        mStorageRef = firebaseStorage.getReference();
    }

    public static synchronized DatabaseReference getFirebaseDBInstance(){
        return rootRef;
    }

    public static synchronized EcommerceApplication getInstance() {
        return mInstance;
    }

    public static synchronized StorageReference getStorageReferenceInstance() { return mStorageRef; }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
