package xyd.programming.repository;

import org.springframework.stereotype.Service;
import xyd.programming.entity.Parent;

import java.util.*;

@Service
public class ParentRepositoryImpl implements FamilyMemberRepository<Parent> {

    private final Map<Long, Parent> parentDataSource = new Hashtable<>();


    @Override
    public Parent findMemberById(Long id) {
        return parentDataSource.get(id);

    }

    @Override
    public boolean saveMember(Parent parent) {
        parentDataSource.putIfAbsent(parent.getPersonId(), parent);

        return parentDataSource.containsKey(parent.getPersonId());
    }

    @Override
    public int count() {
        return parentDataSource.size();
    }

    @Override
    public boolean updateMember(Parent parent) {
        parentDataSource.replace(parent.getPersonId(), parent);
        return false;
    }

    @Override
    public List findAll() {
        return new ArrayList<>(parentDataSource.values());
//        return Collections.addAll(parentDataSource.values());
    }

    @Override
    public boolean exist(Long id) {
        return parentDataSource.containsKey(id);
    }

    @Override
    public boolean delete(Long id) {
        parentDataSource.remove(id);
        return !parentDataSource.containsKey(id);
    }
}
