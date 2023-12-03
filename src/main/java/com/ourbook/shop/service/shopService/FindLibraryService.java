package com.ourbook.shop.service.shopService;

import com.ourbook.shop.dto.library.LibraryInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FindLibraryService {

    List<LibraryInfo> findLibraryToNaverApi(String myLocation);


}
