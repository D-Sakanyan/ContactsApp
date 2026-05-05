package com.contacts.app.repository;

import com.contacts.app.model.Contact;
import com.contacts.app.repository.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactRepository implements ContactRepositoryInterface<Contact> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Contact> getAll() {
        log.debug("Calling ContactRepository->getAll");

        String sql = "SELECT * FROM contacts ORDER BY id";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(int id) {
        log.debug("Calling ContactRepository->findById with ID: {}", id);

        String sql = "SELECT * FROM contacts WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Calling ContactRepository->save with Contact: {}", contact);

        String sql = "INSERT INTO contacts (firstName, lastName, email, phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                contact.getLastName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone()
        );

        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Calling ContactRepository->update with Contact: {}", contact);

        Contact existedContact = findById(contact.getId()).orElse(null);
        if (existedContact != null) {
            String sql = "UPDATE contacts SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhone(),
                    contact.getId()
            );
        }

        return contact;
    }

    @Override
    public void delete(int id) {
        log.debug("Calling ContactRepository->delete with ID {}", id);

        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
