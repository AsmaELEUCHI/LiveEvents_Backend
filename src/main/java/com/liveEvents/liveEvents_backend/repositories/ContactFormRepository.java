package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {
}
