package SpringCommunityService.CommunityService.domain.comment;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment joinJpa(Comment comment){
        commentRepository.saveJpa(comment);
        return comment;
    }

    public List<Comment> findCommentByPosting(Posting posting) {
        return commentRepository.findCommentByPosting(posting);
    }

    @Transactional
    public void removeAllCommentJpa(Posting posting){
        commentRepository.removeAllCommentJpa(posting);
    }

    public Comment findByIdJpa(Long commentId) {
        return commentRepository.findOne(commentId);
    }

    @Transactional
    public void removeJpa(Comment comment) {
        commentRepository.removeOne(comment);
    }
}
