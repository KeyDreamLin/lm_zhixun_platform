package com.lm.tool;

import com.lm.common.Constants;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64Tool {
    //Base64一行不能超过76字符，超过则添加回车换行符!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * Base64加密
     * @param MingWen 明文
     * @return 密文
     */
     public static String Encode(String MingWen){
        return new BASE64Encoder().encodeBuffer(MingWen.getBytes()).replaceAll("\r|\n", "");
    }

    /**
     * Base64解密
     * @param MiWen 密文
     * @return 明文
     */
    public static String Decoder(String MiWen){
        try {
            return new String(new BASE64Decoder().decodeBuffer(MiWen)).replaceAll("\r|\n", "");
        } catch (IOException e) {
           return "";
        }
    }

    @Test
    public void tests() {
//        String str = "afasdfsadfsadhgfsjadfkshdfk2341234621938478290136423yrefdhjzx&";
//        //另一种写法：
//        String frozen = Encode(str);
//        System.out.println("另一种写法 加密后：" + frozen);
//        String defrozen = null;
//
//        defrozen = Decoder(frozen);
//        System.out.println(defrozen);
        System.out.println(Constants.FAILURE_TIMER_MS +"");
    }
}
