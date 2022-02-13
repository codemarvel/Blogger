package blogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogger.model.Comment;
import blogger.model.Post;

public interface CommentRepository extends JpaRepository<Comment, String>{
	
	//List<Comment> findByPostId(String id);
	
	
}
