package com.example.vulnerable.service.impl;

import com.example.vulnerable.models.Pass;
import com.example.vulnerable.repository.PassRepository;
import com.example.vulnerable.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassService {

    private final PassRepository passRepository;

    @Autowired
    private Logger logger;

    @Autowired
    public PassService(final PassRepository passRepository){
        this.passRepository = passRepository;
    }

    public List<Pass> findPassesByPerson(String person){
        try {
            return passRepository.findPassesByPerson(person);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find passes.", e);
            throw new ServiceException("Something went wrong while finding passes", e);
        }
    }

    public List<Pass> findPersonPassesByLine(String person, String lineFrom, String lineTo){
        try {
            return passRepository.findPersonPassesByLine(person, lineFrom, lineTo);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find passes.", e);
            throw new ServiceException("Something went wrong while finding passes", e);
        }
    }

    public Pass findPassBySerialNumber(String person, String pass) {
        try {
            return passRepository.findPassBySerialNumber(person, pass);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find pass.", e);
            throw new ServiceException("Something went wrong while finding pass", e);
        }
    }

    public Pass createPass(String person, String lineFrom, String lineTo, Pass pass) {
        try {
            return passRepository.createPass(person, lineFrom, lineTo, pass.getStartDate(), pass.getEndDate(), pass.getPassTemplate());
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot create pass.", e);
            throw new ServiceException("Something went wrong while creating pass", e);
        }
    }

    public Pass createOrUpdatePass(String person, String serialNumber, Pass pass) {
        try {
            System.out.println(pass.getSerialNumber());
            System.out.println(person);
            return passRepository.createOrUpdatePass(person, serialNumber, pass.getStartDate(), pass.getEndDate(), pass.getPrice(), pass.getPassTemplate());
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot update pass.", e);
            throw new ServiceException("Something went wrong while updating pass", e);
        }
    }
}
