package SpringCommunityService.CommunityService.domain.user;

import SpringCommunityService.CommunityService.domain.comment.CommentService;
import SpringCommunityService.CommunityService.domain.follow.FollowService;
import SpringCommunityService.CommunityService.domain.image.ImageService;
import SpringCommunityService.CommunityService.domain.like.LikeService;
import SpringCommunityService.CommunityService.domain.message.MessageService;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingService;
import SpringCommunityService.CommunityService.web.user.EditUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final MessageService messageService;
    private final FollowService followService;
    private final CommentService commentService;
    private final PostingService postingService;
    private final LikeService likeService;
    private final ImageService imageService;

    @Autowired
    public UserService(UserRepository userRepository, LikeService likeService, ImageService imageService, PostingService postingService,MessageService messageService, FollowService followService, CommentService commentService){
        this.userRepository = userRepository;
        this.followService = followService;
        this.likeService = likeService;
        this.imageService = imageService;
        this.postingService = postingService;
        this.commentService = commentService;
        this.messageService = messageService;
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

    @Transactional
    public void dropUser(User loginUser) {
        messageService.removeAllByUser(loginUser);
        followService.removeAllByUser(loginUser);
        List<Posting> findPosting = postingService.findAllByUser(loginUser);
        commentService.removeByUser(loginUser);
        likeService.removeByUser(loginUser);
        findPosting.forEach(p -> {
            imageService.removeByPosting(p);
            commentService.removeAllCommentJpa(p);
            likeService.removeByPosting(p);
            postingService.removeByPosting(p);
        });
        userRepository.dropUser(loginUser);
    }
}
