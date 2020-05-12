package xyd.programming.repository;

import org.springframework.stereotype.Service;
import xyd.programming.entity.Child;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
public class ChildRepositoryImpl implements FamilyMemberRepository<Child>{

    private final Map<Long, Child> childDataSource = new Hashtable<>();


    @Override
    public Child findMemberById(Long id) {
        return childDataSource.get(id);

    }

    @Override
    public boolean saveMember(Child Child) {
        childDataSource.putIfAbsent(Child.getPersonId(), Child);

        return childDataSource.containsKey(Child.getPersonId());
    }

    @Override
    public int count() {
        return childDataSource.size();
    }

    @Override
    public boolean updateMember(Child Child) {
        childDataSource.replace(Child.getPersonId(), Child);
        return false;
    }

    @Override
    public List findAll() {
        return new ArrayList<>(childDataSource.values());
//        return Collections.addAll(childDataSource.values());
    }

    @Override
    public boolean exist(Long id) {
        return childDataSource.containsKey(id);
    }

    @Override
    public boolean delete(Long id) {
        childDataSource.remove(id);
        return !childDataSource.containsKey(id);
    }
}
