package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentService {


    List<CommentDTO> getComments(Long adId);

    Comment find(Long id);

    Comment addComments(CreateOrUpdateCommentDTO createOrUpdateCommentDTO, Long id, String userName);

    Boolean removeComments(Long adId, Long commentId);

    Comment patchComments(Long adId, Long commentId, CreateOrUpdateCommentDTO comment, String userName);

    List<Comment> findByAdId(Long adId);

    boolean removeAll(List<Comment> comments);
}

