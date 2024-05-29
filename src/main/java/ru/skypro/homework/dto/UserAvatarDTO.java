package ru.skypro.homework.dto;

import java.util.Objects;

public class UserAvatarDTO {
    private Long id;
    private long  fileSize;
    private String mediaType;
    private long userId;

    public UserAvatarDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAvatarDTO that = (UserAvatarDTO) o;
        return fileSize == that.fileSize && userId == that.userId && Objects.equals(id, that.id) && Objects.equals(mediaType, that.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileSize, mediaType, userId);
    }

    @Override
    public String toString() {
        return "UserAvatarDTO{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", userId=" + userId +
                '}';
    }
}
