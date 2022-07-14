package com.sparta.service;

import com.sparta.dto.CommentRequestDto;
import com.sparta.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final com.sparta.repository.CommentRepository CommentRepository;

    public List<Comment> getComment(Long postId) {
        return CommentRepository.findAllByPostidOrderByCreatedAtDesc(postId);
    }


    @Transactional
    public Comment createComment(CommentRequestDto requestDto, Long userId) {
        String commentCheck = requestDto.getComment();
        if (commentCheck.contains("script")|| commentCheck.contains("<")||commentCheck.contains(">")){
            Comment comment = new Comment(requestDto, userId,"잘못된 입력입니다.");
            CommentRepository.save(comment);
            return comment;
        }
        Comment comment = new Comment(requestDto, userId);
        CommentRepository.save(comment);
        return comment;
    }


    @Transactional
    public void update(Long id, CommentRequestDto requestDto) {
        Comment comment = CommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        comment.update(requestDto);
    }
}
