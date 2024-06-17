package ru.skypro.homework.dto.comment;

import lombok.Data;
import lombok.Getter;

import java.util.List;
@Data
@Getter
public class CommentsDTO {
    private int count;
    private List<CommentDTO> results;
    public CommentsDTO() {
    }

    public CommentsDTO(List<CommentDTO> results) {
        this.count = results.size();
        this.results = results;
    }
}
