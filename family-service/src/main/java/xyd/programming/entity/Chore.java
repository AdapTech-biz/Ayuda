package xyd.programming.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(of = "id")
public class Chore {
    private Long id;
    private String title;
    private double reward;
    private String description;
    private LocalDateTime deadline;
    private FamilyMember assignee;
    private FamilyMember assignor;
    private boolean isActive;
    private final List<Photo> gallery;

    public Chore(String title, double reward, String description, FamilyMember assignor) {
        this.title = title;
        this.reward = reward;
        this.description = description;
        this.assignor = assignor;
        this.deadline = null;
        this.isActive = false;
        this.gallery = new ArrayList<>();
    }
}
