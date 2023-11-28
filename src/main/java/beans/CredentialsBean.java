package beans;

/*import exceptions.invalid_data_exception.EmptyFieldsException;
import exceptions.invalid_data_exception.InvalidCredentialsException;*/

import java.util.regex.Pattern;

public class CredentialsBean {
    private String email;
    private String password;

    private CredentialsBean() {}

    public static CredentialsBean ctorWithSyntaxCheck(String email, String password) /*throws InvalidCredentialsException, EmptyFieldsException*/ {
        /*This is a constructor with syntax check and is used by view*/
        CredentialsBean credentialsBean = new CredentialsBean();
        credentialsBean.setEmail(email);

        if (password != null) {
            credentialsBean.setPassword(password);
        } else {
            System.out.println("Password field is null or empty.");
            // Handle the case where password is null (optional: throw an exception or set a default password)
        }

        return credentialsBean;
    }


    public static CredentialsBean ctorWithoutSyntaxCheck(String email, String password) {
        /*This is a constructor without syntax check and is used by controller*/
        CredentialsBean credentialsBean = new CredentialsBean();
        credentialsBean.email = email;
        credentialsBean.password = password;

        return credentialsBean;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) /*throws InvalidCredentialsException, EmptyFieldsException */{

        if(email.isEmpty()){
            System.out.println("Email field empty.");
            /*throw new EmptyFieldsException();*/
        } else if(isValidEmail(email)){
            this.email = email;
        } else {
            /*throw new InvalidCredentialsException();*/
            System.out.println("Email field invalid.");
        }
    }

    private boolean isValidEmail(String email) {
        final Pattern emailRegex = Pattern.compile("^[a-z\\d_!#$%&’*+/=?`{|}~^.-]+@[a-z\\d.-]+$", Pattern.CASE_INSENSITIVE);

        return emailRegex.matcher(email).matches();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) /*throws InvalidCredentialsException, EmptyFieldsException*/ {
        System.out.println("Password" + this.password);
        if(password == null || password.isEmpty()){
            System.out.println("Password field is null or empty.");
            /*throw new EmptyFieldsException();*/
        } else if(isValidPassword(password)){
            this.password = password;
            System.out.println(this.password);
        } else{
            /*throw new InvalidCredentialsException();*/
            System.out.println("Password field is invalid.");
        }
    }


    private boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&+=])(?=\\S+$).{8,45}$",password);
    }
}
