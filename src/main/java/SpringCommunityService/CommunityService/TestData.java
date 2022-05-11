package SpringCommunityService.CommunityService;

import SpringCommunityService.CommunityService.domain.follow.Follow;
import SpringCommunityService.CommunityService.domain.follow.FollowRepository;
import SpringCommunityService.CommunityService.domain.message.Message;
import SpringCommunityService.CommunityService.domain.message.MessageRepository;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingRepository;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import SpringCommunityService.CommunityService.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class TestData {

//    private final UserRepository userRepository;
//    private final FollowRepository followRepository;
//    private final PostingRepository postingRepository;
//    private final MessageRepository messageRepository;

    private final UserService userService;

    @PostConstruct
    public void initForJpa(){
        User user1 = new User("a","a","1@a.com","testName1");
        User user2 = new User("b","b","2@a.com","testName2");
        User user3 = new User("c","c","3@a.com","testName3");

        userService.joinJpa(user1);
        userService.joinJpa(user2);
        userService.joinJpa(user3);

    }
/*
    @PostConstruct
    public void init(){
        User user1 = new User("1","1","1@a.com","testName1");
        User user2 = new User("2","2","2@a.com","testName2");
        User user3 = new User("3","3","3@a.com","testName3");
        Follow follow1 = new Follow(user1.getLoginId(),user2.getLoginId());
        Follow follow2 = new Follow(user1.getLoginId(),user3.getLoginId());
        Follow follow3 = new Follow(user2.getLoginId(),user1.getLoginId());
//        Posting posting1 = new Posting("1","test","fileId", LocalTime.now());
  //      Posting posting2 = new Posting("1","test posting22","fileId", LocalTime.now().minusHours(10));
    //    Posting posting3 = new Posting("2","test posting33","fileId", LocalTime.now().minusHours(30));
        Message message1 = new Message("1","2","testMessage",LocalTime.now().minusSeconds(3L));
        Message message2 = new Message("2","1","testMessage2",LocalTime.now().minusSeconds(20L));
        Message message3 = new Message("1","2","testMessage3",LocalTime.now().minusSeconds(10L));
        Message message4 = new Message("3","2","testMessage3",LocalTime.now());

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        followRepository.save(follow1);
        followRepository.save(follow2);
        followRepository.save(follow3);
 //       postingRepository.save(posting1);
   //     postingRepository.save(posting2);
     //   postingRepository.save(posting3);
        messageRepository.save(message1);
        messageRepository.save(message2);
        messageRepository.save(message3);
        messageRepository.save(message4);
    }
 */
}
