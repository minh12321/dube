package com.bongda.controller;



import com.bongda.model.User;
import com.bongda.respository.UserRepository;
import com.bongda.service.UserService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://ab-mocha.vercel.app")
@RequestMapping("/shopbongda")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/auth/register")
    public User register(@RequestParam String username, @RequestParam String password, @RequestParam String fullName,@RequestParam String email) {
        return userService.registerUser(username, password, fullName,email);
    }


    @PostMapping("/auth/login")
    public User login(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }
    @PutMapping("/admin/update/{accountId}")
    public User updateUser(@PathVariable Long accountId, @RequestParam String fullName, 
                           @RequestParam String status, @RequestParam String accountType,@RequestParam String email) {
        return userService.updateUser(accountId, fullName, status, accountType,email);
    }

    @DeleteMapping("/admin/delete/{accountId}")
    public String deleteUser(@PathVariable Long accountId) {
        userService.deleteUser(accountId);
        return "User deleted successfully";
    }
    @GetMapping("/admin/update/{accountId}")
    public User getAccountDetails(@PathVariable Long accountId) {
        return userService.getUserById(accountId);
    }
    @GetMapping(value = "/list", produces = "application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/login/oauth2/success")
    public String handleOAuth2Login(OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        String provider = authentication.getAuthorizedClientRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");  
        String fullName = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");

        User user = userService.registerSocialUser(provider, providerId, fullName, email);
        
        return "Welcome " + user.getFullName();
    }

}
