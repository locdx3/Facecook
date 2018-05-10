package vn.com.codedao.facecook.view.post;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.inputmethodservice.ExtractEditText;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.presenter.post.PresenterLogicHandlePost;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.view.CircleImageView;

import static vn.com.codedao.facecook.view.updateuser.UpdateUserActivity.PICK_IMAGE_REQUEST;
import static vn.com.codedao.facecook.view.updateuser.UpdateUserActivity.READ_EXTERNAL_REQUEST;

public class PostActiviy extends AppCompatActivity implements View.OnClickListener, IPostView {

    private static final String TAG = "PostActiviy";
    private ImageView mImgAddPhoto;
    private PresenterLogicHandlePost mPresenterLogicHandlePost;
    private String mAvatar;
    private TextView mBtnPost;
    private TextView mBtnCancel;
    private ExtractEditText mExtractEditText;
    private TextView mTxtName;
    private CircleImageView mImgAvatar;
    private int mIdUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activiy);
        getSupportActionBar().hide();
        initView();
        mIdUser = getIntent().getIntExtra("ID_USER", 0);
        mPresenterLogicHandlePost = new PresenterLogicHandlePost(this, this, this);

    }

    private void initView() {
        mExtractEditText = findViewById(R.id.edTextConten);
        mTxtName = findViewById(R.id.txtName);
        mImgAvatar = findViewById(R.id.imgAvatar);
        mBtnCancel = findViewById(R.id.btnCancel);
        mBtnCancel.setOnClickListener(this);
        mImgAddPhoto = findViewById(R.id.imgAddPhoto);
        mBtnPost = findViewById(R.id.btnPost);
        mBtnPost.setOnClickListener(this);
        mImgAddPhoto.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgAddPhoto:
                mPresenterLogicHandlePost.AddPhoto();
                break;
            case R.id.btnPost:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mPresenterLogicHandlePost.postOnService(mIdUser, mExtractEditText.getText().toString());
                }
                break;
            case R.id.btnCancel:
                mPresenterLogicHandlePost.cancel();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        if (requestCode != READ_EXTERNAL_REQUEST) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mPresenterLogicHandlePost.pickImg();
        } else {
            Toast.makeText(getApplicationContext(), R.string.permission_denied,
                    Toast.LENGTH_LONG).show();
        }
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
            //mImageViewAvatar.setImageURI(uri);
            mPresenterLogicHandlePost.uploadFiles(uri);
        }
    }


}
