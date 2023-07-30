package com.ourbook.shop.controller;

import com.ourbook.shop.mapper.TestMapper;
import com.ourbook.shop.vo.Member;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestAPI {

    private final TestMapper testMapper;

    @PostMapping("/SAVE")
    public String insert(@RequestBody Member member){

        testMapper.save(member);
        return "ok";
    }
}
