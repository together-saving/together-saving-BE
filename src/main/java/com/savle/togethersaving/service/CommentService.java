package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.comment.CommentDto;
import com.savle.togethersaving.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentDto> getComments(long challengeId, long client, int offset) {
        return commentRepository.findCommentFrom(challengeId, offset)
                .stream()
                .map(comment -> CommentDto.of(client, comment.getWriter(), comment))
                .collect(Collectors.toList());
    }
}
