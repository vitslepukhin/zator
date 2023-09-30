package com.vitslepukhin.zator.services.interfaces;

import com.vitslepukhin.zator.domain.Url;
import com.vitslepukhin.zator.domain.UrlCheck;

public interface UrlCheckService {
    UrlCheck checkUrl(Url url);
}
