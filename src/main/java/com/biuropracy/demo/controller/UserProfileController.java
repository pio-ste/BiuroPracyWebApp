package com.biuropracy.demo.controller;

import com.biuropracy.demo.model.*;
import com.biuropracy.demo.repository.UserRepository;
import com.biuropracy.demo.repository.WebLinkRepository;
import com.biuropracy.demo.service.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    WebLinkRepository webLinkRepository;

    @GetMapping(path = "/user/myProfile")
    public String viewMyProfile(Model model, ModelAndView modelAndView) {
        model.addAttribute("webLink", new WebLink());
        model.addAttribute("course", new Course());
        model.addAttribute("education", new Education());
        model.addAttribute("jobExperience", new JobExperience());
        model.addAttribute("language", new Language());
        model.addAttribute("organization", new Organization());
        model.addAttribute("skill", new Skill());
        modelAndView.addObject("webLink", new WebLink());
        modelAndView.addObject("course", new Course());
        modelAndView.addObject("education", new Education());
        modelAndView.addObject("jobExperience", new JobExperience());
        modelAndView.addObject("language", new Language());
        modelAndView.addObject("organization", new Organization());
        modelAndView.addObject("skill", new Skill());
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

    @PostMapping(path = "/user/userProfile/addWebLink")
    public String addWebLink(ModelAndView modelAndView, WebLink webLink){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        webLinkService.createWebLink(webLink, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("webLink", new WebLink());
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/addCourse")
    public String addCourse(ModelAndView modelAndView, Course course){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        courseService.createCourse(course, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("course", new Course());
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/addEducation")
    public String addEducation(ModelAndView modelAndView, Education education){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        educationService.createEducation(education, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("education", new Education());
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/addJobExperience")
    public String addJobExperience(ModelAndView modelAndView, JobExperience jobExperience){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jobExperienceService.createJobExperience(jobExperience, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("jobExperience", new JobExperience());
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/addLanguage")
    public String addLanguage(ModelAndView modelAndView, Language language){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        languageService.createLanguage(language, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("language", new Language());
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/addOrganization")
    public String addOrganization(ModelAndView modelAndView, Organization organization){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        organizationService.createOrganization(organization, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("organization", new Organization());
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/addSkill")
    public String addSkill(ModelAndView modelAndView, Skill skill){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        skillService.createSkill(skill, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("skill", new Skill());
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = "/user/userProfile/deleteWebLink")
    public String deleteWebLink(@RequestParam("id") Integer id){
        webLinkService.deleteWebLinkById(id);
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = "/user/userProfile/deleteCourse")
    public String deleteCourse(@RequestParam("id") Integer id){
        courseService.deleteCourseById(id);
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = "/user/userProfile/deleteEducation")
    public String deleteEducation(@RequestParam("id") Integer id){
        educationService.deleteEducation(id);
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = "/user/userProfile/deleteJobExperience")
    public String deleteJobExperience(@RequestParam("id") Integer id){
        jobExperienceService.deleteJobExperienceById(id);
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = "/user/userProfile/deleteLanguage")
    public String deleteLanguage(@RequestParam("id") Integer id){
        languageService.deleteLanguageById(id);
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = "/user/userProfile/deleteOrganization")
    public String deleteOrganization(@RequestParam("id") Integer id){
        organizationService.deleteOrganizationById(id);
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = "/user/userProfile/deleteSkill")
    public String deleteSkill(@RequestParam("id") Integer id){
        skillService.deleteSkillById(id);
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/editWebLink")
    public String editProfile(WebLink webLink) {
        webLinkService.updateWebLink(webLink);
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/editCourse")
    public String editCourse(Course course) {
        courseService.updateCourse(course);
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/editEducation")
    public String editEducation(Education education) {
        educationService.updateEducation(education);
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/editJobExperience")
    public String editJobExperience(JobExperience jobExperience) {
        jobExperienceService.updateJobExperience(jobExperience);
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/editLanguage")
    public String editLanguage(Language language) {
        languageService.updateLanguage(language);
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/editOrganization")
    public String editOrganization(Organization organization) {
        organizationService.updateOrganization(organization);
        return "redirect:/user/myProfile";
    }

    @PostMapping(path = "/user/userProfile/editSkill")
    public String editSkill(Skill skill) {
        skillService.updateSkill(skill);
        return "redirect:/user/myProfile";
    }

    @GetMapping(path = {"/user/userProfile/editProfile", "/user/userProfile/editProfile/{id}"})
    public String editProfile(Model model, @PathVariable("id") Optional<Integer> id) {
        User user = userService.findUser(id.get());
        model.addAttribute("user", user);
        return "/all/profile/editProfile";
    }

    @PostMapping(path = "/user/userProfile/updateProfile")
    public String updateProfile(User user) {
        userService.updateUser(user);
        return "/all/profile/editProfile";
    }

    @GetMapping(path = "/user/{id}/addImage")
    public String addImage(@PathVariable("id") Integer id, Model model){
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "/all/profile/uploadImage";
    }

    @PostMapping(path = "/user/{id}/uploadImage")
    public String uploadImage(@PathVariable("id") Integer id, @RequestParam("imagefile") MultipartFile file) {
        userService.saveProfileImage(id,file);
        return "redirect:/user/userProfile/editProfile/" + id +"";
    }

    @GetMapping(path = "/user/{id}/displayImage")
    public void dispalyImageFromDB(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        User user = userService.findUser(id);

        if (user.getProfileImage() != null) {
            byte[] byteArray = new byte[user.getProfileImage().length];
            int i = 0;
            for (Byte wrappedByte : user.getProfileImage()){
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            IOUtils.copy(inputStream, response.getOutputStream());
        } else {
            System.out.println("Brak zdjęcia");
        }
    }

    @GetMapping(path = "/user/usersProfilesVisible")
    public String usersProfilesVisible(Model model){
        List<User> ProfileList = userRepository.getVisibleUsers();
        model.addAttribute("users", ProfileList);
        return "/all/profile/loggedListProfiles";
    }

    @GetMapping(path = "/user/usersProfileFiltered")
    public String usersProfileFiltered(Model model, String workCity, String category){
        List<User> FilteredProfile = userRepository.getVisibleUsersFiltered(workCity, category);
        model.addAttribute("users", FilteredProfile);
        return "/all/profile/loggedListProfiles";
    }

    @GetMapping(path = {"/user/viewSelectedProfile", "/user/viewSelectedProfile/{id}"})
    public String viewSelectedProfile(Model model,@PathVariable("id") Optional<Integer> id){
        List<User> UserList = userService.findUserById(id.get());
        List<Course> CourseList = courseService.findCourseByUserId(id.get());
        List<Education> EducationList = educationService.findEducationByUserId(id.get());
        List<JobExperience> JobExpList = jobExperienceService.findJobExperienceByUserId(id.get());
        List<Language> LangList = languageService.findLanguageByUserId(id.get());
        List<Organization> OrganizationList = organizationService.findOrganizationByUserId(id.get());
        List<Skill> SkillList = skillService.findSkillByUserId(id.get());
        List<WebLink> WebLinkList = webLinkService.findWebLinkByUserId(id.get());
        model.addAttribute("users", UserList);
        model.addAttribute("courses", CourseList);
        model.addAttribute("educations", EducationList );
        model.addAttribute("jobExps", JobExpList);
        model.addAttribute("languages", LangList);
        model.addAttribute("organizations", OrganizationList);
        model.addAttribute("skills", SkillList);
        model.addAttribute("webLinks", WebLinkList);
        return "/all/profile/viewSelectedProfile";
    }

}
