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
    public GetKorisnikEmailById getKorisnikEmailById(Jaxb2Marshaller marshaller) {
        GetKorisnikEmailById client = new GetKorisnikEmailById();
        //napravi metodu u mikroservisu
        client.setDefaultUri("http://localhost:8080/car/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public UpdateVozilo updateVoziloClient(Jaxb2Marshaller marshaller) {
        UpdateVozilo client = new UpdateVozilo();
        client.setDefaultUri("http://localhost:8080/car/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public PostChat postChat(Jaxb2Marshaller marshaller) {
        PostChat client = new PostChat();
        client.setDefaultUri("http://localhost:8080/user/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public PostMessage postMessage(Jaxb2Marshaller marshaller) {
        PostMessage client = new PostMessage();
        client.setDefaultUri("http://localhost:8080/user/ws");
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

    @Bean
    public OcenaClient ocenaClient(Jaxb2Marshaller marshaller) {
        OcenaClient client = new OcenaClient();
        client.setDefaultUri("http://localhost:8080/car/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
