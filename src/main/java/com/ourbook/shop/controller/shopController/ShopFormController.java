package com.ourbook.shop.controller.shopController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopFormController {

    @GetMapping("/OurBook/shop")
    public String shopList(){

        return "ourBookShop/ourBookShopList";
    }
}
