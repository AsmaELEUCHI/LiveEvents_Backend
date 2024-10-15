package com.liveEvents.liveEvents_backend.services;


import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

//CRUD Artiste
@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    XssSanitizerService xssSanitizerService;

    //Sélectionner tous les artistes
    public List<ArtistDto> getAllArtists(){
        List<Artist> artists =artistRepository.findAll();
        return artists.stream()
                .map(artist-> new ArtistDto(
                        artist.getId(),
                        artist.getArtistName(),
                        artist.getArtistImage(),
                        artist.getArtistBiography(),
                        artist.getFutureFestivalName(),
                        artist.getFutureDate(),
                        artist.getFutureLocation()
                ))
                .toList();
    }

    //Sélectionner un artiste par son id
    public ArtistDto getArtistById(Long id){
        Artist artist=artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste non trouvé avec l'id : " + id));
        return new ArtistDto(
                artist.getId(),
                artist.getArtistName(),
                artist.getArtistImage(),
                artist.getArtistBiography(),
                artist.getFutureFestivalName(),
                artist.getFutureDate(),
                artist.getFutureLocation()
        );
    }

    //Créer un artiste
    public Artist createArtist(ArtistDto artistDto){
        if (artistRepository.existsByArtistName(artistDto.getArtistName())) {
            throw new IllegalArgumentException("L'artiste existe déjà");
        }
        Artist createdArtist = new Artist();
        createdArtist.setArtistName(xssSanitizerService.sanitize(artistDto.getArtistName()));
        createdArtist.setArtistImage(xssSanitizerService.sanitize(artistDto.getArtistImage()));
        createdArtist.setArtistBiography(xssSanitizerService.sanitize(artistDto.getArtistBiography()));
        createdArtist.setFutureFestivalName(xssSanitizerService.sanitize(artistDto.getFutureFestivalName()));
        createdArtist.setFutureDate(artistDto.getFutureDate());
        createdArtist.setFutureLocation(xssSanitizerService.sanitize(artistDto.getFutureLocation()));
        return artistRepository.save(createdArtist);
    }

    //Mettre à jour un artiste
    public Artist updateArtiste(Long id, ArtistDto artistDto){
        Artist updatedArtist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste non trouvé avec l'id : " + id));
        updatedArtist.setArtistName(xssSanitizerService.sanitize(artistDto.getArtistName()));
        updatedArtist.setArtistImage(xssSanitizerService.sanitize(artistDto.getArtistImage()));
        updatedArtist.setArtistBiography(xssSanitizerService.sanitize(artistDto.getArtistBiography()));
        updatedArtist.setFutureFestivalName(xssSanitizerService.sanitize(artistDto.getFutureFestivalName()));
        updatedArtist.setFutureDate(artistDto.getFutureDate());
        updatedArtist.setFutureLocation(xssSanitizerService.sanitize(artistDto.getFutureLocation()));
        return artistRepository.save(updatedArtist);
    }

    //supprimer un artiste
    public void deleteArtist(Long id){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste non trouvé avec l'id : " + id));
        artistRepository.delete(artist);
    }




}
