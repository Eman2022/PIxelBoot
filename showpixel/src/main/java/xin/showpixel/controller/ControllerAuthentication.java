package xin.showpixel.controller;


import org.springframework.web.bind.annotation.*;
import xin.showpixel.request.RequestLoginAuth;
import xin.showpixel.response.ResponseApi;

import xin.showpixel.model.User;
import xin.showpixel.service.ServiceAuthentication;

@RestController
@RequestMapping("/auth")
public class ControllerAuthentication {
    private final ServiceAuthentication authService;

    public ControllerAuthentication(ServiceAuthentication authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseApi<String> register(@RequestBody User request) {
        System.out.println("AuthenticationController register() " +request.getUsername());
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseApi<String> login(@RequestBody RequestLoginAuth request) {
        System.out.println("AuthenticationController login() " + request.getLogin());
        return authService.authenticate(request);//ResponseEntity.ok(authService.authenticate(request));
    }


}
