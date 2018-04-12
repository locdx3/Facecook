package vn.com.codedao.facecook.view.newfeed;


import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.Post;
import vn.com.codedao.facecook.presenter.newfeed.PresenterLogicHandleNewFeed;

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
    private Dialog dialog;
    private CommentAdapter mCommentAdapter;
    private RecyclerView mRecyclerViewComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feed, container, false);
        //Begin Code NamHV4
        mRecyclerView = view.findViewById(R.id.rc_newFeed);
        mPresenterLogicHandleHome = new PresenterLogicHandleNewFeed(this);
        mPresenterLogicHandleHome.getListPost();

        return view;
    }

    @Override
    public void setApdater(List<Post> posts) {
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
        mCommentAdapter = new CommentAdapter(comments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewComment.setLayoutManager(mLayoutManager);
        mRecyclerViewComment.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewComment.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItemComment(int position) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_comment);
        TextView txtDone = dialog.findViewById(R.id.txtDone);
        EditText editText = dialog.findViewById(R.id.edComment);
        mRecyclerViewComment = dialog.findViewById(R.id.rcComment);
        mPresenterLogicHandleHome.getListComment();
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        dialog.show();
    }
}
