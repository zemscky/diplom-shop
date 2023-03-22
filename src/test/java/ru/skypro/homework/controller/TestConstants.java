package ru.skypro.homework.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.security.MyUserDetails;

import java.io.IOException;
import java.time.Instant;

public class TestConstants {

    public static final MockMultipartFile IMAGE_FILE = new MockMultipartFile("image", new byte[0]);
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final int PRICE = 10;
    public static final String TITLE = "title";
    public static final String USER_IMAGE_STRING = "/users/image/1";
    public static final String ADS_IMAGE_STRING = "/ads/image/2";
    public static final Image USER_IMAGE;

    static {
        try {
            USER_IMAGE = new Image(ID, 12, "png", IMAGE_FILE.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Image ADS_IMAGE;

    static {
        try {
            ADS_IMAGE = new Image(2, 12, "png", IMAGE_FILE.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final User USER = new User(ID, "fn", "ln", "test@email.com",
            "password", "phone", "city", Instant.now(),
            USER_IMAGE, Role.USER);

    public static final MyUserDetails MY_USER_DETAILS = new MyUserDetails(USER);

    public static final JSONObject CREATE_ADS_DTO_JSON = new JSONObject();

    static {
        try {
            CREATE_ADS_DTO_JSON.put("description", DESCRIPTION);
            CREATE_ADS_DTO_JSON.put("price", PRICE);
            CREATE_ADS_DTO_JSON.put("title", TITLE);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static final AdsDto ADS_DTO = new AdsDto();

    static {
        ADS_DTO.setPk(ID);
        ADS_DTO.setAuthor(ID);
        ADS_DTO.setImage(ADS_IMAGE_STRING);
        ADS_DTO.setPrice(PRICE);
        ADS_DTO.setTitle(TITLE);
        ADS_DTO.setDescription(DESCRIPTION);
    }

    public static final Ads ADS = new Ads(ID, TITLE, DESCRIPTION, PRICE, USER, ADS_IMAGE);
}
