package com.liveEvents.liveEvents_backend.repositories;

import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Favorite;
import com.liveEvents.liveEvents_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUserAndArtist(User user, Artist artist);

    List<Artist> findArtistsByUser(User user);

    Optional<Favorite> findByUserAndArtist(User user, Artist artist);

    List<Favorite> findByUser(User user);
}
