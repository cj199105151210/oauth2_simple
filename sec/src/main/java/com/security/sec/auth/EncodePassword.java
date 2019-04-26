package com.security.sec.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;



/**
 * md5加密
 */
@Component
public class EncodePassword implements PasswordEncoder {

    /**
     * MD5加密
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return MD5(charSequence.toString());
    }

    /**
     * 匹配规则
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return MD5(charSequence.toString()).equals(s);
    }

    public static String MD5(String key){
        char hexDigits[] = {
                '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
        };
        try {
            byte[] btInput = key.getBytes();
            //获得MD5摘要算法的MessageDigiest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成16进制的字符串形式
            int j = md.length;
            char str[] = new char[j*2];
            int k = 0;
            for (int i = 0; i< j;i++){
                byte byte0 = md[i];
                str[k++] = hexDigits[ byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
