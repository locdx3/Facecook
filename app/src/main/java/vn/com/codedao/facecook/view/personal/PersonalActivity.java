package vn.com.codedao.facecook.view.personal;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.utils.RealPathUtil;

public class PersonalActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    public static final int PICK_IMAGE = 100;
    private ImageView mImageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_layout);
        getSupportActionBar().hide();
        init();

        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        mImageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() called with: view = [" + view + "]");
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE);
            }
        });
    }

    private void init() {
        mImageViewAvatar = findViewById(R.id.img_avatarUpdate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult()");
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null &&
                data.getData() != null) {
            Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
            // Khi đã chọn xong ảnh thì chúng ta tiến hành upload thôi
            Uri uri = data.getData();
            mImageViewAvatar.setImageURI(uri);
            uploadFiles(uri);
        }
    }

    public void uploadFiles(Uri uri) {
        if (uri == null) return;
        // Hàm call api sẽ mất 1 thời gian nên mình show 1 dialog nhé.
        File file = new File(getRealPathFromURI(uri));
        // Khởi tạo RequestBody từ file đã được chọn
        RequestBody requestBody = RequestBody.create(
                MediaType.parse(getContentResolver().getType(uri)),
                file);
        // Trong retrofit 2 để upload file ta sử dụng Multipart, khai báo 1 MultipartBody.Part
        // uploaded_file là key mà mình đã định nghĩa trong khi khởi tạo server
        MultipartBody.Part filePart =
                MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        Log.d(TAG, "uploadFiles() called with: filePart = [" + filePart + "]");
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "locdx3");
//        APIService mAPIService = ApiUtils.getAPIService();
//        Call call = mAPIService.register(filePart, name);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String mbody = new Gson().toJson(response.body());
//                Log.d(TAG, "onResponse() called with: " + mbody);
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
//            }
//        });
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            Log.d(TAG, "getRealPathFromURI() called with: cursor == null");
            res = contentUri.getPath();
        } else {
            if (cursor.moveToFirst()) {
                Log.d(TAG, "getRealPathFromURI() called with: cursor.moveToFirst() = true");
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                Log.d(TAG, "getRealPathFromURI() called with: column_index = [" + column_index + "]");
                res = cursor.getString(column_index);
                Log.d(TAG, "getRealPathFromURI() called with: res = [" + res + "]");
            }
            Log.d(TAG, "getRealPathFromURI() called with: cursor.moveToFirst() = false");
            cursor.close();
        }
        return res;
    }

    private String uriToFilename(Uri uri) {
        String path = null;
        if (Build.VERSION.SDK_INT < 11) {
            path = RealPathUtil.getRealPathFromURI_BelowAPI11(this, uri);
        } else if (Build.VERSION.SDK_INT < 19) {
            path = RealPathUtil.getRealPathFromURI_API11to18(this, uri);
        } else {
            path = RealPathUtil.getRealPathFromURI_API19(this, uri);
        }

        return path;
    }

}