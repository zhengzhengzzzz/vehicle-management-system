package cn.kjxy.controller;
import cn.kjxy.entity.UserEntity;
import cn.kjxy.service.AuthService;
import cn.kjxy.service.impl.AuthServiceImpl;
import cn.kjxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestBody UserEntity user){
        System.out.println("user:"+user);
        if(Objects.isNull(user) || Objects.isNull(user.getUsername())|| Objects.isNull(user.getPassword())){
            return new Result(300,"用户名密码不能为空");
        }
        return authService.login(user);
    }
//    注销
    @RequestMapping("/logout")
    @PreAuthorize("hasAnyRole('1','0')")
    public Result logout(HttpServletRequest request){
        return authService.logout(request);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserEntity user){
        if(user == null || user.getUsername() == null || user.getPassword() == null){
            return new Result(400,"用户名和密码不能为空");
        }
        return authService.register(user);
    }
}
