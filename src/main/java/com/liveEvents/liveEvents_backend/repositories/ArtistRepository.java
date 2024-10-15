package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    boolean existsByArtistName(String artistName);
}
