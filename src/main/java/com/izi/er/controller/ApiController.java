package com.izi.er.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import com.izi.er.controller.dto.ResponseDto;
import com.izi.er.controller.dto.TestDto;
import com.izi.er.controller.dto.LoginDto;
import com.izi.er.controller.dto.InjuryDto;
import com.izi.er.controller.dto.SignupDto;
import com.izi.er.controller.dto.LocationDto;
import com.izi.er.service.UserService;
import com.izi.er.model.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;
    @PostMapping("/test")
    public ResponseDto<String> test(@RequestBody TestDto testDto) {
        return new ResponseDto<String>("test ok", HttpStatus.OK);
    }
    @PostMapping("/injury")
    public ResponseDto<String> requestInjury(@RequestBody InjuryDto<String, String> injuryDto) {
        return new ResponseDto<String>("", HttpStatus.OK);
    }
    @PostMapping("/signup/process")
    public ResponseDto<String> signup(@RequestBody SignupDto signupDto) {
        User user = new User();

        user.setUsername(signupDto.getUsername());
        user.setPassword(signupDto.getPassword());
        user.setRole(signupDto.getRole());
        user.setName(signupDto.getName());
        user.setTelephone(signupDto.getTelephone());
        user.setAddress(signupDto.getAddress());

        ResponseDto<String> responseDto = null;
        try {
            userService.signup(user);
            responseDto = new ResponseDto<String>("Success Sign up", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            responseDto = new ResponseDto<String>("Sign up failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return responseDto;
        }
    }
    @PostMapping("/search-emergency-room/process")
    public ResponseDto<String> findEmerygencyRoom(@RequestBody LocationDto locationDto) {
        return new ResponseDto<String>("find emergency room", HttpStatus.OK);
    }
}
