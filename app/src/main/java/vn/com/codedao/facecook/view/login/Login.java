package vn.com.codedao.facecook.view.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.presenter.login.PresenterLogicHandleLogin;

public class Login extends AppCompatActivity implements iLoginView, View.OnClickListener {
    private EditText edusername, edpassword;
    private Button mBtnlogin, mBtnRegister;
    private TextInputLayout input_layout_Username, input_layout_Password;
    private ProgressBar pbmainlogin;
    SharedPreferences LuuUser;
    public static final String MyPREFERENCES = "luutruthongtin";
    boolean visible;
    ViewGroup transitionsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        transitionsContainer = findViewById(R.id.transitions_containe);
//        LuuUser = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        init();
        action();
    }

    private void action() {
        mBtnlogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

        edusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateName();
            }
        });

        edpassword.addTextChangedListener(new TextWatcher() {
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
        edusername = findViewById(R.id.txtusername);
        edpassword = findViewById(R.id.txtpassword);
        mBtnlogin = transitionsContainer.findViewById(R.id.btnLogin);
        mBtnRegister = findViewById(R.id.btnRegister);
        input_layout_Username = findViewById(R.id.input_layout_Username);
        input_layout_Password = findViewById(R.id.input_layout_Password);
    }

    private boolean validateName() {
        if (edusername.getText().toString().trim().isEmpty()) {
            input_layout_Username.setError(getString(R.string.err_msg_name));
            requestFocus(edusername);
            return false;
        } else {
            input_layout_Username.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePassword() {
        if (edpassword.getText().toString().trim().isEmpty()) {
            input_layout_Password.setError(getString(R.string.err_msg_password));
            requestFocus(edpassword);
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
            edusername.setEnabled(true);
            edpassword.setEnabled(true);
            edusername.requestFocus();
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
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String status) {
        pbmainlogin.setVisibility(View.GONE);
        mBtnlogin.setEnabled(true);
        edusername.setEnabled(true);
        edpassword.setEnabled(true);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (!validateName()) {
                    Toast.makeText(Login.this, "UserName is Invalid",
                            Toast.LENGTH_LONG).show();
                } else if (!validatePassword()) {
                    Toast.makeText(Login.this, "PasssWord is Invalid",
                            Toast.LENGTH_LONG).show();
                } else {
                    pbmainlogin.setVisibility(View.VISIBLE);
                    mBtnlogin.setEnabled(false);
                    edusername.setEnabled(false);
                    edpassword.setEnabled(false);
                    PresenterLogicHandleLogin presenterLogicHandleLogin
                            = new PresenterLogicHandleLogin(this);
                    presenterLogicHandleLogin.checkLogin(edusername.getText().toString(),
                            edpassword.getText().toString());
                }
                break;
            case R.id.btnRegister:
                break;
            default:
                break;
        }
    }
}
