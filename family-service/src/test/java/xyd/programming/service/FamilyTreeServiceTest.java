package xyd.programming.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyd.programming.entity.Child;
import xyd.programming.entity.FamilyTitle;
import xyd.programming.entity.Gender;
import xyd.programming.entity.Parent;
import java.util.Set;

@SpringBootTest(classes = FamilyTreeServiceImpl.class)
public class FamilyTreeServiceTest {

    private static FamilyTreeService familyTreeService;

    @BeforeAll
    public static void beforeAll() {
        familyTreeService = new FamilyTreeServiceImpl();
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
    void createChildWithMultipleParents() {
        Parent dad =familyTreeService.createParent("Xavier", "email.com", "5554445555", FamilyTitle.Dad);
        Parent mom =familyTreeService.createParent("Cari", "email.com", "5554445555", FamilyTitle.Mom);

        Child child = familyTreeService.createChild("Yarnell", Gender.Boy, dad);
        familyTreeService.addParentToChild(child, mom);

        Assertions.assertThat(child.getParents().size()).isEqualTo(2);

    }

    @Test
    void addPartnerToParent() {
        Parent dad =familyTreeService.createParent("Xavier", "email.com", "5554445555", FamilyTitle.Dad);
        Parent mom =familyTreeService.createParent("Cari", "email.com", "5554445555", FamilyTitle.Mom);

        familyTreeService.setParentPartner(mom, dad);

        Assertions.assertThat(dad.getPartner().equals(mom)).isTrue();
    }



}
