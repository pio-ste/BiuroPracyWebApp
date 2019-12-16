package com.biuropracy.demo.controller;

import com.biuropracy.demo.model.*;
import com.biuropracy.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileController {

    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    EducationService educationService;
    @Autowired
    JobExperienceService jobExperienceService;
    @Autowired
    LanguageService languageService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    SkillService skillService;
    @Autowired
    WebLinkService webLinkService;

    @GetMapping(path = "/user/myProfile")
    public String viewMyProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        List<User> UserList = userService.findUserById(id);
        List<Course> CourseList = courseService.findCourseByUserId(id);
        List<Education> EducationList = educationService.findEducationByUserId(id);
        List<JobExperience> JobExpList = jobExperienceService.findJobExperienceByUserId(id);
        List<Language> LangList = languageService.findLanguageByUserId(id);
        List<Organization> OrganizationList = organizationService.findOrganizationByUserId(id);
        List<Skill> SkillList = skillService.findSkillByUserId(id);
        List<WebLink> WebLinkList = webLinkService.findWebLinkByUserId(id);
        model.addAttribute("users", UserList);
        model.addAttribute("courses", CourseList);
        model.addAttribute("educations", EducationList );
        model.addAttribute("jobExps", JobExpList);
        model.addAttribute("languages", LangList);
        model.addAttribute("organizations", OrganizationList);
        model.addAttribute("skills", SkillList);
        model.addAttribute("webLinks", WebLinkList);
        return "/user/myProfile";
    }

}
