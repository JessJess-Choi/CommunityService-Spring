package SpringCommunityService.CommunityService.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    @AfterEach
    void clear(){
        userRepository.clearStore();
    }

    @Test
    void saveTest(){
        User user = new User("userA","password","a@a.com");
        User saveUser = userRepository.save(user);
        User findUser = userRepository.findById(1L);

        Assertions.assertThat(user).isEqualTo(saveUser);
        Assertions.assertThat(saveUser).isEqualTo(findUser);
        Assertions.assertThat(findUser.getId()).isEqualTo(1L);
    }

    @Test
    void findAllTest(){

        User userA = new User("userA","password","a@a.com");
        User userB = new User("userB","password","a@a.com");

        userRepository.save(userA);
        userRepository.save(userB);

        List<User> findUserList = userRepository.findAll();
        Assertions.assertThat(findUserList.size()).isEqualTo(2);
        Assertions.assertThat(findUserList).contains(userA,userB);
    }
}
