package blogger.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import blogger.dto.PostRequest;
import blogger.exception.CustomException;
import blogger.model.Post;
import blogger.repository.PostRepository;
import blogger.security.JwtTokenProvider;

@Service
public class PostService {
	
	  @Autowired
	  private JwtTokenProvider jwtTokenProvider;

	  @Autowired
	  private AuthenticationManager authenticationManager;
	  
	  @Autowired
	  private PostRepository postRepository;
	  
	  public List<Post> getAllPost(HttpServletRequest req)
	  {
		  
		  return postRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
		 
	  }

		
		  public Post getPostById(Integer id) throws CustomException {
		  
			  
			  Optional<Post> res= postRepository.findById(id);
			  
			  if(res==null)
			  {
				  throw new CustomException("Not Found",HttpStatus.NOT_FOUND);
			  }
			  return res.get();
		  }
		 
	  public String createPost(HttpServletRequest req,PostRequest post)
	  {
		  Post p = new Post();
		  p.setDescription(post.getDescription());
		  p.setUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
		  return postRepository.save(p).toString();
		  
		  
	  }
		
		  public ResponseEntity<String> deletePost(HttpServletRequest req,Integer id) 
		  {
			  Optional<Post> p = postRepository.findById(id);
			  
			  if(p.isEmpty()||p==null)
			  {
				  return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
			  }
			  String user = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));
			  
			  if(p.get().getUsername()!=user)
			  {
				  return new ResponseEntity<>("You are not authorized ", HttpStatus.UNAUTHORIZED);
				  
			  }
			  postRepository.deleteById(id);
			  return new ResponseEntity<>("Post succesfully deleted ", HttpStatus.OK);
	  
		  }
		 
}
