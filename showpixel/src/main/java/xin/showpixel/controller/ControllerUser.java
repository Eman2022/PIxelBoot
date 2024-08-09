package xin.showpixel.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.showpixel.response.ResponseApi;
import xin.showpixel.service.ServiceUser;

@RestController
@RequestMapping("/user")
public class ControllerUser {

    private final ServiceUser userService;

    public ControllerUser(ServiceUser userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseApi<Boolean> deleteUser(@PathVariable("id") String id) {
        System.out.println("AuthenticationController deleteUser() " + id);
        return userService.deleteUserById(id);
    }
}
