package vn.com.codedao.facecook.view.updateuser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.login.MUserProfile;
import vn.com.codedao.facecook.presenter.updateuser.PresenterLogicHandleUpdateUser;
import vn.com.codedao.facecook.utils.Constant;

import vn.com.codedao.facecook.view.CircleImageView;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class UpdateUserActivity extends AppCompatActivity implements IViewUpdateUser {
    private final String TAG = this.getClass().getSimpleName();
    public final static int PICK_IMAGE_REQUEST = 1;
    public final static int READ_EXTERNAL_REQUEST = 2;
    private TextView mBtnEdit, mBtnSave;
    private CircleImageView mImageViewAvatar;
    private EditText mEdNickName, mEdUserName, mEdBirthday, mEdFirstName,
            mEdLastName, mEdAddress, mEdHomeTown, mEdPhone, mEdEmail, mEdDescription;
    private RadioGroup mRadioGroup;
    private RadioButton mFemaleRadioButton, mMaleRadioButton;
    private ImageView mBtnCancel;
    private ProgressBar mProgressBar;
    private String sex;
    RequestBody requestBody;
    File file;
    private String mAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        getSupportActionBar().hide();
        init();
        action();
        mProgressBar.setVisibility(View.VISIBLE);
        PresenterLogicHandleUpdateUser updateuser =
                new PresenterLogicHandleUpdateUser(UpdateUserActivity.this);
        updateuser.getUser(getidUser());

        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
    }

    private void action() {
        mImageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() called with: view = [" + view + "]");
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                startActivityForResult(galleryIntent, PICK_IMAGE);
                requestPermionAndPickImage();
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbFemaleUserUpdate) {
                    sex = "female";
                } else if (i == R.id.rbMaleUserUpdate) {
                    sex = "male";
                }
            }
        });
        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnSave.setVisibility(View.VISIBLE);
                mBtnEdit.setVisibility(View.GONE);
                setEnabled(true);
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                PresenterLogicHandleUpdateUser updateuser =
                        new PresenterLogicHandleUpdateUser(UpdateUserActivity.this);
                MUserProfile mUserProfile = new MUserProfile();
                mUserProfile.setUserid(getidUser());
                mUserProfile.setSex(sex);
                mUserProfile.setUrlavatar(mAvatar);
                mUserProfile.setName(mEdNickName.getText().toString());
                mUserProfile.setUsername(mEdUserName.getText().toString());
                mUserProfile.setBirthday(mEdBirthday.getText().toString());
                mUserProfile.setFirstname(mEdFirstName.getText().toString());
                mUserProfile.setLastname(mEdLastName.getText().toString());
                mUserProfile.setPhone(mEdPhone.getText().toString());
                mUserProfile.setAddress(mEdAddress.getText().toString());
                mUserProfile.setHometown(mEdHomeTown.getText().toString());
                mUserProfile.setEmail(mEdEmail.getText().toString());
                mUserProfile.setDescripton(mEdDescription.getText().toString());
                updateuser.saveUpdateUser(mUserProfile, file, requestBody);
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init() {
        mImageViewAvatar = findViewById(R.id.img_avatarUpdate);
        mEdNickName = findViewById(R.id.edNickNameUpdate);
        mEdUserName = findViewById(R.id.edUserNameUpdate);
        mEdBirthday = findViewById(R.id.edBirdayUpdate);
        mEdFirstName = findViewById(R.id.edFirstnameUpdate);
        mEdLastName = findViewById(R.id.edLastnameUpdate);
        mEdAddress = findViewById(R.id.edAddressUpdate);
        mEdHomeTown = findViewById(R.id.edHometownUpdate);
        mEdPhone = findViewById(R.id.edPhoneUpdate);
        mEdEmail = findViewById(R.id.edEmailUpdate);
        mEdDescription = findViewById(R.id.edDescriptionUpdate);
        mRadioGroup = findViewById(R.id.rgsexUserUpdate);
        mMaleRadioButton = findViewById(R.id.rbMaleUserUpdate);
        mFemaleRadioButton = findViewById(R.id.rbFemaleUserUpdate);
        mBtnEdit = findViewById(R.id.tvEditUpdate);
        mBtnSave = findViewById(R.id.tvSaveUpdate);
        mBtnCancel = findViewById(R.id.tvCancelUpdate);
        mProgressBar = findViewById(R.id.pbUpdateUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult()");
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null &&
                data.getData() != null) {
            Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
            // Khi đã chọn xong ảnh thì chúng ta tiến hành upload thôi
            mAvatar = Constant.REQUEST_UPDATE_IMAGE;
            Uri uri = data.getData();
            mImageViewAvatar.setImageURI(uri);
            uploadFiles(uri);
        }
    }

    public void uploadFiles(Uri uri) {
        if (uri == null) return;
        file = new File(getRealPathFromURI(uri));
        // Khởi tạo RequestBody từ file đã được chọn
        requestBody = RequestBody.create(
                MediaType.parse(getContentResolver().getType(uri)),
                file);
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            res = contentUri.getPath();
        } else {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }

    public String getidUser() {
        SharedPreferences prefs = getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
        String id = prefs.getString(Constant.ID, Constant.ID_DEFAULT);
        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermionAndPickImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            pickImage();
            return;
        }
        // Các bạn nhớ request permison cho các máy M trở lên nhé, k là crash ngay đấy.
        int result = ContextCompat.checkSelfPermission(this,
                READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {
            requestPermissions(new String[]{
                    READ_EXTERNAL_STORAGE}, READ_EXTERNAL_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        if (requestCode != READ_EXTERNAL_REQUEST) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {
            Toast.makeText(getApplicationContext(), R.string.permission_denied,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void pickImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void updateUserSuccess(String message) {
        mProgressBar.setVisibility(View.GONE);
        mBtnEdit.setVisibility(View.VISIBLE);
        mBtnSave.setVisibility(View.GONE);
        setEnabled(false);
        Log.d(TAG, "updateUserSuccess() called");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUserFail(String message) {
        mProgressBar.setVisibility(View.GONE);
        Log.d(TAG, "updateUserFail() called with: message = [" + message + "]");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updategetUser(MUserProfile mUserProfile) {
        mProgressBar.setVisibility(View.GONE);
        mAvatar = mUserProfile.getUrlavatar();
        if (mUserProfile.getUrlavatar() != null && !mUserProfile.getUrlavatar().isEmpty()) {
            Picasso.with(this)
                    .load(mUserProfile.getUrlavatar())
                    .placeholder(R.drawable.camera)
                    .error(R.drawable.camera)
                    .into(mImageViewAvatar);
        }
        mEdNickName.setText(mUserProfile.getName());
        mEdUserName.setText(mUserProfile.getUsername());
        mEdBirthday.setText(mUserProfile.getBirthday());
        mEdFirstName.setText(mUserProfile.getFirstname());
        mEdLastName.setText(mUserProfile.getLastname());
        mEdAddress.setText(mUserProfile.getAddress());
        mEdHomeTown.setText(mUserProfile.getHometown());
        mEdPhone.setText(mUserProfile.getPhone());
        mEdEmail.setText(mUserProfile.getEmail());
        if (mUserProfile.getSex().equals("male")) {
            sex = "male";
            mMaleRadioButton.setChecked(true);
            mFemaleRadioButton.setChecked(false);
        } else {
            sex = "female";
            mMaleRadioButton.setChecked(false);
            mFemaleRadioButton.setChecked(true);
        }
        mEdDescription.setText(mUserProfile.getDescripton());
    }


    private void setEnabled(boolean isEnabled) {
        mEdNickName.setEnabled(isEnabled);
        mEdUserName.setEnabled(isEnabled);
        mEdBirthday.setEnabled(isEnabled);
        mEdFirstName.setEnabled(isEnabled);
        mEdLastName.setEnabled(isEnabled);
        mEdAddress.setEnabled(isEnabled);
        mEdHomeTown.setEnabled(isEnabled);
        mEdPhone.setEnabled(isEnabled);
        mEdEmail.setEnabled(isEnabled);
        mEdDescription.setEnabled(isEnabled);
        mFemaleRadioButton.setEnabled(isEnabled);
        mMaleRadioButton.setEnabled(isEnabled);
    }
}