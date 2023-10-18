package com.ourbook.shop.dto.market;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@ToString
@Setter
@Getter
public class SaleBookInfo {


    private String uploaderEmail;
    @NotBlank(message = "dd")
    @Length(min=2, max= 12)
    private String uploaderName;

    @NotBlank
    @Length(min=2 , max= 20)
    private String saleBookName;

    @NotBlank
    @Length(min=2, max= 25)
    private String saleBookWriter;

    @NotBlank
    @Length(min=10, max= 500)
    private String saleBookStory;

    @NotBlank
    @Length(min=10 , max= 100)
    private String saleEtc;

    @NotBlank
    @Length(max= 5)
    private String saleBookPage;

    @Min(value = 1, message = "Value must be greater than or equal to 0")
    @Max(value = 50)
    @Nullable
    private int saleBookCount;


    private BigDecimal saleBookPrice;

    @Nullable
    private int sequence;



    public SaleBookInfo(String uploaderEmail, String uploaderName, String saleBookName, String saleBookWriter, String saleBookStory, String saleEtc, String saleBookPage, int saleBookCount, BigDecimal saleBookPrice, int sequence) {
        this.uploaderEmail = uploaderEmail;
        this.uploaderName = uploaderName;
        this.saleBookName = saleBookName;
        this.saleBookWriter = saleBookWriter;
        this.saleBookStory = saleBookStory;
        this.saleEtc = saleEtc;
        this.saleBookPage = saleBookPage;
        this.saleBookCount = saleBookCount;
        this.saleBookPrice = saleBookPrice;
        this.sequence = sequence;
    }

}
