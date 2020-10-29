package cn.i4.data.cloud.base.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * DES加密 解密算法:可逆
 * @author wangjc
 * @title: DesUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/28-11:31
 */
public class DesUtil {

    private final static String DES = "DES";
    private final static String ENCODE = "UTF-8";
    private final static String defaultKey = "i4-data-cloud";

    public static void main(String[] args) {
        String data = "Wangjc@697295";
        System.out.println(enc(data));
        System.out.println(dec(enc(data)));

    }

    /**
     * 使用 默认key 加密
     * @return String
     */
    public static String enc(String data){
        try {
            byte[] bt = enc(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
            String strs = new BASE64Encoder().encode(bt);
            return strs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用 默认key 解密
     * @return String
     */
    public static String dec(String data){
        try {
            if (data == null)
                return null;
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = dec(buf, defaultKey.getBytes(ENCODE));
            return new String(bt, ENCODE);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws Exception
     */
    public static String enc(String data, String key) {
        try {
            byte[] bt = enc(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
            return new BASE64Encoder().encode(bt);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String dec(String data, String key) {
        try {
            if (data == null)
                return null;
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = dec(buf, key.getBytes(ENCODE));
            return new String(bt, ENCODE);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key
     *            加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] enc(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key
     *            加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] dec(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}
