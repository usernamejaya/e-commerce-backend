package com.ecommerce.ecommercebackend.service;

import com.ecommerce.ecommercebackend.api.model.LoginBody;
import com.ecommerce.ecommercebackend.api.model.RegistartionBody;
import com.ecommerce.ecommercebackend.exception.UserAlreadyExistsException;
import com.ecommerce.ecommercebackend.model.LocalUser;
import com.ecommerce.ecommercebackend.model.dao.LocalUserDAO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;



    public UserService(LocalUserDAO localUserDAO , EncryptionService encryptionService , JWTService jwtService ) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;


    }


    public LocalUser registerUser(RegistartionBody registartionBody) throws UserAlreadyExistsException {
        if(localUserDAO.findByEmailIgnoreCase(registartionBody.getEmail()).isPresent()
        || localUserDAO.findByUsernameIgnoreCase(registartionBody.getUsername()).isPresent()){
             throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setUsername(registartionBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registartionBody.getPassword()));
        user.setFirstName(registartionBody.getFirstName());
        user.setLastName(registartionBody.getLastName());
        user.setEmail(registartionBody.getEmail());

        return localUserDAO.save(user);
    }





    public  String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if(opUser.isPresent()){
            LocalUser user = opUser.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(),user.getPassword())){

                    return jwtService.generateJWT((user));

                }

            }

        return null;

    }
    public  boolean userHasPermissionToUser(LocalUser user , Long id){
        return user.getId() == id;
    }

}
