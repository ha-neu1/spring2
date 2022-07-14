package com.sparta.controller;


import com.sparta.dto.SignupRequestDto;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user/signup")
    public String signup() {

        return "signup";
    }

    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        if (userService.registerUser(requestDto).equals("")) {
            return "login";
        } else {
            model.addAttribute("errortext", userService.registerUser(requestDto));
            return "signup";
        }
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }


    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    @GetMapping("/api/userCheck")
    public String userCheck(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return userDetails.getUser().getUsername();
        }
        return "";
    }
}
