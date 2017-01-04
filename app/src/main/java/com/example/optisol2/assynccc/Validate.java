package com.example.optisol2.assynccc;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by optisol2 on 30-12-2016.
 */

public class Validate {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";

    // Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String PHONE_MSG = "###-#######";
    private static final String PSWD_MSG="passwords doesnot match";


    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }
    public static boolean isPassword(EditText editText,EditText editText2,boolean required){
        return isValidp(editText,editText2,PSWD_MSG,null,required);
    }

    private static boolean isValidp(EditText editText,EditText editText2, String pswdMsg, Object o, boolean required) {
//        if(required && !hasText(editText)) return false;
        if(required && !hasText(editText2)){ return false;}
        if(!TextUtils.equals(editText.getText(), editText2.getText())) {
            editText2.setError(pswdMsg);
            return false;}
        else{

            return true;
        }

    }


    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}
