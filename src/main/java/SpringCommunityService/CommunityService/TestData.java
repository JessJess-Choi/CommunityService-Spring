package SpringCommunityService.CommunityService;

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
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class TestData {

    private final UserService userService;
    private final FollowService followService;
    private final MessageService messageService;
    private final PostingService postingService;
    private final LikeService likeService;

    @PostConstruct
    public void initForJpa(){
        User user1 = new User("a","a","1@a.com","testName1");
        User user2 = new User("b","b","2@a.com","testName2");
        User user3 = new User("c","c","3@a.com","testName3");
        Follow follow1 = new Follow(user1,user2);
        Follow follow2 = new Follow(user1,user3);
        Follow follow3 = new Follow(user2,user1);
        Message message1 = new Message(user1,user2,"testMessage",LocalTime.now().minusSeconds(3L));
        Message message2 = new Message(user2,user1,"testMessage2",LocalTime.now().minusSeconds(5L));
        Message message3 = new Message(user1,user2,"testMessage3",LocalTime.now().minusSeconds(10L));
        Message message4 = new Message(user3,user2,"testMessage3",LocalTime.now());
        Posting posting1 = new Posting(user1,"content1",LocalTime.now());
        Posting posting2 = new Posting(user1,"content2",LocalTime.now().minusHours(1L));
        Posting posting3 = new Posting(user2,"content3",LocalTime.now().minusMinutes(30));
        LikeIt like1 = new LikeIt(user2.getName(),posting1);
        LikeIt like2 = new LikeIt(user3.getName(),posting1);
        LikeIt like3 = new LikeIt(user2.getName(),posting2);

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
    }
}
