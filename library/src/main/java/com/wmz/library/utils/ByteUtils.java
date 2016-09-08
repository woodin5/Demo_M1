package com.wmz.library.utils;

import java.util.Arrays;

/**
 * Created by wmz on 21/6/16.
 */
public class ByteUtils {
    /**
     * byte[]计算全部得到校验码
     *
     * @param bytes
     * @return
     */
    public static byte toAuthCode(byte[] bytes) {
        byte all = 0;
        for (byte b : bytes) {
            all += b;
        }
        return all;
    }

    /**
     * 两个byte[]内容是否相等
     *
     * @param array1
     * @param array2
     * @return
     */
    public static boolean equals(byte[] array1, byte[] array2) {
        return Arrays.equals(array1, array2);
    }

    /**
     * byte[]的16进制 内容是否startWith resouseHex字符串的16进制
     *
     * @param prefix
     * @param resouseHex
     *            (大小写不区分)
     * @return
     */
    public static boolean startWith(String resouseHex, byte[] prefix) {
        return bytesToHex(prefix).startsWith(resouseHex.toUpperCase());
    }

    /**
     * byte[]转16进制
     *
     * @param num
     * @return
     */
    public static String toHex(int num) {
        return String.format("%02X", num);
    }

    /**
     * byte[]转10进制
     *
     * @param bytes
     * @return
     */
    public static int toTen(byte bytes) {
        return bytes&0xff;
    }

    /**
     * 打印byte[]
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        return Arrays.toString(bytes);
    }

    /**
     * byte[]转16进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            StringBuilder sb = new StringBuilder(bytes.length);
            for (byte byteChar : bytes) {
                sb.append(String.format("%02X", byteChar));
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * byte[]转16进制
     *
     * @param bytes
     * @return
     */
    public static String printBytesToHexGoodShow(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            StringBuilder sb = new StringBuilder(bytes.length);
            for (byte byteChar : bytes) {
                sb.append(String.format("%02X", byteChar));
                sb.append("-");
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 字符串转char再转16进制
     *
     * @param charString
     * @return
     */
    public static String charToHex(String charString) {
        char[] charArray = charString.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length);
        for (char c : charArray) {
            sb.append(Integer.toHexString((int) c));
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转换成bytes[]
     */
    public static byte[] hexToBytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }

    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

}
