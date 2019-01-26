package com.events.eventsapp.service.interfaces;

import com.events.eventsapp.model.RelationshipModel;

import java.util.List;

public interface IRelationshipService {

    RelationshipModel findRelationshipById(Long id);

    List<RelationshipModel> getAllRelationships();

    void updateRelationship(RelationshipModel relationshipModel);

    void deleteRelationshipById(Long id);

    void deleteRelationship(RelationshipModel relationshipModel);
}