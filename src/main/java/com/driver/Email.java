package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        boolean isUpperCase = false;
        boolean isLowerCase = false;
        boolean isNumber = false;
        boolean isSpecialCharacter = false;
        boolean space = false;
        if(oldPassword.equals(password) && newPassword.length()>=8){
            for(int i = 0;i<newPassword.length();i++){
                char ch = newPassword.charAt(i);

                if(Character.isUpperCase(ch)){
                    isUpperCase = true;
                }
                else if(Character.isLowerCase(ch)){
                    isLowerCase = true;
                }
                else if(Character.isDigit(ch)){
                    isNumber = true;
                }
                else if(Character.isWhitespace(ch)){
                    space = true;
                }
                else{
                    isSpecialCharacter = true;
                }

                if(isUpperCase == true && isLowerCase == true && isNumber == true & isSpecialCharacter == true && space ==false){
                    password = newPassword;
                    break;
                }

            }
        }
    }
}