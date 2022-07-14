package com.sparta.model;

import com.sparta.dto.ContentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Contents extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contents;

    public Contents(String title, String username, String contents) {
        this.title = title;
        this.name = username;
        this.contents = contents;
    }

    public Contents(ContentsRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.name = username;
        this.contents = requestDto.getContents();
    }

    public void update(ContentsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

    public Contents(ContentsRequestDto requestDto, String username,String contents) {
        this.title = requestDto.getTitle();
        this.name = username;
        this.contents = contents;
    }
}