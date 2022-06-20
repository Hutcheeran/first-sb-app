package com.sbdemo.conference.repository;

import com.sbdemo.conference.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

// session = data type
// long = primary key
// inherits basic CRUD commands
public interface SessionRepo extends JpaRepository<Session, Long> {
}
