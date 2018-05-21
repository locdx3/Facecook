package vn.com.codedao.facecook.view.newfeed;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.newfeed.Like;
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
    private int mUserID;
    private int progess = 0;
    private boolean isUpdateProgessbar = false;


    public PostAdapter(Context mContext, List<PostList> postList, IOnClickItemNewFeed iOnClickItemNewFeed, int UserID) {
        this.mContext = mContext;
        this.postList = postList;
        this.mIOnClickItemNewFeed = iOnClickItemNewFeed;
        this.mUserID = UserID;
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final PostList post = postList.get(position);

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
                final MyViewHolder viewHolder = ((MyViewHolder) holder);
                if (post.isNewAdd()) {
                    viewHolder.relativeConten.setAlpha(0.5f);
                    viewHolder.progressBar.setVisibility(View.VISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofInt(viewHolder.progressBar, "progress", 90);
                    animation.setDuration(1000);
                    animation.start();
                    if (isUpdateProgessbar) {
                        viewHolder.progressBar.setProgress(progess);
                        isUpdateProgessbar = false;
                        viewHolder.progressBar.setVisibility(View.GONE);
                        viewHolder.relativeConten.setAlpha(1);
                        post.setNewAdd(false);
                    }
                }
                viewHolder.txtName.setText(post.getName());
                //((MyViewHolder) holder).txtTime.setText(post.getTime());
                viewHolder.txtConten.setText(post.getContent());
                final int sizelike = post.getLikeList() == null ? 0 : post.getLikeList().size();
                viewHolder.txtLikeCount.setText(sizelike + "");
                int sizeComment = post.getComment() == null ? 0 : post.getComment().size();
                viewHolder.txtCommentCount.setText(sizeComment + " Comment");
                //((MyViewHolder) holder).txtShareCount.setText(post.g() + " Share");
                Picasso.with(mContext).load(post.getUrlavatar()).into(viewHolder.imgAvater);
                viewHolder.lnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIOnClickItemNewFeed.onClickItemComment(post);
                    }
                });
                if (isLike(post)) {
                    viewHolder.imgLike.setPressed(true);
                    viewHolder.btnLike.setTextColor(Color.parseColor("#006DF0"));
                } else {
                    viewHolder.imgLike.setPressed(false);
                    viewHolder.btnLike.setTextColor(Color.parseColor("#BDBDC7"));
                }
                viewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View v) {
                        if (!isLike(post)) {
                            viewHolder.imgLike.setPressed(true);
                            viewHolder.btnLike.setTextColor(Color.parseColor("#006DF0"));
                            Like like = new Like();
                            like.setTypefeel("Like");
                            like.setPostid(post.getPostid());
                            like.setUserid(String.valueOf(mUserID));
                            post.getLikeList().add(like);
                            int likeCount = Integer.parseInt(viewHolder.txtLikeCount.getText().toString());
                            viewHolder.txtLikeCount.setText(likeCount + 1 + "");
                            mIOnClickItemNewFeed.ClickLike(like);
                        } else {
                            for (int i = 0; i < post.getLikeList().size(); i++) {
                                Like like = post.getLikeList().get(i);
                                if (like.getUserid().contains(mUserID + "")) {
                                    post.getLikeList().remove(i);
                                    break;
                                }
                            }
                            viewHolder.imgLike.setPressed(false);
                            viewHolder.btnLike.setTextColor(Color.parseColor("#BDBDC7"));
                            int likeCount = Integer.parseInt(viewHolder.txtLikeCount.getText().toString());
                            if (likeCount > 0) {
                                viewHolder.txtLikeCount.setText(likeCount - 1 + "");
                            }
                        }
                    }
                });

                break;
            case 2:
                final ImgViewHolder imgViewHolder = ((ImgViewHolder) holder);
                MImage mImage = post.getImage().get(0);
                if (post.isNewAdd()) {
                    imgViewHolder.relativeConten.setAlpha(0.5f);
                    imgViewHolder.progressBar.setVisibility(View.VISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofInt(imgViewHolder.progressBar, "progress", 90);
                    Picasso.with(mContext)
                            .load(post.getUriImgPost())
                            .fit()
                            .centerCrop()
                            .into(imgViewHolder.imgPost);
                    animation.setDuration(1000);
                    animation.start();
                    if (isUpdateProgessbar) {
                        imgViewHolder.progressBar.setProgress(progess);
                        isUpdateProgessbar = false;
                        imgViewHolder.progressBar.setVisibility(View.GONE);
                        imgViewHolder.relativeConten.setAlpha(1);
                        post.setNewAdd(false);
                    }
                } else {
                    if (post.getUriImgPost() != null) {
                        Picasso.with(mContext)
                                .load(post.getUriImgPost())
                                .fit()
                                .centerCrop()
                                .into(imgViewHolder.imgPost);
                    } else {

                        Picasso.with(mContext)
                                .load(mImage.getUrlimage())
                                .fit()
                                .centerCrop()
                                .into(imgViewHolder.imgPost);
                    }
                }
                imgViewHolder.txtName.setText(post.getName());
                //((ImgViewHolder) holder).txtTime.setText(post.getTime());
                imgViewHolder.txtConten.setText(post.getContent());
                int sizelike2 = post.getLikeList() == null ? 0 : post.getLikeList().size();
                imgViewHolder.txtLikeCount.setText(sizelike2 + "");
                int sizeComment2 = post.getComment() == null ? 0 : post.getComment().size();
                imgViewHolder.txtCommentCount.setText(sizeComment2 + " Comment");
                // ((ImgViewHolder) holder).txtShareCount.setText(post.getmShareCount() + " Share");
                Picasso.with(mContext)
                        .load(post.getUrlavatar())
                        .into(imgViewHolder.imgAvater);


                imgViewHolder.lnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIOnClickItemNewFeed.onClickItemComment(post);
                    }
                });

                if (isLike(post)) {
                    imgViewHolder.imgLike.setPressed(true);
                    imgViewHolder.btnLike.setTextColor(Color.parseColor("#006DF0"));
                } else {
                    imgViewHolder.imgLike.setPressed(false);
                    imgViewHolder.btnLike.setTextColor(Color.parseColor("#BDBDC7"));
                }
                imgViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View v) {
                        if (!isLike(post)) {
                            imgViewHolder.imgLike.setPressed(true);
                            imgViewHolder.btnLike.setTextColor(Color.parseColor("#006DF0"));
                            Like like = new Like();
                            like.setTypefeel("Like");
                            like.setPostid(post.getPostid());
                            like.setUserid(String.valueOf(mUserID));
                            post.getLikeList().add(like);
                            int likeCount = Integer.parseInt(imgViewHolder.txtLikeCount.getText().toString());
                            imgViewHolder.txtLikeCount.setText(likeCount + 1 + "");
                            mIOnClickItemNewFeed.ClickLike(like);
                        } else {
                            for (int i = 0; i < post.getLikeList().size(); i++) {
                                Like like = post.getLikeList().get(i);
                                if (like.getUserid().contains(mUserID + "")) {
                                    post.getLikeList().remove(i);
                                    break;
                                }
                            }
                            imgViewHolder.imgLike.setPressed(false);
                            imgViewHolder.btnLike.setTextColor(Color.parseColor("#BDBDC7"));
                            int likeCount = Integer.parseInt(imgViewHolder.txtLikeCount.getText().toString());
                            if (likeCount > 0) {
                                imgViewHolder.txtLikeCount.setText(likeCount - 1 + "");
                            }
                        }
                    }
                });
                break;
            default:
                break;
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
        public TextView btnLike;
        public ImageView imgLike;
        public LinearLayout lnLike;
        public RelativeLayout relativeConten;
        public ProgressBar progressBar;

        public MyViewHolder(View view) {
            super(view);
            lnLike = view.findViewById(R.id.linerLike);
            btnLike = view.findViewById(R.id.btnLike);
            imgLike = view.findViewById(R.id.imgLikeClick);
            txtName = view.findViewById(R.id.txtName);
            txtConten = view.findViewById(R.id.txtConten);
            txtTime = view.findViewById(R.id.txtTime);
            imgAvater = view.findViewById(R.id.imgAvatar);
            txtLikeCount = view.findViewById(R.id.txtLikeCount);
            txtCommentCount = view.findViewById(R.id.txtCommentCount);
            txtShareCount = view.findViewById(R.id.txtShareCount);
            imgComment = view.findViewById(R.id.imgComment);
            lnComment = view.findViewById(R.id.lnComment);
            relativeConten = view.findViewById(R.id.relativeConten);
            progressBar = view.findViewById(R.id.progressbarItem);

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
        public TextView btnLike;
        public ImageView imgLike;
        public LinearLayout lnLike;
        public RelativeLayout relativeConten;
        public ProgressBar progressBar;

        public ImgViewHolder(View itemView) {
            super(itemView);
            lnLike = itemView.findViewById(R.id.linerLike);
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
            btnLike = itemView.findViewById(R.id.btnLike);
            imgLike = itemView.findViewById(R.id.imgLikeClick);
            relativeConten = itemView.findViewById(R.id.relativeConten);
            progressBar = itemView.findViewById(R.id.progressbarItem);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root_liner;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            root_liner = itemView.findViewById(R.id.root_liner);

        }
    }

    boolean isLike(PostList postList) {
        if (postList.getLikeList() != null) {
            for (Like like : postList.getLikeList()) {
                if (like.getUserid().equals("" + mUserID)) {
                    return true;
                }
            }
        }
        return false;
    }

    void addPost(PostList post) {
        postList.add(1, post);
        this.notifyDataSetChanged();
    }

    void setProgressBar(int progress) {
        isUpdateProgessbar = true;
        this.progess = progress;
        this.notifyDataSetChanged();
    }

}
