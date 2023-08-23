package com.izi.er.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import com.izi.er.controller.dto.ResponseDto;
import com.izi.er.controller.dto.TestDto;
import com.izi.er.controller.dto.InjuryDto;
import com.izi.er.controller.dto.PartientSignupDto;
import com.izi.er.controller.dto.Data.Location;
import com.izi.er.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;
    @PostMapping("/test")
    public ResponseDto<String> test(@RequestBody TestDto testDto) {
        System.out.println("test id: "+testDto.getId());
        System.out.println("test name: "+testDto.getName());

        System.out.println(userService.encodeTest("test string"));

        return new ResponseDto<String>("test ok", HttpStatus.OK);
    }
    @PostMapping("/injury")
    public ResponseDto<String> requestInjury(@RequestBody InjuryDto<String, String> injuryDto) {
        return new ResponseDto<String>("", HttpStatus.OK);
    }
    @PostMapping("/signup/process")
    public ResponseDto<String> signup(@RequestBody PartientSignupDto signupDto) {
        return new ResponseDto<String>("Success Sign up", HttpStatus.OK);
    }
    @PostMapping("/search-emergency-room/process")
    public ResponseDto<String> findEmerygencyRoom(@RequestBody Location locationDto) {
        return new ResponseDto<String>("find emergency room", HttpStatus.OK);
    }
}
