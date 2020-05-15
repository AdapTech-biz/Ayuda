package xyd.programming.repository;

import xyd.programming.entity.Chore;

import java.util.List;

public interface ChoreRepository<T extends Chore> {

    List<T> findChoreById (List<Long> choreIDs);
    boolean saveChore(T chore);
    boolean updateChore(T chore);
    int count();
    List<T> findAll();
    boolean exist (Long id);
    boolean delete (Long id);
}
