package com.bee.sample.ch1.test;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpFormDataTest {
    private static final Logger log = LoggerFactory.getLogger(HttpFormDataTest.class);

    public static void main(String[] args) {
        int initialCapacity = 27;
        Map<String, Object> resultMap = new HashMap<>(initialCapacity);
        Map<String, String> postParam = new HashMap<>(initialCapacity);
        postParam.put("Chnl_TpCd", "H1");
        postParam.put("TXCODE", "EC3001");
        postParam.put("Serv_Tp", "ZF");
        postParam.put("CCB_IBSVersion", "V6");
        postParam.put("File_Nm", "123.pdf");
        postParam.put("File_Tp", "PDF");
        postParam.put("User_Tp", "1");
        postParam.put("Cst_Nm", "倪慧");
        postParam.put("Crdt_TpCd", "1010");
        postParam.put("Crdt_No", "32102719910820394X");
        postParam.put("S_Self_Sign", "1");
        postParam.put("MblPh_No", "18620798212");
        postParam.put("S_User_Tp", "1");
        postParam.put("S_Cst_Nm", "唐慧");
        postParam.put("S_Crdt_TpCd", "1010");
        postParam.put("S_Crdt_No", "511602198904065994");
        postParam.put("S_MblPh_No", "13908127776");
        postParam.put("PT_STYLE", "10");
        postParam.put("S_Days", "364");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost("https://bankservice.ccbhome.cn/LHECISM/LanHaiDedicatedFileService");
            //设置传输参数
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            //把文件转换成流对象FileBody
            File postFile = new File("123.pdf");
            multipartEntity.addPart("file", new FileBody(postFile));
            for (Map.Entry<String, String> entry : postParam.entrySet()) {
                multipartEntity.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.create("text/plain", Consts.UTF_8)));
            }
            HttpEntity reqEntity = multipartEntity.build();
            httpPost.setEntity(reqEntity);
            long b = httpPost.getEntity().getContentLength();
            log.info("发起请求的页面地址 " + b);
            long star = System.currentTimeMillis();
            //发起请求并返回请求的响应
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                long end = System.currentTimeMillis();
                log.info("time ====={}", end - star);
                resultMap.put("statusCode", response.getStatusLine().getStatusCode());
                //获取响应对象
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    //打印响应长度
                    log.info("Response content length: " + resEntity.getContentLength());
                    //打印响应内容
                    resultMap.put("data", EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
                }
            } catch (Exception e) {
                log.error("Exception{}", e);
            }
        } catch (Exception e1) {
            log.error("Exception{}", e1);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("uploadFileByHTTP result:" + resultMap);
    }

    public static Map<String, Object> test() {
        int initialCapacity = 16;
        Map<String, Object> resultMap = new HashMap<>(initialCapacity);
        Map<String, String> postParam = new HashMap<>(initialCapacity);
        postParam.put("Chnl_TpCd", "H1");
        postParam.put("TXCODE", "EC3001");
        postParam.put("Serv_Tp", "ZF");
        postParam.put("CCB_IBSVersion", "V6");
        postParam.put("File_Nm", "123.pdf");
        postParam.put("File_Tp", "PDF");
        postParam.put("User_Tp", "1");
        postParam.put("Cst_Nm", "倪慧");
        postParam.put("Crdt_TpCd", "1010");
        postParam.put("Crdt_No", "32102719910820394X");
        postParam.put("S_Self_Sign", "1");
        postParam.put("MblPh_No", "18620798212");
        postParam.put("S_User_Tp", "1");
        postParam.put("S_Cst_Nm", "唐慧");
        postParam.put("S_Crdt_TpCd", "1010");
        postParam.put("S_Crdt_No", "511602198904065994");
        postParam.put("S_MblPh_No", "13908127776");
        postParam.put("PT_STYLE", "10");
        postParam.put("S_Days", "364");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String postUrl = "http://bankservice.ccbhome.cn/LHECISM/LanHaiDedicatedFileService";
            HttpPost httpPost = new HttpPost(postUrl);
//            int time = 300*1000;
//            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(time)
//                    .setSocketTimeout(time).setConnectTimeout(time).build();
//            httpPost.setConfig(requestConfig);
            //把文件转换成流对象FileBody
            File postFile = new File("D:/123.pdf");
            FileBody fundFileBin = new FileBody(postFile);
            //设置传输参数
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            multipartEntity.addPart("File_Nm", fundFileBin);
            //设计文件以外的参数
            Set<String> keySet = postParam.keySet();
            for (String key : keySet) {
                multipartEntity.addPart(key, new StringBody(postParam.get(key), ContentType.create("text/plain", Consts.UTF_8)));
            }
            HttpEntity reqEntity = multipartEntity.build();
            httpPost.setEntity(reqEntity);
            log.info("发起请求的页面地址 " + httpPost.getRequestLine());
            long star = System.currentTimeMillis();
            //发起请求   并返回请求的响应
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                long end = System.currentTimeMillis();
                log.info("time ====={}", end - star);
                resultMap.put("statusCode", response.getStatusLine().getStatusCode());
                //获取响应对象
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    //打印响应长度
                    log.info("Response content length: " + resEntity.getContentLength());
                    //打印响应内容
                    resultMap.put("data", EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
                }
                //销毁
//                EntityUtils.consume(resEntity);
            } catch (Exception e) {
                log.error("Exception{}", e);
            }
//            finally {
//                if (response != null) {
//                    response.close();
//                }
//            }
//        } catch (ClientProtocolException e1) {
//            log.error("ClientProtocolException{}",e1);
//        } catch (IOException e1) {
//            log.error("IOException{}",e1);
        } catch (Exception e1) {
            log.error("Exception{}", e1);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("uploadFileByHTTP result:" + resultMap);
        return resultMap;
    }

}
