package SpringCommunityService.CommunityService.domain.like;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingService;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostingService postingService;

    @Autowired
    public LikeService(LikeRepository likeRepository, PostingService postingService){
        this.likeRepository = likeRepository;
        this.postingService = postingService;
    }

    @Transactional
    public Long joinJpa(LikeIt likeIt){
        likeRepository.saveJpa(likeIt);
        return likeIt.getId();
    }

    @Transactional
    public void changeLike(User user, Posting posting) {
        Optional<LikeIt> findLike = likeRepository.findLike(user,posting);
        if(findLike.isEmpty()){
            likeRepository.updateJpa(new LikeIt(user.getName(),posting));
        }
        else{
            likeRepository.removeJpa(findLike.get());
        }
    }
}
