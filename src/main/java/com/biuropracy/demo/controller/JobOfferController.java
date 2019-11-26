package com.biuropracy.demo.controller;

import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class JobOfferController {

    @Autowired
    JobOfferService jobOfferService;

    @RequestMapping(path = "/all/jobOffers", method = RequestMethod.GET)
    public String getAllJobOffers(Model model) {
        List<JobOffer> List = jobOfferService.getAllJobOffers();

        model.addAttribute("jobOffers", List);
        return "/all/jobOffers/viewJobs";
    }

    @RequestMapping(path = {"/all/jobOffers/edit", "/all/jobOffers/edit/{id}"})
    public String editJobOfferById(Model model, @PathVariable("id") Optional<Integer> id) throws RuntimeException {
        if (id.isPresent()) {
            JobOffer jobOffer = jobOfferService.getJobOfferById(id.get());
            model.addAttribute("jobOffer", jobOffer);
        } else {
            model.addAttribute("jobOffer", new JobOffer());
        }
        return "/all/jobOffers/add-edit-jobOffer";
    }
    @RequestMapping(path = "/all/jobOffers/delete/{id}")
    public String deleteJobOfferById(Model model, @PathVariable("id") Integer id) throws RuntimeException {
        jobOfferService.deleteJobOfferById(id);
        return "redirect:/all/jobOffers";
    }

    @RequestMapping(path = "/all/jobOffers/createJobOffer", method = RequestMethod.POST)
    public String createOrUpdateJobOffer(JobOffer jobOffer){
        jobOfferService.createOrUpdateJOffert(jobOffer);
        return "redirect:/all/jobOffers";
    }
}
