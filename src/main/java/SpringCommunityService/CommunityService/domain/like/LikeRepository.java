package SpringCommunityService.CommunityService.domain.like;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class LikeRepository {

    @PersistenceContext
    private EntityManager em;

    public LikeIt saveJpa(LikeIt likeIt){
        Posting posting = em.find(Posting.class,likeIt.getPosting().getId());
        posting.addLike();
        em.persist(likeIt);
        return likeIt;
    }

    public Optional<LikeIt> findLike(User user, Posting posting) {
        return em.createQuery("select l from LikeIt l where l.posting = :posting and l.userName = :userName",LikeIt.class)
                    .setParameter("userName",user.getName())
                    .setParameter("posting",posting)
                    .getResultList().stream().findAny();
    }

    public void removeJpa(LikeIt likeIt) {
        Posting posting = em.find(Posting.class,likeIt.getPosting().getId());
        posting.removeLike();
        em.remove(likeIt);
    }

    public void updateJpa(LikeIt likeIt) {
        Posting posting = em.find(Posting.class,likeIt.getPosting().getId());
        posting.addLike();
        em.merge(posting);
        em.persist(likeIt);
    }

    public void removeByPostingJpa(Posting posting) {
        em.createQuery("select l from LikeIt l where l.posting = :posting",LikeIt.class)
                                .setParameter("posting",posting)
                .getResultList().forEach(likeIt -> em.remove(likeIt));
    }
}
