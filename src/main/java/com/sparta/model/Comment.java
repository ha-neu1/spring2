package com.sparta.model;

import com.sparta.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long postid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long userId;

    public Comment(CommentRequestDto requestDto, Long userId) {
        this.postid = requestDto.getPostid();
        this.comment = requestDto.getComment();
        this.username = requestDto.getUsername();
        this.userId = userId;
    }
    public Comment(CommentRequestDto requestDto, Long userId, String comment) {
        this.postid = requestDto.getPostid();
        this.comment = comment;
        this.username = requestDto.getUsername();
        this.userId = userId;
    }

    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
