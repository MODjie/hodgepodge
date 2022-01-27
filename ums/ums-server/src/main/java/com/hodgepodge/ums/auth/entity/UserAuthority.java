package com.hodgepodge.ums.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * Title: UserAuthority
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月27日
 * @since 1.8
 */
@Data
public class UserAuthority implements GrantedAuthority {

    /**
     * 权限
     */
    private String permission;

    /**
     * 权限ID
     */
    private String permissionId;

    @Override
    public String getAuthority() {
        return this.permission;
    }
}
