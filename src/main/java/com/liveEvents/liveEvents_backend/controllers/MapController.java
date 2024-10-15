package com.liveEvents.liveEvents_backend.controllers;

import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.dtos.MapDto;
import com.liveEvents.liveEvents_backend.dtos.ResponseDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Map;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.services.MapService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.MAP_BASE_URL)
public class MapController {

    @Autowired
    MapService mapService;




    @GetMapping(ApiConstants.MAP_GET_URL)
    public ResponseEntity <MapDto> getMap() {
        try {
            MapDto mapDto=mapService.getMap();
            return ResponseEntity.ok(mapDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carte non trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération de la carte", e);
        }
    }


    @PostMapping(ApiConstants.MAP_CREATE_URL)
    public ResponseEntity <ResponseDto> createMap(@Valid @RequestBody MapDto mapDto) {
        try {
            mapService.createMap(mapDto);
            ResponseDto responseDto = new ResponseDto("La carte est crée avec succés");
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto("Une carte existe déjà. Impossible d'en créer une nouvelle."));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la création de la carte", e);
        }
    }



    @PutMapping(ApiConstants.MAP_UPDATE_URL)
    public  ResponseEntity <ResponseDto> updateMap(@Valid @RequestBody MapDto mapDto){
        try{
            mapService.updateMap(mapDto);
            ResponseDto responseDto = new ResponseDto("La carte est mise à jour avec succés");
            return ResponseEntity.ok(responseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carte non trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour de la carte", e);
        }
    }


    @DeleteMapping(ApiConstants.MAP_DELETE_URL)
    public ResponseEntity<Void> deleteMap() {
        try {
            mapService.deleteCarte();
            return ResponseEntity.noContent().build(); // Retourne un 204 No Content
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carte non trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de la carte", e);
        }
    }







}
