package com.davidson.msclient.msclient.application;

import com.davidson.msclient.msclient.domain.Client;
import com.davidson.msclient.msclient.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client saveClient (Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }
}
