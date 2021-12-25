package com.udacity.jwdnd.course1.cloudstorage.Mapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);
    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{userName}, " +
            "#{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
    @Select("SELECT * FROM USERS WHERE userId = #{userid}")
    User getUserById(Integer userId);

    @Select("SELECT userId FROM USERS WHERE username = #{username}")
    Integer getUserIdByName(String username);

}