package com.liveEvents.liveEvents_backend.services;

import com.liveEvents.liveEvents_backend.dtos.MapDto;
import com.liveEvents.liveEvents_backend.entities.Map;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MapService {

    @Autowired
    MapRepository mapRepository;

    // Récupérer la carte
    public MapDto getMap(){
        Map map= mapRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResourceNotFoundException("Aucune carte trouvée"));
         return new MapDto(
                map.getId(),
                map.getZoomLevel(),
                map.getLatitude(),
                map.getLongitude()
        );
    }

    //Créer la carte
    public Map createMap(MapDto mapDto){
        if(mapRepository.existsByIdIsNotNull()){
            throw new IllegalArgumentException("Une carte existe déjà. Impossible d'en créer une nouvelle.");
        }
        Map map = new Map();
        map.setZoomLevel(mapDto.getZoomLevel());
        map.setLatitude(mapDto.getLatitude());
        map.setLongitude(mapDto.getLongitude());
        return mapRepository.save(map);
    }

    //Update Carte
    public Map updateMap(MapDto mapDto){
        Map updatedMap = mapRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResourceNotFoundException("Aucune carte trouvée"));
        updatedMap.setZoomLevel(mapDto.getZoomLevel());
        updatedMap.setLatitude(mapDto.getLatitude());
        updatedMap.setLongitude(mapDto.getLongitude());
        return mapRepository.save(updatedMap);
    }

    //Suuprimer la carte
    public  void deleteCarte(){
        Map map = mapRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResourceNotFoundException("Aucune carte trouvée"));
        mapRepository.delete(map);
    }



}
