package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface AdService {
    Ad save(CreateOrUpdateAdDTO createOrUpdateAd, MultipartFile file, String userName) throws IOException;

    List<Ad> findAll();

    Ad find(Long id);

    List<AdDTO> getAllAds();

    void removeAd(Long id);

    Ad edite(Long id, CreateOrUpdateAdDTO ad);

    List<AdDTO> getUsersAds(String userName);

    byte[] editeImage(Long id, MultipartFile file);
}
