package charkak.demo.controller;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String placeName;
    private String dateTime; // "2025-05-24T17:28:00"
    private String camera;
    private String lens;
    private String aperture;
    private String shutterSpeed;
    private String iso;
    private String weather;
    private String imageUrl;
    private String text;
    private Long userId;  // 유저 ID

    // 태그명
    private String ratingTagName;
    private String countryTagName;
    private String cityTagName;
    private String targetTagName;
}
