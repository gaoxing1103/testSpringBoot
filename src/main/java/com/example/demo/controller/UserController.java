package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired (required = false)
    private UserMapper userMapper;

    @RequestMapping("/getUsers")
    public List<User> getUserList(@RequestBody Map<String,String> params){
        String search_username=params.get("userName");
        List<User> users=userMapper.selectUserByName(search_username);
        if(users!=null&&users.size()>0){
            return users;
        }else{
            return null;
        }
    }

    @RequestMapping("/addUser")
    public Result addUser(@RequestBody Map<String,String> params){
        String userName=params.get("userName");
        String realName=params.get("realName");
        String msg="";
        int count=0;
        if(userMapper.getUserByUserName(userName)==null){//验证重复
            User user=new User();
            user.setUserName(userName);
            user.setRealName(realName);
            count=userMapper.addUser(user);
            return Result.success("添加成功");
        }else{
            msg="用户【"+userName+"】已经存在，不可重复添加!";
        }
        return Result.fail(msg);
    }

    @RequestMapping("/deleteUser")
    public Result deleteUser(@RequestBody Map<String,String> params){
        String userId=params.get("userId");
        String msg="";
        int count=userMapper.deleteUserById(Integer.valueOf(userId));
        if(count>0){
            return Result.success("操作成功");
        }else{
            return Result.fail("操作失败");
        }

    }
}
