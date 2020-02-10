package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Role;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.RoleRepository;
import com.biuropracy.demo.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerService entityManagerService;

    /**
     * zapisywanie zdjęcia profilowego użytkownika
     * @param id
     * @param file
     */
    @Transactional
    public void saveProfileImage(Integer id, MultipartFile file) {
        try {
            User user = userRepository.findById(id).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            user.setProfileImage(byteObjects);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * zapisywanie pracodawcy
     * @param user
     */
    public void saveEmployer(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus("ZWERYFIKOWANY");
        Role userRole = roleRepository.findByRole("EMPLOYER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    /**
     * zapisywanie użytkownika
     * @param user
     */
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus("ZWERYFIKOWANY");
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    /**
     * aktualizowanie użytkownika
     * @param user
     * @return
     */
    public User updateUser(User user){
        Optional<User> userOpt = userRepository.findById(user.getIdUser());
        if (userOpt.isPresent()) {
            User newUser = userOpt.get();
            newUser.setName(user.getName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setUserPhone(user.getUserPhone());
            newUser.setStatus(user.getStatus());

            newUser = userRepository.save(newUser);
            return newUser;
        } else {
            user = userRepository.save(user);
            return user;
        }
    }

    public boolean isUserAlreadyPresent(User user) {
        return false;
    }

    /**
     * szukanie użytkownika po adresie email
     * @param email
     * @return
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * szukanie użytkownika po id
     * @param id
     * @return
     */
    public User findUser(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()){
            return userOpt.get();
        } else {
            throw new RuntimeException("Id użytkownika nie istnieje");
        }
    }

    /**
     * usuwanie użytkownika z bazy
     * @param id
     * @throws RuntimeException
     */
    public void deleteUserById(Integer id) throws RuntimeException{
        Optional<User> userInfoOpt = userRepository.findById(id);
        if (userInfoOpt.isPresent()){
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from user_role where id_user ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            String queryUser = "Delete from user where id_user ="+id;
            session.doWork(connection -> connection.prepareStatement(queryUser).execute());
            session.close();
        } else {
            throw new RuntimeException("ID userDetails nie znalezione.");
        }
    }

    /**
     * lista szukanie użytkownika po id
     * @param idUser
     * @return
     */
    public List<User> findUserById(Integer idUser) {
        return userRepository.findUserByIdUser(idUser);
    }

}
