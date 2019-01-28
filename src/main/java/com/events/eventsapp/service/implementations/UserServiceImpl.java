package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.TimeLinePostModel;
import com.events.eventsapp.model.UserDetailsModel;
import com.events.eventsapp.model.RoleModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.repositories.IRoleRepository;
import com.events.eventsapp.repositories.IUserRepository;
import com.events.eventsapp.service.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Qualifier("userRepository")
    @Autowired
    private IUserRepository iUserRepository;

    @Qualifier("roleRepository")
    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserModel findUserByEmail(String email) {

        return iUserRepository.findByEmail(email);

    }

    @Override
    public UserModel findUserByName(String username) {

        return iUserRepository.findByName(username);

    }

    @Override
    public Optional <UserModel> findUserById(Long Id) {

        return iUserRepository.findById(Id);

    }

    @Override
    public UserModel validateUser(String email, String password) {

        UserModel userModel = iUserRepository.findByEmail(email);

        if (userModel != null) {

            if (bCryptPasswordEncoder.matches(password,userModel.getPassword()) && (userModel.getActive() != 0)) {

                return userModel;

            } else {

                return null;

            }

        } else {

            return null;

        }

    }

    /**
     * This method should be used ONLY when registering user. Otherwise it is going to overwrite the password
     * causing authentication problems.
     * @param user UserModel object to be saved in database. Must have name, email and password set.
     */
    @Override
    public void saveJustRegisteredUser(UserModel user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleModel userRole = iRoleRepository.findByRole("USER");
        user.setRoles(new HashSet<RoleModel>(Arrays.asList(userRole)));

        UserDetailsModel userDetailsModel = new UserDetailsModel();
        userDetailsModel.setJoindate(new Timestamp(System.currentTimeMillis()));
        user.setUserDetailsModel(userDetailsModel);

        iUserRepository.save(user);

    }

    @Transactional
    @Override
    public void updateUser(UserModel user) {

        iUserRepository.save(user);

    }

    @Transactional
    @Override
    public void deleteUserModelByEmail(String email) {

        iUserRepository.deleteUserModelByEmail(email);

    }

    @Override
    public List<TimeLinePostModel> getUserMainTimeLinePosts(UserModel userModel) {
        List<TimeLinePostModel> timeLinePostModelList = iUserRepository.getUserMainTimeLinePosts(userModel.getId());
        return timeLinePostModelList;
    }
}