package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.GeneralPushNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralPushNotificationRepository extends JpaRepository<GeneralPushNotification, Long> {
}
