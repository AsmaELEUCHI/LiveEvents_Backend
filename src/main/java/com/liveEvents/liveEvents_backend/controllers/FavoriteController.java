package com.liveEvents.liveEvents_backend.controllers;

import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.dtos.ResponseDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Favorite;
import com.liveEvents.liveEvents_backend.entities.User;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.UserRepository;
import com.liveEvents.liveEvents_backend.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.FAVORITE_BASE_URL)
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    UserRepository userRepository;

    //Rajouter un artiste à la liste des favoris
    @PostMapping(ApiConstants.FAVORITE_CREATE_URL)
    public ResponseEntity<ResponseDto> addArtistToFavorites(Authentication authentication, @PathVariable long artistId) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        favoriteService.addArtistToFavorites(user.getId(), artistId);
        ResponseDto response = new ResponseDto("L'artiste a été ajouté à la liste des fovoris avec succès !");
        return new ResponseEntity<>(response, HttpStatus.CREATED); // statut 201 created
    }
    //Récupérer les artistes de la liste des favoris
    @GetMapping(ApiConstants.FAVORITE_GET_ALL_URL)
    public ResponseEntity<List<ArtistDto>> getFavoritesArtist(Authentication authentication) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
            System.out.println("email pour get favorite: " + user.getEmail());
            List<ArtistDto> artistDtoList = favoriteService.getFavoritesArtists(user.getId());
            return new ResponseEntity<>(artistDtoList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des artistes favoris", e);
        }
    }

    //Supprimer un artiste de la liste des favoris
    @DeleteMapping(ApiConstants.FAVORITE_DELETE_URL)
    public ResponseEntity<Void> deleteArtistFromFavorites (Authentication authentication, @PathVariable Long artistId){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'email : " + email));
        favoriteService.deleteArtistFromFavorites(user.getId(), artistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


