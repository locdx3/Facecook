package vn.com.codedao.facecook.view.newfeed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.MImage;
import vn.com.codedao.facecook.model.newfeed.PostList;
import vn.com.codedao.facecook.view.CircleImageView;

/**
 * Created by utnam on 4/10/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<PostList> postList;
    private IOnClickItemNewFeed mIOnClickItemNewFeed;

    public PostAdapter(Context mContext, List<PostList> postList, IOnClickItemNewFeed iOnClickItemNewFeed) {
        this.mContext = mContext;
        this.postList = postList;
        this.mIOnClickItemNewFeed = iOnClickItemNewFeed;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post_header, parent, false);
                return new HeaderViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post, parent, false);
                return new MyViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post_pic, parent, false);
                return new ImgViewHolder(view);
            default:
                break;
        }
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PostList post = postList.get(position);
        if (post != null) {
            switch (holder.getItemViewType()) {
                case 0:
                    ((HeaderViewHolder) holder).root_liner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mIOnClickItemNewFeed.onClickHeader();
                        }
                    });
                    break;
                case 1:
                    ((MyViewHolder) holder).txtName.setText(post.getName());
                    //((MyViewHolder) holder).txtTime.setText(post.getTime());
                    ((MyViewHolder) holder).txtConten.setText(post.getContent());
                    int sizelike = post.getLikeList() == null ? 0 : post.getLikeList().size();
                    ((MyViewHolder) holder).txtLikeCount.setText(sizelike + " like");
                    int sizeComment = post.getComment() == null ? 0 : post.getComment().size();
                    ((MyViewHolder) holder).txtCommentCount.setText(sizeComment + " Comment");
                    //((MyViewHolder) holder).txtShareCount.setText(post.g() + " Share");
                    Picasso.with(mContext).load(post.getUrlavatar()).into(((MyViewHolder) holder).imgAvater);
                    ((MyViewHolder) holder).lnComment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mIOnClickItemNewFeed.onClickItemComment(position);
                        }
                    });

                    break;
                case 2:
                    ((ImgViewHolder) holder).txtName.setText(post.getName());
                    //((ImgViewHolder) holder).txtTime.setText(post.getTime());
                    ((ImgViewHolder) holder).txtConten.setText(post.getContent());
                    int sizelike2 = post.getLikeList() == null ? 0 : post.getLikeList().size();
                    ((ImgViewHolder) holder).txtLikeCount.setText(sizelike2 + " like");
                    int sizeComment2 = post.getComment() == null ? 0 : post.getComment().size();
                    ((ImgViewHolder) holder).txtCommentCount.setText(sizeComment2 + " Comment");
                    // ((ImgViewHolder) holder).txtShareCount.setText(post.getmShareCount() + " Share");
                    Picasso.with(mContext)
                            .load(post.getUrlavatar())
                            .into(((ImgViewHolder) holder).imgAvater);
                    MImage mImage = post.getImage().get(0);
                    Picasso.with(mContext)
                            .load(mImage.getUrlimage())
                            .fit()
                            .centerCrop()
                            .into(((ImgViewHolder) holder).imgPost);
                    ((ImgViewHolder) holder).lnComment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mIOnClickItemNewFeed.onClickItemComment(position);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (postList != null) {
            PostList post = postList.get(position);
            if (post != null) {
                //return TextUtils.isEmpty(post.getUrlImg()) ? 1 : 2;
                return post.getType();
            }
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtConten;
        public TextView txtTime;
        public CircleImageView imgAvater;
        public TextView txtLikeCount;
        public TextView txtShareCount;
        public TextView txtCommentCount;
        public ImageView imgComment;
        public LinearLayout lnComment;

        public MyViewHolder(View view) {
            super(view);

            txtName = view.findViewById(R.id.txtName);
            txtConten = view.findViewById(R.id.txtConten);
            txtTime = view.findViewById(R.id.txtTime);
            imgAvater = view.findViewById(R.id.imgAvatar);
            txtLikeCount = view.findViewById(R.id.txtLikeCount);
            txtCommentCount = view.findViewById(R.id.txtCommentCount);
            txtShareCount = view.findViewById(R.id.txtShareCount);
            imgComment = view.findViewById(R.id.imgComment);
            lnComment = view.findViewById(R.id.lnComment);

        }
    }

    public class ImgViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtConten;
        public TextView txtTime;
        public CircleImageView imgAvater;
        public ImageView imgPost;
        public TextView txtLikeCount;
        public TextView txtShareCount;
        public TextView txtCommentCount;
        public ImageView imgComment;
        public LinearLayout lnComment;

        public ImgViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtConten = itemView.findViewById(R.id.txtConten);
            txtTime = itemView.findViewById(R.id.txtTime);
            imgAvater = itemView.findViewById(R.id.imgAvatar);
            imgPost = itemView.findViewById(R.id.imgPost);
            txtLikeCount = itemView.findViewById(R.id.txtLikeCount);
            txtCommentCount = itemView.findViewById(R.id.txtCommentCount);
            txtShareCount = itemView.findViewById(R.id.txtShareCount);
            imgComment = itemView.findViewById(R.id.imgComment);
            lnComment = itemView.findViewById(R.id.lnComment);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root_liner;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            root_liner = itemView.findViewById(R.id.root_liner);

        }
    }
}
