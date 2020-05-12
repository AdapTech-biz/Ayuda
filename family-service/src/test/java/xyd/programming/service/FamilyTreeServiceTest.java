package xyd.programming.service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import xyd.programming.config.TestConfig;
import xyd.programming.entity.*;
import xyd.programming.exception.FamilyServiceException;
import xyd.programming.repository.FamilyMemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest(classes = FamilyTreeServiceImpl.class)
@ContextConfiguration(classes = TestConfig.class)
public class FamilyTreeServiceTest {


    private static FamilyTreeService familyTreeService;
    @Autowired
    private FamilyMemberRepository<Parent> parentRepository;
    @Autowired
    private FamilyMemberRepository<Child> childRepository;
    private Parent parent;
    private Parent mom;

    @BeforeEach
    public void beforeEach() {
        familyTreeService = new FamilyTreeServiceImpl(parentRepository, childRepository);
        this.parent = familyTreeService.createParent("Xavier", "email.com", "5554445555", FamilyTitle.Dad);
        this.parent.setPersonId(12345L);

        this.mom =familyTreeService.createParent("Cari", "email.com", "5554445555", FamilyTitle.Mom);

    }

    @Test
    void getAllFamilyMembers() {
        ArrayList<Parent> familyMembers = new ArrayList<>();
        familyMembers.add(this.parent);
        Mockito.when(parentRepository.findAll()).thenReturn(familyMembers);

        List<FamilyMember> returnedList = familyTreeService.getAll();

        Assertions.assertThat(returnedList.size()).isEqualTo(1);

    }

    @Test
    void getParentById() {

        Mockito.when(parentRepository.findMemberById(12345l)).thenReturn(this.parent);

        Parent foundParent = familyTreeService.getParent(12345l);

        Assertions.assertThat(foundParent.getName()).isEqualTo("Xavier");
    }


    @Test
    @Tag("FamilyServiceTest")
    void createParent() {

        Assertions.assertThat(this.parent.getName()).isEqualTo("Xavier");

        Set<Child> childList = this.parent.getChildren();
        Assertions.assertThat(childList.size()).isEqualTo(0);
    }

    @Test
    @Tag("FamilyServiceTest")
    void createChildWithOutFamilyIds() {
//        Parent parent = familyTreeService.createParent("Xavier", "email.com", "5554445555", FamilyTitle.Dad);
//        parent.setPersonId(12345L);
        Mockito.when(parentRepository.findMemberById(12345L)).thenReturn(this.parent);

        Child child = familyTreeService.createChild("Zayden", Gender.Boy, this.parent);

        Assertions.assertThat(this.parent.getChildren().size()).isEqualTo(1);
        boolean childExist = this.parent.getChildren().stream().anyMatch((kid) -> kid.getName().equals("Zayden"));
//        boolean childExist = parent.getChildren().contains(child);

        Assertions.assertThat(childExist).isTrue();
        boolean familyIdMatch = parent.getFamilyId().equals(child.getFamilyId());
        Assertions.assertThat(familyIdMatch).isTrue();

    }


    @Test
    @Tag("FamilyServiceTest")
    void addPartnerToParent() {

        familyTreeService.setParentPartner(this.mom, this.parent);

        Assertions.assertThat(this.parent.getPartner().equals(this.mom)).isTrue();
    }

    @Test
    void addChoreToChild() {
        Child child = new Child("Tim", Gender.Boy);
        child.setPersonId(2222L);
        Mockito.when(childRepository.findMemberById(2222L)).thenReturn(child);
        familyTreeService.childAcceptChore(child, 9876L);

        int dashboardSize = child.getDashboard().size();
        boolean choreId = child.getDashboard().stream().anyMatch((id) -> id.equals(9876L));
        Assertions.assertThat(dashboardSize).isEqualTo(1);
        Assertions.assertThat(choreId).isTrue();

    }

    @Test
    void parentAddChore() {
        familyTreeService.addChore(this.parent, 5555L);
        int dashboardSize = this.parent.getDashboard().size();
        boolean choreId = this.parent.getDashboard().stream().anyMatch((id) -> id.equals(5555L));
        Assertions.assertThat(dashboardSize).isEqualTo(1);
        Assertions.assertThat(choreId).isTrue();
    }

    @Test
    void assignFamilyIds_WhenBothMembersNull() throws Exception {
        Child child = new Child("Tim", Gender.Boy);


            familyTreeService.assignFamilyId(this.parent, child);

            boolean familyIdMatch = this.parent.getFamilyId().equals(child.getFamilyId());
            Assertions.assertThat(familyIdMatch).isTrue();

    }

    @Test
    void assignFamilyIds_WhenChildIsAlreadyAssignedId() throws Exception {
        Child child = new Child("Tim", Gender.Boy);
        child.setFamilyId(7777L);

        Assertions.assertThatExceptionOfType(FamilyServiceException.class).isThrownBy(()->familyTreeService.assignFamilyId(this.parent, child));
//        familyTreeService.assignFamilyId(this.parent, child);
    }


    @Test
    @Ignore
    void assignFamilyIds_WhenChildIsAlreadyAssignedParent() throws Exception {
        Child child = new Child("Tim", Gender.Boy);
        child.setFamilyId(7777L);
        child.getParents().add(this.parent.getPersonId());

        this.parent.setPartner(this.mom);
        this.parent.setFamilyId(7777L);

        familyTreeService.assignFamilyId(mom, child);

        Assertions.assertThat(this.mom.getFamilyId()).isEqualTo(7777L);
//        Assertions.assertThatExceptionOfType(FamilyServiceException.class).isThrownBy(()->familyTreeService.assignFamilyId(this.parent, child));
//        familyTreeService.assignFamilyId(this.parent, child);
    }



}
