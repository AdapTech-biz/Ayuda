package xyd.programming.repository;

import org.springframework.stereotype.Service;
import xyd.programming.entity.Chore;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChoreRepositoryImpl implements ChoreRepository<Chore> {

    private final Map<Long, Chore> choreDataSource = new Hashtable<>();


    @Override
    public List<Chore> findChoreById(List<Long> choreIDs) {

        return  choreDataSource.values().stream().filter((chore) -> choreIDs.contains(chore.getId()))
                .collect(Collectors.toList());
//        return choreDataSource.get(id);
    }


    @Override
    public boolean saveChore(Chore chore) {
        choreDataSource.putIfAbsent(chore.getId(), chore);
        return choreDataSource.containsKey(chore.getId());
    }

    @Override
    public boolean updateChore(Chore chore) {
        choreDataSource.replace(chore.getId(), chore);
        return true;
    }

    @Override
    public int count() {
        return choreDataSource.size();
    }

    @Override
    public List<Chore> findAll() {
        return new ArrayList<Chore>(choreDataSource.values());
    }

    @Override
    public boolean exist(Long id) {
        return choreDataSource.containsKey(id);
    }

    @Override
    public boolean delete(Long id) {
        choreDataSource.remove(id);
        return !choreDataSource.containsKey(id);
    }
}
