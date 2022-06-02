package com.java.m2.Milestone2.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.java.m2.Milestone2.entity.UserEntity;
import com.java.m2.Milestone2.repository.UserRepository;
import com.java.m2.Milestone2.services.UserService;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.sql.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.PathParam;
import java.util.List;

import static java.util.Base64.*;


@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/check")
    public String check(){
        return "Hello world";
    }

    @PostMapping("/encryptcreds")
    public String encryptCreds(@RequestParam("username") String user, @RequestParam("password") String password){
        String auth_string = "";
        byte[] bytes = (user + ":" + password).getBytes(StandardCharsets.UTF_8);
        auth_string = Base64.getEncoder().encodeToString(bytes);
        return auth_string;
    }

    @PostMapping("/checklogin")
    public String checkLogin(@RequestParam("auth") String auth) throws SQLException {
        if (isauthenticated(auth)){
            return "success";
        }
        return "error";
    }

    private boolean isauthenticated(String auth) throws SQLException {
        String decode_string = "";
        byte[] bytes = null;
        try {
            bytes = getDecoder().decode(auth);
            decode_string =  new String(bytes, StandardCharsets.UTF_8.toString());
        }
        catch(UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        String[] attributes = decode_string.split(":");
        Boolean result = false;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user","root","1254");
            PreparedStatement stmt = con.prepareStatement("select * from usertable where username=? and password=?;");
            stmt.setString(1, attributes[0]);
            stmt.setString(2, attributes[1]);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {
                result = true;
            }
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
