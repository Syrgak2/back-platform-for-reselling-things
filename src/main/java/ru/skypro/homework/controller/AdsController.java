package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.ExtendedAdDTO;
import ru.skypro.homework.dto.ads.AdsDTO;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

import ru.skypro.homework.service.UserService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private static final Logger log = LoggerFactory.getLogger(AdsController.class);
    private final AdService adService;

    private final AdMapper adMapper = AdMapper.INSTANCE;

    public AdsController(AdService adService, UserService userService) {
        this.adService = adService;
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AdDTO> saveAds(@RequestPart("image") MultipartFile image,
                                          @RequestPart("properties") CreateOrUpdateAdDTO properties) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            return ResponseEntity.ok(adMapper.adToAdDTO(adService.save(properties, image, authentication.getName())));
        } catch (IOException e) {
            log.info("File is wrong");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds () {
        AdsDTO adsDTO = new AdsDTO(adService.getAllAds());
        if (adsDTO.getCount() == 0) {
            log.info("Ads is empty");
            throw new NotFoundException();
        }
        return ResponseEntity.ok(adsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> responseAds (@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ExtendedAdDTO extendedAd = adMapper.adToExtendAd(adService.find(id));
        if (extendedAd == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(extendedAd);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<?> deleteAds (@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Ad ad = adService.find(id);

        if (ad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!authentication.getName().equals(ad.getUser().getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        adService.removeAd(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<AdDTO> editeAd(@RequestBody CreateOrUpdateAdDTO ad,
                                         @PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Ad foundAd = adService.find(id);

        if (foundAd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!authentication.getName().equals(foundAd.getUser().getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(adMapper.adToAdDTO(adService.edite(id, ad)));
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getUserAds () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        AdsDTO adsDTO = new AdsDTO(adService.getUsersAds(authentication.getName()));

        return ResponseEntity.ok(adsDTO);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<byte[]> editeAdImage(@PathVariable Long id,
                                               @RequestParam MultipartFile image) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Ad ad = adService.find(id);

        if (ad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!authentication.getName().equals(ad.getUser().getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(adService.editeImage(id, image));
    }

}
