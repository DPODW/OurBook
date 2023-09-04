package com.ourbook.shop.check;

import com.ourbook.shop.mapper.memberMapper.FindInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class DuplicateCheckApi {

    private final FindInfoMapper findInfoMapper;

    @Autowired
    public DuplicateCheckApi(FindInfoMapper findInfoMapper) {
        this.findInfoMapper = findInfoMapper;
    }

    @PostMapping("/checkId")
    public ResponseEntity<String> checkId(@RequestBody Map<String,String> requestBody){
        String id = requestBody.get("sellerId");
        String searchId = findInfoMapper.searchId(id);
        if(searchId==null) {
            return ResponseEntity.ok().body("사용 가능 아이디");
       }else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 사용중인 아이디");
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(@RequestBody Map<String,String> requestBody){
        String email = requestBody.get("sellerEmail");
        String searchEmail = findInfoMapper.searchEmail(email);
        if(searchEmail==null) {
            return ResponseEntity.ok().body("사용 가능 이메일");
        }else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 사용중인 이메일");
    }
}
