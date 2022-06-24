package SpringCommunityService.CommunityService.domain.comment;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager em;

    public Comment saveJpa(Comment comment){
        em.persist(comment);
        return comment;
    }

    public List<Comment> findCommentByPosting(Posting posting) {
        return em.createQuery("select c from Comment c where c.posting = :posting",Comment.class)
                .setParameter("posting",posting)
                .getResultList();
    }

    public void removeAllCommentJpa(Posting posting) {
        em.createQuery("select c from Comment c where c.posting = :posting",Comment.class)
                .setParameter("posting",posting)
                .getResultList().forEach(comment -> em.remove(comment));
    }

    public Comment findOne(Long commentId) {
        return em.find(Comment.class,commentId);
    }

    public void removeOne(Comment comment) {
        em.remove(comment);
    }

    public List<Comment> findByUser(User loginUser){
        return em.createQuery("select c from Comment c where c.user = :loginUser")
                    .setParameter("loginUser",loginUser).getResultList();
    }
}
