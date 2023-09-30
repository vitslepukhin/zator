package com.vitslepukhin.zator.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "urls")
@NamedEntityGraph(name = "urlEntityGraph", attributeNodes = {
        @NamedAttributeNode("urlChecks"),
})
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @CreationTimestamp
    private Instant createdAt;

    @Fetch(FetchMode.JOIN)
    @OneToMany(targetEntity = UrlCheck.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "url_id")
    private List<UrlCheck> urlChecks;
}
