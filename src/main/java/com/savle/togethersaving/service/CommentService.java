package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.comment.CommentDto;
import com.savle.togethersaving.entity.BaseTime;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.CommentRepository;
import com.savle.togethersaving.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentDto getComments(long challengeId, long client, int offset) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComments(
         commentRepository.findCommentFrom(challengeId)
                .stream().filter(c-> c.getCreatedAt().toLocalDate().equals(LocalDate.now().minusDays(offset)))
                .map(comment -> CommentDto.Comment.of(client, comment.getWriter(), comment))
                .collect(Collectors.toList()));
        commentDto.setDate(LocalDate.now().minusDays(offset));

        return commentDto;
    }
}
