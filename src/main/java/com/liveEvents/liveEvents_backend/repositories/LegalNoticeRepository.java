package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.LegalNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalNoticeRepository extends JpaRepository<LegalNotice, Long> {

    boolean existsByTitle(String title);

}