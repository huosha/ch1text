package com.bee.sample.ch1.test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ApplicationTests {

    // 密钥
    private final static String secretKey = "69c5deecf03a8ddb2fd934f5@747/c#";
    // 向量
    private final static String iv = "b49e425a";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test3desc() {
        StringBuffer str = new StringBuffer();
        str.append("Serv_Tp=ZF&");
        str.append("Usr_ID=ZF00100593429&");
        str.append("Cst_Nm=郭六一&");
        str.append("Crdt_TpCd=1010&");
        str.append("Crdt_No=32032319710809718X");

        String plainText =str.toString();
        try {
            String encode = encode(plainText);
            System.out.println("encode:" + encode);
            String decode = decode(encode);
            System.out.println("decode:" + decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        System.out.println("encryptData:" + new String(encryptData));
        return Base64.encode(encryptData);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData, encoding);
    }


    @Test
    public void test() {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

        String key = "user_like_title_id";
        setRedis(key, "0001");
        setRedis(key, "0002");
        setRedis(key, "0003");
        setRedis(key, "0004","005","005","100","111");
        setRedisList(key);
        log(key);

        Long size = stringRedisTemplate.opsForSet().size(key);
        while (size != null && 100 > size) {
            String pop = stringRedisTemplate.opsForSet().pop(key);
            System.out.println(pop);
            stringRedisTemplate.opsForSet().remove(key,pop);
            log(key);

            int num = 100;
            Set<String> strings = stringRedisTemplate.opsForSet().distinctRandomMembers(key, num);
            System.out.println(strings != null ? strings.toString() : "");
            if (strings != null) {
                String[] arry = strings.toArray(new String[100]);
                stringRedisTemplate.opsForSet().remove(key, (Object) arry);
            }
            log(key);
            size = stringRedisTemplate.opsForSet().size(key);
        }
        Set<String> members = stringRedisTemplate.opsForSet().members(key);
        System.out.println(members != null ? members.toString() : null);
        if (members != null) {
            String[] arry = members.toArray(new String[100]);
            stringRedisTemplate.opsForSet().remove(key, (Object) arry);
            log(key);
        }

    }

    private void log(String key) {
        Set<String> members = stringRedisTemplate.opsForSet().members(key);
        System.out.println("members====" + (members != null ? members.toString() : null));
    }

    private void setRedis(String key, String... value) {
        stringRedisTemplate.opsForSet().add(key,value);
    }

    private void setRedisList(String key) {
        for (int i = 0; i < 100 ; i++) {
            stringRedisTemplate.opsForSet().add(key, String.valueOf(i));
        }
    }
}