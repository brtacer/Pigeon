package com.berat.security;

import com.berat.model.UserProfile;
import com.berat.model.UserProfileStatus;
import com.berat.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {
    @Autowired
    private UserProfileService userProfileService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails loadUserById(Long id){
        Optional<UserProfile> userProfile = userProfileService.findById(id);
        if (userProfile.isEmpty())
            return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("User"));
        boolean status = userProfile.get().getStatus().equals(UserProfileStatus.BLOCKED) ? true : false;
        return User.builder()
                .username(userProfile.get().getUsername())
                .password("")
                .accountExpired(false)
                .accountLocked(status)
                .authorities(authorities)
                .build();
    }
}
