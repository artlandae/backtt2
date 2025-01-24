package com.apptt2.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apptt2.backend.cat_role.CatRole;
import com.apptt2.backend.cat_role.catRoleRepository;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class userService {
    @Autowired
    private userRepository userRepository;
    private final catRoleRepository catRoleRepository;

    @Autowired
    public userService(userRepository userRepository, catRoleRepository catRoleRepository) {
        this.userRepository = userRepository;
        this.catRoleRepository = catRoleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserStatus> findBystatus() {
        return userRepository.findBystatus();
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }
    
    @Transactional
    public User updateUser(int id, UserUpdateHelpDTO userUpdateHelpDTO) {
        return userRepository.findById(id).map(user -> {
            user.setStatus(userUpdateHelpDTO.getStatus());
            user.setLatitud(userUpdateHelpDTO.getLatitud());
            user.setLenght(userUpdateHelpDTO.getLongitud());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }

    @Transactional
    public User updatePassUser(int id, UserPassDTO userPassDTO) {
        String password = userPassDTO.getPassword();
    
        return userRepository.findById(id).map(user -> {
            user.setPassword(password); // Store plain text password
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }

    public User createUser(UserCreateDTO userCreateDTO) {
        CatRole role = catRoleRepository.findById(userCreateDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con el ID: " + userCreateDTO.getRoleId()));

        User user = new User();
        user.setId(userCreateDTO.getId());
        user.setRole(role);
        user.setEmailAddress(userCreateDTO.getEmailAddress());
        user.setPassword(userCreateDTO.getPassword()); // Store plain text password
        user.setName(userCreateDTO.getName());
        user.setSecondName(userCreateDTO.getSecondName());
        user.setLastName(userCreateDTO.getLastName());
        user.setMotherLastName(userCreateDTO.getMotherLastName());
        user.setBloodType(userCreateDTO.getBloodType());
        user.setBirthDate(userCreateDTO.getBirthDate());
        user.setSex(userCreateDTO.getSex());
        user.setAllergies(userCreateDTO.getAllergies());
        user.setCriticalIllnes(userCreateDTO.getCriticalIllnes());
        user.setStatus(userCreateDTO.getStatus());
        user.setCellPhone(userCreateDTO.getCellPhone());
        user.setAuxiliaryCellPhone(userCreateDTO.getAuxiliaryCellPhone());
        user.setLatitud(userCreateDTO.getLatitud());
        user.setLenght(userCreateDTO.getLongitud());
        user.setDate(userCreateDTO.getDate());

        return userRepository.save(user);
    }

    public UserIdPasswordProjection getIdAndPasswordByEmailAndPassword(String emailAddress, String password) {
        return userRepository.findIdAndPasswordByEmailAndPasswordAndRole(emailAddress, password)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con las credenciales y rol especificados."));
    }

    public Integer getRoleByEmailAndPassword(String emailAddress, String password) {
        return userRepository.findRoleByEmailAndPassword(emailAddress, password)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con las credenciales especificadas."));
    }

    public User updateUser (int id, UserUpdateDTO userUpdateDTO) {
        Optional<User> optionalUser  = userRepository.findById(id);
        
        if (optionalUser .isPresent()) {
            User user = optionalUser .get();
            
            if (userUpdateDTO.getRole() != null) {
                user.setRole(userUpdateDTO.getRole());
            }
            if (userUpdateDTO.getEmailAddress() != null) {
                user.setEmailAddress(userUpdateDTO.getEmailAddress());
            }
            if (userUpdateDTO.getPassword() != null) {
                user.setPassword(userUpdateDTO.getPassword()); // Store plain text password
            }
            if (userUpdateDTO.getName() != null) {
                user.setName(userUpdateDTO.getName());
            }
            if (userUpdateDTO.getSecondName() != null) {
                user.setSecondName(userUpdateDTO.getSecondName());
            }
            if (userUpdateDTO.getLastName() != null) {
                user.setLastName(userUpdateDTO.getLastName());
            }
            if (userUpdateDTO.getMotherLastName() != null) {
                user.setMotherLastName(userUpdateDTO.getMotherLastName());
            }
            if (userUpdateDTO.getBloodType() != null) {
                user.setBloodType(userUpdateDTO.getBloodType());
            }
            if (userUpdateDTO.getBirthDate() != null) {
                user.setBirthDate(userUpdateDTO.getBirthDate());
            }
            if (userUpdateDTO.getSex() != null) {
                user.setSex(userUpdateDTO.getSex());
            }
            if (userUpdateDTO.getAllergies() != null) {
                user.setAllergies(userUpdateDTO.getAllergies());
            }
            if (userUpdateDTO.getCriticalIllnes() != null) {
                user.setCriticalIllnes(userUpdateDTO.getCriticalIllnes());
            }
            if (userUpdateDTO.getStatus() != null) {
                user.setStatus(userUpdateDTO.getStatus());
            }
            if (userUpdateDTO.getCellPhone() != null) {
                user.setCellPhone(userUpdateDTO.getCellPhone());
            }
            if (userUpdateDTO.getAuxiliaryCellPhone() != null) {
                user.setAuxiliaryCellPhone(userUpdateDTO.getAuxiliaryCellPhone());
            }
            if (userUpdateDTO.getLatitud() != null) {
                user.setLatitud(userUpdateDTO.getLatitud());
            }
            if (userUpdateDTO.getLenght() != null) {
                user.setLenght(userUpdateDTO.getLenght());
            }
            if (userUpdateDTO.getDate() != null) {
                user.setDate(userUpdateDTO.getDate());
            }

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Transactional
    public String recoverPassword(String email) {
        User user = userRepository.findByEmailAddress(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));

        String token = generateRandomToken();
        user.setPassword(token); // Store plain text token
        userRepository.save(user); // Save updated user

        return token; // Return the generated token
    }

    @Transactional
    public void updatePasswordByToken(String token, String newPassword) {
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el token: " + token));

        user.setPassword(newPassword); // Store plain text new password
        userRepository.save(user); // Save updated user
    }

    private String generateRandomToken() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[24]; // 24 bytes = 192 bits
        random.nextBytes(tokenBytes);
    
        // Generar un token Base64 URL-safe
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    
        // Reemplazar cualquier aparición del carácter '/' por un carácter permitido, como '_'
        return token.replace("/", "_");
    }
}
