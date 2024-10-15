package com.liveEvents.liveEvents_backend.controllers;

import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.ArtistDto;
import com.liveEvents.liveEvents_backend.dtos.PartnerDto;
import com.liveEvents.liveEvents_backend.dtos.ResponseDto;
import com.liveEvents.liveEvents_backend.entities.Artist;
import com.liveEvents.liveEvents_backend.entities.Partner;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.services.PartnerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.PARTNERS_BASE_URL)
public class PartnerController {
    @Autowired
    PartnerService partnerService;

    @PostMapping(ApiConstants.PARTNER_CREATE_URL)
    public ResponseEntity<ResponseDto> createPartner(@Valid @RequestBody PartnerDto partnerDto){

       try{
           Partner createdPartner= partnerService.createPartner(partnerDto);
           ResponseDto responseDto = new ResponseDto("Le partenaire est crée avec succés");
           return ResponseEntity.ok( responseDto );
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body(new ResponseDto("Le partenaire existe déjà"));
       } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la création du partenaire", e);
       }
    }



    @GetMapping(ApiConstants.PARTNER_GET_ALL_URL)
    public ResponseEntity <List<PartnerDto>> getAllPartners(){
        try{
            List<PartnerDto> listPartnersDto = partnerService.getAllPartners();
            return ResponseEntity.ok(listPartnersDto);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des partenaires", e);
        }
    }



    @GetMapping(ApiConstants.PARTNER_GET_URL)
    public ResponseEntity<PartnerDto> getPartnerById(@PathVariable Long id){
        try {
            PartnerDto partnerDto = partnerService.getPartnerById(id);
            return ResponseEntity.ok(partnerDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partenaire non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération du partenaire", e);
        }
    }



    @PutMapping(ApiConstants.PARTNER_UPDATE_URL)
    public ResponseEntity <ResponseDto> updatePartner (@Valid @PathVariable Long id, @RequestBody PartnerDto partnerdto){
        try{
            Partner updatedPartner = partnerService.updatePartner(id, partnerdto);
            ResponseDto responseDto = new ResponseDto("Le partenaire est mis à jour avec succés");
            return ResponseEntity.ok(responseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partenaire non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour du partenaire", e);
        }
    }

    @DeleteMapping(ApiConstants.PARTNER_DELETE_URL)
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        try {
            partnerService.deletePartner(id);
            return ResponseEntity.noContent().build(); // Retourne un 204 No Content
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partenaire non trouvé", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du partenaire", e);
        }
    }


}
