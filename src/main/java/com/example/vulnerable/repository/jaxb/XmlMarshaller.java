package com.example.vulnerable.repository.jaxb;

import com.example.vulnerable.models.BusPassModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class XmlMarshaller {

    @Bean
    @Scope("prototype")
    @PostConstruct
    public static void read() {
        System.out.println("reading");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BusPassModel.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            BusPassModel.setInstance((BusPassModel) unmarshaller.unmarshal(new File("berletek_JAXB.xml")));
            System.out.println(BusPassModel.getInstance().getLines().get(1).getMonthly());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    @Scope("prototype")
    public static void save() {
        try {

            JAXBContext context = JAXBContext.newInstance(BusPassModel.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            BusPassModel model = BusPassModel.getInstance();
            marshaller.marshal(model, new File("berletek_JAXB.xml"));

        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
    }
}
