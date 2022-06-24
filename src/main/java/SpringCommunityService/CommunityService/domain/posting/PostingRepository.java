package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
public class PostingRepository {

    @PersistenceContext
    private EntityManager em;

    public Posting saveJpa(Posting posting){
        em.persist(posting);
        return posting;
    }

    public void removeJpa(Posting posting){
        em.remove(posting);
    }

    public Posting findByIdJpa(Long id){
        return em.createQuery("select p from Posting p where p.id = :id",Posting.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    public List<Posting> findByContent(String content){
        List<Posting> ret = em.createQuery("select p from Posting p where p.content like concat('%',:content,'%') order by p.time desc",Posting.class)
                .setParameter("content",content).getResultList();
        ret.forEach(posting -> posting.setImages(em.createQuery("select i from Image i where i.posting = :posting",Image.class)
                    .setParameter("posting",posting).getResultList()));
        return ret;
    }

    public List<Posting> findPostingByUser(String userName){
        List<Posting> ret =  em.createQuery("select p from Posting p where p.user in (select u from User u where u.name like concat('%',:userName,'%') ) order by p.time desc",Posting.class)
                .setParameter("userName",userName)
                .getResultList();
        ret.forEach(posting -> posting.setImages(em.createQuery("select i from Image i where i.posting = :posting",Image.class)
                    .setParameter("posting",posting).getResultList()));

        return ret;
    }

    public List<Posting> findAllJpa(){
        List<Posting> post =  em.createQuery("select p from Posting p order by p.time desc",Posting.class)
                .getResultList();
        post.forEach(posting -> posting.setImages(em.createQuery("select i from Image i where i.posting = :posting", Image.class)
                        .setParameter("posting",posting).getResultList()));
        return post;
    }

    public Posting setJpa(Long id, Posting posting){
        Posting update = em.find(Posting.class,id);
        update.setContent(posting.getContent());
        update.setTime(LocalDateTime.now());
        update.setLikeNumber(posting.getLikeNumber());
        return update;
    }

    public List<Posting> findAllByUser(User loginUser) {
        return em.createQuery("select p from Posting p where p.user = :loginUser")
                .setParameter("loginUser",loginUser).getResultList();
    }
}
