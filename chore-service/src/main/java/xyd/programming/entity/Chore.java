package xyd.programming.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
public class Chore {
    private Long id;
    private String title;
    private double reward;
    private String description;
    private LocalDateTime deadline;
    private Long assigneeId;
    private Long assignorId;
    private boolean isActive;
    private final List<Long> gallery;

    public Chore(String title, double reward, String description, Long assignorId) {
        this.title = title;
        this.reward = reward;
        this.description = description;
        this.assignorId = assignorId;
        this.deadline = null;
        this.isActive = false;
        this.gallery = new ArrayList<>();
    }
}
