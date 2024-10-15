package com.liveEvents.liveEvents_backend.services;



import com.liveEvents.liveEvents_backend.dtos.MarkerDto;
import com.liveEvents.liveEvents_backend.entities.Map;
import com.liveEvents.liveEvents_backend.entities.Marker;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.MapRepository;
import com.liveEvents.liveEvents_backend.repositories.MarkerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkerService {

    @Autowired
    MarkerRepository markerRepository;
    @Autowired
    MapRepository mapRepository;
    @Autowired
    XssSanitizerService xssSanitizerService;

    //Sélectionner tous les marqueurs
    public List<MarkerDto> getAllMarkers() {
        List<Marker> markers = markerRepository.findAll();
        return markers.stream()
                .map(marker -> new MarkerDto(
                        marker.getId(),
                        marker.getPlaceName(),
                        marker.getAddress(),
                        marker.getIconUrl(),
                        marker.getLatitude(),
                        marker.getLongitude(),
                        marker.getCategory(),
                        marker.getMap().getId()
                ))
                .toList();
    }


    //Sélectionner un marqueur  par son id
    public MarkerDto getMarkerById(Long id){
        Marker marker=markerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marqueur non trouvé avec l'id : " + id));
        return new MarkerDto(
                marker.getId(),
                marker.getPlaceName(),
                marker.getAddress(),
                marker.getIconUrl(),
                marker.getLatitude(),
                marker.getLongitude(),
                marker.getCategory(),
                marker.getMap().getId()
        );
    }


    //Créer un marqueur
    @Transactional
    public Marker createMarker(MarkerDto markerDto){
        if (markerRepository.existsByPlaceName(markerDto.getPlaceName())) {
            throw new IllegalArgumentException("Le marqueur existe déjà");
        }
        Map map= mapRepository.findById(markerDto.getMapId())
                .orElseThrow(() -> new ResourceNotFoundException("Carte non trouvée avec l'ID : " + markerDto.getMapId()));
        Marker createdMarker = new Marker();
        createdMarker.setPlaceName(xssSanitizerService.sanitize(markerDto.getPlaceName()));
        createdMarker.setAddress(xssSanitizerService.sanitize(markerDto.getAddress()));
        createdMarker.setIconUrl(xssSanitizerService.sanitize(markerDto.getIconUrl()));
        createdMarker.setLatitude(markerDto.getLatitude());
        createdMarker.setLongitude(markerDto.getLongitude());
        createdMarker.setCategory(xssSanitizerService.sanitize(markerDto.getCategory()));
        createdMarker.setMap(map);
        return markerRepository.save(createdMarker);
    }

    //Mettre à jour un marqueur
    public Marker updateMarker(Long id, MarkerDto markerDto){
        Marker updatedMarker = markerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marqueur non trouvé avec l'id : " + id));
        updatedMarker.setPlaceName(xssSanitizerService.sanitize(markerDto.getPlaceName()));
        updatedMarker.setAddress(xssSanitizerService.sanitize(markerDto.getAddress()));
        updatedMarker.setIconUrl(xssSanitizerService.sanitize(markerDto.getIconUrl()));
        updatedMarker.setLatitude(markerDto.getLatitude());
        updatedMarker.setLongitude(markerDto.getLongitude());
        updatedMarker.setCategory(xssSanitizerService.sanitize(markerDto.getCategory()));
        return markerRepository.save(updatedMarker);
    }

    //supprimer un marqueur
    public void deleteMarker(Long id){
        Marker marker = markerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("marqueur non trouvé avec l'id : " + id));
        markerRepository.delete(marker);
    }

}
