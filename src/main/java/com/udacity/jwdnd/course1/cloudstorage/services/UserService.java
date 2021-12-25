package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashServices;
    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashServices = hashService;
    }
    public boolean isUserAvailable(String username){
        return userMapper.getUser(username)== null;
    }
    public int create(User user){
        SecureRandom  random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().toString();
        String hashedPassword = hashServices.getHashedValue(user.getPassword(),encodedSalt);
        return userMapper.insert( new User(null,hashedPassword,encodedSalt,user.getUserName(),user.getFirstName(),user.getLastName()));
    }
    public User getUser(String username){
        return userMapper.getUser(username);
    }
    public User getUserById(Integer Id){
        return userMapper.getUserById(Id);
    }

    public Integer getUserId(String username){

        return userMapper.getUserIdByName(username);
    }
}