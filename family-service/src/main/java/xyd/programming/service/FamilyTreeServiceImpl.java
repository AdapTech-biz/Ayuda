package xyd.programming.service;


import org.springframework.stereotype.Service;
import xyd.programming.entity.*;

import java.util.*;

@Service
public class FamilyTreeServiceImpl implements FamilyTreeService {

    private final Random random = new Random();


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

        return parent;
    }

    @Override
    public Child createChild(String childName, Gender gender, Parent forParent) {
        Child child = new Child(childName, gender);
        child.setPersonId(generateId());
        child.setFamilyId(forParent.getFamilyId());

        child.getParents().add(forParent.getPersonId()); //associates parent id to child
        forParent.getChildren().add(child); //associates Child with parent

        return child;
    }

    @Override
    public void addParentToChild(Child child, Parent parent) {
        child.getParents().add(parent.getPersonId());
    }

    @Override
    public void setParentPartner(Parent parent1, Parent parent2) {
        parent1.setPartner(parent2);
        parent2.setPartner(parent1);
    }

    @Override
    public List<FamilyMember> demoFamily() {
        Parent parent = createParent("Xavier", "email@email.com", "555555555", FamilyTitle.Dad);

//        Account account = new Account(parent.getPersonId());
//        parent.setAccount(account);
        createChild("Zayden", Gender.Boy, parent);

        Parent parent2 = createParent("Bob", "email@email.com", "555555555", FamilyTitle.Dad);
        createChild("Zack", Gender.Boy, parent2);

        LinkedList<FamilyMember> familyTree = new LinkedList<>();
        familyTree.add(parent);
        familyTree.add(parent2);
        return familyTree;
    }

}
