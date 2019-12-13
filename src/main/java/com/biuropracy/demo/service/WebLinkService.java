package com.biuropracy.demo.service;

import com.biuropracy.demo.repository.WebLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebLinkService {

    @Autowired
    WebLinkRepository webLinkRepository;
}
