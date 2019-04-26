package com.oauth.oau.config;


import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;


@Configuration
public class WebResponseExceptionTranslateConfig {



    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator(){
        return new DefaultWebResponseExceptionTranslator(){
            @Override
            public ResponseEntity translate(Exception e) throws Exception{
                ResponseEntity responseEntity = super.translate(e);
                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                if (400 == responseEntity.getStatusCode().value()){
                    System.out.println(body.getMessage());
                    if ("Bad credentials".equals(body.getMessage())){
//                        return new ResponseEntity(new ResponseData("400","您输入的用户名或密码错误",null),headers, HttpStatus.OK);
                    }
                }
                return new ResponseEntity(body,headers,responseEntity.getStatusCode());
            }

        };

    }
}
