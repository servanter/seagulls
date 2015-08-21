package com.crop.seagulls.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;

public class UploadUtils {

    /**
     * 5MB
     */
    public static final long DEFAULT_MAX_SIZE = 5 * 1024 * 1024L;

    public static final String WEB_PATH = "/images/upload/";

    public static Response upload(HttpServletRequest request) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.ERROR);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
        // 获得文件名：
        String realFileName = file.getOriginalFilename();
        if (file.getSize() >= DEFAULT_MAX_SIZE) {
            response.setReturnCode(ReturnCode.UPLOAD_OVERFLOW_ERROR);
        } else {
            String newRealFileName = System.currentTimeMillis() + realFileName.substring(realFileName.indexOf("."));
            // 获取路径
            String ctxPath = request.getSession().getServletContext().getRealPath("//") + "//images//upload//";

            // 创建文件
            File dirPath = new File(ctxPath);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
            File uploadFile = new File(ctxPath + newRealFileName);
            try {
                FileCopyUtils.copy(file.getBytes(), uploadFile);
                List<String> webPaths = new ArrayList<String>();
                webPaths.add(WEB_PATH + newRealFileName);
                response.setResult(webPaths);
                response.setReturnCode(ReturnCode.SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
