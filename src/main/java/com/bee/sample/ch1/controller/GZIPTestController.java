package com.bee.sample.ch1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bee.sample.ch1.model.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.beans.Encoder;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@RestController
public class GZIPTestController {

    /**
     * 不压缩接口
     *
     * @return String返回
     */
    @RequestMapping("/unGzip.html")
    public String unGzip() {
        return JSON.toJSONString(creatValue());
    }

    /**
     * 压缩接口
     *
     * @param response 响应
     */
    @RequestMapping("/gzip.html")
    public void gzipTest(HttpServletResponse response) {
        List<Student> list = creatValue();
        String str = JSONObject.toJSONString(list);
        String encoding = "UTF-8";
        try {
            byte[] comp = compress(str, encoding);
            //byte[] comp = compress(str.getBytes());
            String compressType = "gzip";
            response.setHeader(HttpHeaders.CONTENT_ENCODING, compressType);
            response.setContentLength(comp.length);
            // response.setContentType(encoding);
            response.setContentType("text/plain;charset=utf-8");
            OutputStream out = response.getOutputStream();
            out.write(comp);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GZIP压缩方法
     *
     * @param str      需要压缩的字符串
     * @param encoding 编码格式
     * @return 返回byte[]
     */
    private byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.finish();
            gzip.close();
            byte[] outByte = out.toByteArray();
            System.out.println(out.toString("UTF-8"));
            out.close();
            return outByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 压缩
     *
     * @param data 需要压缩的数据
     * @return 压缩后的数据
     * @throws Exception io异常
     */
    private byte[] compress(byte[] data) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 压缩
        GZIPOutputStream gos = new GZIPOutputStream(baos);
        int off = 0;
        gos.write(data, off, data.length);
        gos.finish();
        byte[] output = baos.toByteArray();
        baos.flush();
        baos.close();
        return output;
    }

    /**
     * 创建测试对象
     *
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
