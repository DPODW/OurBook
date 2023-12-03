package com.ourbook.shop.service.shopService.impl;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.ourbook.shop.dto.library.LibraryInfo;
import com.ourbook.shop.service.shopService.FindLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FindLibraryServiceImpl implements FindLibraryService {

    @Value("${Naver.ClientId}")
    private String NaverClientId;

    @Value("${Naver.ClientSecret}")
    private String NaverClientSecret;
    @Override
    public List<LibraryInfo> findLibraryToNaverApi(String myLocation) {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(myLocation);
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
                .header("X-Naver-Client-Id",NaverClientId)
                .header("X-Naver-Client-Secret",NaverClientSecret)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);
        return jsonToDto(result.getBody());
    }

    private static List<LibraryInfo> jsonToDto(String locationJson) {
        JSONParser parser = new JSONParser();
        Gson gson = new Gson();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(locationJson);
            JSONArray locationItem = (JSONArray) jsonObject.get("items");

            List<LibraryInfo> libraryInfoList = (List<LibraryInfo>) locationItem.stream()
                    .map(locationOneItem -> {
                        LibraryInfo libraryOneTime = gson.fromJson(locationOneItem.toString(), LibraryInfo.class);
                        libraryOneTime.setTitle(libraryOneTime.getTitle().replaceAll("[<b></b>]",""));
                        if(libraryOneTime.getLink().equals("")||libraryOneTime.getRoadAddress().equals("")){
                            libraryOneTime.setRoadAddress("정보가 없습니다.");
                            libraryOneTime.setLink("정보가 없습니다.");
                        }
                        return libraryOneTime;
                    })
                    .collect(Collectors.toList());
            return libraryInfoList;

        } catch (ParseException e) {
            e.printStackTrace();
            throw new JsonParseException(e);
        }
    }
}
