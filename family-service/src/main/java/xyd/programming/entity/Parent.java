package xyd.programming.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Parent extends FamilyMember {
    private String email;
    private String phoneNumber;
    private FamilyTitle familyTitle;
    private Long accountId;
    private Set<Child> children;
    private Parent partner;

    public Parent(String name, String email, String phoneNumber, FamilyTitle familyTitle) {
        super(name, Role.Parent);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.familyTitle = familyTitle;
        this.children = new HashSet<>();
//        this.account = new Account(this);

    }
}