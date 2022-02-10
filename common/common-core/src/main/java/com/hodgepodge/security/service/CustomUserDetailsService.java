package com.hodgepodge.security.service;

import com.hodgepodge.security.entity.UserAuthority;
import com.hodgepodge.security.entity.UserDO;
import com.hodgepodge.security.entity.UserDetailsVO;
import com.hodgepodge.security.service.impl.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
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

//    private final IUserService iUserService = new IUserServiceImpl();

    @Qualifier("iUserService")
    @Autowired
    private IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDO user = iUserService.getOneByUsername(username);
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
        return iUserService.selectUserAuthorities(userId);
    }
}
