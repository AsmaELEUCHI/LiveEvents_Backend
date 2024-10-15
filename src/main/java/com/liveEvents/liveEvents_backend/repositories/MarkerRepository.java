package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long> {

    boolean existsByPlaceName(String placeName);

}
