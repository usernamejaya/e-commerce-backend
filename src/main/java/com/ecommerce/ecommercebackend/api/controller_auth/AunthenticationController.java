package com.ecommerce.ecommercebackend.api.controller_auth;

import com.ecommerce.ecommercebackend.api.model.LoginBody;
import com.ecommerce.ecommercebackend.api.model.LoginResponse;
import com.ecommerce.ecommercebackend.api.model.RegistartionBody;
import com.ecommerce.ecommercebackend.exception.UserAlreadyExistsException;
import com.ecommerce.ecommercebackend.model.LocalUser;
import com.ecommerce.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AunthenticationController {

    private UserService userService;
    public AunthenticationController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistartionBody registartionBody){
        try {
            userService.registerUser(registartionBody);
            return ResponseEntity.ok().build();
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }


    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String jwt =userService.loginUser(loginBody);

        if(jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }
    }


    @GetMapping("/me")
    public LocalUser getLoggedUserProfile(@AuthenticationPrincipal LocalUser user){
        return user;

    }
}
