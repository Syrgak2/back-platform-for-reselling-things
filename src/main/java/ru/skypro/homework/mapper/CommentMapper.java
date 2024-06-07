package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentModel;

public class CommentMapper {
    public static Comment toComment(CommentModel commentModel){
        if (commentModel == null) {
            return null;
        }

        Comment comment = new Comment();

        comment.setAuthor(comment.getAuthor());
        comment.setAuthorImage(comment.getAuthorImage());
        comment.setAuthorFirstName(comment.getAuthorFirstName());
        comment.setCreatedAt(comment.getCreatedAt());
        comment.setPk(comment.getPk());
        comment.setText(comment.getText());
        return comment;
    }

    public static Comments toComments(CommentModel commentModel){
        if (commentModel == null){
            return null;
        }

        Comments comments = new Comments();

        comments.setCount(comments.getCount());
        comments.setResults(comments.getResults());
        return comments;
    }

    public static CreateOrUpdateComment createOrUpdateComment(CommentModel commentModel){
        if (commentModel == null){
            return null;
        }

        CreateOrUpdateComment comment = new CreateOrUpdateComment();

        comment.setText(comment.getText());
        return comment;
    }
}
