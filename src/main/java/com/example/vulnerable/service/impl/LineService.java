package com.example.vulnerable.service.impl;

import com.example.vulnerable.models.Line;
import com.example.vulnerable.repository.LineRepository;
import com.example.vulnerable.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService {

    private final LineRepository lineRepository;

    @Autowired
    private Logger logger;

    @Autowired
    public LineService(final LineRepository lineRepository){
        this.lineRepository = lineRepository;
    }

//    public List<Line> findAll(){
//        try {
//            return lineRepository.findAll();
//        } catch (Exception e) {
//            logger.error("Something went wrong. Cannot find datas.", e);
//            throw new ServiceException("Something went wrong while finding users", e);
//        }
//    }

    public List<Line> findAllLines(){
        try {
            return lineRepository.findAllLines();
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find Users.", e);
            throw new ServiceException("Something went wrong while finding users", e);
        }
    }

    public Line findLinesByLinesFromAndLinesTo(String lineFrom, String lineto){
        try {
            return lineRepository.findLinesByLinesFromAndLinesTo(lineFrom, lineto);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find lines.", e);
            throw new ServiceException("Something went wrong while finding lines", e);
        }
    }

    public List<Line> findLinesByPerson(String name){
        try {
            return lineRepository.findLinesByPerson(name);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find person.", e);
            throw new ServiceException("Something went wrong while finding person.", e);
        }
    }

    public Line createOrUpdateLine(Line line) {
        try {
            return lineRepository.createOrUpdateLine(line.getLineFrom(), line.getLineTo(), line.getMonthly(), line.getTwoWeekly(), line.getThreeWeekly());
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot update line.", e);
            throw new ServiceException("Something went wrong while updating line", e);
        }
    }

//    public void deleteLine(String lineFrom, String lineTo) {
//        try {
//            lineRepository.deleteLine(lineFrom, lineTo);
//        } catch (Exception e) {
//            logger.error("Something went wrong. Cannot find Users.", e);
//            throw new ServiceException("Something went wrong while finding users", e);
//        }
//    }
}
