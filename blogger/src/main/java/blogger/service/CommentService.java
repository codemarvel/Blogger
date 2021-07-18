package blogger.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogger.dto.CommentResponse;
import blogger.model.Comment;
import blogger.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired 
	CommentRepository commentRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public List<CommentResponse> getCommentsbyPost(String id)
	{
		List<Comment> cmts=commentRepository.findByPostId(id);
		
		
		List<CommentResponse> dtos = cmts
				  .stream()
				  .map(cmt -> modelMapper.map(cmt, CommentResponse.class))
				  .collect(Collectors.toList());
		return dtos;
	}
		
	
}
