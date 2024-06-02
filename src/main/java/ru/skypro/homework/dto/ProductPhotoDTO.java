package ru.skypro.homework.dto;

import java.util.Objects;

public class ProductPhotoDTO {
    private Long id;
    private long  fileSize;
    private String mediaType;
    private long advertisementId;

    public ProductPhotoDTO() {
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

    public long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(long advertisementId) {
        this.advertisementId = advertisementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPhotoDTO that = (ProductPhotoDTO) o;
        return fileSize == that.fileSize && advertisementId == that.advertisementId && Objects.equals(id, that.id) && Objects.equals(mediaType, that.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileSize, mediaType, advertisementId);
    }

    @Override
    public String toString() {
        return "ProductPhotoDTO{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", advertisementId=" + advertisementId +
                '}';
    }
}
