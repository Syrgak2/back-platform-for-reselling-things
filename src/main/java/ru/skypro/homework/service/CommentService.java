package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

public interface CommentService {

    ResponseEntity<?> getComments(Long id);

    ResponseEntity<?> addComments(Long id);

    ResponseEntity<?> removeComments(Long adId, Long commentId);

    ResponseEntity<?> patchComments(Long adId, Long commentId, CreateOrUpdateComment comment);
}
