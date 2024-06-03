package ru.skypro.homework.dto.comment;

import lombok.Data;

@Data
public class CommentDTO {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private int createdAt;
    private Long pk;
    private String text;

}
