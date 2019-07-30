package com.bee.sample.ch1.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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