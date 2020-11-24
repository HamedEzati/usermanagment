package ir.hamed.firstproject.controller;

import ir.hamed.firstproject.model.User;
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
    public User addNewUsers(@RequestBody User user) {
        return userRepository.save(user);
    }

    // delete user
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void deleteUser(@RequestBody User user){
        userRepository.delete(user);
    }

    // update user
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateUser(@RequestBody User user){
        userRepository.deleteById(user.getUsername());
        userRepository.save(user);
    }

    // find user by id(username)
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public Optional<User> getUser(@PathVariable String username){
        return userRepository.findById(username);
    }
    // get all user
    @RequestMapping(value = "/getalluser")
    public List<User> alluser(){
        return userRepository.findAll();
    }

}
