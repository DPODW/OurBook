package com.ourbook.shop.dto.market;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@ToString
@Setter
@Getter
public class SaleBookInfo {


    private String uploaderEmail;


    private String uploaderName;
    /** 업로더 정보는 유지중인 세션에서 가져옴**/
    @NotBlank
    @Length(min=2 , max= 50)
    private String saleBookName;

    @NotBlank
    @Length(min=2, max= 25)
    private String saleBookWriter;

    @NotBlank
    @Length(min=10, max= 1000)
    private String saleBookStory;

    @NotBlank
    @Length(min=10 , max= 1000)
    private String saleEtc;

    @Nullable
    private String saleImg;

    @Min(value = 1)
    @Max(value = 50000)
    @NotNull
    private Integer saleBookPage;

    @Min(value = 1)
    @Max(value = 99)
    @NotNull
    private Integer saleBookCount;

    @DecimalMin("1.0")
    @DecimalMax("10000000.0")
    @NotNull
    private BigDecimal saleBookPrice;

    @Nullable
    private String saveTime;
    @Nullable
    private int sequence;

    private int purchaseRequestCount;


    public SaleBookInfo(String uploaderEmail, String uploaderName, String saleBookName, String saleBookWriter, String saleBookStory, String saleEtc, @Nullable String saleImg,
                        Integer saleBookPage, Integer saleBookCount, BigDecimal saleBookPrice, @Nullable String saveTime, int sequence) {
        this.uploaderEmail = uploaderEmail;
        this.uploaderName = uploaderName;
        this.saleBookName = saleBookName;
        this.saleBookWriter = saleBookWriter;
        this.saleBookStory = saleBookStory;
        this.saleEtc = saleEtc;
        this.saleImg = saleImg;
        this.saleBookPage = saleBookPage;
        this.saleBookCount = saleBookCount;
        this.saleBookPrice = saleBookPrice;
        this.saveTime = saveTime;
        this.sequence = sequence;
    }
}
