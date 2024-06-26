package cn.kjxy.service.impl;

import cn.kjxy.entity.RoleEntity;
import cn.kjxy.entity.UserEntity;
import cn.kjxy.service.AuthService;
import cn.kjxy.service.UserService;
import cn.kjxy.utils.JwtUtil;
import cn.kjxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Override
    public Result login(UserEntity user){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String,Object> data = new HashMap<>();
        data.put("user",loginUser.getUser());
        data.put("token",jwt);
        return new Result(200,"登录成功",data);
    }
    @Override
    public Result logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request,null,null);
        return new Result(200,"登出成功");
    }
    @Override
    public Result register(UserEntity user){
        UserEntity existingUser = userService.findUserByName(user.getUsername());
        if(existingUser!=null){
            return new Result(400,"用户名已经存在");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        RoleEntity role = new RoleEntity();
                role.setName("普通用户");
        user.setRole(role);
        user.setRid(1);
        user.setDeleted(0);
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 创建日期时间格式化对象，指定格式为 "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        String formattedCurrentTime = currentTime.format(formatter);
        System.out.println("Formatted Current Time: " + formattedCurrentTime);

        // 将格式化后的时间字符串解析为 LocalDateTime 对象
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedCurrentTime, formatter);
        user.setCreate_time(parsedDateTime);
        userService.saveUser(user);
        return new Result(200,"注册成功");
    }
}
