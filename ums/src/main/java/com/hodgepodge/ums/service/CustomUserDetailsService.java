package com.hodgepodge.ums.service;

import com.hodgepodge.ums.entity.UmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Title: 自定义用户详情服务
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2021年12月17日
 * @since 1.8
 */
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UmsUserService umsUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UmsUser user = umsUserService.lambdaQuery().eq(UmsUser::getUsername, username).one();
        if (user == null){
            throw new UsernameNotFoundException("未找到用户："+username);
        }
        List<GrantedAuthority> authorities = this.loadUserAuthorities(user.getId());
        return new User(username, user.getPassword(), user.getIsEnabled(), true, true, true,
                authorities);
    }

    /**
     * <p>
     * Title: 加载用户权限
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param userId
     * @return java.util.List<org.springframework.security.core.GrantedAuthority>
     * @author 刘小杰
     * @date 2021年12月19日
     * @since 1.8
     */
    protected List<GrantedAuthority> loadUserAuthorities(Long userId) {
        List<GrantedAuthority> authorities = Collections.emptyList();
        //TODO 获取权限
//        List<>  umsUserService.selectUserPermissions(userId);
//        SimpleGrantedAuthority
        return authorities;
    }
}
