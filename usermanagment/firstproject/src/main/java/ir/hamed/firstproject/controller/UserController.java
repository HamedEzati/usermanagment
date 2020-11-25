package ir.hamed.firstproject.controller;

import com.google.gson.Gson;
import ir.hamed.firstproject.model.User;
import ir.hamed.firstproject.model.UserStatus;
import ir.hamed.firstproject.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create user
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addNewUsers(@RequestBody User user) {
        if(userRepository.findById(user.getUsername()).isPresent()){
            UserStatus userStatus = new UserStatus("user is exists.",false);
            return new Gson().toJson(userStatus);
        }else{
            UserStatus userStatus = new UserStatus("user saved.",true);
            userRepository.save(user);
            return new Gson().toJson(userStatus);
        }
    }

    // delete user
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String deleteUser(@RequestBody User user){
        if(userRepository.findById(user.getUsername()).isPresent()){
            userRepository.delete(user);
            UserStatus userStatus = new UserStatus("user deleted.",true);
            return new Gson().toJson(userStatus);
        }else{
            UserStatus userStatus = new UserStatus("user not available",false);
            return new Gson().toJson(userStatus);
        }
    }

    // update user
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateUser(@RequestBody User user){
        if(userRepository.findById(user.getUsername()).isPresent()){
            userRepository.save(user);
            UserStatus userStatus = new UserStatus("user is exists and updated.",true);
            return new Gson().toJson(userStatus);
        }else{
            UserStatus userStatus = new UserStatus("user not available.",false);
            return new Gson().toJson(userStatus);
        }
    }

    // find user by id(username)
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String getUser(@PathVariable String username){
        if(userRepository.findById(username).isPresent()){
            Optional<User> user = userRepository.findById(username);
            return new Gson().toJson(user);
        }else{
            UserStatus userStatus = new UserStatus("user not available.",false);
            return new Gson().toJson(userStatus);
        }
    }
}
