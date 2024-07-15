package com.ourbook.shop.mail;

import com.ourbook.shop.service.additionService.emailService.EmailService;
import com.ourbook.shop.service.additionService.emailService.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class NameLockTest {

    EmailService emailService;

    @BeforeEach
    void setup(){
        emailService = new EmailServiceImpl(null,null);
    }

    @Test
    @DisplayName("이름 중간을 * 로 치환하는 기능 테스트")
    void nameLock() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /* given */
        String testName = "테스트이름";

        Method nameLock = EmailServiceImpl.class.getDeclaredMethod("nameLock", String.class);
        nameLock.setAccessible(true);

        /* when */
        String testNameLock = (String) nameLock.invoke(emailService, testName);

        /* then */
        assertThat(testNameLock).isEqualTo("테***름");

    }
}
