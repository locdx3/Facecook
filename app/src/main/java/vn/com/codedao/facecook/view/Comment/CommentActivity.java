package vn.com.codedao.facecook.view.Comment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.PostList;
import vn.com.codedao.facecook.presenter.comment.PresenterLogicHandleComment;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.view.newfeed.CommentAdapter;

public class CommentActivity extends AppCompatActivity implements ICommentActivity, View.OnClickListener {
    private static final String TAG = "CommentActivity";
    private PresenterLogicHandleComment mPresenterLogicHandleComment;
    private RecyclerView mRecyclerView;
    private CommentAdapter mCommentAdapter;
    private PostList mPostLists;
    private EditText mEditText;
    private ImageView mImageViewSend;
    private TextView mBtnDone;
    private int mIdUser;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        SharedPreferences sharedPref = getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
        mIdUser = Integer.parseInt(sharedPref.getString(Constant.ID, "0"));
        mName = sharedPref.getString(Constant.NICKNAME, "User");
        getSupportActionBar().hide();
        mPresenterLogicHandleComment = new PresenterLogicHandleComment(this);
        Intent intent = getIntent();
        mPostLists = (PostList) intent.getSerializableExtra("LIST_COMMENT");
        mPresenterLogicHandleComment.getListComment(mPostLists);
        mEditText.requestFocus();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rcComment);
        mEditText = findViewById(R.id.edComment);
        mImageViewSend = findViewById(R.id.imgSend);
        mBtnDone = findViewById(R.id.txtDone);
        mBtnDone.setOnClickListener(this);
        mImageViewSend.setOnClickListener(this);
    }

    @Override
    public void setAdapterComemnt(PostList postList) {
        Log.d(TAG, "setAdapterComemnt() called with: postList = [" + postList.getComment().size() + "]");
        mCommentAdapter = new CommentAdapter(postList.getComment(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtDone:
                setResult(2);
                finish();
                break;
            case R.id.imgSend:
                Comment comment = new Comment();
                comment.setIdUser(mIdUser);
                comment.setContent(mEditText.getText().toString());
                comment.setPostid(mPostLists.getPostid());
                comment.setName(mName);
                mCommentAdapter.addComentTolocal(comment);
                mPresenterLogicHandleComment.addCommentToSevice(comment);
                mEditText.setText("");
                break;
            default:
                break;
        }
    }
}
