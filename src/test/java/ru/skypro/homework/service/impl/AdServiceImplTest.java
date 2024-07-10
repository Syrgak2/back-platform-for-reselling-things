package ru.skypro.homework.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.PhotoService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static ru.skypro.homework.constants.Constants.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceImplTest {

    @Mock
    AdRepository adRepository;

    @Mock
    PhotoService photoService;
    @Mock
    CommentService commentService;
    @Mock
    UserService userService;

    @InjectMocks
    AdServiceImpl adService;

    private final AdMapper adMapper = AdMapper.INSTANCE;

    @Test
    public void saveTest() throws IOException {
//        Given
        String fileName = "testFile.txt";
        String contentType = "text/plain";
        byte[] content = "This is the file content".getBytes();
        MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName, contentType, content);
        when(adRepository.save(any())).thenReturn(AD);
        when(photoService.save(any())).thenReturn(PHOTO);
        when(userService.find(anyString())).thenReturn(USER);
//        When
        Ad actual = adService.save(CREATE_OR_UPDATE_AD_DTO, MULTIPART_FILE, USER.getUsername());
//        Then
        assertEquals(AD, actual);
    }


    @Test
    public void findAllTest() {
//        Given
        when(adRepository.findAll()).thenReturn(ADS);
//        When
        List<Ad> actual = adService.findAll();
//        Then
        assertEquals(ADS, actual);
    }


    @Test
    public void findTest() {
//        Given
        when(adRepository.findById(anyLong())).thenReturn(Optional.of(AD));
//        When
        Ad actual = adService.find(AD.getId());
//        Then
        assertEquals(AD, actual);
    }

    @Test
    public void getAllAdsTest() {
//        Given
        List<AdDTO> excepted = List.of(adMapper.adToAdDTO(AD));
        when(adRepository.findAll()).thenReturn(ADS);
//        When
        List<AdDTO> actual = adService.getAllAds();
//        Then
        assertEquals(excepted, actual);
    }

    @Test
    public void removeAdWhenExistTest() {
//        Given
        Ad ad = AD;
        ad.setImage(PHOTO);
        ad.getImage().setId(2L);
        when(adRepository.findById(anyLong())).thenReturn(Optional.of(ad));
//         When
        adService.removeAd(ad.getId());
//        Then
        verify(commentService).removeAll(ad.getComments());
        verify(adRepository).save(ad);
        verify(photoService).remove(PHOTO.getId());
        verify(adRepository).deleteById(ad.getId());
    }

    @Test
    public void testRemoveAdWhenDoesNotExist() {
//        When
        when(adRepository.findById(anyLong())).thenReturn(Optional.empty());

//        Then
        assertThrows(NotFoundException.class, () -> adService.removeAd(AD.getId()));
    }

    @Test
    public void testGetUserAds() {
//        Given
        Ad ad = AD;
        ad.setUser(USER);

        List<Ad> ads = List.of(ad);
        List<AdDTO> excepted = List.of(adMapper.adToAdDTO(ad));

//        When
        when(userService.find(anyString())).thenReturn(USER);
        when(adRepository.findAllByUserId(anyLong())).thenReturn(ads);
        List<AdDTO> actual = adService.getUsersAds(USER.getUsername());
//        Then
        assertEquals(excepted, actual);
    }

    @Test
    public void testEditeImage() throws IOException {
//        Given
        Ad ad = AD;
        ad.setImage(PHOTO);

//        When
        when(adRepository.findById(anyLong())).thenReturn(Optional.of(ad));
        when(photoService.save(MULTIPART_FILE)).thenReturn(PHOTO);

        Photo actual = adService.editeImage(ad.getId(), MULTIPART_FILE);
//        Then
        assertEquals(PHOTO, actual);
        verify(adRepository, times(2)).save(ad);
        verify(photoService).remove(PHOTO.getId());
    }
}
