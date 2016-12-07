package com.appzone.postup.backend.security;

import com.appzone.postup.backend.model.UserEntity;
import com.appzone.postup.backend.model.UserEntity.UserRole;
import com.appzone.postup.backend.model.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mohamed Morsy
 */
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("username " + username + " not found");
        }

        Set<GrantedAuthority> grantedAuthoritys = new HashSet();

        UserRole userRole = userEntity.getUserRole();
            
        grantedAuthoritys.add(new SimpleGrantedAuthority(userRole.name()));
        
        return new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthoritys);
    }
    
}
