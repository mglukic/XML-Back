package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Vozilo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        // ovo koristi ako ce se uvek generisati klase na osnovu wsdl-a
//    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//    // this package must match the package in the <generatePackage> specified in
//    // pom.xml
//    marshaller.setContextPath("com.example.consumingwebservice.soap");
//    return marshaller;

        // ovo koristi ako hoces lepo da vidis izgenerisane klase u package-u
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        String[] packagesToScan = { "com.example.Tim25Xml.xsd" };
        marshaller.setPackagesToScan(packagesToScan);

        return marshaller;
    }

    @Bean
    public VoziloClient voziloClient(Jaxb2Marshaller marshaller) {
        VoziloClient client = new VoziloClient();
        client.setDefaultUri("http://localhost:8080/car/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public ZahtevClient zahtevClient(Jaxb2Marshaller marshaller) {
        ZahtevClient client = new ZahtevClient();
        client.setDefaultUri("http://localhost:8080/car/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public GetAgentClient getAgentClient(Jaxb2Marshaller marshaller) {
        GetAgentClient client = new GetAgentClient();
        client.setDefaultUri("http://localhost:8080/car/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


    @Bean
    public KomentarClient komentarClient(Jaxb2Marshaller marshaller) {
        KomentarClient client = new KomentarClient();
        client.setDefaultUri("http://localhost:8080/car/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
