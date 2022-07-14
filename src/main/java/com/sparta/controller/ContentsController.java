package com.sparta.controller;

import com.sparta.dto.ContentsRequestDto;
import com.sparta.model.Contents;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContentsController {

    private final com.sparta.repository.ContentsRepository ContentsRepository;
    private final com.sparta.service.ContentsService ContentsService;

    @GetMapping("/api/contents")
    public List<Contents> getContents() {
        return ContentsRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/contents/{id}")
    public Contents getContents(@PathVariable Long id) {
        Contents contents =  ContentsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return contents;
    }

    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        Contents contents = ContentsService.createContents(requestDto, username);
        return contents;
    }

    @PutMapping("/api/contents/{id}")
    public Long updateContents(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto) {
        ContentsService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/contents/{id}")
    public Long deleteContents(@PathVariable Long id) {
        ContentsRepository.deleteById(id);
        return id;
    }
}
