package com.heihei.heiapiclientsdk.client;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.heihei.heiapiclientsdk.model.User;
import com.heihei.heiapiclientsdk.utils.SignUtils;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 * @author HeiHei
 */
public class HeiApiClient {
    //调用的标识 userA, userB (复杂、无序、无规律)
    private String accessKey;

    //密钥 （复杂、无序、无规律），**该参数不能放在请求头中**
    private String secretKey;

    public static final String GATEWAY_HOST  = "http://localhost:8090";

    //

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String res = HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
        return res;
    }

    public HeiApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String res = HttpUtil.post(GATEWAY_HOST+ "/api/name/", paramMap);
        return res;
    }

    private Map<String, String> getHeader (String body) {
        Map<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        //一定不能发送给后端
        //map.put("secretKey", secretKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("body", body);
        map.put("timestamp", DateUtil.now());
        map.put("sign", SignUtils.getSign(body, secretKey));
        return map;
    }

    public String getUsernameByPost(User user) {

        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeader(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String res = httpResponse.body();
        return res;
    }
}
