package carmenromano.capstone_project.controllers;


import carmenromano.capstone_project.entities.Comune;
import carmenromano.capstone_project.entities.Customer;
import carmenromano.capstone_project.entities.Indirizzo;
import carmenromano.capstone_project.entities.Provincia;
import carmenromano.capstone_project.exceptions.NotFoundException;
import carmenromano.capstone_project.payload.IndirizzoPayload;
import carmenromano.capstone_project.repositories.IndirizzoRepository;
import carmenromano.capstone_project.services.ComuneService;
import carmenromano.capstone_project.services.CustomerService;
import carmenromano.capstone_project.services.IndirizzoService;
import carmenromano.capstone_project.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    @Autowired
    CustomerService customerService;
    @Autowired
    IndirizzoService indirizzoService;
    @PostMapping
    public Customer createIndirizzo(@RequestBody IndirizzoPayload body,
                                    @AuthenticationPrincipal Customer cliente) throws IOException {

        Customer clienteFound = customerService.findById(cliente.getId());
        Indirizzo indirizzo = indirizzoService.save(body, clienteFound.getId()); // Passa l'ID del cliente a IndirizzoService.save()
        return customerService.uploadIndirizzo(indirizzo, clienteFound);
    }


}