package com.zm.zhuma.commons.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by zhuxiaoma on 18-9-4.
 */
public class AsciiUtils {

    /**
     * ASCII表中可见字符从!开始，偏移位值为33(Decimal)
     */
    private static final char DBC_CHAR_START = 33; // 半角!

    /**
     * ASCII表中可见字符到~结束，偏移位值为126(Decimal)
     */
    private static final char DBC_CHAR_END = 126; // 半角~

    /**
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
     */
    private static final char SBC_CHAR_START = 65281; // 全角！

    /**
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
     */
    private static final char SBC_CHAR_END = 65374; // 全角～

    /**
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
     */
    private static final int CONVERT_STEP = 65248; // 全角半角转换间隔

    /**
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
     */
    private static final char SBC_SPACE = 12288; // 全角空格 12288

    /**
     * 半角空格的值，在ASCII中为32(Decimal)
     */
    private static final char DBC_SPACE = ' '; // 半角空格

    /**
     * 包含全角字符正则
     */
    private static final Pattern EXIST_FULL_CHAR_PATTERN = Pattern.compile(".*[\\uFF00-\\uFFFF]+.*");

    /**
     * 中文编码开始
     */
    private static final char CN_CHAR_START = 19968;

    /**
     * 中文编码结束
     */
    private static final char CN_CHAR_END = 40869;

    /**
     * <PRE>
     * 是否存在全角字符
     * 不包含汉字
     * </PRE>
     */
    public static boolean existFullChar(String targetStr) {
        if (StringUtils.isEmpty(targetStr)) {
            return false;
        }

        return EXIST_FULL_CHAR_PATTERN.matcher(targetStr).matches();
    }

    /**
     * <PRE>
     * 半角字符->全角字符转换
     * 只处理空格，!到˜之间的字符，忽略其他
     * </PRE>
     */
    public static String half2Full(String halfStr) {
        if (StringUtils.isEmpty(halfStr)) {
            return halfStr;
        }

        StringBuilder buf = new StringBuilder(halfStr.length());
        char[] ca = halfStr.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == DBC_SPACE) { // 如果是半角空格，直接用全角空格替代
                buf.append(SBC_SPACE);
            } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) { // 字符是!到~之间的可见字符
                buf.append((char) (ca[i] + CONVERT_STEP));
            } else { // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <PRE>
     * 全角字符->半角字符转换
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
     * </PRE>
     */
    public static String full2Half(String fullStr) {
        if (StringUtils.isEmpty(fullStr)) {
            return fullStr;
        }

        StringBuilder buf = new StringBuilder(fullStr.length());
        char[] ca = fullStr.toCharArray();
        for (int i = 0; i < fullStr.length(); i++) {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] == SBC_SPACE) { // 如果是全角空格
                buf.append(DBC_SPACE);
            } else { // 不处理全角空格，全角！到全角～区间外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <PRE>
     * 是否是中文字符
     * </PRE>
     */
    public static boolean isChinese(Character ch) {
        if (ch == null) {
            return false;
        }

        return ch >= CN_CHAR_START && ch <= CN_CHAR_END;
    }

}
