package com.example.onlinemedicine.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.onlinemedicine.Activity.OtpActivity;
import com.example.onlinemedicine.Apis.URLs;
import com.example.onlinemedicine.Login.Login;
import com.example.onlinemedicine.Models.User;
import com.example.onlinemedicine.R;
import com.example.onlinemedicine.VollySingletonClasses.VolleySingleton;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity {


    EditText Username,UserEmail,UserPhoneNumber,UserPassword,UserConformPassword;
    Button SignupButton;

    private String check,name,email,password,mobile,profile;
    private TextView SendLoginButton,ForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Username=findViewById(R.id.inputUsername);
        UserEmail=findViewById(R.id.inputEmail);
        UserPhoneNumber=findViewById(R.id.inputPhoneNUmber);
        UserPassword=findViewById(R.id.inputPassword);
        UserConformPassword=findViewById(R.id.inputConformPassword);
        SendLoginButton = findViewById(R.id.login_now);
        SignupButton=findViewById(R.id.btnLogin);




        //take user to already login page

        SendLoginButton=findViewById(R.id.login_now);

        SendLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        //take user to forgot pass page

        ForgotPass=findViewById(R.id.forgot_pass);
        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });







        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (validateName() && validateEmail() && validatePass() && validateCnfPass() && validateNumber()){

                    name=Username.getText().toString();
                    email=UserEmail.getText().toString();
                    password=UserConformPassword.getText().toString();
                    mobile=UserPhoneNumber.getText().toString();

                    Intent intent=new Intent(getApplicationContext(), OtpActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("password",password);
                    intent.putExtra("mobile",mobile);
                    startActivity(intent);

                }


            }
        });




    }




    private boolean validateNumber() {

        check = UserPhoneNumber.getText().toString();
        android.util.Log.e("inside number",check.length()+" ");
        if (check.length()>10) {
            return false;
        }else if(check.length()<10){
            return false;
        }
        return true;
    }

    private boolean validateCnfPass() {

        check = UserConformPassword.getText().toString();

        return check.equals(UserPassword.getText().toString());
    }

    private boolean validatePass() {


        check = UserPassword.getText().toString();

        if (check.length() < 4 || check.length() > 20) {
            return false;
        } else if (!check.matches("^[A-za-z0-9@]+")) {
            return false;
        }
        return true;
    }

    private boolean validateEmail() {

        check = UserEmail.getText().toString();

        if (check.length() < 4 || check.length() > 40) {
            return false;
        } else if (!check.matches("^[A-za-z0-9.@]+")) {
            return false;
        } else if (!check.contains("@") || !check.contains(".")) {
            return false;
        }

        return true;
    }

    private boolean validateName() {

        check = Username.getText().toString();

        return !(check.length() < 4 || check.length() > 20);

    }

    //TextWatcher for Name -----------------------------------------------------

    TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                Username.setError("Name Must consist of 4 to 20 characters");
            }
        }

    };

    //TextWatcher for Email -----------------------------------------------------

    TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 40) {
                UserEmail.setError("Email Must consist of 4 to 20 characters");
            } else if (!check.matches("^[A-za-z0-9.@]+")) {
                UserEmail.setError("Only . and @ characters allowed");
            } else if (!check.contains("@") || !check.contains(".")) {
                UserEmail.setError("Enter Valid Email");
            }

        }

    };

    //TextWatcher for pass -----------------------------------------------------

    TextWatcher passWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                UserPassword.setError("Password Must consist of 4 to 20 characters");
            } else if (!check.matches("^[A-za-z0-9@]+")) {
                UserEmail.setError("Only @ special character allowed");
            }
        }

    };

    //TextWatcher for repeat Password -----------------------------------------------------

    TextWatcher cnfpassWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (!check.equals(UserPassword.getText().toString())) {
                UserConformPassword.setError("Both the passwords do not match");
            }
        }

    };


    //TextWatcher for Mobile -----------------------------------------------------

    TextWatcher numberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length()>10) {
                UserPhoneNumber.setError("Number cannot be grated than 10 digits");
            }else if(check.length()<10){
                UserPhoneNumber.setError("Number should be 10 digits");
            }
        }

    };


}