package com.ubo.terminal.handler;

import io.netty.buffer.ByteBuf;
import net.sf.json.JSONObject;

public class UtilTest {

    public static void main(String[] args) {

        JSONObject jsonObject = JSONObject.fromObject("{name:yqh,age:1}");
        System.out.println(stringToAscii("[BTOFFPOSITIONALARM,v0,1,200710231200001000,01,yangqinghui;0000;120;120,20071023120000]"));
    }

    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]);
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }


    public static String dumpBuffer2(ByteBuf buffer) {

        StringBuilder builder = new StringBuilder();

        buffer.markReaderIndex();
        while (buffer.isReadable()) {
            for (int i=0; i<16 && buffer.isReadable(); i++) {
                builder.append(String.format("%02x", buffer.readByte()));
                if (i < 15) {
                    builder.append(' ');
                } else {
                    builder.append("\r\n");
                }
            }
        }

        buffer.resetReaderIndex();
      return builder.toString();
    }


}
