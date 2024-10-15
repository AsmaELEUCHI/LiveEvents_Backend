package com.liveEvents.liveEvents_backend.services;


import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Service;

//Application d'une politique de nettoyage des formats et des liens
@Service
public class XssSanitizerService {
    private static final PolicyFactory POLICY= Sanitizers.FORMATTING.and(Sanitizers.LINKS);
    public String sanitize(String input) {
        // Utiliser Jsoup pour nettoyer le contenu des balises HTML et des scripts malveillants
        // Le Safelist permet de définir quels éléments et attributs sont autorisés
        return Jsoup.clean(input, "", Safelist.basic(), new org.jsoup.nodes.Document.OutputSettings().prettyPrint(false));
    }
}

