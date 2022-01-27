package com.hodgepodge.ums.auth.service;

import com.hodgepodge.ums.auth.entity.UserAuthority;
import com.hodgepodge.ums.auth.entity.UserDetailsVO;
import com.hodgepodge.ums.entity.UserDO;
import com.hodgepodge.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

        UserDO user = userService.getOneByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("未找到用户："+username);
        }
        List<UserAuthority> authorities = this.loadUserAuthorities(user.getId());
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
    protected List<UserAuthority> loadUserAuthorities(Long userId) {
        return userService.selectUserAuthorities(userId);
    }
}
