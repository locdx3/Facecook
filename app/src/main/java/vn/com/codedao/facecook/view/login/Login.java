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
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.presenter.register.PresenterLogicHandleRegister;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.view.home.Home;

public class Login extends AppCompatActivity implements ILoginView, View.OnClickListener {
    public static final String TAG = "Login";
    private EditText mEdusername, mEdpassword;
    private TextView mTvRegister;
    private Button mBtnlogin, btnRegister;
    private TextInputLayout input_layout_Username, input_layout_Password;
    private TextInputLayout input_Username_r;
    private TextInputLayout input_Password_r;
    private TextInputLayout input_Password_again_r;
    private EditText username_r, password_r, password_agian_r;
    private ImageView mImgLogo;
    private TranslateAnimation animate;
    private LinearLayout mLinearWapper;

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
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");


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

    // slide the view from below itself to the current position
    public void slideUp(final View view) {
        view.setVisibility(View.VISIBLE);
        animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(2000);
        animate.setFillAfter(true);
        view.requestLayout();
        view.startAnimation(animate);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLinearWapper.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            Log.d(TAG, "onWindowFocusChanged() called with: hasFocus = [" + hasFocus + "]");
            slideUp(mImgLogo);
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
        mLinearWapper = findViewById(R.id.lnWapper);
        mImgLogo = findViewById(R.id.imgLogo);
        mEdusername = findViewById(R.id.edUsername);
        mEdpassword = findViewById(R.id.edPassword);
        mBtnlogin = findViewById(R.id.btnLogin);
        mTvRegister = findViewById(R.id.tvRegister);
        input_layout_Username = findViewById(R.id.input_layout_Username);
        input_layout_Password = findViewById(R.id.input_layout_Password);

    }

    @Override
    protected void onResume() {
        mBtnlogin.setEnabled(true);
        mEdusername.setEnabled(true);
        mEdpassword.setEnabled(true);
        mEdusername.requestFocus();
        super.onResume();
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
    public void registerSuccess() {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void registerFail(String status) {
        btnRegister.setEnabled(true);
        username_r.setEnabled(true);
        password_r.setEnabled(true);
        password_agian_r.setEnabled(true);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
//                if (!validateUsename(mEdusername, input_layout_Username)) {
//                    Toast.makeText(Login.this, "UserName is Invalid",
//                            Toast.LENGTH_LONG).show();
//                } else if (!validatePassword(mEdpassword, input_layout_Password)) {
//                    Toast.makeText(Login.this, "PasssWord is Invalid",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Log.d(TAG, "onClick() called with: v = [" + v + "]");
//                    mBtnlogin.setEnabled(false);
//                    mEdusername.setEnabled(false);
//                    mEdpassword.setEnabled(false);
//                    PresenterLogicHandleLogin presenterLogicHandleLogin
//                            = new PresenterLogicHandleLogin(this, this);
//                    presenterLogicHandleLogin.checkLogin(mEdusername.getText().toString(),
//                            mEdpassword.getText().toString());
//                }
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
        input_Username_r = dialog.findViewById(R.id.input_Username_register);
        input_Password_r = dialog.findViewById(R.id.input_Password_register);
        input_Password_again_r = dialog.findViewById(R.id.input_Password_agian_register);
        username_r = dialog.findViewById(R.id.username_register);
        password_r = dialog.findViewById(R.id.password_register);
        password_agian_r = dialog.findViewById(R.id.password_again_register);

        btnRegister = dialog.findViewById(R.id.btnRegister);
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
                    btnRegister.setEnabled(false);
                    username_r.setEnabled(false);
                    password_r.setEnabled(false);
                    password_agian_r.setEnabled(false);
                    PresenterLogicHandleRegister presenterLogicHandleRegister
                            = new PresenterLogicHandleRegister(Login.this, Login.this);
                    presenterLogicHandleRegister.register(username_r.getText().toString(),
                            password_r.getText().toString());
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
            requestFocus(pw1);
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