package com.ourbook.shop.controller.shopController;

import com.ourbook.shop.service.shopService.FindLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class FindLibraryController {

    private final FindLibraryService findLibraryService;

    public FindLibraryController(FindLibraryService findLibraryService) {
        this.findLibraryService = findLibraryService;
    }


    @GetMapping("/OurBook/findNearestLibrary")
    public String findNearestLibraryView(){
        return "main/librarySearch";
    }

    @GetMapping("/OurBook/findNearestLibrary/myLocation")
    public String findNearestLibraryToMyLocation(@RequestParam String firstDistrict, @RequestParam String secondDistrict){
        String myLocation = firstDistrict+secondDistrict+" 도서관";
        findLibraryService.findLibraryToNaverApi(myLocation);
        return "main/Main";
    }








}
