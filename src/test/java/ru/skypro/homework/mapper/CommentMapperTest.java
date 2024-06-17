package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.model.Comment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommentMapperTest {

    @Test
    void toCommentDTO() {
        //given
        Comment comment = new Comment(1L, 2L, "authorImage", "authorFirstName", "text");

        //when
        CommentDTO commentDTO = CommentMapper.INSTANCE.toCommentDTO(comment);

        //then
        assertThat(commentDTO).isNotNull();
        assertThat(commentDTO.getPk()).isEqualTo(1);
        assertThat(commentDTO.getAuthor()).isEqualTo(2L);
        assertThat(commentDTO.getAuthorImage()).isEqualTo("authorImage");
        assertThat(commentDTO.getAuthorFirstName()).isEqualTo("authorFirstName");
        assertThat(commentDTO.getText()).isEqualTo("text");
    }

    @Test
    void toComments() {
    }

    @Test
    void createOrUpdateComment() {
    }
}