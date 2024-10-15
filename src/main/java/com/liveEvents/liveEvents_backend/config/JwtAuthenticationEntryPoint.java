package com.liveEvents.liveEvents_backend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

//permet de spécifier un point d'entrée d'authentification
// si une requête non authentifiée tente d'accéder à une ressource qui nécessite une authentification, l'exception sera capturée, et le jwtAuthenticationEntryPoint
//Envoie d' une erreur 401 (Unauthorized), indiquant que l'utilisateur doit s'authentifier.
@Component
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Non autorisé : Veuillez vous connecter pour accéder à cette ressource.");
    }
}
