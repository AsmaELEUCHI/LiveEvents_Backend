package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Program;
import com.liveEvents.liveEvents_backend.entities.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    boolean existsByArtistAndSceneAndDateAndStartTimeAndEndTime(Artist artist, Scene scene, LocalDate date, LocalTime startTime, LocalTime endTime);
    void deleteByArtistId(Long artistId);
}
