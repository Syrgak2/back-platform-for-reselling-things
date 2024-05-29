package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @PostMapping
    public ResponseEntity<Ad> saveAds(@RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> responseAds(@PathVariable Long id) {
        return ResponseEntity.ok(new ExtendedAd());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAds(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> editeAd(@RequestBody CreateOrUpdateAd ad) {
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getUserAds() {
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> editeAdImage(@PathVariable Long id,
                                               @RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
