package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by weijia on 2017-03-02.
 * 加密解密工具类
 */
public class CodeUtils {


    /**
     * 取字符串的MD5摘要 32位小写
     * @param code 待加密的字符串
     * @return MD5加密后的字符串 32位小写
     */
    public static String getMD5(String code){
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(code.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Logger logger = LogManager.getLogger(CodeUtils.class);
            logger.error("MD5加密出错,原code为:"+code);
            return "";
        }
    }
}
