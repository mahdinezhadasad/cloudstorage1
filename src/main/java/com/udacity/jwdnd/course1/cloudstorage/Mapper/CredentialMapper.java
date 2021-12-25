package com.udacity.jwdnd.course1.cloudstorage.Mapper;


import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {


    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getAllCredential(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential userId);
    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    int deleteCredential(Integer credentialId);
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} where credentialId = #{credentialId}")
    int updateCredential(Credential credential);
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{userName}, password = #{password} WHERE credentialId = #{credentialId}")
    int updateCredentialByCredentialId(Credential credential);
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getListCredential(Integer userId);
    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential  getCredential(Integer credentialId);
    @Select("SELECT key FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    String credentialKey(Integer credentialId);
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId} AND username = #{userName}")
    int Credntial(Integer credentialId, String username);
    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url} AND username = #{userName}")
    int CredntialUrlAndUserName(String url, String username);

    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url}")
    Credential getCredentialByUrl(String url);


}
