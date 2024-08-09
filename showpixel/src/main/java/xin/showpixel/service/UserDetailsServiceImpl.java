package xin.showpixel.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xin.showpixel.model.User;
import xin.showpixel.repositories.RepositoryUser;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepositoryUser repository;

    public UserDetailsServiceImpl(RepositoryUser repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl loadUserByUsername()");

        User user = null;

        if (login.indexOf('@') == -1){
            // you can use your username to log in if you'd like :-)
            user = repository.findByUsername(login).orElse(null);
        }


        if (user == null) {
            user = repository.findByEmail(login).orElseThrow(() ->
                    new UsernameNotFoundException("User not found by username OR email"));
        }

        return user;
    }
}
