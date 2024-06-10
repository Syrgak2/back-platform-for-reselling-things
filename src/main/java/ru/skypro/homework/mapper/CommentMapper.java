package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

public class CommentMapper {
    public static CommentDTO toComment(Comment commentModel){
        if (commentModel == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setAuthor(commentDTO.getAuthor());
        commentDTO.setAuthorImage(commentDTO.getAuthorImage());
        commentDTO.setAuthorFirstName(commentDTO.getAuthorFirstName());
        commentDTO.setCreatedAt(commentDTO.getCreatedAt());
        commentDTO.setPk(commentDTO.getPk());
        commentDTO.setText(commentDTO.getText());
        return commentDTO;
    }

    public static CommentsDTO toComments(Comment comment){
        if (comment == null){
            return null;
        }

        CommentsDTO comments = new CommentsDTO();

        comments.setCount(comments.getCount());
        comments.setResults(comments.getResults());
        return comments;
    }

    public static CreateOrUpdateCommentDTO createOrUpdateComment(Comment commentModel){
        if (commentModel == null){
            return null;
        }

        CreateOrUpdateCommentDTO comment = new CreateOrUpdateCommentDTO();

        comment.setText(comment.getText());
        return comment;
    }
}
