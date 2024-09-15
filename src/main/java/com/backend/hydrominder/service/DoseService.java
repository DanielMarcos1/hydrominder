package com.backend.hydrominder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hydrominder.dto.DoseDTO;
import com.backend.hydrominder.entity.Dose;
import com.backend.hydrominder.repository.DoseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoseService {

    @Autowired
    private DoseRepository doseRepository;

    public List<DoseDTO> getAllDosesDTO() {
        List<Dose> doses = doseRepository.findAll();
        return doses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DoseDTO convertToDTO(Dose dose) {
        DoseDTO dto = new DoseDTO();
        dto.setId(dose.getIdDose());
        dto.setDose(dose.getMls());
        dto.setDate(dose.getDate());
        return dto;
    }

    public Optional<Dose> getDoseById(int id) {
        return doseRepository.findById(id);
    }

    public Dose saveDose(Dose dose) {
        return doseRepository.save(dose);
    }

    public void deleteDose(int id) {
        doseRepository.deleteById(id);
    }

    public Dose updateDose(int id, Dose updatedDose) {
        Optional<Dose> existingDose = doseRepository.findById(id);
        if (existingDose.isPresent()) {
            Dose dose = existingDose.get();
            dose.setMls(updatedDose.getMls());
            dose.setDate(updatedDose.getDate());
            return doseRepository.save(dose);
        }
        return null;
    }

    public List<Dose> getDosesByGoalId(Long goalId) {
        return doseRepository.findByGoalId(goalId);
    }
    
}


