package com.izi.er.config.jwt;

import com.izi.er.config.jwt.JwtAuthentication;
import com.izi.er.controller.dto.LoginDto;
import com.izi.er.config.jwt.WrongPasswordException;
import com.izi.er.config.jwt.InvalidJwtTokenException;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private static final String LOGIN_URI = "/login/process";
    private static final String SECRET_KEY = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private static final long LIMIT_TIME = 15*60*1000L;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String tokenOrUri = (String)authentication.getCredentials(), jwtToken;

        if(tokenOrUri == null) throw new InvalidJwtTokenException("Empty jwt token");
        if(tokenOrUri.equals(LOGIN_URI)) {     // 로그인 요청일 경우 아이디와 비밀번호가 맞으면 토큰 생성 후 jwtToken 변수에 저장
            LoginDto loginDto = getLoginDto(authentication);
            jwtToken = login(loginDto.getUsername(), loginDto.getPassword());
        }
        else jwtToken = tokenOrUri;     // jwt 토큰이 있을 경우 토큰을 jwtToken 변수에 저장

        // 로그인 요청이면 로그인 성공 시 토큰이 생성되어 jwtToken 변수에 저장되어 있고,
        // 로그인 요청이 아니더라도 jwt 토큰이 쿠키에 있었다면 jwtToken 변수에 저장되어 있으므로
        // 토큰이 유효한지 확인하여 인증을 수행한다.
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build();
        return authenticate(authentication, jwtParser, jwtToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        if(authentication == JwtAuthentication.class)
            return true;
        else
            return false;
    }

    // 로그인 요청일 경우 JwtAuthenticationFilter에서 Authentication 객체에 username과 password 정보를 담아 건네는데
    // 이 메소드에서 Authentication 객체의 username과 password 정보를 추출하고 LoginDto 객체에 담아서 반환한다.
    private LoginDto getLoginDto(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        String password = principal.getPassword();

        return new LoginDto(username, password);
    }
    
    // username과 password를 이용하여 사용자가 맞는지 확인하고, 맞으면 jwt 토큰을 생성하여 반환하는 메소드
    private String login(String username, String password) throws AuthenticationException {
        String token = null;

        UserDetails principal = userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password, principal.getPassword())) {
            Date exp = new Date();
            exp.setTime(exp.getTime()+LIMIT_TIME);
            Claims claims = Jwts.claims().setAudience(username).setExpiration(exp);
            token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        }
        else throw new WrongPasswordException("Wrong Password");

        return token;
    }

    // jwt 토큰이 있을 경우에만 호출되어 토큰을 통해 인증을 수행하는 메소드
    private Authentication authenticate(Authentication authentication, JwtParser jwtParser, String jwtToken) throws AuthenticationException {
        Jws<Claims> claims;
        try {
            claims = jwtParser.parseClaimsJws(jwtToken);
        } catch(Exception e) {
            throw new InvalidJwtTokenException("Manipulated jwt token");
        }

        // 토큰이 유효하면 Authentication 객체를 생성하여 인증됨을 표시한다.
        if(claims.getBody().getExpiration().after(new Date())) {
            String username = claims.getBody().getAudience();
            UserDetails principal = userDetailsService.loadUserByUsername(username);

            authentication = new JwtAuthentication(principal, jwtToken);
            authentication.setAuthenticated(true);
        } else throw new InvalidJwtTokenException("Expire token");
        return authentication;
    }
}

