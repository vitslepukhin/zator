package com.vitslepukhin.zator.services;

import com.vitslepukhin.zator.domain.Url;
import com.vitslepukhin.zator.exceptions.AlreadyExistException;
import com.vitslepukhin.zator.exceptions.IncorrectUrlException;
import com.vitslepukhin.zator.exceptions.NotFoundException;
import com.vitslepukhin.zator.repositories.UrlRepository;
import com.vitslepukhin.zator.services.interfaces.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URL;


@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {
    UrlRepository urlRepository;

    @Override
    public Url getById(long id) throws NotFoundException {
        return urlRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<Url> getPaginated(int page, int size) {

        return urlRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Url save(String urlName) throws AlreadyExistException, IncorrectUrlException {
        String urlNameToSave;

        try {
            URL javaNetUrl = new URI(urlName).toURL();
            urlNameToSave = new URI(javaNetUrl.getProtocol(),
                                    null,
                                    javaNetUrl.getHost(),
                                    javaNetUrl.getPort(),
                                    null,
                                    null,
                                    null
            )
                    .toURL()
                    .toString();
        } catch (Exception ignored) {
            throw new IncorrectUrlException();
        }


        if (urlRepository.findByName(urlNameToSave).isPresent()) {
            throw new AlreadyExistException();
        }

        Url urlToSave = new Url();
        urlToSave.setName(urlNameToSave);

        return urlRepository.save(urlToSave);
    }
}
