package com.sbdemo.conference.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name="sessions")
// without, can't look for individual ids --> serialisation issues
// hibernate puts in place stub methods to handle lazy loading of relational data
// don't want to serialise it -- will try to load in all the relational data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {

    @Id // specifies that session_id is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies how the primary key is populated on an insert
    private Long session_id;

    private String session_name;
    private String session_description;
    private Integer session_length;

    //define the m:n -- must choose which of the two entities will be the "main" one
    @ManyToMany
    // "create" a joint table, specify the fk
    @JoinTable(
            name="session_speakers", // maps to the name in the db
            joinColumns=@JoinColumn(name="session_id"),
            inverseJoinColumns=@JoinColumn(name="speaker_id")
    )
    private List<Speaker> speakers;

    public Session(){
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
