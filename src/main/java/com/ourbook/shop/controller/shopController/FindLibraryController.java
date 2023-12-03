package com.ourbook.shop.controller.shopController;

import com.ourbook.shop.dto.library.LibraryInfo;
import com.ourbook.shop.service.shopService.FindLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String findNearestLibraryToMyLocation(@RequestParam String firstDistrict, @RequestParam String secondDistrict, Model model){
        String myLocation = firstDistrict+secondDistrict+" 도서관";
        List<LibraryInfo> nearestLibraryInfo = findLibraryService.findLibraryToNaverApi(myLocation);
        model.addAttribute("nearestLibraryInfo",nearestLibraryInfo);
        return "main/librarySearch";
    }
}
