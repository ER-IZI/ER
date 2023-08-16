package com.izi.er.config.jwt;

import com.izi.er.config.jwt.JwtAuthentication;
import com.izi.er.controller.dto.LoginDto;
import com.izi.er.model.User;
import com.izi.er.model.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.RequiredArgsConstructor;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;
    private static final String LOGIN_URI = "/login/process";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // jwt 토큰이 있는 경우 tokenOrUri에 토큰을 저장한다.
        String tokenOrUri = request.getHeader("JWT");
        UserDetails principal = null;

        // 요청 주소가 로그인 요청일 경우 tokenOrUri에 uri를 저장한다.
        if(request.getRequestURI().equals(LOGIN_URI)) {
            LoginDto loginDto = parseBody(request);
            User user = new User();
            user.setUsername(loginDto.getUsername());
            user.setPassword(loginDto.getPassword());
            principal = new UserDetailsImpl(user);

            tokenOrUri = LOGIN_URI;
        }

        try {
            // JwtAuthentication 객체를 생성하여 AuthenticationManager에게 전달한다.
            Authentication authentication = new JwtAuthentication(principal, tokenOrUri);
            authentication = authenticationManager.authenticate(authentication);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);

            Cookie cookie = new Cookie("JWT", (String)authentication.getCredentials());
            response.addCookie(cookie);

            logger.info("Authenticated");
        } catch(AuthenticationException e) {
            logger.error(e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }

        // 응답할 때는 SecurityContextHolder를 비워준다.
        SecurityContextHolder.clearContext();
    }

    // 로그인 요청일 경우 request 객체에서 username과 password를 추출하여 반환하는 메소드
    private LoginDto parseBody(HttpServletRequest request) {
        StringBuilder jsonBody = new StringBuilder();

        try (InputStream inputStream = request.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        } catch (IOException e) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = null;
        try {
            loginDto = objectMapper.readValue(jsonBody.toString(), LoginDto.class);
        } catch (IOException e) {
        } finally {
            return loginDto;
        }
    }
}

