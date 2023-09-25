package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CommentDto;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;

@Service
public class CommentServiceImpl {
    
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper mapper;

    public CommentDto createComment(CommentDto dto, int postId){
       Post post =  postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", postId));
        Comment comment = mapper.map(dto, Comment.class);
        comment.setPost(post);
        return mapper.map(commentRepo.save(comment), CommentDto.class);
    }

    public CommentDto updateComment(CommentDto dto, int commentId){

        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", commentId));
        comment.setContent(dto.getContent());
        return mapper.map(commentRepo.save(comment), CommentDto.class);
    }

    public CommentDto getComment(int commentId){
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Commnet", commentId));
        return mapper.map(comment, CommentDto.class);
    }

    public List<CommentDto> getAllComments(){
        List<Comment> comments = commentRepo.findAll();
        return comments.stream().map(comment-> mapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    public String deleteComment(int commentId){
        commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Commnet", commentId));
        commentRepo.deleteById(commentId);
        return "Comment delete successfully";
    }
}
