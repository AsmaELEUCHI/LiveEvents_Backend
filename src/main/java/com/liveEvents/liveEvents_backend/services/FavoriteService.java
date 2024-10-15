package com.liveEvents.liveEvents_backend.services;


import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Favorite;
import com.liveEvents.liveEvents_backend.entities.User;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.ArtistRepository;
import com.liveEvents.liveEvents_backend.repositories.FavoriteRepository;
import com.liveEvents.liveEvents_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FavoriteRepository favoriteRepository;

    //Rajouter un artiste à la liste des artistes favoris
    @Transactional
    public void addArtistToFavorites(Long userId, Long artistId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + userId));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste non trouvé avec l'id : " + artistId));
        if(favoriteRepository.existsByUserAndArtist(user, artist)){
            throw new RuntimeException("L'artiste est déjà dans votre liste de favoris");
        }
        Favorite favorite = new Favorite();
        favorite.setArtist(artist);
        favorite.setUser(user);
        favoriteRepository.save(favorite);
    }

    //Récupérer la liste des artiste favoris
    public List<ArtistDto> getFavoritesArtists(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));

        // Récupérer la liste des favoris de l'utilisateur
        List<Favorite> favorites = favoriteRepository.findByUser(user);

        // Récupérer les artistes à partir des favoris
        return favorites.stream()
                .map(favorite -> {
                    Artist artist = favorite.getArtist(); // Accéder à l'artiste via Favorite
                    return new ArtistDto(
                            artist.getId(),
                            artist.getArtistName(),
                            artist.getArtistImage(),
                            artist.getArtistBiography(),
                            artist.getFutureFestivalName(),
                            artist.getFutureDate(),
                            artist.getFutureLocation()
                    );
                })
                .toList();
    }


    //Supprimer un artiste de la liste des favoris
    public void deleteArtistFromFavorites(Long userId, Long artistId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + userId));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste non trouvé avec l'id : " + artistId));
        Favorite favorite = favoriteRepository.findByUserAndArtist(user, artist)
                .orElseThrow(() -> new ResourceNotFoundException("L'artiste favoris n'est pas dans la liste"));
        favoriteRepository.delete(favorite);
    }
}
