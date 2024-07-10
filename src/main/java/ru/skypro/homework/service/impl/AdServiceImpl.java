package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;
import ru.skypro.homework.exception.BadFileException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.PhotoService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.homework.constant.Constants.URL_PHOTO_CONSTANT;

@Service
public class AdServiceImpl implements AdService {

    private static final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);
    private final AdRepository adRepository;
    private final PhotoService photoService;
    private final UserService userService;
    private final CommentService commentService;
    private final AdMapper adMapper = AdMapper.INSTANCE;

    public AdServiceImpl(AdRepository adRepository, PhotoService photoService,
                         @Lazy UserService userService,
                         @Lazy CommentService commentService) {
        this.adRepository = adRepository;
        this.photoService = photoService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @Override
    public Ad save(CreateOrUpdateAdDTO createOrUpdateAd, MultipartFile file, String userName) throws IOException {
        Ad ad = adMapper.createOrUpdateAdToAd(createOrUpdateAd);
        User user = userService.find(userName);
        Photo photo = photoService.save(file);

        if (ad == null && user == null) {
            throw new NotFoundException();
        }
        assert ad != null;
        ad.setImage(photo);
        ad.setUser(user);
        ad.setImageUrl(URL_PHOTO_CONSTANT + photo.getId());
        return adRepository.save(ad);
    }

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Ad find(Long id) {
        return adRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<AdDTO> getAllAds() {
        List<Ad> ads = findAll();
        return ads.stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAd(Long id) {
        Ad ad = find(id);
        if (ad == null) {
            throw new NotFoundException();
        }

        commentService.removeAll(ad.getComments());
        if (ad.getImage() != null) {
            Long photoId = ad.getImage().getId();
            ad.setImage(null);
            adRepository.save(ad);
            photoService.remove(photoId);
        }

        adRepository.deleteById(id);
    }

    @Override
    public Ad edite(Long id, CreateOrUpdateAdDTO ad) {
        Ad foundAd = find(id);

        foundAd.setTitle(ad.getTitle());
        foundAd.setPrice(ad.getPrice());
        foundAd.setDescription(ad.getDescription());

        return adRepository.save(foundAd);
    }

    @Override
    public List<AdDTO> getUsersAds(String userName) {
        User user = userService.find(userName);

        return adRepository.findAllByUserId(user.getId()).stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Photo editeImage(Long id, MultipartFile file) {
        Ad ad = find(id);
        if (ad.getImage() != null) {
            Long photoId = ad.getImage().getId();
            ad.setImage(null);
            adRepository.save(ad);
            photoService.remove(photoId);
        }
        try {
            Photo photo = photoService.save(file);
            ad.setImage(photo);
            ad.setImageUrl(URL_PHOTO_CONSTANT + photo.getId());
            adRepository.save(ad);
            return photo;
        } catch (IOException ioException) {
            log.info("The file is wrong");
            throw new BadFileException();
        }
    }

    private byte[] getPhoto(Long id) {
        return photoService.getPhoto(id);
    }
}
