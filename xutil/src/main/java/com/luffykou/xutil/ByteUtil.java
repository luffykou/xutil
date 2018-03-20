package com.luffykou.xutil;

import com.luffykou.xutil.IOUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteUtil {
    /**
     * byte[] 转为 对象
     *
     * @param bytes
     * @return
     */
    public static Object byteToObject(byte[] bytes) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(ois);
            return null;
        }
    }

    /**
     * 对象 转为 byte[]
     *
     * @param obj
     * @return
     */
    public static byte[] objectToByte(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(bos);
            IOUtil.close(oos);
            return null;
        }
    }

    public static String byteToBit(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Byte.SIZE * bytes.length; i++) {
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        }
        return sb.toString();
    }
}
