package com.sbdemo.conference.controllers;

import com.sbdemo.conference.models.Session;
import com.sbdemo.conference.repository.SessionRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")  // tells the router the mapping url
public class SessionsController {
    @Autowired  // wires up the repo
    private SessionRepo sessionsRepo;

    @GetMapping
    public List<Session> list(){
        return sessionsRepo.findAll();
    }

    @GetMapping //specifies that the method gets called with GET
    @RequestMapping("{id}") // injects the val into the method
    public Session get(@PathVariable Long id){
        return sessionsRepo.getById(id);
    }

    @PostMapping
    // spring mvc takes in all attributes in a json payload and pushes them into a session object
    // you can "save" objects and flush them to commit them to the database
    public Session create(@RequestBody Session session){
        return sessionsRepo.saveAndFlush(session);
    }

    // no deletemapping annotation, must specify it with requestMapping
    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        // this does not deal well with cascades - gives error when there are children records for a session
        // TODO: fix this
        sessionsRepo.deleteById(id);
    }

    // put updates all the attributes
    // can also use patch - only update part of the attributes
    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        Session s = sessionsRepo.getById(id);
        // copies incoming session data into the existing session
        // ignore the session_id attribute as it's the pk - don't want to replace it
        //TODO: add validation that all attributes were passed in
        BeanUtils.copyProperties(session, s, "session_id");
        return sessionsRepo.saveAndFlush(s);
    }
}
