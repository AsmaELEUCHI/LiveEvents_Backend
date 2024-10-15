package com.liveEvents.liveEvents_backend.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;


@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    @Value("${recaptcha.secret.key}")
    private String recaptchaSecretKey;
    @Value("${recaptcha.api.url}")
    private String recaptchaUrl;

    //Générer un token aprés l'authentification


        public String generateToken(String email) {

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + jwtExpiration);
            try {return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
                    .compact();

           }catch (Exception e) {
                System.err.println("Erreur lors de la génération du token: " + e.getMessage());
                e.printStackTrace();
                return null;
    }
        }

    // Récupère l'email à partir du token JWT
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Vérifie la validité du token JWT lors de la connexion
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            System.out.println("Token is valid");
            return true;
        } catch (Exception e) {
            System.out.println("Invalid token: " + e.getMessage());
            return false;
        }
    }

    //Vérifier la validité du token reCaptcha en envoyant une requete à l'API reCaptcha

    public boolean validateRecaptcha(String token) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        // Utiliser les noms de paramètres corrects
        params.add("secret", recaptchaSecretKey);  // Utilisez 'secret'
        params.add("response", token);              // Utilisez 'response'

        try {
            // Envoyer la requête à l'API reCaptcha
            ResponseEntity<Map> response = restTemplate.postForEntity(recaptchaUrl, params, Map.class);

            // Vérifier la réponse et retourner le résultat de la validation
            return response.getBody() != null && (Boolean) response.getBody().get("success");
        } catch (Exception e) {
            // Gérer les erreurs de requête ici
            e.printStackTrace();
            return false;  // Retourner false en cas d'erreur
        }
    }

}