package SpringCommunityService.CommunityService.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

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
}
