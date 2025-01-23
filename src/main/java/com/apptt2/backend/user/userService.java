package com.apptt2.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;

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
        
        if (password.contains("/")) {
            throw new IllegalArgumentException("La contraseña no puede contener el carácter '/'");
        }
    
        // Declare hashedPassword as final to make it immutable within the lambda
        final String hashedPassword = generateValidHashedPassword(password);
    
        // Use hashedPassword inside the lambda expression
        return userRepository.findById(id).map(user -> {
            user.setPassword(hashedPassword);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }
    
    // Helper method to generate a valid hashed password
    private String generateValidHashedPassword(String password) {
        String hashedPassword;
        boolean validHash = false;
    
        do {
            hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            validHash = !hashedPassword.contains("/");
        } while (!validHash);
    
        return hashedPassword;
    }

    public User createUser(UserCreateDTO userCreateDTO) {
        // Buscar el rol por ID
        CatRole role = catRoleRepository.findById(userCreateDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con el ID: " + userCreateDTO.getRoleId()));

        // Crear un nuevo usuario y configurar sus propiedades
        User user = new User();
        user.setId(userCreateDTO.getId());
        user.setRole(role);
        user.setEmailAddress(userCreateDTO.getEmailAddress());
        user.setPassword(BCrypt.hashpw(userCreateDTO.getPassword(), BCrypt.gensalt())); // Hash the password
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
        user.setDate(userCreateDTO.getDate()); // Retaining the date field

        // Guardar el usuario en la base de datos
        return userRepository.save(user);
    }

    public UserIdPasswordProjection getIdAndPasswordByEmailAndPassword(String emailAddress, String password) {
        // Crear el rol con ID 2
        CatRole role = new CatRole();
        role.setId(2);

        return userRepository.findIdAndPasswordByEmailAndPasswordAndRole(emailAddress, password, role)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con las credenciales y rol especificados."));
    }

    public User updateUser (int id, UserUpdateDTO userUpdateDTO) {
        Optional<User> optionalUser  = userRepository.findById(id);
        
        if (optionalUser .isPresent()) {
            User user = optionalUser .get();
            
            // Actualiza solo los campos que no son nulos
            if (userUpdateDTO.getRole() != null) {
                user.setRole(userUpdateDTO.getRole());
            }
            if (userUpdateDTO.getEmailAddress() != null) {
                user.setEmailAddress(userUpdateDTO.getEmailAddress());
            }
            if (userUpdateDTO.getPassword() != null) {
                user.setPassword(BCrypt.hashpw(userUpdateDTO.getPassword(), BCrypt.gensalt())); // Hash the new password
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
                user.setDate(userUpdateDTO.getDate()); // Retaining the date field
            }

            return userRepository.save(user); // Guarda los cambios en la base de datos
        } else {
            // Manejar el caso en que el usuario no se encuentra
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Transactional
    public String recoverPassword(String email) {
        User user = userRepository.findByEmailAddress(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));

        String token = generateRandomToken();
        user.setPassword(BCrypt.hashpw(token, BCrypt.gensalt())); // Update password with token
        userRepository.save(user); // Save updated user

        return token; // Return the generated token
    }

    @Transactional
    public void updatePasswordByToken(String token, String newPassword) {
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el token: " + token));

        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt())); // Hash the new password
        userRepository.save(user); // Save updated user
    }

    private String generateRandomToken() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[24]; // 24 bytes = 192 bits
        random.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes); // Encode to URL-safe string
    }
}
