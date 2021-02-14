package com.manager.manager.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manager.manager.domain.Worker;

/**
 * 登陆处理
 *
 * @author lsy <liushuoyang03@kuaishou.com>
 * Created on 2021-02-14
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WorkerService workerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        Worker user = workerService.queryWorkerBySysName(username);

        // 判断用户是否存在
        if(user == null || StringUtils.isBlank(user.getSysName())) {
            throw new UsernameNotFoundException("用户名不存在");
        }
//
//        // 添加权限
//        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
//        for (SysUserRole userRole : userRoles) {
//            SysRole role = roleService.selectById(userRole.getRoleId());
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        // 返回UserDetails实现类
        return new User(user.getSysName(),user.getPassword(), authorities);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.matches("123456", "$2a$10$QlTfhS65JK35XpC.7vscPuN9.FGDYWRjEHNWcqcmaLO2Dr2qxXLqW"));
    }
}
