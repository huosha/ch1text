package com.bee.sample.ch1.controller;

import com.bee.sample.ch1.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/file")
public class FileTestController {
    //private static final Logger log = LoggerFactory.getLogger(FileTestController.class);

    /**
     * 上传文件并保存到本地
     *
     * @param uploadFile1 上传的文件
     * @param stu         其他参数
     * @return 结果
     */
    @RequestMapping(path = "upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Student updateFile(Student stu, @RequestParam(name = "file1", required = false) MultipartFile uploadFile1) {
        try {
            // 获取文件名称(包含文件后缀)
            String uploadFile1Name = uploadFile1.getOriginalFilename();
            // 设置文件存放路径
            String pathname = "d:/" + uploadFile1Name;
            // 新建文件
            File file = new File(pathname);
            // 创建文件
            //file.createNewFile();
            // 读写文件
            FileOutputStream out = new FileOutputStream(file);
            InputStream stream = uploadFile1.getInputStream();
            byte[] red = new byte[1024];
            while ((stream.read(red)) != -1) {
                out.write(red);
            }
            stream.close();
            out.close();
            if (stu.getImagePath() == null)
            stu.setImagePath(pathname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stu;
    }
}
