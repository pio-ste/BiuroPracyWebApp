package com.biuropracy.demo.controller;

import com.biuropracy.demo.DTO.UserInformationDTO;
import com.biuropracy.demo.model.*;
import com.biuropracy.demo.repository.UserInformationRepository;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UserProfileController {

    private Integer idCurrentUser;

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
    @Autowired
    UserInformationService userInformationService;
    @Autowired
    ProfilePropositionService profilePropositionService;
    @Autowired
    JobPropositionService jobPropositionService;
    @Autowired
    private UserInformationRepository userInformationRepository;
    //wyświetlenie własnego profilu zawodowego przez użytkownika
    @GetMapping(path = "/user/myProfile")
    public String viewMyProfile(Model model) {
        model.addAttribute("userInformation", new UserInformation());
        model.addAttribute("webLink", new WebLink());
        model.addAttribute("course", new Course());
        model.addAttribute("education", new Education());
        model.addAttribute("jobExperience", new JobExperience());
        model.addAttribute("language", new Language());
        model.addAttribute("organization", new Organization());
        model.addAttribute("skill", new Skill());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        User userImg = userService.findUser(id);
        List<UserInformationDTO> UserInfoDTOList = userInformationRepository.getUserAndUserInfoByUserId(id);
        List<UserInformation> UserInfoList = userInformationService.findUserInfoByUserId(id);
        List<User> UserList = userService.findUserById(id);
        List<Course> CourseList = courseService.findCourseByUserId(id);
        List<Education> EducationList = educationService.findEducationByUserId(id);
        List<JobExperience> JobExpList = jobExperienceService.findJobExperienceByUserId(id);
        List<Language> LangList = languageService.findLanguageByUserId(id);
        List<Organization> OrganizationList = organizationService.findOrganizationByUserId(id);
        List<Skill> SkillList = skillService.findSkillByUserId(id);
        List<WebLink> WebLinkList = webLinkService.findWebLinkByUserId(id);
        if (UserInfoList.isEmpty()){
            model.addAttribute("userInfoList", UserInfoList);
        } else {
            model.addAttribute("userInfoDTOs", UserInfoDTOList);
            model.addAttribute("userInformations", UserInfoList);
            model.addAttribute("users", UserList);
            model.addAttribute("courses", CourseList);
            model.addAttribute("educations", EducationList );
            model.addAttribute("jobExps", JobExpList);
            model.addAttribute("languages", LangList);
            model.addAttribute("organizations", OrganizationList);
            model.addAttribute("skills", SkillList);
            model.addAttribute("webLinks", WebLinkList);
            model.addAttribute("userImg", userImg);
        }
        return "/user/myProfile";
    }
    //wyświetlenie formularza aby dodać inforamcje o koncie
    @GetMapping(path = "/user/addUserInfo")
    public String addUserInfo(Model model){
        model.addAttribute("userInformation", new UserInformation());
        return "/user/addUserInfo";
    }
    //dodawanie inforamcji o koncie
    @PostMapping(path = "/user/addUserInfoPost")
    public String addUserInfoPost(@Valid UserInformation userInformation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userInformationService.createUserInfo(userInformation, userService.findUserByEmail(authentication.getName()));
        return "redirect:/user/myProfile";
    }
    //wyświetlenie formularza do edytowania inforamcji o koncie
    @GetMapping(path = "/user/editUserInfo/{id}")
    public String editUserInfo(Model model, @PathVariable("id") Integer id){
        UserInformation userInformation = userInformationService.getUserInfoById(id);
        model.addAttribute("userInformation", userInformation);
        return "/user/editUserInfo";
    }
    //edytowanie inforamcji o koncie
    @PostMapping(path = "/user/editUserInfoPost")
    public ModelAndView editUserPost(@Valid UserInformation userInformation, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            userInformationService.updateUserInfo(userInformation);
            modelAndView.addObject("successMessage", "Informacje zostały pomyślnie zaktualizowane.");
        }
        modelAndView.setViewName("/user/editUserInfo");
        return modelAndView;
    }
    //dodawanie linków do stron internetowych
    @PostMapping(path = "/user/userProfile/addWebLink")
    public String addWebLink(ModelAndView modelAndView, WebLink webLink){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        webLinkService.createWebLink(webLink, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("webLink", new WebLink());
        return "redirect:/user/myProfile";
    }
    //dodawanie kursu do profilu zawodowego
    @PostMapping(path = "/user/userProfile/addCourse")
    public String addCourse(ModelAndView modelAndView, Course course){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        courseService.createCourse(course, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("course", new Course());
        return "redirect:/user/myProfile";
    }
    //dodawanie edukacji do profilu zawodowego
    @PostMapping(path = "/user/userProfile/addEducation")
    public String addEducation(ModelAndView modelAndView, Education education){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        educationService.createEducation(education, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("education", new Education());
        return "redirect:/user/myProfile";
    }
    //dodawanie poprzednich stanowisk pracy do profilu zawodowego
    @PostMapping(path = "/user/userProfile/addJobExperience")
    public String addJobExperience(ModelAndView modelAndView, JobExperience jobExperience){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jobExperienceService.createJobExperience(jobExperience, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("jobExperience", new JobExperience());
        return "redirect:/user/myProfile";
    }
    //dodawanie języków obcych do profilu zawodowego
    @PostMapping(path = "/user/userProfile/addLanguage")
    public String addLanguage(ModelAndView modelAndView, Language language){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        languageService.createLanguage(language, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("language", new Language());
        return "redirect:/user/myProfile";
    }
    //dodawanie organizacji do profilu zawodowego
    @PostMapping(path = "/user/userProfile/addOrganization")
    public String addOrganization(ModelAndView modelAndView, Organization organization){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        organizationService.createOrganization(organization, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("organization", new Organization());
        return "redirect:/user/myProfile";
    }
    //dodawanie umiejętności do profilu zawodowego
    @PostMapping(path = "/user/userProfile/addSkill")
    public String addSkill(ModelAndView modelAndView, Skill skill){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        skillService.createSkill(skill, userService.findUserByEmail(authentication.getName()));
        modelAndView.addObject("skill", new Skill());
        return "redirect:/user/myProfile";
    }
    //usuwanie linku do strony w profilu zawodowego
    @GetMapping(path = "/user/userProfile/deleteWebLink")
    public String deleteWebLink(@RequestParam("id") Integer id){
        webLinkService.deleteWebLinkById(id);
        return "redirect:/user/myProfile";
    }
    //usuwanie kursu  w profilu zawodowego
    @GetMapping(path = "/user/userProfile/deleteCourse")
    public String deleteCourse(@RequestParam("id") Integer id){
        courseService.deleteCourseById(id);
        return "redirect:/user/myProfile";
    }
    //usuwanie edukacji w profilu zawodowego
    @GetMapping(path = "/user/userProfile/deleteEducation")
    public String deleteEducation(@RequestParam("id") Integer id){
        educationService.deleteEducation(id);
        return "redirect:/user/myProfile";
    }
    //usuwanie doświadczenia zawodowego w profilu zawodowego
    @GetMapping(path = "/user/userProfile/deleteJobExperience")
    public String deleteJobExperience(@RequestParam("id") Integer id){
        jobExperienceService.deleteJobExperienceById(id);
        return "redirect:/user/myProfile";
    }
    //usuwanie języka obcego w profilu zawodowego
    @GetMapping(path = "/user/userProfile/deleteLanguage")
    public String deleteLanguage(@RequestParam("id") Integer id){
        languageService.deleteLanguageById(id);
        return "redirect:/user/myProfile";
    }
    //usuwanie organizacji w profilu zawodowego
    @GetMapping(path = "/user/userProfile/deleteOrganization")
    public String deleteOrganization(@RequestParam("id") Integer id){
        organizationService.deleteOrganizationById(id);
        return "redirect:/user/myProfile";
    }
    //usuwanie umiejętności w profilu zawodowego
    @GetMapping(path = "/user/userProfile/deleteSkill")
    public String deleteSkill(@RequestParam("id") Integer id){
        skillService.deleteSkillById(id);
        return "redirect:/user/myProfile";
    }
    //edytowanie linku do strony w profilu zawodowego
    @PostMapping(path = "/user/userProfile/editWebLink")
    public String editWebLink(WebLink webLink) {
        webLinkService.updateWebLink(webLink);
        return "redirect:/user/myProfile";
    }
    //edytowanie kursu w profilu zawodowego
    @PostMapping(path = "/user/userProfile/editCourse")
    public String editCourse(Course course) {
        courseService.updateCourse(course);
        return "redirect:/user/myProfile";
    }
    //edytowanie edukacji w profilu zawodowego
    @PostMapping(path = "/user/userProfile/editEducation")
    public String editEducation(Education education) {
        educationService.updateEducation(education);
        return "redirect:/user/myProfile";
    }
    //edytowanie kursu w profilu zawodowego
    @PostMapping(path = "/user/userProfile/editJobExperience")
    public String editJobExperience(JobExperience jobExperience) {
        jobExperienceService.updateJobExperience(jobExperience);
        return "redirect:/user/myProfile";
    }
    //edytowanie języka obcego w profilu zawodowego
    @PostMapping(path = "/user/userProfile/editLanguage")
    public String editLanguage(Language language) {
        languageService.updateLanguage(language);
        return "redirect:/user/myProfile";
    }
    //edytowanie organizacji w profilu zawodowego
    @PostMapping(path = "/user/userProfile/editOrganization")
    public String editOrganization(Organization organization) {
        organizationService.updateOrganization(organization);
        return "redirect:/user/myProfile";
    }
    //edytowanie umiejętności w profilu zawodowego
    @PostMapping(path = "/user/userProfile/editSkill")
    public String editSkill(Skill skill) {
        skillService.updateSkill(skill);
        return "redirect:/user/myProfile";
    }
    //wyświetlanie formularza do edytowania profilu zawodowego
    @GetMapping(path = "/user/userProfile/editProfile")
    public String editProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        return "/all/profile/editProfile";
    }
    //edytowanie profilu zawodowego
    @PostMapping(path = "/user/userProfile/updateProfile")
    public ModelAndView updateProfile(User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            userService.updateUser(user);
            modelAndView.addObject("successMessage", "Informacje zostały pomyślnie zaktualizowane.");
        }
        modelAndView.setViewName("/all/profile/editProfile");
        return modelAndView;
    }
    //dodawanie zdjęcia w profilu zawodowym
    @GetMapping(path = "/user/{id}/addImage")
    public String addImage(@PathVariable("id") Integer id, Model model){
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "/all/profile/uploadImage";
    }
    //dodawanie zdjęcia w profilu zawodowym
    @PostMapping(path = "/user/{id}/uploadImage")
    public String uploadImage(@PathVariable("id") Integer id, @RequestParam("imageFile") MultipartFile file) {
        userService.saveProfileImage(id,file);
        return "redirect:/user/myProfile";
    }
    //wyświetlanie zdjęcia w profilu zawodowym
    @GetMapping(path = "/all/{id}/displayImage")
    public void displayImageFromDB(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
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
    //dodawanie zdjęcia w profilu zawodowym
    @GetMapping(path ="/employer/editEmployer/{id}")
    public String editJobOffer(Model model, @PathVariable("id") Integer id) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "/all/profile/editEmployerProfile";
    }
    //edytowanie informacji w profilu przedstawiciela firmy
    @PostMapping(path = "/employer/editUserInfoPost")
    public String editUserPost(User user) {
        userService.updateUser(user);
        return "/all/profile/editEmployerProfile";
    }
    //lista profili zawodowych
    @GetMapping(path = "/employer/usersProfilesVisible")
    public String usersProfileFiltered(Model model, String workCity, String category, String email){
        List<UserInformationDTO> FilteredProfile = userRepository.getVisibleUsersFiltered(workCity, category, email);
        model.addAttribute("users", FilteredProfile);
        return "/all/profile/ProfileList";
    }
    //wyświetlenie profilu zawodowego
    @GetMapping(path = {"/employer/viewSelectedProfile", "/employer/viewSelectedProfile/{id}"})
    public String viewSelectedProfile(Model model,@PathVariable("id") Integer id){
        model.addAttribute("jobProposition", new JobProposition());
        List<UserInformationDTO> UserInfoDTOList = userInformationRepository.getUserAndUserInfoByUserId(id);
        List<Course> CourseList = courseService.findCourseByUserId(id);
        List<Education> EducationList = educationService.findEducationByUserId(id);
        List<JobExperience> JobExpList = jobExperienceService.findJobExperienceByUserId(id);
        List<Language> LangList = languageService.findLanguageByUserId(id);
        List<Organization> OrganizationList = organizationService.findOrganizationByUserId(id);
        List<Skill> SkillList = skillService.findSkillByUserId(id);
        List<WebLink> WebLinkList = webLinkService.findWebLinkByUserId(id);
        model.addAttribute("userInfoDTOs", UserInfoDTOList);
        model.addAttribute("courses", CourseList);
        model.addAttribute("educations", EducationList );
        model.addAttribute("jobExps", JobExpList);
        model.addAttribute("languages", LangList);
        model.addAttribute("organizations", OrganizationList);
        model.addAttribute("skills", SkillList);
        model.addAttribute("webLinks", WebLinkList);
        return "/all/profile/viewSelectedProfile";
    }

    //admin


    //wyświetlenie profilu zawodowego dla admina
    @GetMapping(path = {"/admin/viewUserProfile", "/admin/viewUserProfile/{id}"})
    public String viewProfileAdmin(Model model, @PathVariable("id") Integer id) {
        idCurrentUser = id;
        model.addAttribute("userInformation", new UserInformation());
        model.addAttribute("webLink", new WebLink());
        model.addAttribute("course", new Course());
        model.addAttribute("education", new Education());
        model.addAttribute("jobExperience", new JobExperience());
        model.addAttribute("language", new Language());
        model.addAttribute("organization", new Organization());
        model.addAttribute("skill", new Skill());
        User userImg = userService.findUser(id);
        List<UserInformationDTO> UserInfoDTOList = userInformationRepository.getUserAndUserInfoByUserId(id);
        List<UserInformation> UserInfoList = userInformationService.findUserInfoByUserId(id);
        List<User> UserList = userService.findUserById(id);
        List<Course> CourseList = courseService.findCourseByUserId(id);
        List<Education> EducationList = educationService.findEducationByUserId(id);
        List<JobExperience> JobExpList = jobExperienceService.findJobExperienceByUserId(id);
        List<Language> LangList = languageService.findLanguageByUserId(id);
        List<Organization> OrganizationList = organizationService.findOrganizationByUserId(id);
        List<Skill> SkillList = skillService.findSkillByUserId(id);
        List<WebLink> WebLinkList = webLinkService.findWebLinkByUserId(id);
        model.addAttribute("userInfoDTOs", UserInfoDTOList);
        model.addAttribute("userInformations", UserInfoList);
        model.addAttribute("users", UserList);
        model.addAttribute("courses", CourseList);
        model.addAttribute("educations", EducationList );
        model.addAttribute("jobExps", JobExpList);
        model.addAttribute("languages", LangList);
        model.addAttribute("organizations", OrganizationList);
        model.addAttribute("skills", SkillList);
        model.addAttribute("webLinks", WebLinkList);
        model.addAttribute("userImg", userImg);
        return "/all/profile/selectedUserProfile";
    }
    //wyświetlenie profili zwaodowych dla admina
    @GetMapping(path = "/admin/usersProfiles")
    public String usersProfileAdmin(Model model, String workCity, String category, String email){
        List<UserInformationDTO> FilteredProfile = userRepository.getAllUsersFiltered(workCity, category, email);
        model.addAttribute("users", FilteredProfile);
        return "/all/profile/ProfileListAdmin";
    }
    //dodawanie strony internetowej w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/addWebLink")
    public String addWebLinkAdmin(ModelAndView modelAndView, WebLink webLink){
        webLinkService.createWebLink(webLink, userService.findUser(idCurrentUser));
        modelAndView.addObject("webLink", new WebLink());
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //dodawanie kursu w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/addCourse")
    public String addCourseAdmin(ModelAndView modelAndView, Course course){
        courseService.createCourse(course, userService.findUser(idCurrentUser));
        modelAndView.addObject("course", new Course());
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //dodawanie edukacji w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/addEducation")
    public String addEducationAdmin(ModelAndView modelAndView, Education education){
        educationService.createEducation(education, userService.findUser(idCurrentUser));
        modelAndView.addObject("education", new Education());
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //dodawanie doświadczenia zawodowego w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/addJobExperience")
    public String addJobExperienceAdmin(ModelAndView modelAndView, JobExperience jobExperience){
        jobExperienceService.createJobExperience(jobExperience, userService.findUser(idCurrentUser));
        modelAndView.addObject("jobExperience", new JobExperience());
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //dodawanie języka obcego w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/addLanguage")
    public String addLanguageAdmin(ModelAndView modelAndView, Language language){
        languageService.createLanguage(language, userService.findUser(idCurrentUser));
        modelAndView.addObject("language", new Language());
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //dodawanie organizacji w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/addOrganization")
    public String addOrganizationAdmin(ModelAndView modelAndView, Organization organization){
        organizationService.createOrganization(organization, userService.findUser(idCurrentUser));
        modelAndView.addObject("organization", new Organization());
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //dodawanie umiejętności w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/addSkill")
    public String addSkillAdminAdmin(ModelAndView modelAndView, Skill skill){
        skillService.createSkill(skill, userService.findUser(idCurrentUser));
        modelAndView.addObject("skill", new Skill());
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //uswanie linku do strony w profilu zawodowym przez admina
    @GetMapping(path = "/admin/userProfile/deleteWebLink")
    public String deleteWebLinkAdmin(@RequestParam("id") Integer id){
        webLinkService.deleteWebLinkById(id);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //uswanie kursu w profilu zawodowym przez admina
    @GetMapping(path = "/admin/userProfile/deleteCourse")
    public String deleteCourseAdmin(@RequestParam("id") Integer id){
        courseService.deleteCourseById(id);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //uswanie edukacji w profilu zawodowym przez admina
    @GetMapping(path = "/admin/userProfile/deleteEducation")
    public String deleteEducationAdmin(@RequestParam("id") Integer id){
        educationService.deleteEducation(id);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //uswanie doświadczenia zwodowego w profilu zawodowym przez admina
    @GetMapping(path = "/admin/userProfile/deleteJobExperience")
    public String deleteJobExperienceAdmin(@RequestParam("id") Integer id){
        jobExperienceService.deleteJobExperienceById(id);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //uswanie języka obcego w profilu zawodowym przez admina
    @GetMapping(path = "/admin/userProfile/deleteLanguage")
    public String deleteLanguageAdmin(@RequestParam("id") Integer id){
        languageService.deleteLanguageById(id);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //uswanie organizacji w profilu zawodowym przez admina
    @GetMapping(path = "/admin/userProfile/deleteOrganization")
    public String deleteOrganizationAdmin(@RequestParam("id") Integer id){
        organizationService.deleteOrganizationById(id);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //uswanie umiejętności w profilu zawodowym przez admina
    @GetMapping(path = "/admin/userProfile/deleteSkill")
    public String deleteSkillAdmin(@RequestParam("id") Integer id){
        skillService.deleteSkillById(id);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //edytowanie linku do stron w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/editWebLink")
    public String editProfileAdmin(WebLink webLink) {
        webLinkService.updateWebLink(webLink);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //edytowanie kursu w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/editCourse")
    public String editCourseAdmin(Course course) {
        courseService.updateCourse(course);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //edytowanie edukacji w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/editEducation")
    public String editEducationAdmin(Education education) {
        educationService.updateEducation(education);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //edytowanie doświadczenia zawodowego w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/editJobExperience")
    public String editJobExperienceAdmin(JobExperience jobExperience) {
        jobExperienceService.updateJobExperience(jobExperience);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //edytowanie języka obcego w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/editLanguage")
    public String editLanguageAdmin(Language language) {
        languageService.updateLanguage(language);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //edytowanie organizacji w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/editOrganization")
    public String editOrganizationAdmin(Organization organization) {
        organizationService.updateOrganization(organization);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //edytowanie umiejętności w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/editSkill")
    public String editSkillAdmin(Skill skill) {
        skillService.updateSkill(skill);
        return "redirect:/admin/viewUserProfile/"+ idCurrentUser;
    }
    //formularz do edytowania informacji w profilu zawodowym przez admina
    @GetMapping(path = {"/admin/userProfile/editProfile", "/admin/userProfile/editProfile/{id}"})
    public String editUserProfileAdmin(Model model, @PathVariable("id") Integer id) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "/all/profile/editUserProfileAdmin";
    }
    //edytowanie informacji w profilu zawodowym przez admina
    @PostMapping(path = "/admin/userProfile/updateProfile")
    public String updateUserProfileAdmin(User user) {
        userService.updateUser(user);
        return "redirect:/admin/viewUserProfile/" + idCurrentUser;
    }
    //edytowanie informacji w profilu przedstawiciela firmy  przez admina
    @GetMapping(path = {"/admin/employerProfile/editProfile", "/admin/employerProfile/editProfile/{id}"})
    public String editEmployerProfileAdmin(Model model, @PathVariable("id") Integer id) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "/all/profile/editEmployerProfileAdmin";
    }
    //edytowanie informacji w profilu przedstawiciela firmy przez admina
    @PostMapping(path = "/admin/employerProfile/updateProfile")
    public String updateEmployerProfileAdmin(User user) {
        userService.updateUser(user);
        return "/all/profile/editEmployerProfileAdmin";
    }
    //formularz do edytowania informacji w profilu zawodowym przez admina
    @GetMapping(path = "/admin/editUserInfo/{id}")
    public String editUserInfoAdmin(Model model, @PathVariable("id") Integer id){
        UserInformation userInformation = userInformationService.getUserInfoById(id);
        model.addAttribute("userInformation", userInformation);
        return "/all/profile/editUserInfoAdmin";
    }
    //edytowanie informacji w profilu zawodowym przez admina
    @PostMapping(path = "/admin/editUserInfoPost")
    public String editUserPostAdmin(UserInformation userInformation) {
        userInformationService.updateUserInfo(userInformation);
        return "redirect:/admin/viewUserProfile/" + idCurrentUser;
    }
    //dodawanie zdjęcie w profilu zawodowym przez admina
    @PostMapping(path = "/admin/{id}/uploadImage")
    public String uploadImageAdmin(@PathVariable("id") Integer id, @RequestParam("imageFile") MultipartFile file) {
        userService.saveProfileImage(id,file);
        return "redirect:/admin/viewUserProfile/" + idCurrentUser;
    }
    //usuwanie konta użytkownika
    @GetMapping(path = "/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        UserInformation userInf = userInformationService.findUserInformationByUserId(id);
        Course course = courseService.findByUserId(id);
        Skill skill = skillService.findByUserId(id);
        WebLink webLink = webLinkService.findByUserId(id);
        Language language = languageService.findByUserId(id);
        Education education = educationService.findByUserId(id);
        JobExperience jobExperience = jobExperienceService.findByUserId(id);
        Organization organization = organizationService.findByUserId(id);
        JobProposition jobProposition = jobPropositionService.findByUseId(id);
        ProfileProposition profileProposition = profilePropositionService.findByUserId(id);
        if (userInf != null)
            userInformationService.deleteUserInfoById(userInf.getIdUserInformation());
        if (course != null)
            courseService.deleteCourseById(course.getIdCourse());
        if(skill != null)
            skillService.deleteSkillById(skill.getIdSkill());
        if (webLink != null)
            webLinkService.deleteWebLinkById(webLink.getIdWebLink());
        if (language != null)
            languageService.deleteLanguageById(language.getIdLanguage());
        if (education != null)
            educationService.deleteEducation(education.getIdEducation());
        if (jobExperience != null)
            jobExperienceService.deleteJobExperienceById(jobExperience.getIdJobExperience());
        if (organization != null)
            organizationService.deleteOrganizationById(organization.getIdOrganization());
        if (jobProposition != null)
            jobPropositionService.deleteJobProposition(jobProposition.getIdJobProposition());
        if (profileProposition != null)
            profilePropositionService.deleteProfileProp(profileProposition.getIdProfileProposition());
        userService.deleteUserById(id);
        return "redirect:/admin/usersProfiles";
    }
}
