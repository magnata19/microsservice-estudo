package com.davidson.msclient.msclient.application;

import com.davidson.msclient.msclient.application.representation.ClientSaveRequest;
import com.davidson.msclient.msclient.domain.Client;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String getStatus() {
        return "ok!";
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientSaveRequest clientSaveRequest) {
        Client client = clientSaveRequest.toModel();
        clientService.saveClient(client);
        URI headerLocation = ServletUriComponentsBuilder //criando uma url dinâmica
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity getClientByCpf(@RequestParam String cpf) {
        Optional<Client> byCpf = clientService.getByCpf(cpf);
        if(!byCpf.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byCpf);
    }
}
