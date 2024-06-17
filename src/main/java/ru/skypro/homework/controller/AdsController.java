package ru.skypro.homework.controller;

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
import ru.skypro.homework.model.User;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @PostMapping
    public ResponseEntity<AdDTO> saveAds(@RequestBody CreateOrUpdateAdDTO createOrUpdateAd) {
        try {
            String userName = authentication.getName();
            AdDTO adDTO = new AdDTO();
            return ResponseEntity.ok(adDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> responseAds(@PathVariable Long id) {
        try {
            ExtendedAdDTO extendedAd = new ExtendedAdDTO();
            if (extendedAd == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new ExtendedAdDTO());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<?> deleteAds(@PathVariable Long id) {
        try {
//            example
            AdDTO adDTO = new AdDTO();
            if (adDTO == null) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SecurityException securityException) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<AdDTO> editeAd(@RequestBody CreateOrUpdateAdDTO ad) {
        try {
            AdDTO adDTO1 = new AdDTO();
            if (adDTO1 == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(adDTO1);
        } catch (SecurityException securityException) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getUserAds() {
        try {
            return ResponseEntity.ok(new AdsDTO());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<String> editeAdImage(@PathVariable Long id,
                                               @RequestParam MultipartFile image) {
        try {

            return ResponseEntity.ok().build();
        } catch (SecurityException securityException) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
