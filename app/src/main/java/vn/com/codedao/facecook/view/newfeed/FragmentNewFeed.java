package vn.com.codedao.facecook.view.newfeed;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.PostList;
import vn.com.codedao.facecook.presenter.newfeed.PresenterLogicHandleNewFeed;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.Comment.CommentActivity;
import vn.com.codedao.facecook.view.post.PostActiviy;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNewFeed extends Fragment implements INewFeed, IOnClickItemNewFeed {


    public FragmentNewFeed() {
        // Required empty public constructor
    }

    private PresenterLogicHandleNewFeed mPresenterLogicHandleHome;
    private PostAdapter mPostAdapter;
    private RecyclerView mRecyclerView;
    private CommentAdapter mCommentAdapter;
    private RecyclerView mRecyclerViewComment;
    PopupWindow mPopWindow;
    TextView mTextView;
    private int mIdUser = 1;
    private String mName = "";
    private String mUrlAvt = "";
    private ImageView mImgSend;
    MessageEvent mEvent;
    private PostList mPostList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feed, container, false);
        //Begin Code NamHV4
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
        mIdUser = Integer.parseInt(sharedPref.getString(Constant.ID, "0"));
        mName = sharedPref.getString(Constant.NICKNAME, "User");
        mRecyclerView = view.findViewById(R.id.rc_newFeed);
        mPresenterLogicHandleHome = new PresenterLogicHandleNewFeed(this);
        mPresenterLogicHandleHome.getListPost();
        return view;
    }

    @Override
    public void setApdater(List<PostList> posts) {
        mPostAdapter = new PostAdapter(getActivity(), posts, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPostAdapter);
        mPostAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapterComment(List<Comment> comments) {
        mCommentAdapter = new CommentAdapter(comments,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewComment.setLayoutManager(mLayoutManager);
        mRecyclerViewComment.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewComment.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void checkInternet(boolean iscontect) {
        if (iscontect){
            Toast.makeText(getActivity(), "Connect Internet Success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Connect Internet Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickItemComment(PostList comments) {
        Intent intent = new Intent(getActivity(), CommentActivity.class);
        intent.putExtra("LIST_COMMENT",comments);
        startActivityForResult(intent,1994);

    }

    @Override
    public void onClickHeader() {
        Intent intent = new Intent(getActivity(), PostActiviy.class);
        intent.putExtra("ID_USER", mIdUser);
        intent.putExtra("NAME_USER", mName);
        startActivityForResult(intent, 1994);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1994) {
            if (resultCode == 1) {
                Toast.makeText(getActivity(), data.getStringExtra("conten"), Toast.LENGTH_SHORT).show();
            }else {
                mPresenterLogicHandleHome.getListPost();
            }

        }
    }

    public void onShowPopup(View v, int p, final int idPost) {

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the custom popup layout
        final View inflatedView = layoutInflater.inflate(R.layout.dialog_comment, null, false);
        // find the ListView in the popup layout
        // ListView listView = (ListView)inflatedView.findViewById(R.id.commentsListView);
        mRecyclerViewComment = inflatedView.findViewById(R.id.rcComment);
        mPresenterLogicHandleHome.getListComment(p);
        TextView txtDone = inflatedView.findViewById(R.id.txtDone);
        final EditText editText = inflatedView.findViewById(R.id.edComment);
        mTextView = inflatedView.findViewById(R.id.txtNocomment);
        mImgSend = inflatedView.findViewById(R.id.imgSend);
        mImgSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Comment comment = new Comment();
                comment.setContent(editText.getText().toString());
                comment.setIdUser(mIdUser);
                comment.setPostid(String.valueOf(idPost));
                mPresenterLogicHandleHome.addComment(comment);
            }
        });


        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        // get device size
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        // set height depends on the device size
        mPopWindow = new PopupWindow(inflatedView, size.x, size.y - 800, true);
        // set a background drawable with rounders corners
        mPopWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.fb_popup_bg));
        mPopWindow.setAnimationStyle(R.style.DialogAnimation_2);
        // make it focusable to show the keyboard to enter in `EditText`
        mPopWindow.setFocusable(true);
        // make it outside touchable to dismiss the popup window
        mPopWindow.setOutsideTouchable(true);

        // show the popup at bottom of the screen and set some margin at bottom ie,
        mPopWindow.showAtLocation(v, Gravity.TOP, 0, 0);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    InputMethodManager inputMgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    inputMgr.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
                    mPopWindow.update();

                }
            }
        });
        editText.requestFocus();
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                InputMethodManager inputMgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMgr.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                inputMgr.showSoftInput(getView(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        mEvent = new MessageEvent();
        mEvent.setmEvent(Constant.SEND_LIST_COMMENT);
        mEvent.setmMRepone(mPostList);
        EventBus.getDefault().post(mEvent);
    }
}
