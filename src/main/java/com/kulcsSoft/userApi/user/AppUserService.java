package com.kulcsSoft.userApi.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppUserService {


    @Autowired
    AppUserRepository appUserRepository;

    public List<Map<String,String>> getAllUsers(){
        List<Map<String,String>> resultList = new ArrayList<>();
        List<AppUser> appUsers = appUserRepository.findAll();
        for (AppUser appUser : appUsers) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userId", appUser.getId().toString());
            userMap.put("userName", appUser.getUsername());
            resultList.add(userMap);
        }
        return resultList;
    }


}
