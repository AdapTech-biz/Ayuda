package xyd.programming.service;

import xyd.programming.entity.*;

import java.util.List;

public interface FamilyTreeService {

    void saveMember(FamilyMember member);

    List<FamilyMember> getAll();

    Long generateId();

    void assignFamilyId(Parent parent, Child child) throws Exception;

    Parent createParent (String name, String email, String phoneNumber, FamilyTitle title);

    Parent getParent (Long id);

    Child createChild(String childName, Gender gender, Parent parent);

    Parent addChildToParent(Long parentId, Child child);

    void setParentPartner(Parent parent1, Parent parent2);

    void addChore(Parent parent, Long choreID);

    void childAcceptChore(Child child, Long choreID);

    List<Parent> demoFamily();

    void setUniqueIds(FamilyMember member);

}
