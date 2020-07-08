package com.example.Tim25Xml.controler;

import com.example.Tim25Xml.model.Zahtev;
import com.example.Tim25Xml.service.ZahtevService;
import com.example.Tim25Xml.soap.GetAgentClient;
import com.example.Tim25Xml.soap.GetKorisnikEmailById;
import com.example.Tim25Xml.xsd.GetMailPodnosiocaResponse;
import com.example.Tim25Xml.xsd.GetMailUlogovanogAgentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/zahtev")
public class ZahtevKontroler {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private GetAgentClient getAgentClient;

    @Autowired
    private GetKorisnikEmailById getKorisnikEmailById;

    @GetMapping(value = "/getByIzdavacMail/{mail}")
    public ResponseEntity<?> vratiZahteveIzdavaocaByMail(@PathVariable("mail") String  mail) {

        List<Zahtev> zahtevi = zahtevService.findByEmail(mail);

        if (zahtevi == null) {
            return new ResponseEntity<>("Neuspesno preuzimanje zahteva od mikroservisa!!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/getMejlAgenta")
    public ResponseEntity<?> getMejlUlogovanogAgenta() {

        GetMailUlogovanogAgentaResponse mailUlogovanogAgentaResponse = getAgentClient.getMailUlogovanogAgentaResponse();

        if (mailUlogovanogAgentaResponse == null) {
            return new ResponseEntity<>("Neuspesno preuzimanje mejl ulogovanog agenta!!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mailUlogovanogAgentaResponse.getVraceniMejl(), HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/getByPodnosilacEmail/{idPodnosilac}")
    public String getByPodnosilacEmail(@PathVariable("idPodnosilac") Long  idPodnosilac) {
        GetMailPodnosiocaResponse mail = getKorisnikEmailById.postGetKorisnikEmailById(idPodnosilac);
        return mail.getVraceniMejl();
    }


}
