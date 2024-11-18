package com.apptt2.backend.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/api/users")
public class userController {
    @Autowired
    private userService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Endpoint para obtener un usuario por su ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/status")
    public List<UserStatus> getUserBystatus() {
        return userService.findBystatus();
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UserUpdateHelpDTO userUpdateHelpDTO) {
        return userService.updateUser(id, userUpdateHelpDTO);
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping("/create")
    public User createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @PutMapping("/updatepass/{id}")
    public User updatePassUser(@PathVariable int id, @RequestBody UserPassDTO UserPassDTO) {
        return userService.updatePassUser(id, UserPassDTO);
    }

    @PostMapping("/login")
    public UserIdPasswordProjection getUserIdAndPassword(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.getIdAndPasswordByEmailAndPassword(userLoginDTO.getEmailAddress(), userLoginDTO.getPassword());
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser (@PathVariable int id, @RequestBody UserUpdateDTO userUpdateDTO) {
        User updatedUser  = userService.updateUser (id, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
