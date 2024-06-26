package cn.kjxy;

import cn.kjxy.entity.UserEntity;
import cn.kjxy.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MyTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void t1(){
        UserEntity user = userMapper.findUserByName("郑嘉茜");
        System.out.println("大小姐驾到："+user);
    }
}
