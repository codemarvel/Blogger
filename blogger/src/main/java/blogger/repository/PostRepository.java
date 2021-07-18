package blogger.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import blogger.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>
{
	List<Post> findByUsername(String username);
	
	Optional<Post> findById(Integer id);
	
	@Transactional
	void deleteById(Integer id);
}
