package com.events.eventsapp.repositories;

import com.events.eventsapp.model.RelationshipModel;
import com.events.eventsapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("relationshipRepository")
public interface IRelationshipRepository extends JpaRepository<RelationshipModel, Long> {

    Optional <RelationshipModel> findById(Long id);
    RelationshipModel findByAlphaUserModelAndBetaUserModel(UserModel alpha, UserModel beta);
}