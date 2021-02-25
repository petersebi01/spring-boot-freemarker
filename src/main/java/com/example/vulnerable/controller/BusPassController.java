package com.example.vulnerable.controller;

import com.example.vulnerable.controller.assembler.Assembler;
import com.example.vulnerable.controller.assembler.LineAssembler;
import com.example.vulnerable.controller.assembler.PassAssembler;
import com.example.vulnerable.controller.assembler.PersonAssembler;
import com.example.vulnerable.controller.exceptions.ApiException;
import com.example.vulnerable.dto.LineDto;
import com.example.vulnerable.dto.PassDto;
import com.example.vulnerable.dto.PersonDto;
import com.example.vulnerable.models.Line;
import com.example.vulnerable.models.Pass;
import com.example.vulnerable.models.Person;
import com.example.vulnerable.service.exceptions.ServiceException;
import com.example.vulnerable.service.impl.LineService;
import com.example.vulnerable.service.impl.PassService;
import com.example.vulnerable.service.impl.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/buspass")
public class BusPassController {

    private final LineService lineService;
    private final PassService passService;
    private final PersonService personService;

    private final Assembler<Line, LineDto> lineAssembler;
    private final Assembler<Pass, PassDto> passAssembler;
    private final Assembler<Person, PersonDto> personAssembler;

    @Autowired
    private Logger logger;

    @Autowired
    public BusPassController(final LineService lineService, final PassService passService, final PersonService personService,
                             final LineAssembler lineAssembler, final PassAssembler passAssembler, final PersonAssembler personAssembler) {
        this.lineService = lineService;
        this.passService = passService;
        this.personService = personService;
        this.lineAssembler = lineAssembler;
        this.passAssembler = passAssembler;
        this.personAssembler = personAssembler;
    }

    @GetMapping("/lines")
    public String findAllLines(Model model){
        try {
            logger.info("Getting all lines.");

            List<LineDto> lines = lineService.findAllLines().stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines", lines);
            return "index";

        } catch (ServiceException e) {
            logger.error("Cannot find users.");
            throw new ApiException("Cannot find users.");
        }
    }

    @GetMapping("/lines/{lineFrom}/{lineTo}")
    public String findLinesByLinesFromAndLinesTo(Model model, @PathVariable final String lineFrom, @PathVariable final String lineTo){
        try {
            logger.info("Getting lines.");
            Line line = lineService.findLinesByLinesFromAndLinesTo(lineFrom, lineTo);
            LineDto lineDetails = lineAssembler.modelToDto(line);
//            Person person = personService.login();
//            PersonDto login = personService.modelToDto();
            model.addAttribute("lineData", lineDetails);
//            model.addAttribute("loginData", login);
            return "lineDetails";

        } catch (ServiceException e) {
            logger.error("Cannot find lines.");
            throw new ApiException("Cannot find lines.");
        }
    }

    @GetMapping("/people/{person}/lines")
    public String findLinesByPerson(Model model, @PathVariable final String person){
        try {
            logger.info("Getting all lines by person.");
            List<LineDto> lines = lineService.findLinesByPerson(person).stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            Person findPerson = personService.findPersonByName(person);
            PersonDto personDto = personAssembler.modelToDto(findPerson);
            model.addAttribute("personData", personDto);
            model.addAttribute("lines_person", lines);
            return "index";

        } catch (ServiceException e) {
            logger.error("Cannot find person.");
            throw new ApiException("Cannot find person.");
        }
    }

    @GetMapping("/lines/{lineFrom}/{lineTp}/people/{person}")
    public String findPersonByName(Model model, @PathVariable final String lineFrom, @PathVariable final String lineTo,
                                   @PathVariable final String person){
        try {
            Person findPerson = personService.findPersonByName(person);
            PersonDto personDto = personAssembler.modelToDto(findPerson);
            model.addAttribute("personData", personDto);
            return "index";

        } catch (final ServiceException e) {
            logger.error("Cannot fetch person: " + person);
            throw new ApiException("Cannot fetch person: " + person);
        }
    }

    @GetMapping("/people/{person}/passes")
    public String findPassesByPerson(Model model, @PathVariable final String person){
        try {
            List<PassDto> passDto = passService.findPassesByPerson(person).stream().map(passAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("personPasses", passDto);

            Person findPerson = personService.findPersonByName(person);
            PersonDto personDto = personAssembler.modelToDto(findPerson);
            model.addAttribute("personData", personDto);

            List<LineDto> lines = lineService.findLinesByPerson(findPerson.getName()).stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines_person", lines);
            List<LineDto> allLines = lineService.findAllLines().stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines", allLines);

            return "index";

        } catch (final ServiceException e) {
            logger.error("Cannot fetch " + person + " passes.");
            throw new ApiException("Cannot fetch " + person + " passes.");
        }
    }

    @GetMapping("/people/{person}/{lineFrom}/{lineTo}/passes")
    public ResponseEntity<List<PassDto>> findPersonPassesByLine(@PathVariable final String person, @PathVariable final String lineFrom, @PathVariable final String lineTo) {
        try {
            return ResponseEntity.ok(passService.findPersonPassesByLine(person, lineFrom, lineTo).stream().map(passAssembler::modelToDto).collect(Collectors.toList()));
        } catch (final ServiceException e) {
            logger.error("Cannot fetch " + person + " passes.");
            throw new ApiException("Cannot fetch " + person + " passes.");
        }
    }

    @GetMapping("/people/{person}/{lineFrom}/{lineTo}/{pass}")
    public String findPassBySerialNumber(Model model, @PathVariable final String lineFrom, @PathVariable final String lineTo, @PathVariable final String person, @PathVariable final String pass) {
        try {
            Pass findPass = passService.findPassBySerialNumber(person, pass);
            PassDto passDto = passAssembler.modelToDto(findPass);
            model.addAttribute("passData", passDto);

            Person findPerson = personService.findPersonByName(person);
            PersonDto personDto = personAssembler.modelToDto(findPerson);
            model.addAttribute("personData", personDto);
            model.addAttribute("lineFrom", lineFrom);
            model.addAttribute("lineTo", lineTo);

            return "passDetails";

        } catch (final ServiceException e) {
            logger.error("Cannot fetch person's pass " + pass);
            throw new ApiException("Cannot fetch person's pass " + pass);
        }
    }

    @PostMapping("/people/{person}/{lineFrom}/{lineTo}")
    public String createPass(Model model, @PathVariable final String person, @PathVariable final String lineFrom, @PathVariable final String lineTo, @ModelAttribute(value = "passToSave") final PassDto passToSave) {
        try{
            logger.info("Saving pass in resource");
            passToSave.setStartDate(passToSave.getStartDate().replaceAll("\\W", "")); // változás az előbbi kódhoz,kiszűrjük a speciális karaktereket
            passToSave.setEndDate(passToSave.getEndDate().replaceAll("\\W", "")); // változás az előbbi kódhoz,kiszűrjük a speciális karaktereket
            Pass savedPass = passService.createPass(person, lineFrom, lineTo, passAssembler.dtoToModel(passToSave));
            PassDto passDto = passAssembler.modelToDto(savedPass);

            Person findPerson = personService.findPersonByName(person);
            PersonDto personDto = personAssembler.modelToDto(findPerson);

            model.addAttribute("passData", passDto);
            model.addAttribute("personData", personDto);
            model.addAttribute("lineFrom", lineFrom);
            model.addAttribute("lineTo", lineTo);

            return "passDetails";

        } catch (final ServiceException e) {
            logger.error("Cannot update pass");
            throw new ApiException("Cannot update pass  ", e);
        }
    }

    @PostMapping("/people/{localperson}/{lineFrom}/{lineTo}/{pass}")
    public String createOrUpdatePass(Model model, @PathVariable final String localperson, @PathVariable final String lineFrom, @PathVariable final String lineTo, @PathVariable final String pass, @ModelAttribute(value = "passToUpdate") final PassDto passToUpdate) {
        try{
            logger.info("Saving pass in resource");
            passToUpdate.setStartDate(passToUpdate.getStartDate().replaceAll("\\W", "")); // változás az előbbi kódhoz,kiszűrjük a speciális karaktereket
            passToUpdate.setEndDate(passToUpdate.getEndDate().replaceAll("\\W", "")); // változás az előbbi kódhoz,kiszűrjük a speciális karaktereket
            Pass savedPass = passService.createOrUpdatePass(localperson, pass, passAssembler.dtoToModel(passToUpdate));
            PassDto passDto = passAssembler.modelToDto(savedPass);

            Person findPerson = personService.findPersonByName(localperson);
            PersonDto personDto = personAssembler.modelToDto(findPerson);

            model.addAttribute("passData", passDto);
            model.addAttribute("personData", personDto);
            model.addAttribute("lineFrom", lineFrom);
            model.addAttribute("lineTo", lineTo);

            return "passDetails";
        } catch (final ServiceException e) {
            logger.error("Cannot update pass");
            throw new ApiException("Cannot update pass  ", e);
        }
    }

    @PostMapping("/people/{lineFrom}/{lineTo}")
    public String login(Model model, @PathVariable final String lineFrom, @PathVariable final String lineTo, @ModelAttribute("personLogin") final PersonDto personLogin) {
        try{
            logger.info("Saving pass in resource");
            System.out.println(personLogin);
            personLogin.setName(personLogin.getName().replaceAll("\\W", "")); // változás az előbbi kódhoz,kiszűrjük a speciális karaktereket
            final Person loginPerson = personService.login(lineFrom, lineTo, personAssembler.dtoToModel(personLogin));
            PersonDto personDto = personAssembler.modelToDto(loginPerson);
            model.addAttribute("personData", personDto);

            List<LineDto> lines = lineService.findLinesByPerson(loginPerson.getName()).stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines_person", lines);
            List<LineDto> allLines = lineService.findAllLines().stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines", allLines);

            model.addAttribute("lineFrom", lineFrom);
            model.addAttribute("lineTo", lineTo);

            return "personDetails";

        } catch (final ServiceException e) {
            logger.error("Cannot save user");
            throw new ApiException("Cannot save user", e);
        }
    }

    @GetMapping("/lines/{lineFrom}/{lineTo}/{person}")
    public String findPerson(Model model, @PathVariable final String lineFrom, @PathVariable final String lineTo, @PathVariable final String person) {
        try{
            logger.info("Saving person in resource");
            Person findPerson = personService.findPersonByNameByLine(lineFrom, lineTo, person);
            PersonDto personDto = personAssembler.modelToDto(findPerson);
            model.addAttribute("personData", personDto);

            List<LineDto> lines = lineService.findLinesByPerson(person).stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines_person", lines);
            List<LineDto> allLines = lineService.findAllLines().stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines", allLines);

            model.addAttribute("lineFrom", lineFrom);
            model.addAttribute("lineTo", lineTo);

        } catch (final ServiceException e) {
            logger.error("Cannot update person");
            throw new ApiException("Cannot update person", e);
        }

        return "personDetails";
    }

    @PostMapping("/lines/{lineFrom}/{lineTo}/{person}")
    public String createOrUpdatePerson(Model model, @PathVariable final String lineFrom, @PathVariable final String lineTo,
                                                          @PathVariable final String person, @ModelAttribute("personToSave") final PersonDto personToSave) {
        try{
            logger.info("Saving person in resource");
            personToSave.setName(personToSave.getName().replaceAll("\\W", "")); // változás az előbbi kódhoz,kiszűrjük a speciális karaktereket
            System.out.println(personToSave.getFirm());
            personToSave.setFirm(personToSave.getFirm().replaceAll("\\W", "")); // változás az előbbi kódhoz,kiszűrjük a speciális karaktereket


            System.out.println(personToSave.getFirm());
            final Person updatePerson = personService.createOrUpdatePerson(personAssembler.dtoToModel(personToSave));
            PersonDto personDto = personAssembler.modelToDto(updatePerson);
            model.addAttribute("personData", personDto);

            List<LineDto> lines = lineService.findLinesByPerson(person).stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines_person", lines);
            List<LineDto> allLines = lineService.findAllLines().stream().map(lineAssembler::modelToDto).collect(Collectors.toList());
            model.addAttribute("lines", allLines);

            model.addAttribute("lineFrom", lineFrom);
            model.addAttribute("lineTo", lineTo);

        } catch (final ServiceException e) {
            logger.error("Cannot update person");
            throw new ApiException("Cannot update person", e);
        }

        return "personDetails";
    }
}
