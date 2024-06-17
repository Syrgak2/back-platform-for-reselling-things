package ru.skypro.homework.dto.comment;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class CommentDTO {
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Long pk;
    private String text;

    public CommentDTO() {
    }
}
