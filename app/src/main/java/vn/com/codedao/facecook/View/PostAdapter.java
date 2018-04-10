package vn.com.codedao.facecook.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.com.codedao.facecook.model.home.mPost;
import vn.com.codedao.facecook.R;

/**
 * Created by utnam on 4/10/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private Context mContext;
    private List<mPost> mPostList;

    public PostAdapter(Context mContext, List<mPost> mPostList) {
        this.mContext = mContext;
        this.mPostList = mPostList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mPost post = mPostList.get(position);
        holder.txtName.setText(post.getName());
        holder.txtTime.setText(post.getTime());
        holder.txtConten.setText(post.getConten());
        //TODO IMG
        //holder.imgAvater.setBackground("");
    }

    @Override
    public int getItemCount() {
        return mPostList == null ? 0 : mPostList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtConten;
        public TextView txtTime;
        public ImageView imgAvater;

        public MyViewHolder(View view) {
            super(view);

            txtName = view.findViewById(R.id.txtName);
            txtConten = view.findViewById(R.id.txtConten);
            txtTime = view.findViewById(R.id.txtTime);
            imgAvater = view.findViewById(R.id.imgAvatar);

        }
    }
}
