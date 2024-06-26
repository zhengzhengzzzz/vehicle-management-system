package cn.kjxy.service;

import cn.kjxy.entity.UserEntity;
import cn.kjxy.utils.Result;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    Result login(UserEntity user);
    Result logout(HttpServletRequest request);
    Result register(UserEntity user);
}
