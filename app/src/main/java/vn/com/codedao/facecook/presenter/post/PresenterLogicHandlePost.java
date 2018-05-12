package vn.com.codedao.facecook.presenter.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import vn.com.codedao.facecook.apiservice.ApiConnect;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.post.IPostView;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static vn.com.codedao.facecook.view.updateuser.UpdateUserActivity.PICK_IMAGE_REQUEST;
import static vn.com.codedao.facecook.view.updateuser.UpdateUserActivity.READ_EXTERNAL_REQUEST;

public class PresenterLogicHandlePost implements IPresenterLogicHandlePost {
    private static final String TAG = "PresenterLogicHandlePost" ;
    private IPostView mIPostView;
    private Activity activity;
    private Context mContext;
    private File mFile;
    private RequestBody mRequestBody;

    public PresenterLogicHandlePost(IPostView mIPostView, Activity activity, Context context) {
        EventBus.getDefault().register(this);
        this.mIPostView = mIPostView;
        this.activity = activity;
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void AddPhoto() {
        requestPermionAndPickImage();
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmEvent()) {
            case MessageEvent.CONNECT_INTERNET_OK:

                break;
            case MessageEvent.CONNECT_INTERNET_FAIL:

                break;
            case Constant.ADD_POST_FINISH:
                Log.d(TAG, "onMessageEvent() called with: event = [" + event + "]");
                event.setmEvent(Constant.HANDLE_ADD_POST_FINISH);
                EventBus.getDefault().post(event);
                //mIPostView.finishActivity();
                break;
            case Constant.ADD_POST_FAIL:
                Toast.makeText(activity, "Post fail", Toast.LENGTH_SHORT).show();
            break;
            default:
                break;
        }
    }

    @Override
    public void pickImg() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void uploadFiles(Uri uri) {
        if (uri == null) return;
        mFile = new File(getRealPathFromURI(uri));
        // Khởi tạo RequestBody từ file đã được chọn
        mRequestBody = RequestBody.create(
                MediaType.parse(mContext.getContentResolver().getType(uri)),
                mFile);
    }


    @Override
    public void postOnService(int idUser, String conten) {
        ApiConnect apiConnect = new ApiConnect();
        String request;
        if (mRequestBody == null) {
            request = "#";
        } else {
            request = "request_post_image";
        }
        apiConnect.AddPost(idUser, conten, request, mFile, mRequestBody);
    }


    @Override
    public void cancel() {
        activity.finish();
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(contentUri, proj, null, null, null);
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


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermionAndPickImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            pickImg();
            return;
        }
        // Các bạn nhớ request permison cho các máy M trở lên nhé, k là crash ngay đấy.
        int result = ContextCompat.checkSelfPermission(mContext,
                READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            pickImg();
        } else {
            activity.requestPermissions(new String[]{
                    READ_EXTERNAL_STORAGE}, READ_EXTERNAL_REQUEST);
        }
    }
}
