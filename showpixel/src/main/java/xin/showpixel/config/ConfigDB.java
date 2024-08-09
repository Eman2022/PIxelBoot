package xin.showpixel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import xin.showpixel.model.Role;
import xin.showpixel.model.User;
import xin.showpixel.repositories.RepositoryRoles;
import xin.showpixel.repositories.RepositoryUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class ConfigDB {

    @Value("${security.show.pixel.root.account.name}")
    private String rootAccountName;

    @Value("${security.show.pixel.root.account.password}")
    private String rootAccountPass;

    @Value("${security.show.pixel.root.account.email}")
    private String rootAccountEmail;

    @Bean
    public CommandLineRunner setupDefaultData(RepositoryRoles roleRepository, RepositoryUser userRepository, PasswordEncoder passwordEncoder) {
        System.out.println("ConfigDB setupDefaultData()");
        return args -> {


            Optional<Role> rootRole = roleRepository.findById(0);
            if (rootRole.isEmpty()) {
                roleRepository.save(new Role(0, "ROLE_ROOT"));
            }
            Optional<Role> userRole = roleRepository.findById(1);
            if (userRole.isEmpty()) {
                roleRepository.save(new Role(1,"ROLE_USER"));
            }
            Optional<Role> adminRole = roleRepository.findById(2);
            if (adminRole.isEmpty()) {
                roleRepository.save(new Role(2,"ROLE_ADMIN"));
            }



            Optional<User> rootUser = userRepository.findByUsername(rootAccountName);
            if(rootUser.isEmpty()) {
                User user = new User();
                user.setUsername(rootAccountName);
                user.setEmail(rootAccountEmail);
                user.setPassword(passwordEncoder.encode(rootAccountPass));
                List<Role> roles = new ArrayList<>();
                roles.add(new Role(0,"ROLE_ROOT"));
                user.setRoles(roles);

                userRepository.save(user);
            }

        };
    }
}
