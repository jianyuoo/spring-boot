package air.admin.spring_boot.common.Security.service;

import air.admin.spring_boot.login.entity.MyUserDetails;
import air.admin.spring_boot.login.mapper.Loginmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private Loginmapper loginmapper;

    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginmapper.loadUserByUsername(username); // 此处返回实际用户信息
    }

}

