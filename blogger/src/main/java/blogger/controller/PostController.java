package blogger.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogger.dto.PostRequest;
import blogger.exception.CustomException;
import blogger.model.Post;
import blogger.model.User;
import blogger.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostService postService;

	@GetMapping
	private List<Post> getAllPosts()
	{
		return postService.getAllPosts();
	}
	@GetMapping("/user/{id}")
	private List<Post> getAllPostByUser(HttpServletRequest req,@PathVariable (name="id")Integer id)
	{
		return postService.getAllPostByUser(req);
	}

	
	  @GetMapping("/{id}")
	  private Post getPostByPostId(@PathVariable (name="id")Integer id) throws CustomException
	  {
	  
	  return postService.getPostById(id);
	  
	  }
	 
	
	  @PostMapping private ResponseEntity<String> createPost(HttpServletRequest req, @RequestBody PostRequest postRequest)
	  {

		  return postService.createPost(req,postRequest);

	  }
	 

	
	  @DeleteMapping("/{id}") 
	  private ResponseEntity<String> deletePost(HttpServletRequest req,@PathVariable (name="id") Integer id)
	  { return postService.deletePost(req,id);
	  }
	 
}
