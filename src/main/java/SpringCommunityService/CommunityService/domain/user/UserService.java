package SpringCommunityService.CommunityService.domain.user;

import SpringCommunityService.CommunityService.web.user.EditUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public Long joinJpa(User user){
        validateDuplicateUser(user);
        userRepository.saveJpa(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {

        List<User> findUsersByLoginId = userRepository.findByLoginIdJpa(user.getLoginId());
        List<User> findUsersByEmail = userRepository.findByEmailJpa(user.getEmail());

        if(!findUsersByEmail.isEmpty() || !findUsersByLoginId.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다. 이메일 혹은 아이디 중복");
        }
    }


    public User findOne(Long id){
        return userRepository.findOneJpa(id);
    }

    @Transactional
    public void editUser(User loginUser, EditUserForm editUserForm) {
        userRepository.editUser(loginUser,editUserForm);
    }
}
