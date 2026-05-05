package com.contacts.app.repository;

import java.util.Collection;
import java.util.Optional;

public interface ContactRepositoryInterface<T> {
    Collection<T> getAll();
    Optional<T> findById(int id);
    T save(T contacts);
    T update(T contacts);
    void delete(int id);
}
