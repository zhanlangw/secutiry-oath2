package secutiryoath2.secutiryoath2;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping({"", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/adminRole")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminRole() {
        return "adminRole";
    }

    @GetMapping("/userRole")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'VIP')")
    @PreAuthorize("hasAnyRole('asdfwe', 'USfwesfER', 'USER')")
    public String userRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "userRole";
    }

    @GetMapping("/dbaRole")
    @PreAuthorize("hasRole('DBA')")
    public String dbaRole() {
        return "dbaRole";
    }











    @GetMapping("/vipRole")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_VIP')")
    public String vipRole() {
        return "vipRole";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

    @GetMapping("/aaa")
    @PreAuthorize("hasAnyAuthority('aaaaa')")
    public String aaa() {
        return "redirect:/login?logout";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "403";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

    @GetMapping("/500")
    public String internalServerError() {
        return "500";
    }

    @GetMapping("/vip/info")
    public String info() {
        return "VIP Info";
    }
}
