package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "user.userAvatar.url", target = "authorImage")
    @Mapping(source = "user.firstName", target = "authorFirstName")
   CommentDTO toCommentDTO(Comment comment);


    Comment createOrUpdateToComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
