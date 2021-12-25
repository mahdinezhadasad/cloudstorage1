package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private final CredentialMapper credentialMapper;
    @Autowired
    private  HashService hashService;
    @Autowired
    private EncryptionService encryptService;

   /* public Integer saveOne(Credential credential) {
        String key =  hashService.createEncodedSalt();
        String hashedPass = encryptService
                .encryptValue(credential.getPassword(), key);
        credential.setKey(key);
        credential.setPassword(hashedPass);
        return credentialMapper.addCredential(credential);
    }*/
    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @PostConstruct
    public void postConstruct(){

        System.out.println("Credential post consrcut created");

    }

    public Integer addOrEditCredential(Credential credential) {
        if (credential.getCredentialId() == null){
            String key = hashService.createEncodedSalt();
            String hashedPass = encryptService.encryptValue(credential.getPassword(),key);
            credential.setKey(key);
            credential.setPassword(hashedPass);
            return credentialMapper.addCredential(credential);
        }
        else{
            Credential credential1 = this.getCredentialById(credential.getCredentialId());
            String key = credential1.getKey();
            String hashedPass = encryptService.encryptValue(credential.getPassword(),key);
            credential.setKey(key);
            credential.setPassword(hashedPass);
            return this.credentialMapper.updateCredential(credential);
        }
    }

    public Integer deleteCredential(Integer credentialId){
        return credentialMapper.deleteCredential(credentialId);
    }

    public List<Credential> getCredentials(Integer userId){
        return credentialMapper.getAllCredential(userId);
    }

    public Credential getCredentialById(Integer credential){ return credentialMapper.getCredential(credential); }

    public List<Credential> getCredentialListById(Integer credentialId){return credentialMapper.getListCredential(credentialId);

    };

    public String getKeyByCredentialId(Integer credential){ return credentialMapper.credentialKey(credential); }

    public int getCredentialByURLandUsername(String url, String username){
        return credentialMapper.CredntialUrlAndUserName(url, username);
    }

    public Credential getCredentialByUrl(String Url){


        return credentialMapper.getCredentialByUrl(Url);
    }





}
