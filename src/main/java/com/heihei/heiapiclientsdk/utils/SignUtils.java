package com.heihei.heiapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {

    /**
     * 生成签名工具
     * @param body
     * @param secretKey
     * @return
     */
    public static String getSign(String body, String secretKey) {

        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;

        // 5393554e94bf0eb6436f240a4fd71282
        String hex = md5.digestHex(content);
        return hex;
    }
}
