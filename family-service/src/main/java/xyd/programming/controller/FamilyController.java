package xyd.programming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xyd.programming.entity.FamilyMember;
import xyd.programming.entity.Parent;
import xyd.programming.service.FamilyTreeService;

import java.util.List;

@RestController
public class FamilyController {

    private final FamilyTreeService familyTreeService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public FamilyController(FamilyTreeService familyTreeService) {
        this.familyTreeService = familyTreeService;
    }

    @PostMapping("new/parent")
    public Parent createParent(@RequestBody Parent parent) {
        parent.setPersonId(familyTreeService.generateId());

        String url = "http://account-service/new/" + parent.getPersonId();
        Long accountId = restTemplate.getForObject(url, Long.class);
        parent.setAccountId(accountId);
        return parent;
    }

    @GetMapping("demo")
    public List<FamilyMember> demo() {

        return this.familyTreeService.demoFamily();
    }

}
