package com.hodgepodge.ums.config;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 * Title: MD5加密类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2021年12月19日
 * @since 1.8
 */
@Slf4j
public class Md5Encoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String encode = md5.digestHex(String.valueOf(rawPassword));
        return encode;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("请输入密码");
        }

        if (encodedPassword == null || encodedPassword.length() == 0) {
            log.warn("数据库密码为空");
            return false;
        }
        if (this.encode(rawPassword).equals(encodedPassword)){
            return true;
        }
        return false;
    }
}
