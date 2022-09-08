package com.lm.tool;

import org.springframework.lang.NonNull;

public class LmAssert {

    /**
     * 如果字符串是null或者""返回true
     *
     * @param value
     * @return
     **/
    public static boolean isEmpty(String value) {
        return null == value || "" .equals(value);
    }

    /**
     * 如果不是null或者""返回true
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

}
