package xyd.programming.repository;

import xyd.programming.entity.FamilyMember;
import xyd.programming.entity.Parent;

import java.util.List;

public interface FamilyMemberRepository<T extends FamilyMember> {

    T findMemberById (Long id);
    boolean saveMember(T member);
    boolean updateMember(T member);
    int count();
    List<T> findAll();
    boolean exist (Long id);
    boolean delete (Long id);
}
