package com.vitslepukhin.zator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "url_checks")
public class UrlCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    int statusCode;

    String title;

    String h1;

    String description;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(targetEntity = Url.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id", nullable = false)
    Url url;

    @CreationTimestamp
    private Instant createdAt;

    public UrlCheck(int statusCode, String title, String h1, String description, Url url) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.url = url;
    }
}
