package com.app.ecom.service;

import com.app.ecom.model.User;
import com.app.ecom.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
  //  private long idCounter = 0L;
    //List<User> userList = new ArrayList<>();


    public List<User> fetchUserDetails(){

        return userRepository.findAll();
    }


    public void AddUser(User user){
        //user.setId(idCounter++);
       // userList.add(user);
       // return userList;
        userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {

       return userRepository.findById(id);
                
    }

    public boolean updateUser(User updatedUser, Long id) {

        return userRepository.findById(id)
                .map(existingUser ->{
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
