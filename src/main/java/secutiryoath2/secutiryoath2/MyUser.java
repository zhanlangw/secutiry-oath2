package secutiryoath2.secutiryoath2;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

@Data
public class MyUser extends User implements Serializable {
    private ROLE role;
    private String userName;
    private String password;


    public MyUser(ROLE role, String userName, String password, Collection<GrantedAuthority> authorities) {
        super(userName, password, authorities);
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

}
