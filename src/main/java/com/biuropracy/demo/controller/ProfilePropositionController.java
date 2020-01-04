package com.biuropracy.demo.controller;

import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.ProfileProposition;
import com.biuropracy.demo.repository.JobOfferRepository;
import com.biuropracy.demo.service.JobOfferService;
import com.biuropracy.demo.service.ProfilePropositionService;
import com.biuropracy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProfilePropositionController {

    @Autowired
    ProfilePropositionService profilePropositionService;

    @Autowired
    UserService userService;

    @Autowired
    JobOfferService jobOfferService;

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @PostMapping(path = "/user/jobOffer/createProfileProp/{id}")
    public String createProfileProp(ModelAndView modelAndView, ProfileProposition profileProposition, @PathVariable("id") Optional<Integer> id){
        JobOffer jobOfferId = jobOfferService.getJobOfferById(id.get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        profilePropositionService.createProfileProp(profileProposition,userService.findUserByEmail(authentication.getName()), jobOfferId);
        modelAndView.addObject("profileProposition", new ProfileProposition());
        return "redirect:/user/jobOffers/viewSelectedJobOffer/{id}";
    }
}
