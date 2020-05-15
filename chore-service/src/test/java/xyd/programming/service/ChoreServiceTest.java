package xyd.programming.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import xyd.programming.config.TestConfig;
import xyd.programming.entity.Chore;
import xyd.programming.entity.ChoreStatus;
import xyd.programming.repository.ChoreRepository;


import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = ChoreServiceImpl.class)
@ContextConfiguration(classes = TestConfig.class)
public class ChoreServiceTest {

    @Mock
    private ChoreRepository<Chore> choreRepository;
    private ChoreService choreService;
    private Chore chore;



    @BeforeEach
    void setup() {
        this.choreService = new ChoreServiceImpl(choreRepository);
        this.chore = choreService.createChore("title", "Description", 200.0, 12345L);

    }


    @Test
    void createChore() {

       Assertions.assertNotNull(this.chore.getId());
    }

    @Test
    void findChore() {
        this.chore.setId(9090909L); //override chore Id for testing
        List<Long> searchIDs = List.of(9090909L);

        Mockito.when(choreRepository.findChoreById(searchIDs)).thenReturn(List.of(this.chore));

        List<Chore> foundChores = this.choreService.findChore(searchIDs);
        Assertions.assertEquals(1, foundChores.size());
        Assertions.assertEquals("title", foundChores.get(0).getTitle());
    }

    @Test
    void assignChore() {
        this.chore.setId(9090909L); //override chore Id for testing
        Mockito.when(choreRepository.findChoreById(List.of(9090909L))).thenReturn(List.of(this.chore));
        List<Chore> chores = choreService.assignChore(9090909L, 5555L);

        Assertions.assertEquals(1, chores.size());

        Assertions.assertEquals(9090909L, chores.get(0).getId());
        Assertions.assertEquals(5555L, chores.get(0).getAssigneeId());
    }

    @Test
    void updateChoreWhenAssigneeRemoved() {
        this.chore.setId(9090909L); //override chore Id for testing
        this.chore.setAssigneeId(null);
        Mockito.when(choreRepository.findChoreById(List.of(this.chore.getId())))
                .thenReturn(List.of(this.chore));

        this.choreService.updateChore(chore);
        Assertions.assertEquals(ChoreStatus.Draft, this.chore.getStatus());
    }

    @Test
    void updateChoreWhenAssigneeUnmodified() {
        this.chore.setId(9090909L); //override chore Id for testing
        this.chore.setAssigneeId(12345L);
        Mockito.when(choreRepository.findChoreById(List.of(this.chore.getId())))
                .thenReturn(List.of(this.chore));

        this.choreService.updateChore(chore);
        Assertions.assertEquals(ChoreStatus.Active, this.chore.getStatus());
    }
}
