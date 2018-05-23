package com.kulcsSoft.userApi.user;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class AppUserController {


    private AppUserRepository appUserRepository;
    private AppUserService appUserService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public AppUserController(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping( value = "/sign-up")
    public void signUp(@RequestBody AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
    }

    @GetMapping( value = "/get-users")
    public ResponseEntity getUsers(){
        try {
            List<Map<String, String>> userList;
            userList = appUserService.getAllUsers();
            return ResponseEntity.ok(userList);
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            return ResponseEntity.ok("Permission denied!");
        }
    }


    @DeleteMapping(value = "/delete-user")
    public ResponseEntity deleteUser(@RequestParam("userId") Long userId){
        try {
            appUserRepository.delete(userId);
            return ResponseEntity.ok("Deleted");
        } catch ( io.jsonwebtoken.MalformedJwtException | org.springframework.dao.EmptyResultDataAccessException e) {
            return ResponseEntity.ok("Wrong request");
        }
    }


}
