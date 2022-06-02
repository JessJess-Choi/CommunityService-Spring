package SpringCommunityService.CommunityService;

import SpringCommunityService.CommunityService.domain.comment.Comment;
import SpringCommunityService.CommunityService.domain.comment.CommentService;
import SpringCommunityService.CommunityService.domain.follow.Follow;
import SpringCommunityService.CommunityService.domain.follow.FollowService;
import SpringCommunityService.CommunityService.domain.like.LikeIt;
import SpringCommunityService.CommunityService.domain.like.LikeService;
import SpringCommunityService.CommunityService.domain.message.Message;
import SpringCommunityService.CommunityService.domain.message.MessageService;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class TestData {

    private final UserService userService;
    private final FollowService followService;
    private final MessageService messageService;
    private final PostingService postingService;
    private final LikeService likeService;
    private final CommentService commentService;

    @PostConstruct
    public void initForJpa(){
        User user1 = new User("a","a","1@a.com","testName1");
        User user2 = new User("b","b","2@a.com","testName2");
        User user3 = new User("c","c","3@a.com","testName3");
        Follow follow1 = new Follow(user1,user2);
        Follow follow2 = new Follow(user1,user3);
        Follow follow3 = new Follow(user2,user1);
        Message message1 = new Message(user1,user2,"testMessage", LocalDateTime.now().minusSeconds(3L));
        Message message2 = new Message(user2,user1,"testMessage2",LocalDateTime.now().minusSeconds(5L));
        Message message3 = new Message(user1,user2,"testMessage3",LocalDateTime.now().minusSeconds(10L));
        Message message4 = new Message(user3,user2,"testMessage3",LocalDateTime.now());
        Posting posting1 = new Posting(user1,"title1","content1",LocalDateTime.now());
        Posting posting2 = new Posting(user1,"title2","content2",LocalDateTime.now().minusHours(1L));
        Posting posting3 = new Posting(user2,"title3","content3",LocalDateTime.now().minusMinutes(30));
        LikeIt like1 = new LikeIt(user2,posting1);
        LikeIt like2 = new LikeIt(user3,posting1);
        LikeIt like3 = new LikeIt(user2,posting2);
        Comment comment1 = new Comment(user2,posting1,"test comment1",LocalDateTime.now().plusSeconds(30));
        Comment comment2 = new Comment(user1,posting1,"test comment2",LocalDateTime.now().plusSeconds(40));
        Comment comment3 = new Comment(user2,posting1,"test comment3",LocalDateTime.now().plusSeconds(50));
        Comment comment4 = new Comment(user3,posting1,"test comment4",LocalDateTime.now().plusSeconds(60));


        userService.joinJpa(user1);
        userService.joinJpa(user2);
        userService.joinJpa(user3);
        followService.joinJpa(follow1);
        followService.joinJpa(follow2);
        followService.joinJpa(follow3);
        messageService.joinJpa(message1);
        messageService.joinJpa(message2);
        messageService.joinJpa(message3);
        messageService.joinJpa(message4);
        postingService.joinJpa(posting1);
        postingService.joinJpa(posting2);
        postingService.joinJpa(posting3);
        likeService.joinJpa(like1);
        likeService.joinJpa(like2);
        likeService.joinJpa(like3);
        commentService.joinJpa(comment1);
        commentService.joinJpa(comment2);
        commentService.joinJpa(comment3);
        commentService.joinJpa(comment4);
    }
}
