package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.*;
import com.events.eventsapp.repositories.IRelationshipRepository;
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

    @Qualifier("relationshipRepository")
    @Autowired
    private IRelationshipRepository iRelationshipRepository;

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

    @Override
    public boolean areFriends(String userName, String principalName) {
        UserModel userModel1 = iUserRepository.findByName(userName);
        UserModel userModel2 = iUserRepository.findByName(principalName);

        RelationshipModel relationshipModel1 = iRelationshipRepository.findByAlphaUserModelAndBetaUserModel(userModel1, userModel2);
        RelationshipModel relationshipModel2 = iRelationshipRepository.findByAlphaUserModelAndBetaUserModel(userModel2, userModel1);

        if (relationshipModel1 == null || relationshipModel2 == null) {
            return false;
        }
        else if (!relationshipModel1.isFriend() || !relationshipModel2.isFriend()) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void addContacts(UserModel userModel1, UserModel userModel2) {
        //Loading relationship between users.
        RelationshipModel relationshipModel = iRelationshipRepository.findByAlphaUserModelAndBetaUserModel(userModel1, userModel2);
        if (relationshipModel != null) {
            relationshipModel.setFriend(true);
            iRelationshipRepository.save(relationshipModel);
        }
        else {
            RelationshipModel localRelationshipModel = new RelationshipModel();
            localRelationshipModel.setFriend(true);
            localRelationshipModel.setBlocked(false);
            localRelationshipModel.setAlphaUserModel(userModel1);
            localRelationshipModel.setBetaUserModel(userModel2);
            iRelationshipRepository.save(localRelationshipModel);
        }

        relationshipModel = null;
        relationshipModel = iRelationshipRepository.findByAlphaUserModelAndBetaUserModel(userModel2, userModel1);
        if (relationshipModel != null) {
            relationshipModel.setFriend(true);
            iRelationshipRepository.save(relationshipModel);
        }
        else {
            RelationshipModel localRelationshipModel = new RelationshipModel();
            localRelationshipModel.setFriend(true);
            localRelationshipModel.setBlocked(false);
            localRelationshipModel.setAlphaUserModel(userModel2);
            localRelationshipModel.setBetaUserModel(userModel1);
            iRelationshipRepository.save(localRelationshipModel);
        }
    }

    @Override
    public void deleteContacts(UserModel userModel1, UserModel userModel2) {
        //Loading relationship between users.
        RelationshipModel relationshipModel = iRelationshipRepository.findByAlphaUserModelAndBetaUserModel(userModel1, userModel2);
        if (relationshipModel != null) {
            relationshipModel.setFriend(false);
            iRelationshipRepository.save(relationshipModel);
        }
        else {
            RelationshipModel localRelationshipModel = new RelationshipModel();
            localRelationshipModel.setFriend(false);
            localRelationshipModel.setBlocked(false);
            localRelationshipModel.setAlphaUserModel(userModel1);
            localRelationshipModel.setBetaUserModel(userModel2);
            iRelationshipRepository.save(localRelationshipModel);
        }

        relationshipModel = null;
        relationshipModel = iRelationshipRepository.findByAlphaUserModelAndBetaUserModel(userModel2, userModel1);
        if (relationshipModel != null) {
            relationshipModel.setFriend(false);
            iRelationshipRepository.save(relationshipModel);
        }
        else {
            RelationshipModel localRelationshipModel = new RelationshipModel();
            localRelationshipModel.setFriend(false);
            localRelationshipModel.setBlocked(false);
            localRelationshipModel.setAlphaUserModel(userModel2);
            localRelationshipModel.setBetaUserModel(userModel1);
            iRelationshipRepository.save(localRelationshipModel);
        }
    }
}