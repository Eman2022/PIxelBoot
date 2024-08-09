package xin.showpixel.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xin.showpixel.model.User;
import xin.showpixel.repositories.RepositoryUser;
import xin.showpixel.response.ResponseApi;

@Service
public class ServiceUser {

    private final RepositoryUser repository;

    public ServiceUser(RepositoryUser repository) {
        this.repository = repository;
    }

    public ResponseApi<Boolean> deleteUserById(String id){
        System.out.println("UserService deleteUserById()" + id);
        User user = repository.findById(id).orElse(null);
        if (user == null){
            return new ResponseApi<>(HttpStatus.NOT_MODIFIED, "User not found", true, false);
        }
        repository.deleteById(user.getId());
        return new ResponseApi<>(HttpStatus.ACCEPTED, "User deleted", true, true);
    }

}
