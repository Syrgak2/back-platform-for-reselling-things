package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import ru.skypro.homework.dto.ads.Ad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skypro.homework.constants.Constants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
public class AdsControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    AdsController adsController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads()throws Exception {
        assertThat(adsController).isNotNull();
    }

    @Test
    public void saveAdsTest() {
//        When
        ResponseEntity<Ad> response = testRestTemplate.postForEntity(
                HOST + port + "/ads",
                CREATE_OR_UPDATE_AD,
                Ad.class
        );
//        Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AD, response.getBody());
    }
}
