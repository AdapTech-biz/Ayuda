package xyd.programming.service;

import xyd.programming.entity.Chore;

import java.util.List;

public interface ChoreService {

    Long generateID();

    Chore createChore(String title, String description, double reward, Long assignor);

    List<Chore> assignChore(Long choreId, Long assigneeId);

    List<Chore> findChore(List<Long> searchIDs);

    Chore updateChore(Chore chore);
}
