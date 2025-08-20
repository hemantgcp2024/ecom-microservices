package com.app.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
   private UserService userService;

    @GetMapping
    public ResponseEntity <List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.fetchUserDetails(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User>  getUser(@PathVariable("id") Long id){

        return userService.fetchUser(id).map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody  User user){
      userService.AddUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody  User UpdateUser ,@PathVariable("id") Long id){
       boolean update = userService.updateUser(UpdateUser , id );
       if(!update){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
       }
        return ResponseEntity.ok("User updated successfully");
    }
}
