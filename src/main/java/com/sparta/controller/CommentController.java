package com.sparta.controller;

import com.sparta.dto.CommentRequestDto;
import com.sparta.model.Comment;
import com.sparta.repository.CommentRepository;
import com.sparta.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CommentController<userId> {

    private final CommentRepository CommentRepository;
    private final com.sparta.service.CommentService CommentService;

    @GetMapping("/api/comment/{postId}")
    public List<Comment> getComment(@PathVariable Long postId) {
        return CommentService.getComment(postId);
    }

    @PostMapping("/api/comment")
    public boolean createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            Long userId = userDetails.getUser().getId();
            CommentService.createComment(requestDto, userId);
            return true;
        }
        return false;
    }

    @PutMapping("/api/comment/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        CommentService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/comment/{id}")
    public Long deleteComment(@PathVariable Long id) {
        CommentRepository.deleteById(id);
        return id;
    }
}
