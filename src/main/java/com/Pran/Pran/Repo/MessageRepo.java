package com.Pran.Pran.Repo;

import com.Pran.Pran.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Contact,String> {
}
