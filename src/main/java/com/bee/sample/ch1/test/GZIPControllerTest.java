package com.bee.sample.ch1.test;

import com.alibaba.fastjson.JSONObject;
import com.bee.sample.ch1.controller.HelloworldController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloworldController.class)
public class GZIPControllerTest {
    private static final Logger log = LoggerFactory.getLogger(GZIPControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @Test
    public void testMvc() {

        try {
            String url = "/gzip.html";
            ResultActions perform = mvc.perform(get(url));
            // perform.andExpect(HttpStatus.OK);
            log.info(JSONObject.toJSONString(perform));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
