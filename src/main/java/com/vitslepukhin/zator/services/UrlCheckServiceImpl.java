package com.vitslepukhin.zator.services;

import com.vitslepukhin.zator.domain.Url;
import com.vitslepukhin.zator.domain.UrlCheck;
import com.vitslepukhin.zator.repositories.UrlCheckRepository;
import com.vitslepukhin.zator.services.interfaces.UrlCheckService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlCheckServiceImpl implements UrlCheckService {
    private final UrlCheckRepository urlCheckRepository;

    @Override
    public UrlCheck checkUrl(Url url) {
        HttpResponse<String> responseUrl = Unirest.get(url.getName()).asString();
        int statusCode = responseUrl.getStatus();
        Document doc = Jsoup.parse(responseUrl.getBody());
        String title = doc.title();
        String h1 = Optional.ofNullable(doc.select("h1").first()).orElse(new Element("h1")).text();
        String description = Optional
                .ofNullable(doc.select("meta[name='description']").first())
                .orElse(new Element("meta"))
                .attr("content");

        UrlCheck newUrlCheck = new UrlCheck(statusCode, title, h1, description, url);
        return urlCheckRepository.save(newUrlCheck);
    }
}
