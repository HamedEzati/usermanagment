package ir.hamed.firstproject.controller;

import com.google.gson.Gson;
import ir.hamed.firstproject.model.User;
import ir.hamed.firstproject.model.UserStatus;
import ir.hamed.firstproject.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    UserRepository userRepository;
    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // login user
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password){
        if(userRepository.findById(username).isPresent()){
            Optional<User> user = userRepository.findById(username);
            String pass = user.get().getPassword();
            if(pass.equals(password)){
                UserStatus userStatus = new UserStatus("user login.",true);
                return new Gson().toJson(userStatus);
            }
            UserStatus userStatus = new UserStatus("Wrong password.",false);
            return new Gson().toJson(userStatus);
        }
        UserStatus userStatus = new UserStatus("user not available",false);
        return new Gson().toJson(userStatus);

    }
}
