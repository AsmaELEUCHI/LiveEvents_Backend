package com.liveEvents.liveEvents_backend.controllers;


import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.dtos.ProgramDto;
import com.liveEvents.liveEvents_backend.dtos.ResponseDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Program;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.services.ProgramService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.PROGRAMS_BASE_URL)
public class ProgramController {

    @Autowired
    ProgramService programService;

    @GetMapping(ApiConstants.PROGRAMS_GET_ALL_URL)
    public ResponseEntity<List<ProgramDto>> getAllPrograms() {
        try {
            List<ProgramDto> ListProgramsDto= programService.getAllPrograms();
            return ResponseEntity.ok(ListProgramsDto);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des programmes", e);
        }
    }



    @GetMapping(ApiConstants.PROGRAMS_GET_URL)
    public ResponseEntity<ProgramDto> getProgramById(@PathVariable Long id){
        try {
            ProgramDto programDto = programService.getProgramById(id);
            return ResponseEntity.ok(programDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Programme non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération du programme", e);
        }
    }


    @PostMapping(ApiConstants.PROGRAMS_CREATE_URL)
    public ResponseEntity <ResponseDto> createProgram(@Valid @RequestBody ProgramDto programDto) {
        try {
            programService.createProgram(programDto);
            ResponseDto responseDto = new ResponseDto("Le programme est crée avec succés");
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto("Le programme existe déjà"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la création du programme", e);
        }
    }


    @PutMapping(ApiConstants.PROGRAMS_UPDATE_URL)
    public  ResponseEntity  <ResponseDto> updateProgram(@Valid @PathVariable Long id, @RequestBody ProgramDto programDto){
        try{
            programService.updateProgram(id,programDto);
            ResponseDto responseDto = new ResponseDto("Le programme est mis à jour avec succés");
            return ResponseEntity.ok(responseDto );
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Programme non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour du programme", e);
        }
    }


    @DeleteMapping(ApiConstants.PROGRAMS_DELETE_URL)
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        try {
            programService.deleteProgram(id);
            return ResponseEntity.noContent().build(); // Retourne un 204 No Content
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Programme non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du programme", e);
        }
    }

}
