package ru.skypro.homework.dto.comment;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateOrUpdateCommentDTO {
    private String text;

    public CreateOrUpdateCommentDTO() {
    }

    public CreateOrUpdateCommentDTO(String text) {
        this.text = text;
    }
}
