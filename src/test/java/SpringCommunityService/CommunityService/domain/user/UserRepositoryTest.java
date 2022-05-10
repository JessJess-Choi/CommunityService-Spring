package SpringCommunityService.CommunityService.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

//    UserRepository userRepository = new UserRepository();

    @Autowired UserRepository userRepository;

    @AfterEach
    void clear(){
        userRepository.clearStore();
    }

    @Test
    void saveTest(){
        User user = new User("userA","password","a@a.com","name1");
        User saveUser = userRepository.save(user);
        User findUser = userRepository.findById(1L);

        assertThat(user).isEqualTo(saveUser);
        assertThat(saveUser).isEqualTo(findUser);
        assertThat(findUser.getId()).isEqualTo(1L);
    }

    @Test
    void findAllTest(){

        User userA = new User("userA","password","a@a.com","name1");
        User userB = new User("userB","password","a@a.com","name2");

        userRepository.save(userA);
        userRepository.save(userB);

        List<User> findUserList = userRepository.findAll();
        assertThat(findUserList.size()).isEqualTo(2);
        assertThat(findUserList).contains(userA,userB);
    }

    @Test
    @Transactional
    public void testUser(){
        User user = new User();
        user.setUserId("userA");
        User savedUser = userRepository.save(user);
        User findUser = userRepository.find(savedUser.getId());

        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(findUser.getUserId()).isEqualTo(user.getUserId());
        Assertions.assertThat(findUser).isEqualTo(user);
    }
}
