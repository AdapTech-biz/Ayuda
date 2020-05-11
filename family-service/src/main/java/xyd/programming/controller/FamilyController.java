package xyd.programming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xyd.programming.entity.Child;
import xyd.programming.entity.FamilyMember;
import xyd.programming.entity.Parent;
import xyd.programming.service.FamilyTreeService;
import xyd.programming.utils.Endpoints;
import xyd.programming.utils.Mappings;
import xyd.programming.utils.PathVariables;

import java.util.List;
import java.util.Map;

@RestController
public class FamilyController {

    private final FamilyTreeService familyTreeService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public FamilyController(FamilyTreeService familyTreeService) {
        this.familyTreeService = familyTreeService;
    }

    @PostMapping(Mappings.NEW_PARENT)
    public Parent createParent(@RequestBody Parent parent) {
        parent.setPersonId(familyTreeService.generateId());

        String url = Endpoints.ACCOUNT_SERVICE_NEW + parent.getPersonId();
        Long accountId = restTemplate.getForObject(url, Long.class);
        parent.setAccountId(accountId);
        familyTreeService.saveMember(parent);
        return parent;
    }

    @PostMapping(Mappings.NEW_CHILD)
    public Parent createChild(@RequestBody Child child, @PathVariable(PathVariables.PARENT_ID) Long id) {
        child.setPersonId(familyTreeService.generateId());
        Parent parent = familyTreeService.addChildToParent(id, child);

        return parent;
    }

    @PostMapping("/new/chore")
    public void addChore(@RequestBody Map<String, Long> choreNotification) {
       Long parentID = choreNotification.get("assignee");
       Long choreID = choreNotification.get("choreId");
        Parent parent = familyTreeService.getParent(parentID);
        familyTreeService.addChore(parent, choreID);
    }

    @GetMapping("all")
    public List<FamilyMember> demo() {

        return this.familyTreeService.getAll();
    }


}


