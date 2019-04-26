package com.security.sec.auth;

import com.security.sec.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户信息实体类
 */
public class UserDetail implements UserDetails {

    private User user;
    private String id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 通过构造方法在UserDetailsServcie的方法中将查到的user注入进去
     * @param user
     */
    public UserDetail(User user){
        this.user = user;
        if ( user != null){
            this.id = user.getId();
        }
    }

    /**
     * 对当前的用户赋予其应有的权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List authorisList = new ArrayList();
        authorisList.add(new SimpleGrantedAuthority("GG"));
        authorisList.add(new SimpleGrantedAuthority("ROLE_AA"));
        authorisList.add(new SimpleGrantedAuthority("ROLE_BB"));
        authorisList.add(new SimpleGrantedAuthority("ROLE_CC"));
        authorisList.add(new SimpleGrantedAuthority("ROLE_DD"));
        return authorisList;
    }

    /**
     * 获取密码
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户名
     * @return
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 证书是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否有效，对应数据库中的delete_flag 字段
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
