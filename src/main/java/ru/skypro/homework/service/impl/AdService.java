package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class AdService {
    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }
    public Collection<Ad> getAllAds () {
        return adRepository.findAll();
    }
    public Ad createAd (Ad ad) {
        return adRepository.save(ad);
    }
    public Optional<Ad> findAdById(long id) {
        return adRepository.findById(id);
    }
}
