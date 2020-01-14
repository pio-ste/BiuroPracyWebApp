package com.biuropracy.demo.controller;

import com.biuropracy.demo.DTO.EmployerUserDTO;
import com.biuropracy.demo.model.Employer;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.model.UserInformation;
import com.biuropracy.demo.repository.EmployerRepository;
import com.biuropracy.demo.service.EmployerService;
import com.biuropracy.demo.service.UserService;
import javafx.geometry.Pos;
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

    @Autowired
    UserService userService;

    @Autowired
    EmployerService employerService;

    @Autowired
    EmployerRepository employerRepository;

    @GetMapping(path = "/all/employersList")
    public String employersListAll(Model model, String companyName){
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerFiltered(companyName);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/employersListAll";
    }

    @GetMapping(path = "/all/selectedEmployerProfile/{id}")
    public String selectedEmployerProfileAll(Model model, @PathVariable("id") Integer id) {
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerUserByIdEmpl(id);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/selectedEmployerAll";
    }

    @PostMapping(path = "/employer/uploadCompanyImage/{id}")
    public String uploadCompanyImage(@PathVariable("id") Integer id, @RequestParam("imageFileEmployer")MultipartFile file){
        employerService.saveCompanyImgImage(id,file);
        return "redirect:/employer/myProfileEmployer";
    }

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
        if (employerList.isEmpty()){
            model.addAttribute("employerList", employerList);
        } else {
            model.addAttribute("users", userList);
            model.addAttribute("employers", employerList);
            model.addAttribute("employerImg", employerImg);
            model.addAttribute("userImg", userImg);
        }
        return "/employer/myProfileEmployer";
    }

    @PostMapping(path = "/employer/{id}/uploadImage")
    public String uploadImage(@PathVariable("id") Integer id, @RequestParam("imagefile") MultipartFile file) {
        userService.saveProfileImage(id,file);
        return "redirect:/employer/myProfileEmployer";
    }

    @GetMapping(path = "/user/selectedEmployerProfile/{id}")
    public String selectedEmployerProfile(Model model, @PathVariable("id") Integer id) {
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerUserByIdEmpl(id);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/selectedEmployer";
    }

    @GetMapping(path = "/user/employersList")
    public String employersList(Model model, String companyName){
        List<EmployerUserDTO> employerUserDTOList = employerRepository.getEmployerFiltered(companyName);
        model.addAttribute("employers", employerUserDTOList);
        return "/employer/employerListLoginUser";
    }

    @GetMapping(path = "/employer/addEmployerInfo")
    public String addEmployerInfo(Model model){
        model.addAttribute("employer", new Employer());
        return "/employer/addEmployerInfo";
    }

    @PostMapping(path = "/employer/addEmployerInfoPost")
    public ModelAndView addEmployerInfoPost(@Valid Employer employer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            employerService.createEmployer(employer, userService.findUserByEmail(authentication.getName()));
            modelAndView.addObject("successMessage", "Informacje zostały pomyślnie dodane.");
        }
        modelAndView.addObject("jobOffer", new Employer());
        modelAndView.setViewName("/employer/addEmployerInfo.html");
        return modelAndView;
    }

    @GetMapping(path = "/employer/editEmployerInfo/{id}")
    public String editEmployerInfo(@PathVariable("id") Integer id, Model model){
        Employer employer = employerService.findEmployer(id);
        model.addAttribute("employer", employer);
        return "/employer/editEmployerInfo";
    }

    @PostMapping(path = "/employer/updateEmployerInfoPost")
    public ModelAndView updateEmployerInfo(@Valid Employer employer, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Popraw błędy w formularzu");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            employerService.updateEmployer(employer);
            modelAndView.addObject("successMessage", "Ogłoszenie zostało zaktualizowane");
        }
        modelAndView.setViewName("/employer/editEmployerInfo");
        return modelAndView;
    }
}
