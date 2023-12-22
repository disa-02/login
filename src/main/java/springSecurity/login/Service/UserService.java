package springSecurity.login.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springSecurity.login.Entity.Role;
import springSecurity.login.Entity.User;
import springSecurity.login.Repository.RoleRepository;
import springSecurity.login.Repository.UserRepository;
import springSecurity.login.Utils.EnumRole;
import springSecurity.login.dto.UserRequest;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    
    public void create(UserRequest user) {
        Optional<User> userBD = userRepository.findByUsername(user.getUsername());
        if(!userBD.isEmpty()){
            throw new RuntimeException("The user is already save");
        }
        String password = passwordEncoder.encode(user.password);
        List<Role> role = roleRepository.findByName("USER");
        if(role.isEmpty()){
            throw new RuntimeException("Role USER not found");
        }
        User userEntity = new User();
        userEntity.setUsername(user.username);
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(password);
        userEntity.setRole(role.get(0));
        userRepository.save(userEntity);
    }


    public void update(int id, UserRequest userUpd) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new RuntimeException("User not found");

        boolean esAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        String logName = SecurityContextHolder.getContext().getAuthentication().getName();
        int logId = userRepository.findByUsername(logName).get().getId();
        
        if(esAdmin || logId == id){
            User finalUser = user.get();
            if(!finalUser.getUsername().equals(userUpd.getUsername())){
                Optional<User> userBD = userRepository.findByUsername(userUpd.getUsername());
                if(!userBD.isEmpty())
                    throw new RuntimeException("The username is not disponible");
            }
            finalUser.setUsername(userUpd.getUsername());
            finalUser.setEmail(userUpd.getEmail());
            finalUser.setPassword(userUpd.getPassword());

            userRepository.save(finalUser);
        }
        else    
            throw new RuntimeException("You do not have permissions");
    }


    public void delete(int id) {
        if(!userRepository.existsById(id))
            throw new RuntimeException("User not found");
        boolean esAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if(esAdmin)
            userRepository.deleteById(id);
        else{
            String logName = SecurityContextHolder.getContext().getAuthentication().getName();
            int logId = userRepository.findByUsername(logName).get().getId();
            if(logId == id)
                userRepository.deleteById(id);
            else    
                throw new RuntimeException("You do not have permission");
        }
    }


    public User get(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new RuntimeException("User not found");

        boolean esAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        String logName = SecurityContextHolder.getContext().getAuthentication().getName();
        int logId = userRepository.findByUsername(logName).get().getId();
        
        if(esAdmin || logId == id){
            return user.get();
        }
        else
            throw new RuntimeException("You do not have permissions");
    }


    public List<User> list() {
        return userRepository.findAll();
    }


    public void updateRol(int id, String role) {
        User user = userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not found"));
        List<Role> rol = roleRepository.findByName(role);
        if(rol.isEmpty())
            throw new RuntimeException("Rol not found");
        user.setRole(rol.get(0));
        userRepository.save(user);



         
    }

    
}
