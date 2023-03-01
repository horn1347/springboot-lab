package at.horn1347.springbootlab.rest.controller.impl;

import at.horn1347.springbootlab.model.User;
import at.horn1347.springbootlab.rest.controller.UserRestController;
import at.horn1347.springbootlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserRestControllerImpl implements UserRestController {

    @Autowired
    UserService service;

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/put/{name}")
    public ResponseEntity<User> save(@PathVariable String name) {
        User user = new User();
        user.setUserName(name);
        user = service.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/get/{name}")
    public ResponseEntity<User> findByName(@PathVariable String name) {
        Optional<User> user = service.findUserByName(name);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<User> save(@RequestBody User entity) {
        User user = service.save(entity);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    @GetMapping("/get")
    @CrossOrigin(origins="*")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
