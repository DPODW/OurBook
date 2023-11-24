package com.ourbook.shop.service.shopService;

import com.ourbook.shop.dto.book.BookSearchResult;
import com.ourbook.shop.dto.payment.PaymentInfo;
import com.ourbook.shop.dto.book.Book;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface FindBookService {

    Book findBook(String id);

    List<Book> findAllBook();

    PaymentInfo orderNumberToBook(String orderNumber);

    String findBookImg(String bookId);

    BigDecimal findBookPrice(String bookId);

    List<BookSearchResult> userSearchBook(String searchBookName);

}
