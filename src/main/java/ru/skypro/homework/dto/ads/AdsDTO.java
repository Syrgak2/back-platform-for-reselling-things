package ru.skypro.homework.dto.ads;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class AdsDTO {
    private int count;
    private List<AdDTO> results;

    public AdsDTO() {
    }

    public AdsDTO( List<AdDTO> results) {
        this.count = results.size();
        this.results = results;
    }
}
