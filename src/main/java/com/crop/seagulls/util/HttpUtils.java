package com.crop.seagulls.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

    private static Logger logger = Logger.getLogger(HttpUtils.class);

    public static String httpGet(String url) {
        String result = StringUtils.EMPTY;
        HttpGet request = new HttpGet(url);
        try {
            // 设置请求头
            request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
            // 用逗号分隔显示可以同时接受多种编码
            request.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            request.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

            HttpClient client = HttpClients.custom().build();
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == (HttpStatus.SC_OK)) {
                InputStream in = response.getEntity().getContent();
                result = readString(in, "utf-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            logger.error("Http get error url:{0}", e, url);
        }
        return result;
    }

    private static String readString(InputStream in, String encoding) throws Exception {
        byte[] data = new byte[1024];
        int length = 0;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        while ((length = in.read(data)) != -1) {
            bout.write(data, 0, length);
        }
        return new String(bout.toByteArray(), encoding);
    }

}
