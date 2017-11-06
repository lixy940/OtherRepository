package com.kuaixin.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/4/24.
 * @description:httpClient请求
 */
public class HttpClientHandleUtils {


    /***
     * post请求
     * @param url 请求路径
     * @param paramValues 参数
     * @param cls Class类
     */
    public Object requestByPostMethod(String url,Map<String, String> paramValues,Class cls){

        CloseableHttpClient httpClient = getHttpClient();
        Object obj = null;
        try {
            HttpPost post = new HttpPost(url);
            List<NameValuePair> list = new ArrayList<>();
            Set<String> keys = paramValues.keySet();
            //将map对象按照封装为NameValuePair集合对象
            list.addAll(keys.stream().map(key -> new BasicNameValuePair(key, paramValues.get(key))).collect(Collectors.toList()));
            UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(urlEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    String str = EntityUtils.toString(entity, Charset.forName("UTF-8"));
                    //将返回对象解析为客户端调用的Class类型
                    obj  = JSONObject.parseObject(str, cls);
//                    JSONObject.toJSONString(obj);
                }
            }finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
              closeHttpClient(httpClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return obj;

    }

    /***
     * get 请求
     * @param url 请求路径
     * @param cls Class类
     */
    public Object requestByGetMethod(String url,Class cls){

        CloseableHttpClient httpClient = getHttpClient();
        Object obj = null;
        try {
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    String str = EntityUtils.toString(entity, Charset.forName("UTF-8"));
                    //将返回对象解析为客户端调用的Class类型
                    obj  = JSONObject.parseObject(str, cls);
                }
            }finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                closeHttpClient(httpClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return obj;

    }

    //创建client
    private  CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    //关闭client
    private  void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null) {
            client.close();
        }
    }


}
