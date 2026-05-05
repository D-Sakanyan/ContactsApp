package com.contacts.app.service;

import com.contacts.app.model.Contact;
import com.contacts.app.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService implements ContactServiceInterface<Contact> {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getAll() {
        return contactRepository.getAll().stream().toList();
    }

    @Override
    public Optional<Contact> findById(int id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact add(Contact contacts) {
        return contactRepository.save(contacts);
    }

    @Override
    public Contact update(Contact contacts) {
        return contactRepository.update(contacts);
    }

    @Override
    public void delete(int id) {
        contactRepository.delete(id);
    }
}
