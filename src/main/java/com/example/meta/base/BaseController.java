package com.example.meta.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class BaseController {
    private final BaseService baseService;

    BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/test")
    String test(){
        return "test";
    }

    @PostMapping("/saveuser")
    ResponseEntity<?> saveUser(@RequestBody UserDetail request){
        baseService.save(request.username(), request.email());
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/users")
    ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(baseService.getUsers());
    }

    @GetMapping("/search")
    ResponseEntity<?> getUsersWithEmail(@RequestParam("email") String email){
        return ResponseEntity.ok().body(baseService.getUserByEmail(email));
    }
}

record UserDetail(String username, String email) {}
