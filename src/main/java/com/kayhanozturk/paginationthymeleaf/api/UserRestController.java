package com.kayhanozturk.paginationthymeleaf.api;

import com.kayhanozturk.paginationthymeleaf.model.User;
import com.kayhanozturk.paginationthymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @Value("${info.app.name}")
    private String name;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "<strong>Say hello everyone!adadas</strong>" + "<i>" + name + "</i>";
    }

    @GetMapping("/test")
    public String test() {
        String name = env.getProperty("info.app.name");
        return "Env info: " + name;
    }

    @GetMapping("/list")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/pagination")
    public Page<User> pagination(@RequestParam("page") int currentPage,
                                 @RequestParam("size") int pageSize){

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return userService.findAll(pageable);
    }

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }

}
