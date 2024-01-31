package beans;


import exceptions.dataException.DataFieldException;
import exceptions.dataException.TyperEnumerations.FieldsEnum;
import exceptions.dataException.TyperEnumerations.ProblemEnum;

import java.util.regex.Pattern;

public class CredentialsBean {
    private String email;
    private String password;

    private CredentialsBean() {}

    public static CredentialsBean ctorWithSyntaxCheck(String email, String password) throws DataFieldException{
        /*This is a constructor with syntax check and is used by view*/
        CredentialsBean credentialsBean = new CredentialsBean();
        credentialsBean.setEmail(email);
        credentialsBean.setPassword(password);
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

    public void setEmail(String email) throws DataFieldException {
        if(email.isEmpty()){
            throw new DataFieldException(FieldsEnum.Email, ProblemEnum.Empty);
        } else if(isValidEmail(email)){
            this.email = email;
        } else {
            throw new DataFieldException(FieldsEnum.Email, ProblemEnum.NotValid);
        }
    }

    private boolean isValidEmail(String email) {
        final Pattern emailRegex = Pattern.compile("^[a-z\\d_!#$%&’*+/=?`{|}~^.-]+@[a-z\\d.-]+$", Pattern.CASE_INSENSITIVE);

        return emailRegex.matcher(email).matches();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws DataFieldException {
        if(password == null){
            throw new DataFieldException(FieldsEnum.Password,ProblemEnum.IsNull);
        }else if (password.isEmpty()){
            throw new DataFieldException(FieldsEnum.Password,ProblemEnum.Empty);
        } else if(isValidPassword(password)){
            this.password = password;
        } else{
            throw new DataFieldException(FieldsEnum.Password,ProblemEnum.NotValid);
        }
    }


    private boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&+=])(?=\\S+$).{8,45}$",password);
    }
}
