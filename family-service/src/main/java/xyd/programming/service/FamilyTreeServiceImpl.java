package xyd.programming.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyd.programming.entity.*;
import xyd.programming.exception.FamilyServiceException;
import xyd.programming.repository.FamilyMemberRepository;

import java.util.*;

@Service
@Slf4j
public class FamilyTreeServiceImpl implements FamilyTreeService {

    private final Random random = new Random();
    private  final FamilyMemberRepository<Parent> parentRepository;
    private  final FamilyMemberRepository<Child> childRepository;

    @Autowired
    public FamilyTreeServiceImpl(FamilyMemberRepository<Parent> parentRepository, FamilyMemberRepository<Child> childRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }

    @Override
    public void saveMember(FamilyMember member) {
        parentRepository.saveMember((Parent) member);
    }

    @Override
    public List getAll() {
        return parentRepository.findAll();
    }

    @Override
    public Long generateId() {
        return random.nextLong();
    }

    @Override
    public void setUniqueIds(FamilyMember member) {
        member.setPersonId(generateId());
    }

    @Override
    public Parent createParent(String name, String email, String phone, FamilyTitle title) {
        Parent parent = new Parent(name, email, phone, title );
        parent.setPersonId(generateId());
        parent.setFamilyId(generateId());

        parentRepository.saveMember(parent);

        return parent;
    }

    @Override
    public Parent getParent(Long id) {
        return parentRepository.findMemberById(id);
    }

    @Override
    public Child createChild(String childName, Gender gender, Parent forParent) {
        Child child = new Child(childName, gender);
        child.setPersonId(generateId());
        child.setFamilyId(forParent.getFamilyId());

        child.getParents().add(forParent.getPersonId()); //associates parent id to child
        forParent.getChildren().add(child); //associates Child with parent

        childRepository.saveMember(child);
        parentRepository.updateMember(forParent);


        return child;
    }

    @Override
    public Parent addChildToParent(Long parentId, Child child) {
       Parent parent =  parentRepository.findMemberById(parentId);


       parent.getChildren().add(child);
       child.getParents().add(parent.getPersonId());

       try{

           assignFamilyId(parent, child); //both child and parent are update on method call
       }catch (FamilyServiceException familyServiceException) {
           log.debug(familyServiceException.getMessage());
       }
       return parent;
    }

    @Override
    public void assignFamilyId(Parent parent, Child child) throws FamilyServiceException {
        //TODO: Check if child has max parents assigned

        Long familyId;
        if (parent.getFamilyId() == null && child.getFamilyId() == null) {

            familyId = generateId();
            parent.setFamilyId(familyId);
            child.setFamilyId(familyId);
            parentRepository.updateMember(parent);
        } else if (parent.getFamilyId() != null && child.getFamilyId() == null) {
            Long parentFamilyId = parent.getFamilyId();
            child.setFamilyId(parentFamilyId);
        } else if (parent.getFamilyId() == null && child.getFamilyId() != null) {
            Long childParentId = child.getParents().stream().findFirst().get();
            //check if parent is partners with child's parent
            boolean isPartners = parent.getPartner().getPersonId().equals(childParentId);

            if(isPartners) {
                Parent partner = parent.getPartner();
                parent.setFamilyId(partner.getFamilyId());

                parentRepository.updateMember(parent);
            } else {
                throw  new FamilyServiceException("CHILD ALREADY ASSIGNED TO PARENT && NOT PARTNERS");
            }

        } else {
            if (parent.getFamilyId().equals(child.getFamilyId())){

               throw  new FamilyServiceException("Two members already associated");
            } else
                throw  new FamilyServiceException("conflicting family IDs");
        }


        childRepository.updateMember(child);

    }

    @Override
    public void addChore(Parent parent, Long choreID) {
        parent.getDashboard().add(choreID);
        parentRepository.updateMember(parent);
    }

    @Override
    public void childAcceptChore(Child child, Long choreID) {
        Child foundChild = childRepository.findMemberById(child.getPersonId());
        foundChild.getDashboard().add(choreID);
        //TODO: send childId over to Chore service to assign to Chore
        childRepository.updateMember(foundChild);

    }


    @Override
    public void setParentPartner(Parent parent1, Parent parent2) {
        parent1.setPartner(parent2);
        parent2.setPartner(parent1);

        //TODO: SET FAMILY ID HERE
    }

    @Override
    public List<Parent> demoFamily() {

        return parentRepository.findAll();
    }

}
