package com.ourbook.shop.controller.shopController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class FindLibraryController {

    @GetMapping("/OurBook/findNearestLibrary")
    public String findNearestLibraryView(){
        return "main/librarySearch";
    }

    @GetMapping("/OurBook/findNearestLibrary/myLocation")
    public String findNearestLibraryToMyLocation(){
        String query = "강원도 철원군 도서관";
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
        String encode = StandardCharsets.UTF_8.decode(buffer).toString();

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query",encode)
                .queryParam("display",10)
                .queryParam("sort","random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);
        return result.getBody();
    }

    @GetMapping("/OurBook/test")
    public String testValueCheck(@RequestParam String firstDistrict, @RequestParam String secondDistrict){
        log.info("{}",firstDistrict);
        log.info("{}",secondDistrict);
        return "main/Main";
    }







}
