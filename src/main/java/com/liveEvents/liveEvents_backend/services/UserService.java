package com.liveEvents.liveEvents_backend.services;


import com.liveEvents.liveEvents_backend.config.TokenProvider;
import com.liveEvents.liveEvents_backend.dtos.ContactFormDto;
import com.liveEvents.liveEvents_backend.dtos.UserLoginDto;
import com.liveEvents.liveEvents_backend.dtos.UserRegisterDto;
import com.liveEvents.liveEvents_backend.entities.ContactForm;
import com.liveEvents.liveEvents_backend.entities.Role;
import com.liveEvents.liveEvents_backend.entities.User;
import com.liveEvents.liveEvents_backend.exceptions.EmailAlreadyExistsException;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.ContactFormRepository;
import com.liveEvents.liveEvents_backend.repositories.RoleRepository;
import com.liveEvents.liveEvents_backend.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ContactFormRepository contactFormRepository;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    XssSanitizerService xssSanitizerService;
    @Autowired
    EmailService emailService;



    //Enregistrement de l'utilisateur
    @Transactional
    public UserRegisterDto registerUser(UserRegisterDto userRegisterDto){
        if(userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        //créer une instance de BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUserFirstName(xssSanitizerService.sanitize(userRegisterDto.getUserFirstName()));
        user.setUserLastName(xssSanitizerService.sanitize(userRegisterDto.getUserLastName()));
        user.setEmail(xssSanitizerService.sanitize(userRegisterDto.getEmail()));
        user.setPassword(userRegisterDto.getPassword());

        Role role = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role non trouvé"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        System.out.println("password: " + savedUser.getPassword());
        return new UserRegisterDto(Encode.forHtml(savedUser.getUserFirstName()), Encode.forHtml(savedUser.getUserLastName()));
    }

    //Connexion de l'utilisateur
    public UserLoginDto loginUser(UserLoginDto userLoginDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user= userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email non trouvé"));
        System.out.println("nom: " + user.getUserFirstName());

        String token=tokenProvider.generateToken(userLoginDto.getEmail());
        System.out.println("token: " + token);
        UserLoginDto createdUserLoginDto = new UserLoginDto();
        createdUserLoginDto.setEmail(user.getEmail());
        createdUserLoginDto.setToken(token);
        System.out.println("login user: " + createdUserLoginDto.getEmail());
        return createdUserLoginDto;

    }

    //Demande de contact d'utilisateur

    public ContactFormDto contactQuestionForm(ContactFormDto contactFormDto){
        ContactForm contactForm = new ContactForm();
        contactForm.setUserFirstName(xssSanitizerService.sanitize(contactFormDto.getUserFirstName()));
        contactForm.setUserLastName(xssSanitizerService.sanitize(contactFormDto.getUserLastName()));
        contactForm.setEmail(xssSanitizerService.sanitize(contactFormDto.getEmail()));
        contactForm.setMessage(xssSanitizerService.sanitize(contactFormDto.getMessage()));
        contactForm.setStatus("non répondu");
        contactFormRepository.save(contactForm);
        try {
            emailService.sendConfirmationEmail(contactForm.getEmail(), contactForm.getUserFirstName(), contactForm.getUserLastName(), contactForm.getMessage());
        } catch (MailSendException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Échec de l'envoi de l'email", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenue lors du traitement de la réponse.", e);
        }
        return new ContactFormDto(
                contactForm.getId(),
                contactForm.getUserFirstName(),
                contactForm.getUserLastName(),
                contactForm.getEmail(),
                contactForm.getMessage(),
                contactForm.getStatus(),
                contactForm.getResponse()
        );
    }

        public ContactFormDto contactResponseForm(Long id, String response){

            ContactForm contactForm = contactFormRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Le demande de contact non trouvé avec l'id : " + id));

            contactForm.setResponse(xssSanitizerService.sanitize(response));
            contactForm.setStatus("répondu");
            contactFormRepository.save(contactForm);
            try{
                emailService.sendResponseEmail(contactForm.getEmail(), contactForm.getUserFirstName(),contactForm.getUserLastName(), contactForm.getResponse());
            } catch (MailSendException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Échec de l'envoi de l'email", e);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenue lors du traitement de la réponse.", e);
            }
            return new ContactFormDto(
                contactForm.getId(),
                contactForm.getUserFirstName(),
                contactForm.getUserLastName(),
                contactForm.getEmail(),
                contactForm.getMessage(),
                contactForm.getStatus(),
                contactForm.getResponse()
        );


    }


}