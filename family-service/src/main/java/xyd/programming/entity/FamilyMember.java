package xyd.programming.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Data
public class FamilyMember {


    private Long personId = 0L; //UUID for each member
    private Long familyId = 0L; //id shared by entire family
    private String name;
    private Photo photo;
    private Role role;
    private Set<Long> dashboard;


    public FamilyMember(String name, Role role) {
        this.name = name;
        this.photo = new Photo("", LocalDateTime.now());
        this.role = role;
        this.dashboard = new HashSet<>();
    }
}