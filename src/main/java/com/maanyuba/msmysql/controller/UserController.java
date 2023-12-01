package com.maanyuba.msmysql.controller;

import com.maanyuba.msmysql.dto.GenericResponse;
import com.maanyuba.msmysql.dto.UserDto;
import com.maanyuba.msmysql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public ResponseEntity<GenericResponse> createUser(@RequestBody UserDto userDto) {
        GenericResponse gr = userService.createUser(userDto);
        if (gr.isStatus()) {
            return new ResponseEntity<>(gr, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(gr, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user", produces = "application/json")
    public  ResponseEntity<GenericResponse> getAllUsers() {
        GenericResponse gr = userService.getAllUsers();
        if (gr.isStatus()) {
            return ResponseEntity.ok(gr);
        } else {
            return new ResponseEntity<>(gr, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}", produces = "application/json")
    public ResponseEntity<GenericResponse> getUser(@PathVariable int id) {
        GenericResponse gr = userService.getUser(id);
        if (gr.isStatus()) {
            return ResponseEntity.ok(gr);
        } else {
            return new ResponseEntity<>(gr, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/user/{id}", produces = "application/json")
    public ResponseEntity<GenericResponse> deleteUser(@PathVariable int id) {
        GenericResponse gr = userService.deleteUser(id);
        if (gr.isStatus()) {
            return ResponseEntity.ok(gr);
        } else {
            return new ResponseEntity<>(gr, HttpStatus.NOT_FOUND);
        }
    }
}
