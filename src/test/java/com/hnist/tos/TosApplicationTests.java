package com.hnist.tos;

import com.oracle.tools.packager.Log;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Log4j2
@SpringBootTest
class TosApplicationTests {

    @Test
    void contextLoads() {
       String token =  Jwts.builder()
                .setId("1")
                .setSubject("123")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "fsddsf")// 不使用公钥私钥
                .compact();
        log.info("token:{}",token);
    }

}
