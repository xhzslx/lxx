package com.example.jpm.librarydemo.tools.util;

import java.util.List;

public class ArraysUtil {

    //list转换String 中间用不同符号隔开
    public static String listToString(List<String> list, String s) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(s);
            }
            result.append(string);
        }
        return result.toString();
    }


    //封装字节数组与参数
    public static byte[] getPacket(String json, byte[] image) {
        byte[] jsonb = json.getBytes();
        int length = image.length + jsonb.length;
        System.out.println(image.length + "    " + jsonb.length);
        byte[] bytes = new byte[length + 1];
        byte[] lengthb = InttoByteArray(jsonb.length, 1);
        System.arraycopy(lengthb, 0, bytes, 0, 1);
        System.arraycopy(jsonb, 0, bytes, 1, jsonb.length);
        System.arraycopy(image, 0, bytes, 1 + jsonb.length, image.length);
        return bytes;

    }

    //将int转换为字节数组
    public static byte[] InttoByteArray(int iSource, int iArrayLen) {

        byte[] bLocalArr = new byte[iArrayLen];
        for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
            bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
        }
        return bLocalArr;
    }

    // 将byte数组bRefArr转为一个整数,字节数组的低位是整型的低字节位
    public static int BytestoInt(byte[] bRefArr) {

        int iOutcome = 0;
        byte bLoop;
        for (int i = 0; i < bRefArr.length; i++) {
            bLoop = bRefArr[i];
            iOutcome += (bLoop & 0xFF) << (8 * i);
        }
        return iOutcome;
    }
}
