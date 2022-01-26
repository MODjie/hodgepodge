package com.hodgepodge.ums.auth.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * <p>
 * Title: UserDetailsVO
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月26日
 * @since 1.8
 */
public class UserDetailsVO extends User {

    @Getter
    @Setter
    private Long userId;

    public UserDetailsVO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }
}
