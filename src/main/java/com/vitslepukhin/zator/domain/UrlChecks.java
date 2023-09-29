package com.vitslepukhin.zator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "url_checks")
public class UrlChecks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    int statusCode;

    String title;

    String h1;

    String description;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(targetEntity = Url.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    Url url;

    private Instant createdAt;
}
