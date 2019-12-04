package com.biuropracy.demo.controller;

import com.biuropracy.demo.DTO.JobOfferDTO;
import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.JobOfferRepository;
import com.biuropracy.demo.service.JobOfferService;
import com.biuropracy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class JobOfferController {

    @Autowired
    JobOfferService jobOfferService;

    @Autowired
    UserService userService;

    @Autowired
    private JobOfferRepository jobOfferRepository;

    // dla wszystkich
    @GetMapping("/all/jobOffers")
    public String getAllJobOffers(Model model, String location, String category, String contractType) {
        List<JobOfferDTO> JobList = jobOfferRepository.getJobOfferFiltered(category, location, contractType);
        model.addAttribute("jobOffers", JobList);
        return "/all/jobOffers/viewJobs";
    }

    @RequestMapping(path = "/all/jobOffers/delete/{id}")
    public String deleteJobOfferById(Model model, @PathVariable("id") Integer id) throws RuntimeException {
        jobOfferService.deleteJobOfferById(id);
        return "redirect:/all/jobOffers";
    }


    //dla użytkownika

    @RequestMapping(path = "/user/jobOffers", method = RequestMethod.GET)
    public String getAllJobOffersUser(Model model) {
        List<JobOffer> List = jobOfferService.getAllJobOffers();

        model.addAttribute("jobOffers", List);
        return "/all/jobOffers/viewJobsLoginUser";
    }
    @RequestMapping(path = {"/user/jobOffers/edit", "/user/jobOffers/edit/{id}"})
    public String editJobOfferById(Model model, @PathVariable("id") Optional<Integer> id) throws RuntimeException {
        if (id.isPresent()) {
            JobOffer jobOffer = jobOfferService.getJobOfferById(id.get());
            model.addAttribute("jobOffer", jobOffer);
        } else {
            model.addAttribute("jobOffer", new JobOffer());
        }
        return "/all/jobOffers/add-edit-jobOffer";
    }

    @RequestMapping(path = "/user/jobOffers/createJobOffer", method = RequestMethod.POST)
    public String createOrUpdateJobOffer(JobOffer jobOffer){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jobOfferService.createOrUpdateJOffert(jobOffer, userService.findUserByEmail(authentication.getName()));
        return "redirect:/user/jobOffers";
    }
}
