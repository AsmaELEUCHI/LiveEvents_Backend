package com.liveEvents.liveEvents_backend.services;


import com.liveEvents.liveEvents_backend.dtos.PartnerDto;
import com.liveEvents.liveEvents_backend.entities.Partner;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


//CRUD partenaires

@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    XssSanitizerService xssSanitizerService;

    //Sélectionner tous les partenaires
        public List<PartnerDto> getAllPartners(){
        List<Partner> partners =partnerRepository.findAll();
        return partners.stream()
                .map(partner-> new PartnerDto(
                        partner.getId(),
                        partner.getPartnerName(),
                        partner.getPartnerDescription(),
                        partner.getPartnerImage(),
                        partner.getPartnerLink()

                ))
                .toList();
    }



    //Sélectionner un partenaire par son id
    public PartnerDto getPartnerById(Long id){
            Partner partner=partnerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Partenaire non trouvé avec l'id : " + id));
            return new PartnerDto(
                    partner.getId(),
                    partner.getPartnerName(),
                    partner.getPartnerDescription(),
                    partner.getPartnerImage(),
                    partner.getPartnerLink()
            );

    }


    //créer un  partenaire
    public Partner createPartner(PartnerDto partnerDto){
        if (partnerRepository.existsByPartnerName(partnerDto.getPartnerName())) {
            throw new IllegalArgumentException("Le partenaire existe déjà");
        }
        Partner partner = new Partner();
        partner.setPartnerName(xssSanitizerService.sanitize(partnerDto.getPartnerName()));
        partner.setPartnerImage(xssSanitizerService.sanitize(partnerDto.getPartnerImage()));
        partner.setPartnerDescription(xssSanitizerService.sanitize(partnerDto.getPartnerDescription()));
        partner.setPartnerLink(xssSanitizerService.sanitize(partnerDto.getPartnerLink()));
        return partnerRepository.save(partner);
    }



    //Mettre à jour un partenaire
    public Partner updatePartner(Long id, PartnerDto partnerDto){
        Partner updatedPartner = partnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partenaire non trouvé avec l'id : " + id));
        updatedPartner.setPartnerName(xssSanitizerService.sanitize(partnerDto.getPartnerName()));
        updatedPartner.setPartnerImage(xssSanitizerService.sanitize(partnerDto.getPartnerImage()));
        updatedPartner.setPartnerDescription(xssSanitizerService.sanitize(partnerDto.getPartnerDescription()));
        updatedPartner.setPartnerLink(xssSanitizerService.sanitize(partnerDto.getPartnerLink()));
        return partnerRepository.save(updatedPartner);
    }



    //Supprimer un partenaire par son id
    public void deletePartner(Long id){
            Partner partner = partnerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Partenaire non trouvé avec l'id : " + id));
        partnerRepository.delete(partner);
    }



}
