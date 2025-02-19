package io.bforbank.cagnotte;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CagnotteRepository {

    private Map<String, Cagnotte> cagnottes = new HashMap<>();

    public Optional<Cagnotte> findById(String clientId) {
        return Optional.ofNullable(cagnottes.get(clientId));
    }

    public void save(Cagnotte cagnotte) {
        cagnottes.put(cagnotte.getClientId(), cagnotte);
    }
}
