package com.ourbook.shop.dto.market;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Length(min=2 , max= 50)
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
    @Pattern(regexp = "^[0-9]*$")
    @Length(max= 5)
    private String saleBookPage;

    @Min(value = 1)
    @Max(value = 99)
    @Nullable
    private int saleBookCount;

    @Min(value = 1)
    @Max(value = 10000000)
    private BigDecimal saleBookPrice;

    @Nullable
    private String saveTime;
    @Nullable
    private int sequence;



    public SaleBookInfo(String uploaderEmail, String uploaderName, String saleBookName, String saleBookWriter, String saleBookStory, String saleEtc, String saleBookPage, int saleBookCount, BigDecimal saleBookPrice,
                        String saveTime,int sequence) {
        this.uploaderEmail = uploaderEmail;
        this.uploaderName = uploaderName;
        this.saleBookName = saleBookName;
        this.saleBookWriter = saleBookWriter;
        this.saleBookStory = saleBookStory;
        this.saleEtc = saleEtc;
        this.saleBookPage = saleBookPage;
        this.saleBookCount = saleBookCount;
        this.saleBookPrice = saleBookPrice;
        this.saveTime = saveTime;
        this.sequence = sequence;
    }

}
