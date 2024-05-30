package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    @PostMapping
    public ResponseEntity<Ad> saveAds(@RequestBody CreateOrUpdateAd createOrUpdateAd) {
        try {
            Ad ad = new Ad();
            return ResponseEntity.ok(ad);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> responseAds(@PathVariable Long id) {
        try {
            ExtendedAd extendedAd = new ExtendedAd();
            if (extendedAd == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new ExtendedAd());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAds(@PathVariable Long id) {
        try {
//            example
            Ad ad = new Ad();
            if (ad == null) {
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
    public ResponseEntity<Ad> editeAd(@RequestBody CreateOrUpdateAd ad) {
        try {
            Ad ad1 = new Ad();
            if (ad1 == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ad1);
        } catch (SecurityException securityException) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getUserAds() {
        try {
            return ResponseEntity.ok(new Ads());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
