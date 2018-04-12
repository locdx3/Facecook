package vn.com.codedao.facecook.view.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.presenter.login.PresenterLogicHandleLogin;
import vn.com.codedao.facecook.view.home.Home;

public class Login extends AppCompatActivity implements iLoginView, View.OnClickListener {
    private EditText mEdusername, mEdpassword;
    private TextView mTvRegister;
    private Button mBtnlogin;
    private TextInputLayout input_layout_Username, input_layout_Password;
    private ProgressBar pbmainlogin;
    SharedPreferences LuuUser;
    public static final String MyPREFERENCES = "luutruthongtin";
    boolean visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        LuuUser = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getSupportActionBar().hide();
        init();
        action();
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
                validateUsename();
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
                validatePassword();
            }
        });
    }

    private void init() {
        pbmainlogin = findViewById(R.id.pbmainlogin);
        mEdusername = findViewById(R.id.edUsername);
        mEdpassword = findViewById(R.id.edPassword);
        mBtnlogin = findViewById(R.id.btnLogin);
        mTvRegister = findViewById(R.id.tvRegister);
        input_layout_Username = findViewById(R.id.input_layout_Username);
        input_layout_Password = findViewById(R.id.input_layout_Password);
    }

    private boolean validateUsename() {
        if (!validatePhone(mEdusername) && !validateEmail(mEdusername)) {
            input_layout_Username.setError(getString(R.string.err_msg_name));
            requestFocus(mEdusername);
            return false;
        } else {
            input_layout_Username.setErrorEnabled(false);
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

    private boolean validatePassword() {
        if (mEdpassword.getText().toString().trim().isEmpty()) {
            input_layout_Password.setError(getString(R.string.err_msg_password));
            requestFocus(mEdpassword);
            return false;
        } else {
            input_layout_Password.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager
                    .LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
        pbmainlogin.setVisibility(View.GONE);
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String status) {
        pbmainlogin.setVisibility(View.GONE);
        mBtnlogin.setEnabled(true);
        mEdusername.setEnabled(true);
        mEdpassword.setEnabled(true);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (!validateUsename()) {
                    Toast.makeText(Login.this, "UserName is Invalid",
                            Toast.LENGTH_LONG).show();
                } else if (!validatePassword()) {
                    Toast.makeText(Login.this, "PasssWord is Invalid",
                            Toast.LENGTH_LONG).show();
                } else {
                    pbmainlogin.setVisibility(View.VISIBLE);
                    mBtnlogin.setEnabled(false);
                    mEdusername.setEnabled(false);
                    mEdpassword.setEnabled(false);
                    PresenterLogicHandleLogin presenterLogicHandleLogin
                            = new PresenterLogicHandleLogin(this);
                    presenterLogicHandleLogin.checkLogin(mEdusername.getText().toString(),
                            mEdpassword.getText().toString());
                }
                break;
            case R.id.tvRegister:
                // custom dialog
                final Dialog dialog = new Dialog(Login.this, R.style.PauseDialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.setContentView(R.layout.register_layout);

                Button btnRegister = dialog.findViewById(R.id.btnRegister);
                // if button is clicked, close the custom dialog
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
}
