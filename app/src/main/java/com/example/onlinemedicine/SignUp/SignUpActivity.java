package com.example.onlinemedicine.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
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
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.onlinemedicine.Activity.ForgotPassword;
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
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        Username=findViewById(R.id.inputUsername);
        UserEmail=findViewById(R.id.inputEmail);
        UserPhoneNumber=findViewById(R.id.inputPhoneNUmber);
        UserPassword=findViewById(R.id.inputPassword);
        UserConformPassword=findViewById(R.id.inputConformPassword);
        SendLoginButton = findViewById(R.id.login_now);
        SignupButton=findViewById(R.id.btnLogin);

      /*  awesomeValidation.addValidation(this, R.id.inputUsername, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.inputEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.inputPhoneNUmber, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.inputConformPassword, "^[2-9]{2}[0-9]{8}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.inputPassword, "^[2-9]{2}[0-9]{8}$", R.string.passworderror);
*/

      /*  Username.addTextChangedListener(nameWatcher);
        UserEmail.addTextChangedListener(emailWatcher);
        UserPassword.addTextChangedListener(passWatcher);
        UserConformPassword.addTextChangedListener(cnfpassWatcher);
        UserPhoneNumber.addTextChangedListener(numberWatcher);*/

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

              finish();
              startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
            }
        });







        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



              /*  if (awesomeValidation.validate()) {
                    Log.e("onClick: ", "All is Right.......");*/
                    if (UserPassword.getText().toString().equalsIgnoreCase(UserConformPassword.getText().toString()))
                    {

                        Log.e( "onClick: ","matched" );
                        name=Username.getText().toString();
                        email=UserEmail.getText().toString();
                        password=UserConformPassword.getText().toString();
                        mobile=UserPhoneNumber.getText().toString();


                        register_user(name,email,password,mobile);

                       /* Intent intent=new Intent(getApplicationContext(), OtpActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        intent.putExtra("mobile",mobile);
                        startActivity(intent);*/
                    }
                    else
                    {
                        Log.e( "onClick: ","not matched" );

                        Toast.makeText(SignUpActivity.this, "Not Match Password and Confirm Password....", Toast.LENGTH_SHORT).show();
                    }



               /* else {
                    Toast.makeText(SignUpActivity.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();
                   *//* UserPassword.setError("Password and Confirm Password Not Matched");
                    UserConformPassword.setError("Password and Confirm Password Not Matched");*/
                }



        });








    }

    private void register_user(String username, String email, String password, String phonenumber) {

        final KProgressHUD progressDialog=  KProgressHUD.create(SignUpActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.EMAIL_VALIDATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {




                        try {
                            //converting response to json object
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if (status.equalsIgnoreCase("0")){


                                progressDialog.dismiss();
                                Toasty.success(getApplicationContext(),"Successful",Toast.LENGTH_SHORT,true).show();
                                Intent intent=new Intent(getApplicationContext(), OtpActivity.class);
                                intent.putExtra("name",name);
                                intent.putExtra("email",email);
                                intent.putExtra("password",password);
                                intent.putExtra("mobile",mobile);
                                startActivity(intent);


                            }

                            if (status.equalsIgnoreCase("1")){

                                progressDialog.dismiss();
                                Toasty.error(getApplicationContext(),"User Already Exits..",Toast.LENGTH_SHORT,true).show();
                                // session.createLoginSession(" "," "," "," ");







                            }

                            if (status.equalsIgnoreCase("2")){


                                progressDialog.dismiss();
                                Toasty.error(getApplicationContext(),"User Already Exits",Toast.LENGTH_SHORT,true).show();



                            }




                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_email", email);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }




/*
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
*/

/*
    private boolean validateCnfPass() {

        check = UserConformPassword.getText().toString();

        return check.equals(UserPassword.getText().toString());
    }
*/

/*
    private boolean validatePass() {


        check = UserPassword.getText().toString();

        if (check.length() < 4 || check.length() > 20) {
            return false;
        } else if (!check.matches("^[A-za-z0-9@]+")) {
            return false;
        }
        return true;
    }
*/

/*
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
*/

/*
    private boolean validateName() {

        check = Username.getText().toString();

        return !(check.length() < 4 || check.length() > 20);

    }
*/

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