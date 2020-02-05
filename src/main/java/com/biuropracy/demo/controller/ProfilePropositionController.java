package com.biuropracy.demo.controller;

import com.biuropracy.demo.DTO.ProfilePropositionDTO;
import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.ProfileProposition;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.ProfilePropositionRepository;
import com.biuropracy.demo.service.JobOfferService;
import com.biuropracy.demo.service.ProfilePropositionService;
import com.biuropracy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfilePropositionController {

    private Integer idJobOffer;

    @Autowired
    ProfilePropositionService profilePropositionService;

    @Autowired
    UserService userService;

    @Autowired
    JobOfferService jobOfferService;

    @Autowired
    private ProfilePropositionRepository profilePropositionRepository;

    @PostMapping(path = "/user/jobOffer/createProfileProp/{id}")
    public String createProfileProp(ModelAndView modelAndView, ProfileProposition profileProposition, @PathVariable("id") Optional<Integer> id){
        JobOffer jobOfferId = jobOfferService.getJobOfferById(id.get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        profilePropositionService.createProfileProp(profileProposition,userService.findUserByEmail(authentication.getName()), jobOfferId);
        modelAndView.addObject("profileProposition", new ProfileProposition());
        return "redirect:/user/jobOffers/viewSelectedJobOffer/{id}";
    }

    @GetMapping(path = "/employer/viewProfilePropByJobOffer/{id}")
    public String viewProfilePropByJobOfferId(Model model, @PathVariable("id") Integer id, String decision){
        model.addAttribute("profileProposition", new ProfileProposition());
        List<ProfilePropositionDTO> profilePropList = profilePropositionRepository.getProfilePropByJobOfferId(id, decision);
        idJobOffer = id;
        model.addAttribute("currentId", id);
        model.addAttribute("profilePropositions", profilePropList);
        return "/all/profileProposition/profilePropListByJobOfferId";
    }

    @PostMapping(path = "/employer/ProfileProp/changeDecision")
    public String profilePropChangeDec(ProfileProposition profileProposition){
        profilePropositionService.updateProfileProp(profileProposition);
        return "redirect:/employer/viewProfilePropByJobOffer/"+idJobOffer;
    }

    @GetMapping(path = "/user/myAllProfileProp")
    public String myAllProfileProp(Model model, String decision){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<ProfilePropositionDTO> profilePropList = profilePropositionRepository.getAllProfilePropByUserId(id, decision);
        model.addAttribute("profilePropositions", profilePropList);
        return "/all/profileProposition/profilePropUser";
    }

    @GetMapping(path = "/user/deleteMyProfileProposition")
    public String deleteMyProfileProposition(@RequestParam("id") Integer id){
        profilePropositionService.deleteProfileProp(id);
        return "redirect:/user/myAllProfileProp";
    }

    @GetMapping(path = "/admin/viewProfilePropByJobOffer/{id}")
    public String viewProfilePropByJobOfferIdAdmin(Model model, @PathVariable("id") Integer id, String decision){
        model.addAttribute("profileProposition", new ProfileProposition());
        List<ProfilePropositionDTO> profilePropList = profilePropositionRepository.getProfilePropByJobOfferId(id, decision);
        idJobOffer = id;
        model.addAttribute("currentId", id);
        model.addAttribute("profilePropositions", profilePropList);
        return "/all/profileProposition/profilePropAdmin";
    }

    @PostMapping(path = "/admin/ProfileProp/editJobProp")
    public String editJobProp(ProfileProposition profileProposition){
        profilePropositionService.updateProfilePropAdmin(profileProposition);
        return "redirect:/admin/viewProfilePropByJobOffer/"+idJobOffer;
    }

    @GetMapping(path = "/admin/deleteProfileProposition")
    public String deleteProfileProposition(@RequestParam("id") Integer id){
        profilePropositionService.deleteProfileProp(id);
        return "redirect:/admin/viewProfilePropByJobOffer/"+idJobOffer;
    }

}
