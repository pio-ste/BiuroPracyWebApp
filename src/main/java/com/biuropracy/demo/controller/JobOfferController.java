package com.biuropracy.demo.controller;

import com.biuropracy.demo.DTO.EmployerUserDTO;
import com.biuropracy.demo.DTO.JobOfferDTO;
import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.ProfileProposition;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.EmployerRepository;
import com.biuropracy.demo.repository.JobOfferRepository;
import com.biuropracy.demo.service.EmployerService;
import com.biuropracy.demo.service.JobOfferService;
import com.biuropracy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class JobOfferController {

    private Integer Userid;

    private Integer idEmployer;

    @Autowired
    JobOfferService jobOfferService;

    @Autowired
    UserService userService;

    @Autowired
    EmployerService employerService;

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private EmployerRepository employerRepository;
/*
    // dla wszystkich
    @GetMapping("/all/jobOffers")
    public String getAllJobOffers(Model model, String title, String location, String category, String contractType, String workingTime, String positionLevel) {
        List<JobOfferDTO> JobList = jobOfferRepository.getJobOfferFiltered(title, category, location, contractType, workingTime, positionLevel);
        model.addAttribute("jobOffers", JobList);
        return "/all/jobOffers/viewJobs";
    }

    @GetMapping(path = {"/all/jobOffers/viewSelectedJobOffer", "/all/jobOffers/viewSelectedJobOffer/{id}"})
    public String viewSelected(Model model,@PathVariable("id") Optional<Integer> id) {
        List<JobOfferDTO> JobOfferList = jobOfferRepository.getSelectedJobOffer(id.get());
        model.addAttribute("jobOffers", JobOfferList);
        return "/all/jobOffers/selectedJobOffer";
    }
*/
    //dla użytkownika

    @GetMapping(path = "/employer/jobOffers/createNew")
    public String addNewJobOffer(Model model){
        model.addAttribute("jobOffer", new JobOffer());
        return "/all/jobOffers/add-jobOffer";
    }

    @PostMapping(path = "/employer/jobOffers/createJobOfferPost")
    public ModelAndView createJobOffer(@Valid JobOffer jobOffer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails)authentication.getPrincipal();
            User user = userService.findUserByEmail(userDetails.getUsername());
            Integer id = user.getIdUser();
            jobOfferService.createJobOffer(jobOffer, employerService.findEmployerByUser_id(id));
            modelAndView.addObject("successMessage", "Ogłoszenie zostało pomyślnie dodane.");
        }
        modelAndView.addObject("jobOffer", new JobOffer());
        modelAndView.setViewName("/all/jobOffers/add-jobOffer");
        return modelAndView;
    }

    @GetMapping(path = "/user/jobOffersFiltered")
    public String getAllJobOffersLogged(Model model, String title, String location, String category, String contractType, String workingTime, String positionLevel, Integer monthlyPay, String categorySalary) {
        List<JobOfferDTO> JobList = jobOfferRepository.getJobOfferFiltered(title, category, location, contractType, workingTime, positionLevel,monthlyPay,categorySalary);
        model.addAttribute("jobOffers", JobList);
        return "/all/jobOffers/viewJobsLoginUser";
    }

    @GetMapping(path = {"/user/jobOffers/viewSelectedJobOffer", "/user/jobOffers/viewSelectedJobOffer/{id}"})
    public String viewSelectedJobOfferUser(Model model,@PathVariable("id") Optional<Integer> id) {
        model.addAttribute("profileProposition", new ProfileProposition());
        List<JobOfferDTO> JobOfferList = jobOfferRepository.getSelectedJobOffer(id.get());
        model.addAttribute("jobOffers", JobOfferList);
        return "/all/jobOffers/selectedJobOfferLogin";
    }

    @GetMapping(path = "/user/getEmployerJobOffers/{id}")
    public String getEmployerJobOffers(Model model, @PathVariable("id") Integer id){
        model.addAttribute("profileProposition", new ProfileProposition());
        List<JobOfferDTO> jobOfferDTOList = jobOfferRepository.getSelectedJobOfferByEmployerId(id);
        model.addAttribute("jobOffers", jobOfferDTOList);
        return "/all/jobOffers/selectedJobOfferLogin";
    }

    @GetMapping(path = "/employer/getEmployerJobOffer/{id}")
    public String getUserJobOffer(Model model, @PathVariable("id") Integer id){
        model.addAttribute("jobOffer", new JobOffer());
        idEmployer = id;
        List<JobOfferDTO> jobOfferDTOList = jobOfferRepository.getSelectedJobOfferByEmployerId(id);
        model.addAttribute("jobOffers", jobOfferDTOList);
        return "/all/jobOffers/viewUserJobOffers";
    }

    @GetMapping(path ="/employer/jobOffers/editOffer/{id}")
    public String editJobOffer(Model model, @PathVariable("id") Optional<Integer> id) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(id.get());
        model.addAttribute("jobOffer", jobOffer);
        return "/all/jobOffers/edit-jobOffer";
    }

    @GetMapping(path = "/employer/jobOffers/delete/{id}")
    public String deleteJobOfferById(@PathVariable("id") Integer id) {
        jobOfferService.deleteJobOfferById(id);
        return "redirect:/employer/getEmployerJobOffer/"+idEmployer;
    }

    @PostMapping(path = "/employer/jobOffers/updateJobOfferPost")
    public ModelAndView updateJobOffer(@Valid JobOffer jobOffer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            jobOfferService.updateJobffer(jobOffer);
            modelAndView.addObject("successMessage", "Ogłoszenie zostało zaktualizowane");
        }
        modelAndView.setViewName("/all/jobOffers/edit-jobOffer");
        return modelAndView;
    }
/*
    // admin

    @GetMapping("/admin/jobOffers")
    public String getAllJobOffersAdmin(Model model, String title, String location, String category, String contractType, String workingTime, String positionLevel) {
        List<JobOfferDTO> JobList = jobOfferRepository.getJobOfferFilteredAdmin(title, category, location, contractType, workingTime, positionLevel);
        model.addAttribute("jobOffers", JobList);
        return "/all/jobOffers/allJobOffersAdmin";
    }

    @GetMapping(path = {"/admin/jobOffers/editOffer", "/admin/jobOffers/editOffer/{id}"})
    public String editJobOfferAdmin(Model model, @PathVariable("id") Optional<Integer> id) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(id.get());
        model.addAttribute("jobOffer", jobOffer);
        return "/all/jobOffers/editJobOfferAdmin";
    }

    @PostMapping(path = "/admin/jobOffers/updateJobOfferPost")
    public ModelAndView updateJobOfferAdmin(@Valid JobOffer jobOffer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            jobOfferService.updateJobffer(jobOffer);
            modelAndView.addObject("successMessage", "Oferta została zaktualizowana.");
        }
        modelAndView.setViewName("/all/jobOffers/editJobOfferAdmin");
        return modelAndView;
    }

    @GetMapping(path = "/admin/jobOffers/delete/{id}")
    public String deleteJobOfferByIdAdmin(@PathVariable("id") Integer id) {
        jobOfferService.deleteJobOfferById(id);
        return "redirect:/admin/jobOffers";
    }

    @GetMapping(path = "/admin/jobOffers/createNew")
    public String addNewJobOfferAdmin(Model model){
        model.addAttribute("jobOffer", new JobOffer());
        return "/all/jobOffers/addJobOfferAdmin";
    }

    @PostMapping(path = "/admin/jobOffers/createJobOfferPost")
    public String createJobOfferAdmin(@Valid JobOffer jobOffer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            jobOfferService.createJobOffer(jobOffer, userService.findUserByEmail(authentication.getName()));
            modelAndView.addObject("successMessage", "Ogłoszenie zostało dodane");
        }
        modelAndView.addObject("jobOffer", new JobOffer());
        return "/all/jobOffers/addJobOfferAdmin";
    }

    @GetMapping("/admin/userJobOffers/{id}")
    public String getAllUserJobOffersAdmin(Model model, @PathVariable("id") Optional<Integer> id) {
        Userid = id.get();
        List<JobOfferDTO> JobList = jobOfferRepository.getUserJobOfferAdmin(id.get());
        model.addAttribute("jobOffers", JobList);
        return "/all/jobOffers/userJobOffersAdmin";
    }

    @GetMapping(path = "/admin/userJobOffers/delete/{id}")
    public String deleteUserJobOfferByIdAdmin(@PathVariable("id") Integer id) {
        jobOfferService.deleteJobOfferById(id);
        return "redirect:/admin/userJobOffers/"+Userid;
    }*/
}
