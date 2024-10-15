package com.liveEvents.liveEvents_backend.services;


import com.liveEvents.liveEvents_backend.dtos.SceneDto;
import com.liveEvents.liveEvents_backend.entities.Scene;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//CRUD scenes
@Service
public class SceneService {

    @Autowired
    SceneRepository sceneRepository;
    @Autowired
    XssSanitizerService xssSanitizerService;


    //Sélectionner toutes les scenes
    public List<SceneDto> getAllScenes(){
        List<Scene> scenes =sceneRepository.findAll();
        return scenes.stream()
                .map(scene-> new SceneDto(
                        scene.getId(),
                        scene.getSceneName()
                ))
                .toList();
    }

    //Sélectionner une scéne par son id
    public SceneDto getSceneById(Long id){
        Scene scene=sceneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scéne non trouvée avec l'id : " + id));
        return new SceneDto(
                scene.getId(),
                scene.getSceneName()
        );
    }

    //Créer une scéne
    public Scene createScene(SceneDto sceneDto){
        if(sceneRepository.existsBySceneName(sceneDto.getSceneName()))
            throw new IllegalArgumentException("La scéne existe déjà");
        Scene createdScene = new Scene();
        createdScene.setSceneName(xssSanitizerService.sanitize(sceneDto.getSceneName()));
        return sceneRepository.save(createdScene);
    }



    //Mettre à jour une scéne
    public Scene updateScene(Long id, SceneDto sceneDto){
        Scene updatedScene = sceneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("scéne non trouvée avec l'id : " + id));
        updatedScene.setSceneName(xssSanitizerService.sanitize(sceneDto.getSceneName()));
        return sceneRepository.save(updatedScene);
    }




    //supprimer une scéne
    public void deleteScene(Long id){
        Scene scene = sceneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scéne non trouvée avec l'id : " + id));
        sceneRepository.delete(scene);
    }

}
