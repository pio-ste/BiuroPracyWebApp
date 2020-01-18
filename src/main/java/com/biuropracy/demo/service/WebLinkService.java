package com.biuropracy.demo.service;

import com.biuropracy.demo.model.User;
import com.biuropracy.demo.model.WebLink;
import com.biuropracy.demo.repository.WebLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebLinkService {

    @Autowired
    WebLinkRepository webLinkRepository;

    public List<WebLink> findWebLinkByUserId(Integer user) {
        return webLinkRepository.findByUserIdUser(user);
    }

    public WebLink findByUserId(Integer id) {
        return webLinkRepository.findWebLinkByUserIdUser(id);
    }

    public WebLink getWebLinkById(Integer id) {
        Optional<WebLink> webLinkOpt = webLinkRepository.findById(id);

        if (webLinkOpt.isPresent()) {
            return webLinkOpt.get();
        } else {
            throw new RuntimeException("Id linku nie znalezione");
        }
    }

    public WebLink updateWebLink(WebLink webLink) {
        Optional<WebLink> webLinkOpt = webLinkRepository.findById(webLink.getIdWebLink());

        if (webLinkOpt.isPresent()) {
            WebLink newWebLink = webLinkOpt.get();
            newWebLink.setUrlAddress(webLink.getUrlAddress());
            newWebLink.setUrlType(webLink.getUrlType());

            newWebLink = webLinkRepository.save(newWebLink);
            return newWebLink;
        } else {
            webLink = webLinkRepository.save(webLink);
            return webLink;
        }
    }

    public WebLink createWebLink(WebLink webLink, User user) {
        webLink.setUser(user);
        webLink = webLinkRepository.save(webLink);
        return webLink;
    }

    public void deleteWebLinkById(Integer id) {
        Optional<WebLink> webLinkOpt = webLinkRepository.findById(id);
        if (webLinkOpt.isPresent()) {
            webLinkRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brak linka o tym id");
        }
    }
}
