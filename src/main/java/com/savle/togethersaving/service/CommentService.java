package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.comment.CommentDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.CommentRepository;
import com.savle.togethersaving.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentDto> getComments(long challengeId, long client, int offset) {
        return commentRepository.findCommentFrom(challengeId, offset)
                .stream()
                .map(comment -> CommentDto.of(client, comment.getUser(), comment))
                .collect(Collectors.toList());
    }
}
