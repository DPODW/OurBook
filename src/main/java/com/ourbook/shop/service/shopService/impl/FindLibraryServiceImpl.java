package com.ourbook.shop.service.shopService.impl;

import com.google.gson.Gson;
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

@Slf4j
@Service
public class FindLibraryServiceImpl implements FindLibraryService {

    @Value("${Naver.ClientId}")
    private String NaverClientId;

    @Value("${Naver.ClientSecret}")
    private String NaverClientSecret;
    @Override
    public ResponseEntity<String> findLibraryToNaverApi(String myLocation) {
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
        String jsonResult = result.getBody();
        log.info("지역 검색 결과 -> {}",jsonResult);

        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResult);
            JSONArray itemObject = (JSONArray) jsonObject.get("items");
            String oneItem = itemObject.get(0).toString();

            Gson gson = new Gson();
            LibraryInfo libraryInfo = gson.fromJson(oneItem, LibraryInfo.class);


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
