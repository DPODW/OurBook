package com.ourbook.shop.service.shopService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FindLibraryService {

    ResponseEntity<String> findLibraryToNaverApi(String myLocation);


}
