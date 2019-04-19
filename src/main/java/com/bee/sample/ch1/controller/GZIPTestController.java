package com.bee.sample.ch1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bee.sample.ch1.model.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@RestController
public class GZIPTestController {

    /**
     * 不压缩接口
     * @return String返回
     */
    @RequestMapping("/unGzip.html")
    public String unGzip() {
        return JSON.toJSONString(creatValue());
    }

    /**
     * 压缩接口
     * @param response 响应
     */
    @RequestMapping("/gzip.html")
    public void gzipTest(HttpServletResponse response) {
        List<Student> list = creatValue();
        String str = JSONObject.toJSONString(list);
        String encoding = "UTF-8";
        byte[] comp = compress(str, encoding);
        String compressType = "gzip";
        response.setHeader(HttpHeaders.CONTENT_ENCODING, compressType);
        response.setContentType(encoding);
        response.setContentLength(comp.length);
        try {
            OutputStream out = response.getOutputStream();
            out.write(comp);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * GZIP压缩方法
     * @param str 需要压缩的字符串
     * @param encoding 编码格式
     * @return 返回byte[]
     */
    private byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * 创建测试对象
     * @return 测试对象List
     */
    private List<Student> creatValue() {
        List<Student> list = new ArrayList<>();
        final int size = 1000;
        for (int i = 0; i < size; i++) {
            Student stu = new Student();
            stu.setId("10086");
            stu.setName("张润程张润程张润程张润程");
            stu.setSex(1);
            list.add(stu);
        }
        return list;
    }
}
