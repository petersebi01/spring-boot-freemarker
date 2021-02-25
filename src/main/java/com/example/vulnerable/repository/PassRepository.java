package com.example.vulnerable.repository;

import com.example.vulnerable.models.BusPassModel;
import com.example.vulnerable.models.Line;
import com.example.vulnerable.models.Pass;
import com.example.vulnerable.models.Person;
import com.example.vulnerable.repository.jaxb.XmlMarshaller;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PassRepository {

    public List<Pass> findPassesByPerson(String name){

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        ArrayList<Pass> passes = new ArrayList<>();

        for (Line line : lines){
            for (Person person : line.getPeople()){
                if (person.getName().equals(name)) {
                    passes.addAll(person.getPasses());
                }
            }
        }
        return passes;
    }

    public List<Pass> findPersonPassesByLine(String name, String lineFrom, String lineTo) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        ArrayList<Pass> passes = new ArrayList<>();

        for (Line line : lines){
            if (line.getLineFrom().equals(lineFrom) && line.getLineTo().equals(lineTo)) {

                for (Person person : line.getPeople()) {
                    if (person.getName().equals(name)) {
                        passes.addAll(person.getPasses());

                        break;
                    }
                }
            }
        }
        return passes;
    }

    public Pass findPassBySerialNumber(String name, String serialNumber) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        Pass pass = null;

        for (Line line : lines) {
            for (Person person : line.getPeople()) {
                if (person.getName().equals(name)) {
                    for (Pass p : person.getPasses()) {
                        if (p.getSerialNumber().equals(serialNumber)) {
                            pass = p;
                        }
                    }
                }
            }
        }
        return pass;
    }

    public Pass createPass(String name, String lineFrom, String lineTo, String startDate, String endDate, int passTemplate) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());

        Pass pass = new Pass();
        pass.setSerialNumber(readSerialNumber());
        pass.setStartDate(startDate);
        pass.setEndDate(endDate);
        pass.setPrice(getPrice(lineFrom, lineTo));
        pass.setPassTemplate(passTemplate);

        for (Line line : lines) {
            for (Person person : line.getPeople()) {
                if (person.getName().equals(name)) {

                    pass.setPerson(person);
                    pass.setLine(line);
                    person.addPasses(pass);

                    break;
                }
            }
        }

        XmlMarshaller.save();
        return pass;
    }

    public Pass createOrUpdatePass(String name, String serialNumber, String startDate, String endDate, int price, int passTemplate) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());

        Pass pass = null;

        for (Line line : lines) {
            for (Person person : line.getPeople()) {
                if (person.getName().equals(name)) {
                    for (Pass p : person.getPasses()) {
                        if (p.getSerialNumber().equals(serialNumber)) {

                            p.setSerialNumber(serialNumber);
                            p.setStartDate(startDate);
                            p.setEndDate(endDate);
                            p.setPrice(price);
                            p.setPassTemplate(passTemplate);
                            p.setPerson(person);
                            p.setLine(line);

                            pass = p;

                            XmlMarshaller.save();
                            break;
                        }
                    }
                }
            }
        }

//        if (pass == null) {
//
//            pass = createPass(name, serialNumber, startDate, endDate);
//        }
        return pass;
    }

    private int getPrice(String lineFrom, String lineTo) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        int price = 0;
        Line line = null;

        for (Line value : lines) {
            if (lineFrom.equals(value.getLineFrom()) && lineTo.equals(value.getLineTo())) {
                line = value;
            }
        }

        if (line != null)
            price = line.getMonthly();

        return price;
    }

    private String readSerialNumber() {

        String serialNumber;

        serialNumber = readFile();
        serialNumber = incrementSerialNumber(serialNumber);

        writeFile(serialNumber);

        return serialNumber;
    }

    private String readFile(){

        String sorszam = null;

        try {

            FileReader fileReader = new FileReader(new File("sorszam.txt"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();

            while((sorszam = bufferedReader.readLine()) != null){

                stringBuilder.append(sorszam);
                System.out.println(sorszam);
            }

            sorszam = "000000";
            System.out.println("null sorok szama: " + bufferedReader.readLine());

            sorszam = stringBuilder.toString();
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("A fájl nem létezik");

            sorszam = "000000";

        } catch (IOException eio) {
            System.out.println("Fajl megnyitasa sikeretelen");
            eio.printStackTrace();
        }

        return sorszam;
    }

    private String incrementSerialNumber(String sorszam){

        int szam = Integer.parseInt(sorszam);
        szam++;
        sorszam = String.format("%06d", szam);

        return sorszam;
    }

    private void writeFile(String sorszam){

        try {

            System.out.println("1. Mentve: " + sorszam);
            FileWriter fileWriter = new FileWriter("sorszam.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(sorszam);

            System.out.println("2. Mentve: " + sorszam);
            bufferedWriter.close();

        } catch (IOException e1){
            System.out.println("A fajl nem mentheto");
        }
    }
}
