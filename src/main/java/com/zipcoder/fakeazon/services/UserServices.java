package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.exception.NotFoundException;
import com.zipcoder.fakeazon.models.Address;
import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zipcoder.fakeazon.exception.IllegalArgumentException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    final private UserRepository userRepo;

    @Autowired
    public UserServices(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User user){
        return userRepo.save(user);
    }

    public User createUser(User user){
        if(!userRepo.findUserByEmail(user.getEmail()).isPresent()){
            return userRepo.save(user);
        }
        else throw new IllegalArgumentException("Email is already in our system. Try something else!");
    }

    public Optional<User> findUserById(Integer id){
        return userRepo.findById(id);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    //put methods
    public User changeFirstName(Integer id, String newFirstName){
        User u = checkIfUserExists(id);
        u.setFirstName(newFirstName);
        return userRepo.save(u);
    }

    public User changeLastName(Integer id, String newLastName){
        User u = checkIfUserExists(id);
        u.setLastName(newLastName);
        return userRepo.save(u);
    }

    public User changeEmail(Integer id, String newEmail){
        User u = checkIfUserExists(id);
        u.setEmail(newEmail);
        return userRepo.save(u);
    }

    public User changeAddress(Integer id, Address newAddress){
        User u = checkIfUserExists(id);
        u.setAddress(newAddress);
        return userRepo.save(u);
    }

    public User changeShop(Integer id, Shop newShop){
        User u = checkIfUserExists(id);
        u.setShop(newShop);
        return userRepo.save(u);
    }

    public User checkIfUserExists(Integer id){
        Optional<User> user = findUserById(id);
        if (user.isPresent())
            return user.get();
        else throw new NotFoundException("No data found for this user.");
    }


}
