package com.bee.sample.ch1.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class MD5Utils {

    private static final String ALGORITHM_NAME = "md5";

    private static final String SECRET_KEY = "dfasuiyhkuhjk2t5290wouojjeerweeqwqdfd";

    private static final int RADIX = 16;

    private static final int LEN = 32;

    private static final String ADD_STR = "0";

    /**
     * 转换成对应的MD5信息
     *
     * @param paramMap
     * @return
     */
    public static String stringToMD5(Map<String, String> paramMap) {
        String covertString = covertParamMapToString(paramMap);
        return toMd5(covertString);
    }

    /**
     * 转换成对应的string信息
     *
     * @param paramMap
     * @return
     */
    private static String covertParamMapToString(Map<String, String> paramMap) {
        Set<String> sets = paramMap.keySet();
        List<String> valueList = new ArrayList<>();
        for (String key : sets) {
            if (key.equals(Constants.SIGN_KEY)) {
                continue;
            }
            String value = paramMap.get(key);
            valueList.add(value);
        }
        // 此处可以使用TreeMap
        Collections.sort(valueList);
        String jsonString = new Gson().toJson(valueList);
        jsonString = jsonString + SECRET_KEY;
        return jsonString;
    }

    private static final Logger log = LoggerFactory.getLogger(MD5Utils.class);

    /**
     * 转换成对应的MD5信息
     *
     * @param paramMap
     * @return
     */
    public static String creatSign(Map<String, ?> paramMap) {
        paramMap = paramMap.entrySet().stream().filter(map -> !(map.getValue() instanceof byte[]))
                .collect(Collectors.toMap(h -> h.getKey(), h -> h.getValue()));
        paramMap.remove("sign");
        String s = JSON.toJSONString(paramMap);
        Map<String, String> stringStringMap = JSONObject.parseObject(s,
                new TypeReference<Map<String, String>>() {
                });
        String covertString = SECRET_KEY + JSON.toJSONString(stringStringMap, SerializerFeature.MapSortField) + SECRET_KEY;
        log.info("creatSign===" + covertString);
        String md5code = toMd5(covertString);
        log.info("sogn===" + md5code);
        return md5code;
    }


    private static String toMd5(String covertString) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance(ALGORITHM_NAME).digest(
                    covertString.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such MD5 Algorithm.");
        }
        String md5code = new BigInteger(1, secretBytes).toString(RADIX);
        for (int i = 0; i < LEN - md5code.length(); i++) {
            md5code = ADD_STR + md5code;
        }
        return md5code;
    }

}

