package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public Posting removeJpa(Posting posting){
        em.remove(posting);
        return posting;
    }

    public Posting findByIdJpa(Long id){
        return em.createQuery("select p from Posting p where p.id = :id",Posting.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    public List<Posting> findByContent(String content){
        return em.createQuery("select p from Posting p where p.content like concat('%',:content,'%')",Posting.class)
                .setParameter("content",content).getResultList();
    }

    public List<Posting> findAllJpa(){
        return em.createQuery("select p from Posting p",Posting.class)
                .getResultList();
    }

    public List<Posting> findByUser(User loginUser){
        return em.createQuery("select p from Posting p where p.user  = :user",Posting.class)
                .setParameter("user",loginUser)
                .getResultList();
    }

    public Posting setJpa(Long id, Posting posting){
        Posting update = em.find(Posting.class,id);
        update.setContent(posting.getContent());
        update.setUser(posting.getUser());
        update.setTime(posting.getTime());
        update.setUserName(posting.getUserName());
        em.merge(update);
        return update;
    }

}
