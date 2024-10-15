package com.liveEvents.liveEvents_backend.controllers;


import com.liveEvents.liveEvents_backend.config.ApiConstants;
import com.liveEvents.liveEvents_backend.dtos.LegalNoticeDto;
import com.liveEvents.liveEvents_backend.dtos.PartnerDto;
import com.liveEvents.liveEvents_backend.dtos.ResponseDto;
import com.liveEvents.liveEvents_backend.entities.LegalNotice;
import com.liveEvents.liveEvents_backend.entities.Partner;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.services.LegalNoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.LEGAL_NOTICE_BASE_URL)
public class LegalNoticeController {

    @Autowired
    LegalNoticeService legalNoticeService;

    @PostMapping(ApiConstants.LEGAL_NOTICE_CREATE_URL)
    public ResponseEntity<ResponseDto> createLegalNotice (@Valid @RequestBody LegalNoticeDto legalNoticeDto) {
        try {
            legalNoticeService.createLegalNotice(legalNoticeDto);
            ResponseDto responseDto = new ResponseDto("La mention légale est crée avec succés");
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto("La mention légale existe déjà"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la création de la mention légale", e);
        }
    }

    @GetMapping(ApiConstants.LEGAL_NOTICE_GET_ALL_URL)
    public ResponseEntity<List<LegalNoticeDto>> getAllLegalNotices () {
        try {
            List<LegalNoticeDto> legalNoticeListDto =legalNoticeService.getAllLegalNotices();

            return ResponseEntity.ok(legalNoticeListDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la récupération des metions légales", e);
        }
    }

    @GetMapping(ApiConstants.LEGAL_NOTICE_GET_URL)
    public ResponseEntity<LegalNoticeDto> getLegalNoticeById(@PathVariable Long id){
        try{
            LegalNoticeDto legalNoticeDto= legalNoticeService.getLegalNoticeById(id);
            return ResponseEntity.ok(legalNoticeDto);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La mention légale n'a pas été trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération de la mention légale", e);
        }
    }

    @PutMapping(ApiConstants.LEGAL_NOTICE_UPDATE_URL)
    public ResponseEntity<ResponseDto> updateLegalNotice(@Valid @PathVariable Long id, @RequestBody LegalNoticeDto legalNoticeDto) {
        try {
            legalNoticeService.updateLegalNotice(id, legalNoticeDto);
            ResponseDto responseDto = new ResponseDto("La mention légale a été mise à jour avec succès");
            return ResponseEntity.ok(responseDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("La mention légale n'a pas été trouvée"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de la mise à jour de la mention légale", e);
        }
    }

    @DeleteMapping(ApiConstants.LEGAL_NOTICE_DELETE_URL)
    public ResponseEntity<Void> deleteLegalNotice(@PathVariable Long id){
        try{
            legalNoticeService.deleteLegalNotice(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La mention légale n'a pas été trouvée", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de la mention légale", e);
        }
    }






}
