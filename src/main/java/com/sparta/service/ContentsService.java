package com.sparta.service;

import com.sparta.dto.ContentsRequestDto;
import com.sparta.model.Contents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ContentsService {

    private final com.sparta.repository.ContentsRepository ContentsRepository;

    @Transactional
    public Contents createContents(ContentsRequestDto requestDto, String username) {
        String contentsCheck = requestDto.getContents();
        String titleCheck = requestDto.getTitle();
        if (contentsCheck.contains("script")||contentsCheck.contains("<")||contentsCheck.contains(">")){
            Contents contents = new Contents(requestDto,username,"잘못된 입력입니다.");
            ContentsRepository.save(contents);
            return contents;
        }
        if (titleCheck.contains("script")||titleCheck.contains("<")||titleCheck.contains(">")) {
            Contents contents = new Contents("잘못된 입력입니다.", username, "잘못된 입력입니다.");
            ContentsRepository.save(contents);
            return contents;
        }
        Contents contents = new Contents(requestDto, username);
        ContentsRepository.save(contents);
        return contents;
    }

    @Transactional
    public Long update(Long id, ContentsRequestDto requestDto) {
        Contents Contents = ContentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
        Contents.update(requestDto);
        return Contents.getId();
    }
}
