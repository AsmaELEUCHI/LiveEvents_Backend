package com.liveEvents.liveEvents_backend.controllers;


import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.dtos.ResponseDto;
import com.liveEvents.liveEvents_backend.dtos.SceneDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Scene;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.services.SceneService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SCENES_BASE_URL)
public class SceneController {

    @Autowired
    SceneService sceneService;

    @GetMapping(ApiConstants.SCENES_GET_ALL_URL)
    public ResponseEntity<List<SceneDto>> getAllScenes() {
        try {
            List<SceneDto> ListScenesDto =sceneService.getAllScenes();
            return ResponseEntity.ok(ListScenesDto);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des scénes", e);
        }
    }


    @GetMapping(ApiConstants.SCENES_GET_URL)
    public ResponseEntity<SceneDto> getSceneById(@PathVariable Long id){
        try {
            SceneDto sceneDto = sceneService.getSceneById(id);
            return ResponseEntity.ok(sceneDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scéne non trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération de scéne", e);
        }
    }


    @PostMapping(ApiConstants.SCENES_CREATE_URL)
    public ResponseEntity <ResponseDto> createScene(@Valid @RequestBody SceneDto sceneDto) {
        try {
           sceneService.createScene(sceneDto);
           ResponseDto responseDto = new ResponseDto("La scéne est crée avec succés");
            return ResponseEntity.ok(responseDto );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto("La scéne existe déjà"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la création de scéne", e);
        }
    }



    @PutMapping(ApiConstants.SCENES_UPDATE_URL)
    public  ResponseEntity <ResponseDto> updateScene(@Valid @PathVariable Long id, @RequestBody SceneDto sceneDto){
        try{
            sceneService.updateScene(id, sceneDto);
            ResponseDto responseDto = new ResponseDto("La scéne est mis à jour avec succés");
            return ResponseEntity.ok(responseDto );
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scéne non trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour de scéne", e);
        }
    }


    @DeleteMapping(ApiConstants.SCENES_DELETE_URL)
    public ResponseEntity<Void> deleteScene(@PathVariable Long id) {
        try {
            sceneService.deleteScene(id);
            return ResponseEntity.noContent().build(); // Retourne un 204 No Content
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "scéne non trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de scéne", e);
        }
    }

}
