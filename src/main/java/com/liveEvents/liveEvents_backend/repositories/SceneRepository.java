package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SceneRepository extends JpaRepository<Scene, Long> {
    boolean existsBySceneName(String sceneName);
}
