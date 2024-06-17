package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;
import ru.skypro.homework.exception.BadFileException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.PhotoService;

import java.io.IOException;
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final PhotoService photoService;
    private final AdMapper adMapper = AdMapper.INSTANCE;

    public AdServiceImpl(AdRepository adRepository, PhotoService photoService) {
        this.adRepository = adRepository;
        this.photoService = photoService;
    }

    @Override
    public AdDTO save(CreateOrUpdateAdDTO createOrUpdateAd, MultipartFile file) throws IOException {
        Ad ad = adMapper.createOrUpdateAdToAd(createOrUpdateAd);
        Photo photo = photoService.save(file);
        ad.setImage(photo);
        ad.setImageUrl("/ads/" + photo.getId());
        return adMapper.adToAdDTO(adRepository.save(ad));
    }


}
