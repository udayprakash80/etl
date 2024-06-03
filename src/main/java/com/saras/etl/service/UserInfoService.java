package com.saras.etl.service;

import com.saras.etl.entity.Userinfo;
import com.saras.etl.model.UserInfoDetails;
import com.saras.etl.repository.UserInfoRepository;
import com.saras.etl.security.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    public static final Logger LOG = LoggerFactory.getLogger(UserInfoService.class);
    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Userinfo> userDetail = repository.findByEmail(userName);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + userName));
    }


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        Optional<Userinfo> userDetail = repository.findByEmail(email);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }

    public Userinfo addUser(Userinfo user) {
        Userinfo userinfo = null;
        user.setPassword(encoder.encode(user.getPassword()));
        try{
            userinfo = repository.save(user);
        }catch (Exception e){
            LOG.error("Error while creating user" + e.getLocalizedMessage());
        }
        return userinfo;
    }


}

