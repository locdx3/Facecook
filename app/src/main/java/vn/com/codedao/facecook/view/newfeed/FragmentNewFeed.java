package vn.com.codedao.facecook.view.newfeed;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.mPost;
import vn.com.codedao.facecook.presenter.newfeed.PresenterLogicHandleNewFeed;
import vn.com.codedao.facecook.view.PostAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNewFeed extends Fragment implements INewFeed {


    public FragmentNewFeed() {
        // Required empty public constructor
    }

    private PresenterLogicHandleNewFeed mPresenterLogicHandleHome;
    private PostAdapter mPostAdapter;
    private RecyclerView mRecyclerView;

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
    public void setApdater(List<mPost> posts) {
        mPostAdapter = new PostAdapter(getActivity(), posts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(50));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPostAdapter);
        mPostAdapter.notifyDataSetChanged();
    }
}
