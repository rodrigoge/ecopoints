package br.com.ecopoints.userservice.api;

import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.dto.UserTO;
import br.com.ecopoints.userservice.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Api(tags = "User endpoints")
@Log4j2
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    @ApiOperation(value = "Health-check endpoint application")
    public ResponseEntity<String> ping() {
        log.info("Successfully health check endpoint");
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping("/user")
    @ApiOperation(value = "Creating a new user")
    public ResponseEntity<UserTO> create(@Valid @RequestBody User user) {
        var response = userService.create(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
