package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.model.Comment;

public interface CommentMapper {
    public static ru.skypro.homework.dto.comment.Comment toComment(Comment commentModel){
        if (commentModel == null) {
            return null;
        }

        ru.skypro.homework.dto.comment.Comment comment = new ru.skypro.homework.dto.comment.Comment();

        comment.setAuthor(comment.getAuthor());
        comment.setAuthorImage(comment.getAuthorImage());
        comment.setAuthorFirstName(comment.getAuthorFirstName());
        comment.setCreatedAt(comment.getCreatedAt());
        comment.setPk(comment.getPk());
        comment.setText(comment.getText());
        return comment;
    }

    public static Comments toComments(Comment comment){
        if (comment == null){
            return null;
        }

        Comments comments = new Comments();

        comments.setCount(comments.getCount());
        comments.setResults(comments.getResults());
        return comments;
    }

    public static CreateOrUpdateComment createOrUpdateComment(Comment commentModel){
        if (commentModel == null){
            return null;
        }

        CreateOrUpdateComment comment = new CreateOrUpdateComment();

        comment.setText(comment.getText());
        return comment;
    }
}
