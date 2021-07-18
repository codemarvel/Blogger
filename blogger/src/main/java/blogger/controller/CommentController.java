package blogger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blogger.dto.CommentResponse;
import blogger.service.CommentService;

@RestController
@RequestMapping("users/post/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@GetMapping()
	public List<CommentResponse> getComments(@RequestParam (name="id") String id)
	{
		return commentService.getCommentsbyPost(id);
		
	}
	
}
