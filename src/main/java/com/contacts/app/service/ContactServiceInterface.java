package com.contacts.app.service;

import java.util.Collection;
import java.util.Optional;

public interface ContactServiceInterface<T> {
    Collection<T> getAll();
    Optional<T> findById(int id);
    T add(T contacts);
    T update(T contacts);
    void delete(int id);
}
