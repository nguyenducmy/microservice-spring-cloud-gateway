//package com.example.gateway.dto;
//
//import com.example.gateway.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//
//public class CustomeUserDetails implements UserDetails {
//    private User user;
//
//    public CustomeUserDetails(User user) {
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.getAuthority(user);
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    private List<GrantedAuthority> getAuthority(User user) {
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//        List<String> roles = user.getRoles();
//        for (String i : roles) {
//            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(i);
//            grantedAuthorityList.add(simpleGrantedAuthority);
//        }
//        return grantedAuthorityList;
//    }
//}
