package xyd.programming.service;

import xyd.programming.entity.*;

import java.util.List;

public interface FamilyTreeService {

    Long generateId();

    Parent createParent (String name, String email, String phoneNumber, FamilyTitle title);

    Child createChild(String childName, Gender gender, Parent parent);

    void addParentToChild(Child child, Parent parent);

    void setParentPartner(Parent parent1, Parent parent2);

    List<FamilyMember> demoFamily();

    void setUniqueIds(FamilyMember member);

}
