package io.bforbank.cagnotte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CagnotteService {

    private final CagnotteRepository cagnotteRepository;

    @Autowired
    public CagnotteService(CagnotteRepository cagnotteRepository) {
        this.cagnotteRepository = cagnotteRepository;
    }

    public void addTransaction(String clientId, double amount) {
        Optional<Cagnotte> optionalCagnotte = cagnotteRepository.findById(clientId);
        Cagnotte cagnotte = optionalCagnotte.orElseGet(() -> new Cagnotte(clientId, 0, 0));
        cagnotte.setTotalAmount(cagnotte.getTotalAmount() + amount);
        cagnotte.setTransactionCount(cagnotte.getTransactionCount() + 1);
        cagnotteRepository.save(cagnotte);
    }

    public Optional<Cagnotte> getCagnotte(String clientId) {
        return cagnotteRepository.findById(clientId);
    }
}
