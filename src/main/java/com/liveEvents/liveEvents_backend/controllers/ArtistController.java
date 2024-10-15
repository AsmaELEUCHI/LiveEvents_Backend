package com.liveEvents.liveEvents_backend.controllers;

import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.dtos.ResponseDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.services.ArtistService;
import com.liveEvents.liveEvents_backend.services.ProgramService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.ARTIST_BASE_URL)
public class ArtistController {

    @Autowired
    ArtistService artistService;
    @Autowired
    ProgramService programService;


    @GetMapping(ApiConstants.ARTIST_GET_ALL_URL)
    public ResponseEntity <List<ArtistDto>> getAllArtists() {
        try {
            List<ArtistDto> ListArtistsDto =artistService.getAllArtists();
            return ResponseEntity.ok(ListArtistsDto);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des artistes", e);
        }
    }
    @GetMapping(ApiConstants.ARTIST_GET_URL)
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable Long id){
        try {
            ArtistDto artistDto = artistService.getArtistById(id);
            return ResponseEntity.ok(artistDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artiste non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération de l'artiste", e);
        }
    }

    @PostMapping(ApiConstants.ARTIST_CREATE_URL)
    public ResponseEntity <ResponseDto> createArtist(@Valid @RequestBody ArtistDto artistDto) {
        try {
            artistService.createArtist(artistDto);
            ResponseDto responseDto = new ResponseDto("L'artiste est crée avec succés");
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto("L'artiste existe déjà"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la création de l'artiste", e);
        }
    }

    @PutMapping(ApiConstants.ARTIST_UPDATE_URL)
    public  ResponseEntity <ResponseDto> updateArtist(@Valid @PathVariable Long id, @RequestBody ArtistDto artistDto){
        try{
            artistService.updateArtiste(id, artistDto);
            ResponseDto responseDto = new ResponseDto("L'artiste est mis à jour avec succés");
            return ResponseEntity.ok( responseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artiste non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour de l'artiste", e);
        }
    }

    @DeleteMapping(ApiConstants.ARTIST_DELETE_URL)
    public ResponseEntity<ResponseDto> deleteArtist(@PathVariable Long id) {
        try {
            programService.deleteProgramByArtistId(id);
            artistService.deleteArtist(id);
            ResponseDto responseDto = new ResponseDto("L'artiste est supprimé avec succés");
            return ResponseEntity.ok( responseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artiste non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de l'artiste", e);
        }
    }


}
