package com.app.ecom.service;

import com.app.ecom.dto.AddressDRTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.User;
import com.app.ecom.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
  //  private long idCounter = 0L;
    //List<User> userList = new ArrayList<>();


    public List<UserResponse> fetchUserDetails(){

        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }


    public void AddUser(UserRequest userRequest){
        //user.setId(idCounter++);
       // userList.add(user);
       // return userList;
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }


    public Optional<UserResponse> fetchUser(Long id) {

       return userRepository.findById(id)
               .map(this::mapToUserResponse);
                
    }

    public boolean updateUser(UserRequest updateUserRequest, Long id) {

        return userRepository.findById(id)
                .map(existingUser ->{
                    updateUserFromRequest(existingUser, updateUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        if(user.getAddress() != null){
            AddressDRTO addressDRTO = new AddressDRTO();
            addressDRTO.setStreet(user.getAddress().getStreet());
            addressDRTO.setCity(user.getAddress().getCity());
            addressDRTO.setState(user.getAddress().getState());
            addressDRTO.setZipCode(user.getAddress().getZipCode());
            addressDRTO.setCountry(user.getAddress().getCountry());
            userResponse.setAddress(addressDRTO);
        }
        return userResponse;
    }


    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if (userRequest.getAddress() != null) {
            AddressDRTO addressDRTO = userRequest.getAddress();
            com.app.ecom.model.Address address = new com.app.ecom.model.Address();
            address.setStreet(addressDRTO.getStreet());
            address.setCity(addressDRTO.getCity());
            address.setState(addressDRTO.getState());
            address.setZipCode(addressDRTO.getZipCode());
            address.setCountry(addressDRTO.getCountry());
            user.setAddress(address);
        }
    }
}
