package com.vitslepukhin.zator.services.interfaces;

import com.vitslepukhin.zator.domain.Url;
import com.vitslepukhin.zator.exceptions.AlreadyExistException;
import com.vitslepukhin.zator.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

public interface UrlService {
    Url getById(long id) throws NotFoundException;

    Page<Url> getPaginated(int page, int size);

    Url save(String name) throws AlreadyExistException;
}
