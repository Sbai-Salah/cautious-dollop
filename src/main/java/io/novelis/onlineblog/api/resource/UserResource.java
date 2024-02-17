package io.novelis.onlineblog.api.resource;

import io.novelis.onlineblog.service.UserService;
import io.novelis.onlineblog.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.findAll();
        return ResponseEntity.ok(userDtoList);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Add more endpoints as needed
}