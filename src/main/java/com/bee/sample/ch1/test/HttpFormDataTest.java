package com.bee.sample.ch1.test;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpFormDataTest {
    private static final Logger log = LoggerFactory.getLogger(HttpFormDataTest.class);

    public static void main(String[] args) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,String> postParam = new HashMap<String,String>();
        postParam.put("Serv_Tp","ZF");
        postParam.put("CCB_IBSVersion","V6");
        postParam.put("Chnl_TpCd","H1");
        postParam.put("TXCODE","EC3001"); postParam.put("File_Tp","PDF");
        postParam.put("User_Tp","1");
        postParam.put("Cst_Nm","倪慧");
        postParam.put("Crdt_TpCd","1010");
        postParam.put("Crdt_No","32102719910820394X");
        postParam.put("MblPh_No","18620798212");
        postParam.put("S_User_Tp","1");
        postParam.put("S_Cst_Nm","唐慧");
        postParam.put("S_Crdt_TpCd","1010");
        postParam.put("S_Crdt_No","511602198904065994");
        postParam.put("S_MblPh_No","13908127776");
        postParam.put("PT_STYLE","10");
        postParam.put("S_Days","364");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            //把一个普通参数和文件上传给下面这个地址    是一个servlet
            String postUrl = "http://127.0.0.1:8080/say.html";
            HttpPost httpPost = new HttpPost(postUrl);
            int time = 3*1000;
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(time)
                    .setSocketTimeout(time).setConnectTimeout(time).build();
            httpPost.setConfig(requestConfig);
            //把文件转换成流对象FileBody
            File postFile = new File("G:/123.pdf");
            FileBody fundFileBin = new FileBody(postFile);
            //设置传输参数
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            multipartEntity.addPart(postFile.getName(), fundFileBin);//相当于<input type="file" name="media"/>
            //设计文件以外的参数
            Set<String> keySet = postParam.keySet();
            for (String key : keySet) {
                //相当于<input type="text" name="name" value=name>
                multipartEntity.addPart(key, new StringBody(postParam.get(key), ContentType.create("text/plain", Consts.UTF_8)));
            }
            HttpEntity reqEntity =  multipartEntity.build();
            httpPost.setEntity(reqEntity);
            log.info("发起请求的页面地址 " + httpPost.getRequestLine());
            long star=System.currentTimeMillis();
            //发起请求   并返回请求的响应
            CloseableHttpResponse response =null;
            try {
                response = httpClient.execute(httpPost);
                long end=System.currentTimeMillis();
                log.info("time ====={}",end-star);
                log.info("----------------------------------------");
                //打印响应状态
                //log.info(response.getStatusLine());
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
                EntityUtils.consume(resEntity);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally{
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("uploadFileByHTTP result:"+resultMap);

    }
}
