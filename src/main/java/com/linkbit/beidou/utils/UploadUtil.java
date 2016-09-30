package com.linkbit.beidou.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by huangbin on 2016/2/22 0022.
 * 上传附件工具类
 */
public class UploadUtil {
    public static void uploadFile(MultipartFile file, String filePath) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
                BufferedOutputStream stream = new BufferedOutputStream(fileOutputStream);
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

