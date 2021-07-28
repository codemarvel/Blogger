package blogger.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import blogger.dto.CommentResponse;
import blogger.model.Comment;
import blogger.repository.CommentRepository;
import blogger.security.JwtTokenProvider;

@Service
public class CommentService {
	
	@Autowired 
	CommentRepository commentRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	 private JwtTokenProvider jwtTokenProvider;
	
	public List<CommentResponse> getCommentsbyPost(String id)
	{
		List<Comment> cmts=commentRepository.findByPostId(id);
		
		
		List<CommentResponse> dtos = cmts
				  .stream()
				  .map(cmt -> modelMapper.map(cmt, CommentResponse.class))
				  .collect(Collectors.toList());
		return dtos;
	}
		public ResponseEntity<String> addComment(HttpServletRequest req, String desc , Integer id)
		{	
			Comment C = new Comment();
			C.setValue(desc);
			C.setUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
			if(C.getUsername()==null)
				return new ResponseEntity<>("Please login First", HttpStatus.UNAUTHORIZED);
			C.setPostId(id);
			commentRepository.save(C);
			return new ResponseEntity<>("Comment successfully added", HttpStatus.OK);
		}
	
}
