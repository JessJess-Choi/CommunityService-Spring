package SpringCommunityService.CommunityService.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    @Transactional
    public void testUser(){
        User user1 = new User("1","1","1@1.com","testName1");
        User user2 = new User("2","2","2@2.com","testName2");
        User user3 = new User("3","3","3@3.com","testName3");

        userService.joinJpa(user1);
        userService.joinJpa(user2);
        userService.joinJpa(user3);

        User savedUser = userService.findOne(4L);
        List<User> findAll = userService.findUsers();

        Assertions.assertThat(findAll.size()).isEqualTo(6);
        Assertions.assertThat(savedUser).isEqualTo(user1);
        Assertions.assertThat(savedUser).isNotEqualTo(user2);
    }
}
