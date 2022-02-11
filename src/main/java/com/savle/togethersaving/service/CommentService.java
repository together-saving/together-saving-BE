package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.comment.CommentDto;
import com.savle.togethersaving.entity.BaseTime;
import com.savle.togethersaving.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentDto getComments(long challengeId, long client, int offset) {
        CommentDto commentDto = new CommentDto();
        commentDto.setChallengeComments(
         commentRepository.findCommentFrom(challengeId)
                .stream().sorted(Comparator.comparing(BaseTime::getCreatedAt).reversed()).filter(c-> c.getCreatedAt().toLocalDate().equals(LocalDate.now().minusDays(offset)))
                .map(comment -> CommentDto.ChallengeComment.of(client, comment.getWriter(), comment))
                .collect(Collectors.toList()));
        commentDto.setDate(LocalDate.now().minusDays(offset));

        return commentDto;
    }
}
