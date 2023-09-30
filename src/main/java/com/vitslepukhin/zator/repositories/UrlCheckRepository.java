package com.vitslepukhin.zator.repositories;

import com.vitslepukhin.zator.domain.UrlCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlCheckRepository extends JpaRepository<UrlCheck, Long> {
}
