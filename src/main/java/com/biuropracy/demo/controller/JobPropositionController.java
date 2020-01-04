package com.biuropracy.demo.controller;

import com.biuropracy.demo.DTO.JobPropositionDTO;
import com.biuropracy.demo.model.JobProposition;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.JobPropositionRepository;
import com.biuropracy.demo.service.JobPropositionService;
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
public class JobPropositionController {

    @Autowired
    JobPropositionService jobPropositionService;

    @Autowired
    UserService userService;

    @Autowired
    private JobPropositionRepository jobPropositionRepository;

    @PostMapping(path = {"/user/userProfile/createJobProposition", "/user/userProfile/createJobProposition/{id}"})
    public String createJobProposition(ModelAndView modelAndView, JobProposition jobProposition, @PathVariable("id") Optional<Integer> id){
        User toUserId = userService.findUser(id.get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jobPropositionService.createJobProposition(jobProposition, userService.findUserByEmail(authentication.getName()), toUserId);
        modelAndView.addObject("jobProposition", new JobProposition());
        return "redirect:/user/viewSelectedProfile/{id}";
    }

    @GetMapping(path = "/user/getAllJPropByToUserId")
    public String getAllJPropByToUser(Model model){
        model.addAttribute("jobProposition", new JobProposition()); //do edycji w modalu
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<JobPropositionDTO> JobPropDto = jobPropositionRepository.getAllJPropByToUserId(id);
        model.addAttribute("jobPropositions", JobPropDto);
        return "/all/jobProposition/allJPropToUser";
    }

    @GetMapping(path = "/user/getAcceptJPropByToUserID")
    public String getAcceptJPropByToUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<JobPropositionDTO> JobPropDto = jobPropositionRepository.getAcceptJPropByToUserID(id);
        model.addAttribute("jobPropositions", JobPropDto);
        return "/all/jobProposition/acceptRejectedJPropToUser";
    }

    @GetMapping(path = "/user/getRejectedJPropByToUserID")
    public String getRejectedJPropByToUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<JobPropositionDTO> JobPropDto = jobPropositionRepository.getRejectedJPropByToUserID(id);
        model.addAttribute("jobPropositions", JobPropDto);
        return "/all/jobProposition/acceptRejectedJPropToUser";
    }

    @GetMapping(path = "/user/getAllJPropByFromUserId")
    public String getAllJPropByFromUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<JobPropositionDTO> JobPropDto = jobPropositionRepository.getAllJPropByFromUserId(id);
        model.addAttribute("jobPropositions", JobPropDto);
        return "/all/jobProposition/allJPropFromUser";
    }

    @GetMapping(path = "/user/getAcceptJPropByFromUserID")
    public String getAcceptJPropByFromUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<JobPropositionDTO> JobPropDto = jobPropositionRepository.getAcceptJPropByFromUserID(id);
        model.addAttribute("jobPropositions", JobPropDto);
        return "/all/jobProposition/acceptRejectedJPropFromUser";
    }

    @GetMapping(path = "/user/getRejectedJPropByFromUserID")
    public String getRejectedJPropByFromUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<JobPropositionDTO> JobPropDto = jobPropositionRepository.getRejectedJPropByFromUserID(id);
        model.addAttribute("jobPropositions", JobPropDto);
        return "/all/jobProposition/acceptRejectedJPropFromUser";
    }

    @PostMapping(path = "/user/JobProp/changeDecision")
    public String changeDecision(JobProposition jobProposition){
        jobPropositionService.updateJobProposition(jobProposition);
        return "redirect:/user/getAllJPropByToUserId";
    }

    @GetMapping(path = "/user/jobPropositionDelete")
    public String jobPropositionDelete(@RequestParam("id") Integer id){
        jobPropositionService.deleteJobProposition(id);
        return "redirect:/user/getAllJPropByFromUserId";
    }

}
