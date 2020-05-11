package xyd.programming.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import xyd.programming.config.TestConfig;
import xyd.programming.entity.Child;
import xyd.programming.entity.FamilyTitle;
import xyd.programming.entity.Gender;
import xyd.programming.entity.Parent;
import xyd.programming.repository.FamilyMemberRepository;

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

    @BeforeEach
    public void beforeAll() {
        familyTreeService = new FamilyTreeServiceImpl(parentRepository, childRepository);
    }


    @Test
    void createParent() {
        Parent parent = familyTreeService.createParent("Xavier", "email.com", "5554445555", FamilyTitle.Dad);
        Assertions.assertThat(parent.getName()).isEqualTo("Xavier");

        Set<Child> childList = parent.getChildren();
        Assertions.assertThat(childList.size()).isEqualTo(0);
    }

    @Test
    void createChild() {
        Parent parent = familyTreeService.createParent("Xavier", "email.com", "5554445555", FamilyTitle.Dad);
        Child child = familyTreeService.createChild("Zayden", Gender.Boy, parent);

        Assertions.assertThat(parent.getChildren().size()).isEqualTo(1);
        boolean childExist = parent.getChildren().contains(child);

        Assertions.assertThat(childExist).isTrue();

    }


    @Test
    void addPartnerToParent() {
        Parent dad =familyTreeService.createParent("Xavier", "email.com", "5554445555", FamilyTitle.Dad);
        Parent mom =familyTreeService.createParent("Cari", "email.com", "5554445555", FamilyTitle.Mom);

        familyTreeService.setParentPartner(mom, dad);

        Assertions.assertThat(dad.getPartner().equals(mom)).isTrue();
    }



}
