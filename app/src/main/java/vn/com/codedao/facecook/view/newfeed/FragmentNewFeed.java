package vn.com.codedao.facecook.view.newfeed;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.Like;
import vn.com.codedao.facecook.model.newfeed.MImage;
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
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feed, container, false);
        //Begin Code NamHV4
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
        mIdUser = Integer.parseInt(sharedPref.getString(Constant.ID, "0"));
        mName = sharedPref.getString(Constant.NICKNAME, "User");
        mRecyclerView = view.findViewById(R.id.rc_newFeed);
        mProgressBar =view.findViewById(R.id.pbHeaderProgress);
        mPresenterLogicHandleHome = new PresenterLogicHandleNewFeed(this);
        mPresenterLogicHandleHome.getListPost();
        return view;
    }

    @Override
    public void setApdater(List<PostList> posts) {
        mProgressBar.setVisibility(View.GONE);
        mPostAdapter = new PostAdapter(getActivity(), posts, this, mIdUser);
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
    public void addPost(PostList post) {
        post.setName(mName);
        if (post.getUriImgPost() != null) {
            List<MImage> mImages = new ArrayList<>();
            mImages.add(new MImage());
            post.setImage(mImages);
        } else {
            post.setType(1);
        }


        List<Like> likes = new ArrayList<>();
        post.setLikeList(likes);
        List<Comment> comments = new ArrayList<>();
        post.setComment(comments);
        post.setNewAdd(true);
        mPostAdapter.addPost(post);
    }

    @Override
    public void updateProgessbar() {
        mPostAdapter.setProgressBar(100);
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
    public void ClickLike(Like like) {
        mPresenterLogicHandleHome.addLike(like);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1994) {
            if (resultCode == 1) {
                Toast.makeText(getActivity(), data.getStringExtra("conten"), Toast.LENGTH_SHORT).show();
                PostList post = new PostList();
                String uri = data.getStringExtra("uri");
                if (uri != null) {
                    post.setUriImgPost(uri);
                }
                post.setContent(data.getStringExtra("conten"));
                mPresenterLogicHandleHome.addPost(post);
            }else {
                mPresenterLogicHandleHome.getListPost();
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mEvent = new MessageEvent();
        mEvent.setmEvent(Constant.SEND_LIST_COMMENT);
        mEvent.setmMRepone(mPostList);
        EventBus.getDefault().post(mEvent);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
