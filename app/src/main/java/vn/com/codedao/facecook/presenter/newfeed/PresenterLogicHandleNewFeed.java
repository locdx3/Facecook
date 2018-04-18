package vn.com.codedao.facecook.presenter.newfeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.Like;
import vn.com.codedao.facecook.model.newfeed.Post;
import vn.com.codedao.facecook.view.newfeed.INewFeed;

/**
 * Created by utnam on 4/10/2018.
 */

public class PresenterLogicHandleNewFeed implements IPresenterHandleNewFeed {

    private INewFeed mINewFeed;

    public PresenterLogicHandleNewFeed(INewFeed mINewFeed) {
        this.mINewFeed = mINewFeed;
    }

    @Override
    public void getListPost() {

        List<Post> posts = new ArrayList<>();
        Post p = new Post();
        p.setName("NamHV4");
        p.setConten("Làm gì cũng nên tin rằng mình làm được thì nhất định sẽ thành công =)))");
        p.setTime("1 hrs");
        p.setImgAvatar("http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-6.jpg");
        p.setmLikeList(likeListFake());
        p.setType(1);
        p.setmCommentList(commentListFake());
        posts.add(p);

        Post p1 = new Post();
        p1.setName("QuyenCV1");
        p1.setConten("Làm gì cũng nên tin rằng mình làm được thì nhất định sẽ thành công =)))");
        p1.setTime("3 hrs");
        p1.setType(2);
        p1.setImgAvatar("http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-12.jpg");
        p1.setmLikeList(likeListFake());
        p1.setmCommentList(commentListFake());
        p1.setUrlImg("https://kenh14cdn.com/2017/17-1511843044269.jpg");
        p1.setmShareCount(89);
        posts.add(p1);

        Post p2 = new Post();
        p2.setName("LocDX3");
        p2.setConten("Hãy cùng ngắm thêm một số hình ảnh của cô bạn nhé");
        p2.setTime("2 hrs");
        p2.setmLikeList(likeListFake());
        p2.setImgAvatar("http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-2.jpg");
        p2.setmCommentList(commentListFake());
        p2.setUrlImg("https://kenh14cdn.com/thumb_w/660/2017/10-1511843120161.jpg");
        p2.setType(2);
        p2.setmShareCount(999);
        posts.add(p2);

        Post p3 = new Post();
        p3.setName("QuyenNH");
        p3.setImgAvatar("https://i.imgur.com/45Q7Qrn.png");
        p3.setConten("Hãy cùng ngắm thêm một số hình ảnh của cô bạn nhé");
        p3.setTime("5 hrs");
        p3.setmLikeList(likeListFake());
        p3.setmCommentList(commentListFake());
        p3.setType(1);
        p3.setmShareCount(10);
        posts.add(p3);

        Post p4 = new Post();
        p4.setName("Hoàng Văn Nam");
        p4.setConten("Ngon Vãi loằn ");
        p4.setImgAvatar("http://anh.24h.com.vn/upload/3-2017/images/2017-07-11/1499758034-hot-girl-bong-chuyen-8.jpg");
        p4.setTime("1 hrs");
        p4.setmLikeList(likeListFake());
        p4.setmCommentList(commentListFake());
        p4.setmShareCount(200);
        p4.setUrlImg("https://s.hswstatic.com/gif/10-food-beautiful-skin-8.jpg");
        p4.setType(2);
        posts.add(p4);


        Post p5 = new Post();
        p5.setName("Đào Xuân Lộc");
        p5.setConten("To reduce dryness and inflammation, count on Omega-3. This agent is found in seafood and if you aren’t getting enough of it, you may experience eczema and psoriasis. ");
        p5.setTime("10 hrs");
        p5.setImgAvatar("http://streaming1.danviet.vn/upload/2-2017/images/2017-06-14/149744919362001-01.jpg");
        p5.setmLikeList(likeListFake());
        p5.setmCommentList(commentListFake());
        p5.setmShareCount(100);
        p5.setUrlImg("https://s.hswstatic.com/gif/10-food-beautiful-skin-10.jpg");
        p5.setType(2);
        posts.add(p5);


        mINewFeed.setApdater(posts);
    }

    @Override
    public void getListComment() {
        List<Comment> comment = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(10); i++) {
            comment.add(new Comment(i, "Vĩ dụ các ngày bình thường trong năm ấy vì anh chưa có lịch cụ thể em ơi " + i, "2018-04-" + i, "2018-04-" + i));
        }
        mINewFeed.setAdapterComment(comment);
    }

    private List<Like> likeListFake() {
        List<Like> likes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(100); i++) {
            likes.add(new Like(i, "2018-04-" + i));
        }
        return likes;
    }

    private List<Comment> commentListFake() {
        List<Comment> comment = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(100); i++) {
            comment.add(new Comment(i, "Vĩ dụ các ngày bình thường trong năm ấy vì anh chưa có lịch cụ thể em ơi " + i, "2018-04-" + i, "2018-04-" + i));
        }
        return comment;
    }
}
