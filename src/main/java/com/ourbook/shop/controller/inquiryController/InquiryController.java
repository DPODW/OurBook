package com.ourbook.shop.controller.inquiryController;

import com.ourbook.shop.dto.inquiry.PublishProposalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class InquiryController {
    

    @GetMapping("/OurBook/inquiry/publishproposal")
    public String SendPublishProposalView(){
        return "inquiry/publishProposal";
    }


    @PostMapping("/OurBook/inquiry/publishproposal")
    public String SendPublishProposal (@ModelAttribute PublishProposalInfo publishProposalInfo){
        log.info("{}",publishProposalInfo);
        return "main/Main";
    }
}
