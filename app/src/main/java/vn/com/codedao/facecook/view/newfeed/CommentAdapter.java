package vn.com.codedao.facecook.view.newfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.view.CircleImageView;

/**
 * Created by utnam on 4/12/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    List<Comment> mCommentList;
    private Context mContext;

    public CommentAdapter(List<Comment> mCommentList, Context context) {
        this.mCommentList = mCommentList;
        this.mContext = context;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = mCommentList.get(position);
        holder.txtConten.setText(Html.fromHtml("<strong><font color='#333399'>" + comment.getName() + " </font></strong>" + comment.getComment()));
        Picasso.with(mContext)
                .load(comment.getUrlavatar())
                .into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imgAvatar;
        public TextView txtConten;

        public CommentViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatarComment);
            txtConten = itemView.findViewById(R.id.txtConten);
        }
    }
}
