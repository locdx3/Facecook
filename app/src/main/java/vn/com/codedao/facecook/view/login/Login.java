package vn.com.codedao.facecook.view.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.presenter.login.PresenterLogicHandleLogin;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.view.home.Home;

public class Login extends AppCompatActivity implements ILoginView, View.OnClickListener {
    public static final String TAG = "Login";
    private EditText mEdusername, mEdpassword;
    private TextView mTvRegister;
    private Button mBtnlogin;
    private TextInputLayout input_layout_Username, input_layout_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        if (checkAuthen()) {
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            this.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else {

        }
        init();
        action();
    }

    private boolean checkAuthen() {
        SharedPreferences prefs = getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
        String id = prefs.getString(Constant.ID, Constant.ID_DEFAULT);
        String token = prefs.getString(Constant.TOKEN, Constant.TOKEN_DEFAULT);
        if (id.equals(Constant.ID_DEFAULT) || token.equals(Constant.TOKEN_DEFAULT)) {
            return false;
        } else {
            return true;
        }
    }

    private void action() {
        mTvRegister.setOnClickListener(this);
        mBtnlogin.setOnClickListener(this);
        mEdusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateUsename(mEdusername, input_layout_Username);
            }
        });

        mEdpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validatePassword(mEdpassword, input_layout_Password);
            }
        });
    }

    private void init() {
        mEdusername = findViewById(R.id.edUsername);
        mEdpassword = findViewById(R.id.edPassword);
        mBtnlogin = findViewById(R.id.btnLogin);
        mTvRegister = findViewById(R.id.tvRegister);
        input_layout_Username = findViewById(R.id.input_layout_Username);
        input_layout_Password = findViewById(R.id.input_layout_Password);
    }

    @Override
    protected void onResume() {
        if (getRole().equalsIgnoreCase("")) {
            mBtnlogin.setEnabled(true);
            mEdusername.setEnabled(true);
            mEdpassword.setEnabled(true);
            mEdusername.requestFocus();
        } else {

        }
        super.onResume();
    }

    public String getRole() {
        SharedPreferences luuUser = getSharedPreferences("luutruthongtin", Context.MODE_PRIVATE);
        return luuUser.getString("role", "");
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String status) {
        mBtnlogin.setEnabled(true);
        mEdusername.setEnabled(true);
        mEdpassword.setEnabled(true);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (!validateUsename(mEdusername, input_layout_Username)) {
                    Toast.makeText(Login.this, "UserName is Invalid",
                            Toast.LENGTH_LONG).show();
                } else if (!validatePassword(mEdpassword, input_layout_Password)) {
                    Toast.makeText(Login.this, "PasssWord is Invalid",
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "onClick() called with: v = [" + v + "]");
                    mBtnlogin.setEnabled(false);
                    mEdusername.setEnabled(false);
                    mEdpassword.setEnabled(false);
                    PresenterLogicHandleLogin presenterLogicHandleLogin
                            = new PresenterLogicHandleLogin(this, this);
                    presenterLogicHandleLogin.checkLogin(mEdusername.getText().toString(),
                            mEdpassword.getText().toString());
                }
                break;
            case R.id.tvRegister:
                // custom dialog
                handleRegister();
                break;
            case R.id.btn_facebook_login:
                handleFbLogin();
                break;
            case R.id.btn_google_login:
                handleGgLogin();
                break;
            default:
                break;
        }
    }

    private void handleGgLogin() {

    }

    private void handleFbLogin() {

    }

    private void handleRegister() {
        final Dialog dialog = new Dialog(Login.this, R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setContentView(R.layout.register_layout);
        final TextInputLayout input_Username_r = dialog.findViewById(R.id.input_Username_register);
        final TextInputLayout input_Password_r = dialog.findViewById(R.id.input_Password_register);
        final TextInputLayout input_Password_again_r = dialog.findViewById(R.id.input_Password_agian_register);
        final EditText username_r = dialog.findViewById(R.id.username_register);
        final EditText password_r = dialog.findViewById(R.id.password_register);
        final EditText password_agian_r = dialog.findViewById(R.id.password_again_register);

        Button btnRegister = dialog.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsename(username_r, input_Username_r)) {
                    Toast.makeText(Login.this, "UserName is Invalid",
                            Toast.LENGTH_LONG).show();
                } else if (!validatePassword(password_r, input_Password_r)) {
                    Toast.makeText(Login.this, "PasssWord is Invalid",
                            Toast.LENGTH_LONG).show();
                } else if (!validatePassword_again(password_agian_r,
                        password_r, input_Password_again_r)) {
                    Toast.makeText(Login.this, "PasssWord agian is Invalid",
                            Toast.LENGTH_LONG).show();
                } else {

                }
            }
        });


        username_r.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateUsename(username_r, input_Username_r);
            }
        });

        password_r.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validatePassword(password_r, input_Password_r);
            }
        });

        password_agian_r.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validatePassword_again(password_agian_r, password_r, input_Password_again_r);
            }
        });

        dialog.show();
    }

    private boolean validateUsename(EditText editText, TextInputLayout textInputLayout) {
        if (!validatePhone(editText) && !validateEmail(editText)) {
            textInputLayout.setError(getString(R.string.err_msg_name));
            requestFocus(editText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone(EditText username) {
        String phone = username.getText().toString().trim();
        if (phone.length() < 10 || !PhoneNumberUtils.isGlobalPhoneNumber(phone)) {
            return false;
        }
        return true;
    }


    private boolean validateEmail(EditText username) {
        String email = username.getText().toString().trim();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private boolean validatePassword(EditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(getString(R.string.err_msg_password));
            requestFocus(editText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword_again(EditText pw1, EditText pw2, TextInputLayout textInputLayout) {
        if (pw2.getText().toString().trim().isEmpty() || !pw1.getText().toString().trim()
                .equals(pw2.getText().toString().trim())) {
            textInputLayout.setError(getString(R.string.err_msg_password_again));
            requestFocus(pw2);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager
                    .LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}