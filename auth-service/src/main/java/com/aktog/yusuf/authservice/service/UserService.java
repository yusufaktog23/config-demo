package com.aktog.yusuf.authservice.service;

import com.aktog.yusuf.authservice.dto.CreateUserRequest;
import com.aktog.yusuf.authservice.model.User;
import com.aktog.yusuf.authservice.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository,
                       PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User createUser(CreateUserRequest request) {

        if(userNameExists(request.username())){
            throw new EntityExistsException("User name " + request.username() + " already exists.");
        }
        if(mailExists(request.mail())){
            throw new EntityExistsException("Mail " + request.mail() + " already exists.");
        }


        User user = User.builder()
                .name(request.name())
                .username(request.username())
                .password(bCryptPasswordEncoder.encode(request.password()))
                .mail(request.mail())
                .roles(request.roles())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        return userRepository.save(user);
    }



    public String deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("Id: " + id + " not found!");
        }

        return user.getId() + " has been deleted...";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public boolean userNameExists(String username){
        User user = userRepository.findUserByUsername(username).orElse(null);
        return Objects.nonNull(user);
    }

    private boolean mailExists(String mail) {
        User user = userRepository.findUserByMail(mail).orElse(null);
        return Objects.nonNull(user);
    }


}
