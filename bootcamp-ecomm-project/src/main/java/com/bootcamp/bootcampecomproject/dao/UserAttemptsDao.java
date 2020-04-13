package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.entities.UserAttempts;
import com.bootcamp.bootcampecomproject.repositories.UserAttemptsRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;


@Component
public class UserAttemptsDao {
    @Autowired
    private UserAttemptsRepository userAttemptsRepository;
    @Autowired
    UserRepository userRepository;

    private Integer count=0;

    public UserAttempts getUserAttemps(String email){
        UserAttempts userAttempts=userAttemptsRepository.findByEmail(email);
        if(userAttempts!=null){
            return userAttempts;
        }
        else{
            doSave(email);
            return userAttemptsRepository.findByEmail(email);
        }
    }
    private void doSave(String email){

        UserAttempts userAttempts=new UserAttempts();
        userAttempts.setEmail(email);
        userAttempts.setAttempts(count);
        Calendar cal = Calendar.getInstance();
        userAttempts.setLastModified(new Date(cal.getTime().getTime()));
        userAttemptsRepository.save(userAttempts);
    }
    public void updateAttempts(String email){
        UserAttempts userAttempts=userAttemptsRepository.findByEmail(email);
        Integer attempt=userAttempts.getAttempts()+1;
        userAttemptsRepository.doUpdateAttempt(attempt,email);
    }
    public void updateAttemptsToNull(String email){
        UserAttempts userAttempts=userAttemptsRepository.findByEmail(email);
        userAttemptsRepository.doUpdateAttempt(count,email);
    }
    private Integer getAttempt(String email){
        return userAttemptsRepository.findByEmail(email).getAttempts();
    }
    public Boolean checkLock(String email){
        Integer attempts=getAttempt(email);
        if(attempts>3){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean checkIsActive(String email){
        User user=userRepository.findByEmail(email);
        Boolean status=user.getActive();
        System.out.println(status+"============");
        return status;
    }
}
