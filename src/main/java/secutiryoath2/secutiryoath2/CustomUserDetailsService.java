package secutiryoath2.secutiryoath2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = new MyUser(ROLE.USER, username, passwordEncoder.encode("1234"),  AuthorityUtils.commaSeparatedStringToAuthorityList(ROLE.ADMIN.name()));
        if (user == null || !user.getUserName().equals(username)) {
            // 如果抛出UsernameNotFoundException，异常会被Security捕捉并替换掉
            throw new DisabledException("userName :" + username + " not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println("name:" + user.getRole().name() + ",getName:" + user.getRole().getName());
        System.out.println("CustomUserDetailsService----BCryptPasswordEncoder" + passwordEncoder);
        /*
         * 设置权限，与http.authorizeRequests().antMatchers("/vip/**").hasAuthority("VIP")中的hasAuthority("VIP")
         * 以及@PreAuthorize注解中的hasAuthority('VIP')、hasAnyAuthority('VIP')对应
         * 不与http.authorizeRequests().antMatchers("/vip/**").hasRole("VIP")中的hasRole("VIP")、
         * http.authorizeRequests().antMatchers("/vip/**").hasAuthority("ROLE_VIP")中的hasAuthority("ROLE_VIP")
         * 以及@PreAuthorize注解中的hasRole('VIP'))、hasRole('ROLE_VIP')、hasAnyRole('VIP')、 hasAnyRole('ROLE_VIP')
         * hasAuthority('ROLE_VIP')、hasAnyAuthority('ROLE_USER','ROLE_VIP')对应，
         */
//        AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().name());
//        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        /*
         * 设置角色，与http.authorizeRequests().antMatchers("/vip/**").hasRole("VIP")中的hasRole("VIP")、
         * http.authorizeRequests().antMatchers("/vip/**").hasAuthority("ROLE_VIP")中的hasAuthority("ROLE_VIP")
         * 以及@PreAuthorize注解中的hasRole('VIP'))、hasRole('ROLE_VIP')、hasAnyRole('VIP')、 hasAnyRole('ROLE_VIP')
         * hasAuthority('ROLE_VIP')、hasAnyAuthority('ROLE_USER','ROLE_VIP')对应，
         * 不与http.authorizeRequests().antMatchers("/vip/**").hasAuthority("VIP")中的hasAuthority("VIP")
         * 以及@PreAuthorize注解中的hasAuthority('VIP')、hasAnyAuthority('VIP')对应
         */
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new MyUser(ROLE.VIP,username, passwordEncoder.encode("1234"), authorities);
    }
}
