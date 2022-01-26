package com.hodgepodge.ums.auth.service;

import com.hodgepodge.ums.auth.entity.UserDetailsVO;
import com.hodgepodge.ums.entity.UserDO;
import com.hodgepodge.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDO user = userService.lambdaQuery().eq(UserDO::getUsername, username).one();
        if (user == null){
            throw new UsernameNotFoundException("未找到用户："+username);
        }
        List<GrantedAuthority> authorities = this.loadUserAuthorities(user.getId());
        return new UserDetailsVO(username, user.getPassword(), user.getIsEnabled(), true, true, true,
                authorities,user.getId());
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
