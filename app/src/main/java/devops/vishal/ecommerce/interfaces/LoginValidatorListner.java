package devops.vishal.ecommerce.interfaces;

public interface LoginValidatorListner {
    void checkUserCredentials(String email, String password);
    void showLoginOrSignupScreen(String type);
    void signUpNewUser(String email, String pass,String phone,String name);
    void fetchValuesFromFirebase();

}
