package com.sbdemo.conference.controllers;

import com.sbdemo.conference.models.Speaker;
import com.sbdemo.conference.repository.SpeakerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepo speakerRepo;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepo.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepo.getById(id);
    }

    @PostMapping
    public Speaker create(@RequestBody Speaker speaker){
        return speakerRepo.saveAndFlush(speaker);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerRepo.deleteById(id);
    }

    @RequestMapping(value="{id}", method= RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepo.getById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepo.saveAndFlush(existingSpeaker);
    }
}
