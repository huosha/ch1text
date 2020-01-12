package deamo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bee.sample.ch1.interceptor.Constants;
import com.bee.sample.ch1.interceptor.MD5Utils;
import com.bee.sample.ch1.interceptor.ResultInfo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import okhttp3.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestController {

    private static final String URL_PREFIX = "http://localhost:8080/";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testQuery() {
        ResultInfo resultInfo = restTemplate.getForObject(URL_PREFIX + "/query?origin=1&sign=f8a7e51875f63413479d561248398264", ResultInfo.class);
        Assert.isTrue(resultInfo.getCode() == 0, "Query Failed");
    }

    @Test
    public void testClear() {
        Map<String, Object> request = new HashMap<>();
        request.put(Constants.SIGN_ORIGIN_KEY, "1");
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        request.put("list",list.toString());
//        byte[] bytes = readFromByteFile("C:/aow_drv.log");
//        String encode = Base64.encode(bytes);
//        request.put("file", encode);
        request.put(Constants.SIGN_KEY, MD5Utils.creatSign(request));
        ResultInfo resultInfo = restTemplate.postForObject(URL_PREFIX + "/clear", request, ResultInfo.class);
        Assert.isTrue(resultInfo.getCode() == 0, "Query Failed");
    }

    public byte[] readFromByteFile(String pathname) {
        byte[] content = new byte[0];
        try {
            File filename = new File(pathname);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while((size = in.read(temp)) != -1){
                out.write(temp, 0, size);
            }
            in.close();
            content = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    @Test
    public void testPost() throws IOException {
        OkHttpClient client = new OkHttpClient();
        JSONObject body = new JSONObject();
        body.put("name","123");
        Map<String, String> params = JSONObject.parseObject(body.toJSONString(), new TypeReference<Map<String, String>>(){});
        params.put("v","1.0");
        body.put(Constants.SIGN_KEY,MD5Utils.stringToMD5(params));
        Request request = new Request.Builder()
                .url(URL_PREFIX + "/clear")
                .addHeader("v","1.0")
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body.toJSONString()))
                .build();
        Response response = client.newCall(request).execute();

    }
}

