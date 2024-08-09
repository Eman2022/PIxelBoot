package xin.showpixel.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import xin.showpixel.model.Role;
import xin.showpixel.request.RequestLoginAuth;
import xin.showpixel.response.ResponseApi;
import xin.showpixel.model.User;
import xin.showpixel.repositories.RepositoryUser;
import xin.showpixel.security.JwtService;

import java.util.*;


@Service
public class ServiceAuthentication {

    private final RepositoryUser repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public ServiceAuthentication(RepositoryUser repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        System.out.println("AuthenticationService constructor");
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseApi<String> register(User request) {
        System.out.println("AuthenticationService register " + request.getUsername());

        if(!isValidUser(request)) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, "Invalid User Data", false, null);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role r = new Role(1, "ROLE_USER");
        roles.add(r);
        user.setRoles(roles);

        user = repository.save(user);

        String token = jwtService.generateToken(user);
        return new ResponseApi<>(HttpStatus.CREATED, "Registration successful", true, token);
    }

    //login
    public ResponseApi<String> authenticate(RequestLoginAuth request) {
        System.out.println("authenticate " + request);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        User user = repository.findByUsername(request.getLogin()).orElse(null);
        if (user == null){
            user = repository.findByEmail(request.getLogin()).orElseThrow();
        }
        System.out.println("user login request: " + user.getUsername());
        for(Role ro : user.getRoles()) {
            System.out.println(ro.getAuthority());
        }

        String token = jwtService.generateToken(user);
        return new ResponseApi<>(HttpStatus.OK, "Login Accepted", true, token);
    }



    private boolean isValidUser(User user){
        //TODO: implement logic
        return true;
    }
}
