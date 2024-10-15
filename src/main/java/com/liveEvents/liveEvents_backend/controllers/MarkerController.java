package com.liveEvents.liveEvents_backend.controllers;


import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.dtos.MarkerDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Marker;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.services.MarkerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.MARKER_BASE_URL)
public class MarkerController {

    @Autowired
    MarkerService markerService;

    @GetMapping(ApiConstants.MARKER_GET_ALL_URL)
    public ResponseEntity<List<MarkerDto>> getAllMarkers() {
        try {
            List<MarkerDto> ListMarkersDto =markerService.getAllMarkers();
            return ResponseEntity.ok(ListMarkersDto);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des marqueurs", e);
        }
    }

    @GetMapping(ApiConstants.MARKER_GET_URL)
    public ResponseEntity<MarkerDto> getMarkerById(@PathVariable Long id){
        try {
            MarkerDto markerDto = markerService.getMarkerById(id);
            return ResponseEntity.ok(markerDto );
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marqueur non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération du marqueur", e);
        }
    }

    @PostMapping(ApiConstants.MARKER_CREATE_URL)
    public ResponseEntity <MarkerDto> createMarker(@Valid @RequestBody MarkerDto markerDto) {
        try {
            Marker createdMarker= markerService.createMarker(markerDto);
            MarkerDto createdMarkerDto = convertToDto(createdMarker);
            return ResponseEntity.ok(createdMarkerDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new MarkerDto("Le marqueur existe déjà"));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carte non trouvée avec l'ID : "+ markerDto.getMapId(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la création du marqueur", e);
        }
    }

    @PutMapping(ApiConstants.MARKER_UPDATE_URL)
    public  ResponseEntity <MarkerDto> updateMarker(@Valid @PathVariable Long id, @RequestBody MarkerDto markerDto){
        try{
            Marker updatedMarker = markerService.updateMarker(id, markerDto);
            MarkerDto updatedArtistDto = convertToDto(updatedMarker );
            return ResponseEntity.ok(updatedArtistDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artiste non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour de l'artiste", e);
        }
    }


    @DeleteMapping(ApiConstants.MARKER_DELETE_URL)
    public ResponseEntity<Void> deleteMarker(@PathVariable Long id) {
        try {
            markerService.deleteMarker(id);
            return ResponseEntity.noContent().build(); // Retourne un 204 No Content
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marqueur non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de marqueur", e);
        }
    }

    // Méthode d'extraction pour créer le MarkerDto
    private MarkerDto convertToDto(Marker marker) {
        MarkerDto markerDto = new MarkerDto();
        markerDto.setId(marker.getId());
        markerDto.setPlaceName(marker.getPlaceName());
        markerDto.setAddress(marker.getAddress());
        markerDto.setIconUrl(marker.getIconUrl());
        markerDto.setLatitude(marker.getLatitude());
        markerDto.setLongitude(marker.getLongitude());
        markerDto.setCategory(marker.getCategory());
        return markerDto;
    }



}
