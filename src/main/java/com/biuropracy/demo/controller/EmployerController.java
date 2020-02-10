package com.biuropracy.demo.controller;

import com.biuropracy.demo.DTO.EmployerUserDTO;
import com.biuropracy.demo.model.Employer;
import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.JobProposition;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.EmployerRepository;
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
public class EmployerController {

    private Integer idCurrentEmp;

    @Autowired
    UserService userService;

    @Autowired
    EmployerService employerService;

    @Autowired
    JobOfferService jobOfferService;

    @Autowired
    JobPropositionService jobPropositionService;

    @Autowired
    EmployerRepository employerRepository;
    //wyświtlanie listy pracodawców dla wszystkich
    @GetMapping(path = "/all/employersList")
    public String employersListAll(Model model, String companyName){
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerFiltered(companyName);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/employersListAll";
    }
    //wyświetlanie profilu pracodawcy dla wszystkich
    @GetMapping(path = "/all/selectedEmployerProfile/{id}")
    public String selectedEmployerProfileAll(Model model, @PathVariable("id") Integer id) {
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerUserByIdEmpl(id);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/selectedEmployerAll";
    }
    //dodawanie zdjęcia firmy przez pracodawcę
    @PostMapping(path = "/employer/uploadCompanyImage/{id}")
    public String uploadCompanyImage(@PathVariable("id") Integer id, @RequestParam("imageFileEmployer")MultipartFile file){
        employerService.saveCompanyImgImage(id,file);
        return "redirect:/employer/myProfileEmployer";
    }
    //wyświetlanie zdjęcia firmy
    @GetMapping(path = "/all/displayCompanyImg/{id}")
    public void displayCompanyImg(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Employer employer = employerService.findEmployer(id);
        if (employer.getCompanyImage() != null){
            byte[] byteArray = new byte[employer.getCompanyImage().length];
            int i = 0;
            for (Byte wrappedByte : employer.getCompanyImage()){
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            IOUtils.copy(inputStream, response.getOutputStream());
        } else {
            System.out.println("Brak zdjęcia");
        }
    }
    //wyświtlanie własnego profilu pracodawcy
    @GetMapping(path = "/employer/myProfileEmployer")
    public String myProfileEmployer(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        Integer id = user.getIdUser();
        Employer employerImg = employerService.findEmployerByUser_id(id);
        User userImg = userService.findUser(id);
        List<User> userList = userService.findUserById(id);
        List<Employer> employerList = employerService.findEmployerByUserId(id);
        model.addAttribute("users", userList);
        model.addAttribute("employers", employerList);
        model.addAttribute("employerImg", employerImg);
        model.addAttribute("userImg", userImg);
        return "/employer/myProfileEmployer";
    }
    //dodawanie zdjęcia dla przedstawiciela firmy
    @PostMapping(path = "/employer/{id}/uploadImage")
    public String uploadImage(@PathVariable("id") Integer id, @RequestParam("imagefile") MultipartFile file) {
        userService.saveProfileImage(id,file);
        return "redirect:/employer/myProfileEmployer";
    }
    //wyśewietlanie wybranego profilu pracodawcy przez użytkownika
    @GetMapping(path = "/user/selectedEmployerProfile/{id}")
    public String selectedEmployerProfile(Model model, @PathVariable("id") Integer id) {
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerUserByIdEmpl(id);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/selectedEmployer";
    }
    //wyświetlanie listy pracodawców dla użytkownika
    @GetMapping(path = "/user/employersList")
    public String employersList(Model model, String companyName){
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerFiltered(companyName);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/employerListLoginUser";
    }
    //wyświetlanie formularza do edytowania informacji o pracodawcy
    @GetMapping(path = "/employer/editEmployerInfo/{id}")
    public String editEmployerInfo(@PathVariable("id") Integer id, Model model){
        Employer employer = employerService.findEmployer(id);
        model.addAttribute("employer", employer);
        return "/employer/editEmployerInfo";
    }
    //edytowanie informacji o pracodawcy
    @PostMapping(path = "/employer/updateEmployerInfoPost")
    public ModelAndView updateEmployerInfo(@Valid Employer employer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            employerService.updateEmployer(employer);
            modelAndView.addObject("successMessage", "Dane zostały zaktualizowane");
        }
        modelAndView.setViewName("/employer/editEmployerInfo");
        return modelAndView;
    }

    //admin
    //lista pracodawców dla admina
    @GetMapping(path = "/admin/employersList")
    public String employersListAllAdmin(Model model, String companyName){
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerFiltered(companyName);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/employerListAdmin";
    }
    //wyświetlanie wybranego profilu przez admina
    @GetMapping(path = "/admin/selectedEmployerProfile/{id}")
    public String selectedEmployerProfileAdmin(Model model, @PathVariable("id") Integer id) {
        idCurrentEmp = id;
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerUserByIdEmpl(id);
        Employer employerImg = employerService.findEmployer(id);
        model.addAttribute("employerImg", employerImg);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/selectedEmployerAdmin";
    }
    //dodawanie zdjęcia dla pracodawcy przez admina
    @PostMapping(path = "/admin/uploadCompanyImage/{id}")
    public String uploadImageAdmin(@PathVariable("id") Integer id, @RequestParam("imageFileEmployer") MultipartFile file) {
        employerService.saveCompanyImgImage(id,file);
        return "redirect:/admin/selectedEmployerProfile/"+idCurrentEmp;
    }
    //wyświetlanie formularza do edycji pracodawcy przez admina
    @GetMapping(path = "/admin/editEmployerInfo/{id}")
    public String editEmployerInfoAdmin(@PathVariable("id") Integer id, Model model){
        Employer employer = employerService.findEmployer(id);
        model.addAttribute("employer", employer);
        return "/employer/editEmployerInfo";
    }
    //edytowanie informacji o pracodawcy przez admina
    @PostMapping(path = "/admin/updateEmployerInfoPost")
    public ModelAndView updateEmployerInfoAdmin(@Valid Employer employer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            employerService.updateEmployer(employer);
            modelAndView.addObject("successMessage", "Dane zostały zaktualizowane");
        }
        modelAndView.setViewName("/employer/editEmployerInfoAdmin");
        return modelAndView;
    }
    //wyswietlanie formularza rejestracji pracodawcy
    @GetMapping(value = "/admin/registerEmployer")
    public ModelAndView registerEmployer() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        Employer employer = new Employer();
        modelAndView.addObject("user" , user);
        modelAndView.addObject("employer", employer);
        modelAndView.setViewName("/employer/registerEmployer.html");
        return modelAndView;
    }
    //dodawanie pracodawcy przez admina
    @PostMapping(value = "/admin/registerEmployer")
    public ModelAndView registerEmployer(@Valid User user, @Valid Employer employer, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else if (userService.isUserAlreadyPresent(user)) {
            modelAndView.addObject("successMessage", "Uzytkownik o podanych danych już istnieje.");
        } else {
            userService.saveEmployer(user);
            employerService.createEmployer(employer, user);
            modelAndView.addObject("successMessage", "Konto zostało utworzone pomyślnie.");
        }
        modelAndView.addObject("user", new User());
        modelAndView.addObject("employer", new Employer());
        modelAndView.setViewName("/employer/registerEmployer.html");
        return modelAndView;
    }
    //usuwanie pracodawcy z bazy
    @GetMapping(path = "/admin/deleteEmployer/{id}")
    public String deleteEmployer(@PathVariable("id") Integer id){
        Employer employer = employerService.findEmployerByUser_id(id);
        JobOffer jobOffer = jobOfferService.findByEmployerId(employer.getIdEmployer());
        JobProposition jobProp = jobPropositionService.findByEmployerId(employer.getIdEmployer());
        if (jobOffer != null)
            jobOfferService.deleteJobOfferById(jobOffer.getIdJobOffer());
        if (jobProp != null)
            jobPropositionService.deleteJobProposition(jobProp.getIdJobProposition());

        employerService.deleteEmployerById(employer.getIdEmployer());
        userService.deleteUserById(id);
        return "redirect:/admin/employersList";
    }
}
