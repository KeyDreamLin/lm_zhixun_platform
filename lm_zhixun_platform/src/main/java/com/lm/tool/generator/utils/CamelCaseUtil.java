package com.lm.tool.generator.utils;

/**
 * <p>
 * 驼峰与下划线之间互转工具类
 * </p>
 * 
 * @author：bood
 * @date：2020/10/16
 */
public class CamelCaseUtil {

    private static final char SEPARATOR = '_';

    public CamelCaseUtil() {
    }

    /**
     * <p>
     * 转下划线
     * </p>
     *
     * @param s: 
     * @return：java.lang.String
     * @author：bood
     * @date：2020/10/16
     */
    public static String toUnderlineName(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * <p>
     * 转驼峰
     * </p>
     *
     * @param s: 
     * @return：java.lang.String
     * @author：bood
     * @date：2020/10/16
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * 转大写驼峰
     * </p>
     *
     * @param s: 
     * @return：java.lang.String
     * @author：bood
     * @date：2020/10/16
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
