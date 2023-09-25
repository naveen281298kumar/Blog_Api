package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.CommentDto;
import com.blog.service.CommentServiceImpl;

@RestController
@RequestMapping("blog-api/post/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<CommentDto> createCommnet(@RequestBody CommentDto dto, @RequestParam int postId){

        return new ResponseEntity<CommentDto>(service.createComment(dto, postId), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto dto, @RequestParam int commentId){

        return new ResponseEntity<CommentDto>(service.updateComment(dto, commentId), HttpStatus.OK);
    }

    @GetMapping("/getComment")
     public ResponseEntity<CommentDto> getComment( @RequestParam int commentId){

        return new ResponseEntity<CommentDto>(service.getComment(commentId), HttpStatus.OK);
    }

    @GetMapping("/getAllComments")
     public ResponseEntity<List<CommentDto>> getAllComments(){

        return new ResponseEntity<List<CommentDto>>(service.getAllComments(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment")
     public ResponseEntity<String> deleteComment( @RequestParam int commentId){

        return new ResponseEntity<String>(service.deleteComment(commentId), HttpStatus.OK);
    }
    

    
}
