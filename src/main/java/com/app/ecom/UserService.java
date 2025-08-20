package com.app.ecom;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private long idCounter = 0L;
    List<User> userList = new ArrayList<>();


    public List<User> fetchUserDetails(){
        return userList;
    }


    public List<User> AddUser(User user){
        user.setId(idCounter++);
        userList.add(user);
        return userList;
    }

    public Optional<User> fetchUser(Long id) {

       return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
                
    }

    public boolean updateUser(User updatedUser, Long id) {

        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .map(existingUser ->{
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);
    }
}
