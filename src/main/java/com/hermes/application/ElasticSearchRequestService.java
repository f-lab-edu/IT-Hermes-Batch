package com.hermes.application;

import com.hermes.presentation.client.HermesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticSearchRequestService {

    private final HermesClient hermesClient;


}
