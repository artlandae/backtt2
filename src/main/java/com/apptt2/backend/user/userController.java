package com.apptt2.backend.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/create")
    public User createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @PutMapping("/updatepass/{id}")
    public ResponseEntity<?> updatePassUser(@PathVariable int id, @RequestBody UserPassDTO userPassDTO) {
        try {
            User updatedUser = userService.updatePassUser(id, userPassDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserIdPasswordProjection> getUserIdAndPassword(@RequestBody UserLoginDTO userLoginDTO) {
        UserIdPasswordProjection userIdPassword = userService.getIdAndPasswordByEmailAndPassword(userLoginDTO.getEmailAddress(), userLoginDTO.getPassword());
        if (userIdPassword == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(userIdPassword);
    }

    @PostMapping("/login-role")
    public ResponseEntity<Object[]> getUserRole(@RequestBody UserLoginDTO userLoginDTO) {
        Object[] userRoleId = userService.getRoleByEmailAndPassword(userLoginDTO.getEmailAddress(), userLoginDTO.getPassword());
        if (userRoleId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Return unauthorized if user not found
        }
        // Map<String, Integer> response = new HashMap<>();
        // response.put("id_role", userRoleId);
        return ResponseEntity.ok(userRoleId);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser (@PathVariable int id, @RequestBody UserUpdateDTO userUpdateDTO) {
        User updatedUser  = userService.updateUser (id, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/recover-password")
    public String recoverPassword(@RequestParam String email) {
        return userService.recoverPassword(email);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Map<String, String>> updatePassword(@RequestBody UserPassDTO userPassDTO, @RequestParam String token) {
        userService.updatePasswordByToken(token, userPassDTO.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Password updated successfully");
        return ResponseEntity.ok(response);
    }

    public static class JsonResponse {
        public String token;

        public JsonResponse(String token) {
            this.token = token;
        }
    }
}
