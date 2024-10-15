package com.liveEvents.liveEvents_backend.controllers;


import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.config.TokenProvider;
import com.liveEvents.liveEvents_backend.dtos.*;
import com.liveEvents.liveEvents_backend.exceptions.EmailAlreadyExistsException;
import com.liveEvents.liveEvents_backend.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ApiConstants.USER_BASE_URL)
public class UserController {



        @Autowired
        UserService userService;

        @Autowired
        TokenProvider tokenProvider;

       // Inscription d'un nouvel utilisateur
        @PostMapping(ApiConstants.REGISTER_URL)
        public ResponseEntity<UserRegisterDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {

            try{
                System.out.println("Received user data: " + userRegisterDto);
                UserRegisterDto savedUserDto =  userService.registerUser(userRegisterDto);
                return ResponseEntity.ok(savedUserDto);
            }  catch ( EmailAlreadyExistsException  e) {
                return ResponseEntity.badRequest().body(new UserRegisterDto("L'email existe déjà"));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de l'enregistrement de l'utilisateur", e);
            }
        }

         // Connexion d'un nouvel utilisateur
        @PostMapping(ApiConstants.LOGIN_URL)
        public ResponseEntity<UserLoginDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {

            try{
                System.out.println("Received user data: " + userLoginDto.getEmail());
                UserLoginDto  createdUserLoginDto=  userService.loginUser(userLoginDto);
                String token=createdUserLoginDto.getToken();
                // créer un cookie pour le token
                Cookie cookie = new Cookie("token", token);
                cookie.setHttpOnly(true); //cookie n'est pas accessible via js, uniquement via des requettes
                cookie.setPath("/"); //accessible sur toutes les pages
                cookie.setMaxAge(86400); //Durée de vie de 1 jour
                response.addCookie(cookie);

                return ResponseEntity.ok(createdUserLoginDto);
            }catch (  BadCredentialsException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserLoginDto("Les données d'authentification sont invalides"));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la connexion de l'utilisateur", e);
            }
        }

        // Vérification de token et d'affichage du compte personnel
        @GetMapping(ApiConstants.PERSONAL_SPACE_URL)
        public ResponseEntity<ResponseDto> getPersonalSpace(){
            // Récupérer l'objet Authentication depuis le contexte de sécurité
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication!=null && authentication.isAuthenticated()){
                ResponseDto responseDto = new ResponseDto();
                responseDto.setMessage("Accès à l'espace personnel est autorisé.");
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDto("Accès à l'espace personnel est non autorisé."));
            }
        }

        //Se déconnecter du compte
        @PostMapping(ApiConstants.LOGOUT_URL)
        public ResponseEntity<ResponseDto> logoutUser(HttpServletResponse response){
            try{
                Cookie cookie = new Cookie("token", null);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                ResponseDto responseDto = new ResponseDto("Déconnexion réussie.");
                return ResponseEntity.ok( responseDto);
            } catch (Exception e) {
                // Gérer les exceptions, si nécessaire
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("Erreur lors de la déconnexion"));
            }
        }


        // Gestion de la demande de contact via le formulaire
        @PostMapping(ApiConstants.CONTACT_FORM_URL)
        public ResponseEntity<ContactFormDto> contactQuestionForm(@RequestHeader("X-Recaptcha-token") String token, @Valid @RequestBody ContactFormDto contactFormDto){
            if(!tokenProvider.validateRecaptcha(token)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            try{
                ContactFormDto contactQuestionFormDto= userService.contactQuestionForm(contactFormDto);
                return ResponseEntity.ok(contactQuestionFormDto);
            }catch (MailSendException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Échec de l'envoi de l'email", e);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenue lors du traitement de la réponse.", e);
            }

        }

    // Gestion de la réponse à la demande de contact via le formulaire
    @PostMapping(ApiConstants.CONTACT_RESPONSE_URL)
    public ResponseEntity<ContactFormDto> contactResponseForm(@Valid @PathVariable Long id, @RequestBody String response){

        try{
            ContactFormDto contactResponseFormDto= userService.contactResponseForm(id, response);
            return ResponseEntity.ok(contactResponseFormDto);
        }catch (MailSendException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Échec de l'envoi de l'email", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenue lors du traitement de la réponse.", e);
        }

    }


    }