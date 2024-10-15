package com.liveEvents.liveEvents_backend.services;

import com.liveEvents.liveEvents_backend.dtos.LegalNoticeDto;
import com.liveEvents.liveEvents_backend.entities.LegalNotice;
import com.liveEvents.liveEvents_backend.exceptions.ResourceNotFoundException;
import com.liveEvents.liveEvents_backend.repositories.LegalNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalNoticeService {

    @Autowired
    LegalNoticeRepository legalNoticeRepository;
    @Autowired
    XssSanitizerService xssSanitizerService;

    //Récupérer les mentions légales
    public List<LegalNoticeDto> getAllLegalNotices(){
        List<LegalNotice> legalNoticeList = legalNoticeRepository.findAll();
        return legalNoticeList.stream()
                .map(legalNotice -> new LegalNoticeDto(
                        legalNotice.getId(),
                        legalNotice.getTitle(),
                        legalNotice.getContent(),
                        legalNotice.getCreatedBy()
                ))
                .toList();
    }

    //Récupérer une mention légale avec son id
    public LegalNoticeDto getLegalNoticeById(Long id){
       LegalNotice legalNotice=legalNoticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La mention légale n'a pas été trouvée avec l'id : " + id));
       return convertToDto(legalNotice);

    }

    //Créer une mention légale
    public void createLegalNotice(LegalNoticeDto legalNoticeDto){
        if(legalNoticeRepository.existsByTitle(legalNoticeDto.getTitle())){
            throw new IllegalArgumentException("La mention légale existe déjà");
        }
        LegalNotice legalNotice = new LegalNotice();
        legalNotice.setTitle(xssSanitizerService.sanitize(legalNoticeDto.getTitle()));
        legalNotice.setContent(xssSanitizerService.sanitize(legalNoticeDto.getContent()));
        legalNotice.setCreatedBy(xssSanitizerService.sanitize(legalNoticeDto.getCreatedBy()));
        legalNoticeRepository.save(legalNotice);
    }

    //Mettre à jour une mention légale
    public void updateLegalNotice(Long id, LegalNoticeDto legalNoticeDto){
        LegalNotice legalNotice = legalNoticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La mention légale n'a pas été trouvée avec l'id : " + id));
        legalNotice.setTitle(xssSanitizerService.sanitize(legalNoticeDto.getTitle()));
        legalNotice.setContent(xssSanitizerService.sanitize(legalNoticeDto.getContent()));
        legalNotice.setCreatedBy(xssSanitizerService.sanitize(legalNoticeDto.getCreatedBy()));
        legalNoticeRepository.save(legalNotice);
    }


    //Supprimer une mention légale
    public void deleteLegalNotice(Long id){
        LegalNotice legalNotice = legalNoticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La mention légale n'a pas été trouvée avec l'id : " + id));
        legalNoticeRepository.delete(legalNotice);
    }

    //Convertir une entité à un dto
    private LegalNoticeDto convertToDto(LegalNotice legalNotice) {
        LegalNoticeDto legalNoticeDto = new LegalNoticeDto();
        legalNoticeDto.setId(legalNotice.getId());
        legalNoticeDto.setTitle(legalNotice.getTitle());
        legalNoticeDto.setContent(legalNotice.getContent());
        legalNoticeDto.setCreatedBy(legalNotice.getCreatedBy());
        return legalNoticeDto;

    }

}
