package ru.kazantsev.myeighthuirestdbservice.service;

import ru.kazantsev.myeighthuirestdbservice.entity.User;
import ru.kazantsev.myeighthuirestdbservice.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(userNameOrEmail);
        if(user != null)
        {
            return new org.springframework.security.core.userdetails.User(user.getName(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
        }
        else
        {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
