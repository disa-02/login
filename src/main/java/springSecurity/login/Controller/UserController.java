package springSecurity.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springSecurity.login.Entity.Role;
import springSecurity.login.Entity.User;
import springSecurity.login.Service.UserService;
import springSecurity.login.Utils.EnumRole;
import springSecurity.login.dto.UserRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserRequest user){
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody UserRequest user){
        userService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id){
        User user = userService.get(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<User> users = userService.list();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/updateRol/{id}")
    public ResponseEntity<?> updateRol(@PathVariable int id,@RequestBody String role){
        userService.updateRol(id, role);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
