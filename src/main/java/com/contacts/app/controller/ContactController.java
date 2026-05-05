package com.contacts.app.controller;

import com.contacts.app.model.Contact;
import com.contacts.app.service.ContactService;
import com.contacts.app.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;
    private final Validator validator;

    @GetMapping("/contacts")
    public String allContacts(Model model) {
        List<Contact> contacts = service.getAll();
        model.addAttribute("contacts", contacts);

        return "contact-form";
    }

    @GetMapping("/contacts/create")
    public String showCreateContact(Model model) {
        model.addAttribute("contact", new Contact());

        return "create";
    }

    @PostMapping("/contacts/create")
    public String createContact(@ModelAttribute Contact contact, BindingResult result) {
        validator.validate(contact, result);

        if (result.hasErrors()) {
            return "create";
        }
        service.add(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/contacts/edit/{id}")
    public String showEditContact(@PathVariable Integer id, Model model) {
        Contact existedContact = service.findById(id).orElse(null);
        if (existedContact != null) {
            model.addAttribute("contact", existedContact);
        }

        return "edit";
    }

    @PostMapping("/contacts/edit")
    public String editContact(@ModelAttribute Contact contact, BindingResult result) {
        Contact existedContact = service.findById(contact.getId()).orElse(null);
        validator.validate(contact, result);
        if (result.hasErrors()) {
            return "edit";
        }
        if (existedContact != null) {
            service.update(contact);
        }
        return "redirect:/contacts";
    }

    @GetMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/contacts";
    }
}
