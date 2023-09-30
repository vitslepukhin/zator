package com.vitslepukhin.zator.repositories;

import com.vitslepukhin.zator.domain.Url;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    @EntityGraph(value = "urlEntityGraph")
    @Override
    @Nonnull
    List<Url> findAll();

    @EntityGraph(value = "urlEntityGraph")
    @Override
    @Nonnull
    Page<Url> findAll(Pageable pageable);

    @EntityGraph(value = "urlEntityGraph")
    @Nonnull
    @Override
    Optional<Url> findById(@Nonnull Long id);

    @EntityGraph(value = "urlEntityGraph")
    @Nonnull
    Optional<Url> findByName(@Nonnull String name);
}
