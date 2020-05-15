package xyd.programming.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyd.programming.entity.Chore;
import xyd.programming.entity.ChoreStatus;
import xyd.programming.repository.ChoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class ChoreServiceImpl implements ChoreService {

    private final ChoreRepository<Chore> choreRepository;
    private final Random random = new Random();

    @Autowired
    public ChoreServiceImpl(ChoreRepository<Chore> choreRepository) {
        this.choreRepository = choreRepository;
    }

    @Override
    public Long generateID() {
        return this.random.nextLong();
    }

    @Override
    public Chore createChore(String title, String description, double reward, Long assignor) {
        Chore chore = new Chore(title, reward, description, assignor);
        chore.setId(generateID());
        this.choreRepository.saveChore(chore);
        return chore;
    }

    @Override
    public List<Chore> assignChore(Long choreId, Long assigneeId) {
       List<Chore> foundChores = this.choreRepository.findChoreById(List.of(choreId));

        for (Chore chore: foundChores ) {
            chore.setAssigneeId(assigneeId);
            chore.setStatus(ChoreStatus.Active);
            this.choreRepository.updateChore(chore);
        }

       return foundChores;
    }

    @Override
    public List<Chore> findChore(List<Long> searchIDs) {
        return this.choreRepository.findChoreById(searchIDs);
    }

    @Override
    public Chore updateChore(Chore chore) {
        //check update for change in assignee -- reflect status accordingly
        if(chore.getAssigneeId() == null)
            chore.setStatus(ChoreStatus.Draft);
        else chore.setStatus(ChoreStatus.Active);

        this.choreRepository.updateChore(chore);
        return this.choreRepository.findChoreById(List.of(chore.getId())).get(0);
    }
}
