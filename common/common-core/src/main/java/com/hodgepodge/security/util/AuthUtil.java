package com.hodgepodge.security.util;

/**
 * <p>
 * Title: JWT工具类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月26日
 * @since 1.8
 */
public class AuthUtil {

    public static final String TOKEN_KEY_PREFIX = "token-";


    /**
     * <p>
     * Title: 获取加密的密钥
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return java.lang.String
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    public static String getSecretKey(Long userId, String username) {
        return userId + "-" + username + "-" + System.currentTimeMillis();
    }

    /**
     * <p>
     * Title: 获取存入redis中token的key
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param userId
     * @param username
     * @return java.lang.String
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    public static String getTokenKeyInRedis(Long userId, String username) {
        return TOKEN_KEY_PREFIX + userId + "-" + username;
    }

}
