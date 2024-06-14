package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;

import java.io.IOException;

public interface AdService {
    AdDTO save(CreateOrUpdateAdDTO createOrUpdateAd, MultipartFile file) throws IOException;
}
