package com.Pran.Pran.Service;


import com.Pran.Pran.Model.Contact;
import com.Pran.Pran.Repo.MessageRepo;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {
    private final MessageRepo messageRepo;

    public ContactMessageService(MessageRepo messageRepo){
        this.messageRepo = messageRepo;
    }


    public void addMessage(Contact contact) {
        messageRepo.save(contact);
    }
}
