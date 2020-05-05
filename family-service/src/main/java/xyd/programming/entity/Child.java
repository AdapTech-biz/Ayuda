package xyd.programming.entity;

import lombok.Data;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Data
public class Child extends FamilyMember {

    private Gender gender;
    private Long accountId;
    private Set<Long> parents;

    public Child(String name, Gender gender) {
        super(name, Role.Child);
        this.gender = gender;
        this.parents = new HashSet<>();
    }

}
