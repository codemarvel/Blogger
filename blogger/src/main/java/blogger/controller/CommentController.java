package blogger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blogger.dto.CommentResponse;
import blogger.service.CommentService;

@RestController
@RequestMapping("users/post/{id}/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
//	@GetMapping()
//	public List<CommentResponse> getComments(@RequestParam (name="id") String id)
//	{
//		retu	rn commentService.getCommentsbyPost(id);
//
//	}
	@PostMapping()
	public ResponseEntity<String> postComment(HttpServletRequest req, @RequestParam (name="comment") String desc,@PathVariable (name="id") Integer id)
	{
		return commentService.addComment(req, desc,id);
	}
	
}
