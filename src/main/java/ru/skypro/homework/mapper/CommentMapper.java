package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;

public class CommentMapper {

    public static Comment toCommentDto(CommentModel commentModel, UserModel userModel) {
        if (commentModel == null) {
            return null;
        }

        Comment comment = new Comment();

        comment.setAuthor(userModel.getId());
        comment.setAuthorImage(String.valueOf(userModel.getUserAvatar().getImage()));
        comment.setAuthorFirstName(userModel.getFirstName());
        comment.setCreatedAt(comment.getCreatedAt());
        comment.setPk(commentModel.getId());
        comment.setText(commentModel.getText());
        return comment;
    }

    public static CommentModel toCommentModel(CreateOrUpdateComment comment) {
        if (comment == null) {
            return null;
        }

        CommentModel commentModel = new CommentModel();

        commentModel.setText(comment.getText());
        return commentModel;
    }
}
