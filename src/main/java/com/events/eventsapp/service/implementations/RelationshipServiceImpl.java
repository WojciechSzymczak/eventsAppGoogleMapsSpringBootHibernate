package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.RelationshipModel;
import com.events.eventsapp.repositories.IRelationshipRepository;
import com.events.eventsapp.service.interfaces.IRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("relationshipService")
public class RelationshipServiceImpl implements IRelationshipService {

    @Qualifier("relationshipRepository")
    @Autowired
    private IRelationshipRepository iRelationshipRepository;


    @Override
    public RelationshipModel findRelationshipById(Long id) {
        return iRelationshipRepository.findById(id).get();
    }

    @Override
    public List<RelationshipModel> getAllRelationships() {
        return null;
    }

    @Override
    public void updateRelationship(RelationshipModel relationshipModel) {
        iRelationshipRepository.save(relationshipModel);
    }

    @Override
    public void deleteRelationshipById(Long id) {
        iRelationshipRepository.deleteById(id);
    }

    @Override
    public void deleteRelationship(RelationshipModel relationshipModel) {
        iRelationshipRepository.delete(relationshipModel);
    }
}