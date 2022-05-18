package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.image.ImageService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostingService {

    private final PostingRepository postingRepository;
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public PostingService(PostingRepository postingRepository, UserService userService, ImageService imageService){
        this.postingRepository = postingRepository;
        this.userService = userService;
        this.imageService = imageService;
    }

    @Transactional
    public Long joinJpa(Posting posting){
        postingRepository.saveJpa(posting);
        return posting.getId();
    }

    public List<Posting> findAll(){
        return postingRepository.findAllJpa();
    }

    public List<Posting> findByUser(String user){
        List<Posting> find = new ArrayList<>();
        List<User> findAllUsers = userService.findUserByUserName(user);
        findAllUsers.forEach((u) -> {
            find.addAll(postingRepository.findByUser(u));
        });
        return find;
    }

    public Posting findByIdJpa(Long postingId){
        return postingRepository.findByIdJpa(postingId);
    }

    @Transactional
    public Posting setJpa(Long id,Posting posting){
 //       imageService.removeByPosting(posting);
//        postingRepository.removeJpa(postingRepository.findByIdJpa(id));
  //      postingRepository.saveJpa(posting);
        postingRepository.setJpa(id,posting);
        return posting;
    }

    @Transactional
    public Posting removeJpa(Posting posting){
        return postingRepository.removeJpa(posting);
    }
}
