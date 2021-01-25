package com.manager.manager.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public static String ContentTypeXml = "application/xml";
    public static String ContentTypeJson = "application/json";
    public static String ContentTypeForm = "application/x-www-form-urlencoded";

    public static String POST = "POST";
    public static String GET = "GET";

    //由于 errbook 并发获取json 达到150 左右，所以将 timeout从 5s 修改为20s
    public static int DefaultTimeout = 1000*20;

    public static String convertToFormUrlencodedString(Map<String, String> parameterMap){
        if(parameterMap == null || parameterMap.size() == 0){
            return null;
        }

        String params = "";
        for(Map.Entry<String, String> entry : parameterMap.entrySet()){
            String k = entry.getKey();
            String v = entry.getValue();
            params += k + "=" + v + "&";
        }
        params = StringUtils.substringBeforeLast(params, "&");

        return params;
    }

    public static String sendRequest(String uri, String requestMethod, Map<String,String> requestProperties, String contentType, String contentBody) throws IOException {
        log.info("发送HTTP请求, url={}, requestMethod={}, requestProperties={}, contentType={}, contentBody={}",
                uri, requestMethod, JSON.toJSONString(requestProperties), contentType, contentBody);
        URL url = new URL(uri);
        HttpURLConnection conn = null;
        OutputStream out = null;
        BufferedReader in = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(DefaultTimeout);
            conn.setReadTimeout(DefaultTimeout);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            if (requestProperties!=null && !requestProperties.isEmpty()){
                for (Map.Entry<String,String> entry : requestProperties.entrySet()){
                    conn.setRequestProperty(entry.getKey(),entry.getValue());
                }
            }
            if (contentBody != null) {
                conn.setRequestProperty("Content-Type", contentType);
                out = conn.getOutputStream();
                out.write(contentBody.getBytes());
                out.flush();
            }
            conn.connect();
            StringBuffer content = new StringBuffer();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }
            } else {
                String resMessage = conn.getResponseMessage();
                String location = conn.getHeaderField("Location");
                log.error("failed, url={}, code={}, message={}, location={}", uri, responseCode, resMessage, location);
            }
            return content.toString();
        }
        catch (IOException e) {
            log.error("Failed to get http response from: " + uri + ", due to: " + e.getMessage(), e);
            throw e;
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                }
            }
        }
    }
}
