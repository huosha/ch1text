package com.bee.sample.ch1.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import sun.misc.BASE64Encoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Des加解密工具类
 *
 * @author Lee
 */
public class DesUtil {

    // 密钥
    private final static String secretKey = "69c5deecf03a8ddb2fd934f5@747/c#";
    // 向量
    private final static String ivs = "b49e425a";
    /**
     * DESCBC加密
     *
     * @param src
     *            数据源
     * @param key
     *            密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static String encryptDESCBC( String src,  String key) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(secretKey.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);
        IvParameterSpec iv = new IvParameterSpec(ivs.getBytes("UTF-8"));
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
        final byte[] b = cipher.doFinal(src.getBytes());
        final BASE64Encoder encoder = new BASE64Encoder();
        // return encoder.encode(b);
        return  new String(Base64.encodeBase64(b), "utf-8");

    }

    /**
     * DESCBC解密
     *
     * @param src
     *            数据源
     * @param key
     *            密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static String decryptDESCBC(String src, String key) throws Exception {
        // --通过base64,将字符串转成byte数组
        final byte[] bytesrc = Base64.decodeBase64(src);
        // --解密的key
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);
        IvParameterSpec iv = new IvParameterSpec(ivs.getBytes("UTF-8"));
        // --Chipher对象解密
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
        final byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);
    }

    private static void posth(String url) {
        try {
            PostMethod postMethod = null;
            postMethod = new PostMethod(url) ;
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;
            //参数设置，需要注意的就是里边不能传NULL，要传空字符串
            NameValuePair[] data = {
                    new NameValuePair("TXCODE","EC2000"),
                    new NameValuePair("Chnl_TpCd","H1"),
                    new NameValuePair("ccbParam","882XsZVPRNOJ8cpSHEpmxO2hegee/SPo4eXjn1NV6YS3DI0cX+gLejVPuoWot30sEaQynrxte2u6A7RYMHd29acNZ7982EGIPZvEjNoYFO3DqR3MNNKbjzcx0gaGRz50rMbSTh3t3oX4=")
            };
            postMethod.setRequestBody(data);
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            int response = httpClient.executeMethod(postMethod); // 执行POST方法
            String result = postMethod.getResponseBodyAsString() ;
            System.out.println("========================");
            System.out.println(response);
            System.out.println(result);
            System.out.println("===========================");
        } catch (Exception e) {
            // logger.info("请求异常"+e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String httpPost(String reqUrl, String outStr) {
        ObjectMapper mapper =   new ObjectMapper();
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type","application/x-www-form-urlencoded");
            if (null != outStr) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outStr.getBytes("utf-8"));
                outputStream.close();
            }
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buff = new StringBuffer();
            while((str = bufferedReader.readLine()) != null) {
                buff.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            System.out.println("**********************");
            System.out.println(buff.toString());
            System.out.println("**********************");
            return buff.toString();
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args) throws Exception {
        String reqUrl = "https://bankservice.ccbhome.cn/LHECISM/LanHaiDedicatedService";
        String outStr = "82XsZVPRNOJ8cpSHEpmxO2hegee/SPo4eXjn1NV6YS3DI0cX+gLejVPuoWot30sEaQynrxte2u6A7RYMHd29acNZ7982EGIPZvEjNoYFO3DqR3MNNKbjzcx0gaGRz50rMbSTh3t3oX4=";
        String aaaa = "TXCODE=EC2000&Chnl_TpCd=H1&ccbParam="+URLEncoder.encode(outStr,"utf-8");
        //httpPost(reqUrl,aaaa);
        //posth(reqUrl);

        //String sss = "Serv_Tp=ZF&Usr_ID=ZF00100032138&Cst_Nm=倪慧&Crdt_TpCd=1010&Crdt_No=32102719910820394X&Rsrv_TpCd=1";
        String sss = "Serv_Tp=ZF&Ctr_ID=201907261503055380&User_Tp=1&Crdt_No=32102719910820394X";
        //telePhone_encrypt = URLEncoder.encode(encryptDESCBC(telePhone, secretKey),"utf-8");
        String telePhone_encrypt = encryptDESCBC(sss, secretKey);
        System.out.println("456{}"+ telePhone_encrypt);

        // 解密流程
        String tele_decrypt = decryptDESCBC(telePhone_encrypt, secretKey);
        System.out.println("模拟代码解密:" + tele_decrypt);
    }

}