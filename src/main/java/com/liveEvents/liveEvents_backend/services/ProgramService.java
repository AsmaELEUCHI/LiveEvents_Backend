package com.liveEvents.liveEvents_backend.services;


import com.liveEvents.liveEvents_backend.dtos.ProgramDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Program;
import com.liveEvents.liveEvents_backend.entities.Scene;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.ArtistRepository;
import com.liveEvents.liveEvents_backend.repositories.ProgramRepository;
import com.liveEvents.liveEvents_backend.repositories.SceneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ProgramService {


    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    XssSanitizerService xssSanitizerService;


    public List<ProgramDto> getAllPrograms(){
        List<Program> listPrograms= programRepository.findAll();
        return listPrograms.stream()
                .map(program -> new ProgramDto(
                        program.getId(),
                        program.getArtist().getId(),
                        program.getScene().getId(),
                        program.getDate().toString(),
                        program.getStartTime().toString(),
                        program.getEndTime().toString()
                ))
        .toList();


    }


    public ProgramDto getProgramById(Long id){
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programme non trouvé avec l'id : " + id));
        return new ProgramDto(
                program.getId(),
                program.getArtist().getId(),
                program.getScene().getId(),
                program.getDate().toString(),
                program.getStartTime().toString(),
                program.getEndTime().toString()
        );
    }



    @Transactional
    public Program createProgram(ProgramDto programDto){
        Artist artist = artistRepository.findById(programDto.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Programme non trouvé avec l'id : " + programDto.getArtistId(), new Throwable()));
        Scene scene = sceneRepository.findById(programDto.getSceneId())
                .orElseThrow(() -> new ResourceNotFoundException("Programme non trouvé avec l'id : " + programDto.getSceneId(), new Throwable()));

        // Conversion des chaînes en LocalDate et LocalTime
        LocalDate date = LocalDate.parse(programDto.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime startTime = LocalTime.parse(programDto.getStartTime(), DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime endTime = LocalTime.parse(programDto.getEndTime(), DateTimeFormatter.ISO_LOCAL_TIME);
        // Vérifiez si le programme existe déjà
        if (programRepository.existsByArtistAndSceneAndDateAndStartTimeAndEndTime(artist, scene, date, startTime, endTime)) {
            throw new IllegalArgumentException("Le programme existe déjà");
        }

        // Créez et enregistrez le nouveau programme
        Program program = new Program();
        program.setArtist(artist);
        program.setScene(scene);
        program.setDate(date); // LocalDate
        program.setStartTime(startTime); // LocalTime
        program.setEndTime(endTime); // LocalTime
        return programRepository.save(program);
    }



    @Transactional
    public Program updateProgram(Long id, ProgramDto programDto){
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programme non trouvé avec l'id : " + id));

        Artist artist = artistRepository.findById(programDto.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Artiste non trouvé avec l'id : " + programDto.getArtistId()));

        Scene scene = sceneRepository.findById(programDto.getSceneId())
                .orElseThrow(() -> new ResourceNotFoundException("Scéne non trouvée avec l'id : " + programDto.getSceneId()));

        program.setArtist(artist);
        program.setScene(scene);
        program.setDate(LocalDate.parse(programDto.getDate(), DateTimeFormatter.ISO_LOCAL_DATE)); // String vers LocalDate
        program.setStartTime(LocalTime.parse(programDto.getStartTime(), DateTimeFormatter.ISO_LOCAL_TIME)); // String vers LocalTime
        program.setEndTime(LocalTime.parse(programDto.getEndTime(), DateTimeFormatter.ISO_LOCAL_TIME)); // String vers LocalTime

        return programRepository.save(program);
    }


    @Transactional
    public void deleteProgram(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programme non trouvé avec l'id : " + id));
        programRepository.delete(program);
    }

    public void deleteProgramByArtistId(long artistId){
        programRepository.deleteByArtistId(artistId);
    }



}
