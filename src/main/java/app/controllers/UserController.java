package app.controllers;

import app.data.ResponseMessage;
import app.entities.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
@EnableWebSecurity
@RequestMapping("/")
public class UserController {

    @Autowired
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @GetMapping
    public RedirectView redirectToIndex() {
        return new RedirectView("/index.html");
    }

    @GetMapping("/login")
    public RedirectView redirectToIndex1() {
        return new RedirectView("/index.html");
    }

    @PostMapping(value = "/register")
    public ResponseMessage register(@RequestBody User newUser) {
        if (newUser.getUsername() == null || newUser.getPassword() == null ||
                newUser.getPassword().trim().equals("") || newUser.getUsername().trim().equals(""))
            return new ResponseMessage(400, "Username or password is null");

        if (userService.findByUsername(newUser.getUsername()) != null) {
            System.out.println("Уже существует");
            return new ResponseMessage(409, "User already exists");
        }

        System.out.println("Добавляю " + newUser.getUsername() + " " + newUser.getPassword());
        userService.saveUser(newUser);
        return new ResponseMessage(200, "User successfully created");
    }

    @PostMapping(value = "/user/logout")
    public ResponseMessage logout(Principal user) {
        try {
            return new ResponseMessage(200, "User logout");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(400, e.getMessage());
        }
    }

    @PostMapping("/ok")
    public ResponseMessage ok() {
        return new ResponseMessage(200, "ok");
    }

    @PostMapping("/err")
    public ResponseMessage err() {
        return new ResponseMessage(400, "error");
    }
}