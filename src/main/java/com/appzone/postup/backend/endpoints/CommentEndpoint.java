/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appzone.postup.backend.endpoints;

import com.appzone.postup.backend.json.PostCommentJson;
import com.appzone.postup.backend.model.CommentEntity;
import com.appzone.postup.backend.model.CommentEntity.Status;
import com.appzone.postup.backend.model.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohamed Morsy
 */
@RestController
public class CommentEndpoint implements PostupEndpoint {

    @Autowired
    private CommentRepository commentRepository;        
    
    @PostMapping(value = "/comment")
    public CommentEntity postComment(@RequestBody PostCommentJson postCommentJson) {
        CommentEntity comment = new CommentEntity();
        comment.setText(postCommentJson.getText());
        comment.setStatus(Status.WAITING_FOR_APPROVE);
        return commentRepository.save(comment);
    }
    
    @GetMapping(value = "/comment")
    public List<CommentEntity> findAllComments() {
        return commentRepository.findAll();
    }

    @PutMapping(value = "/comment/approve/{id}")
    public CommentEntity approveComment(@PathVariable Long id) {
        CommentEntity comment = commentRepository.findOne(id);
        comment.setStatus(Status.APPROVED);
        return commentRepository.save(comment);
    }

    @PutMapping(value = "/comment/reject/{id}")
    public CommentEntity rejectComment(@PathVariable Long id) {
        CommentEntity comment = commentRepository.findOne(id);
        comment.setStatus(Status.REJECTED);
        return commentRepository.save(comment);
    }

}
