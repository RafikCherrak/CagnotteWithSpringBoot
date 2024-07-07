package io.bforbank.cagnotte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CagnotteServiceTest {

    @Test
    void testAddTransaction() {
        CagnotteRepository repository = new CagnotteRepository();
        CagnotteService service = new CagnotteService(repository);

        service.addTransaction("client1", 5);
        Cagnotte cagnotte = repository.findById("client1").orElse(null);

        assertNotNull(cagnotte);
        assertEquals(5, cagnotte.getTotalAmount());
        assertEquals(1, cagnotte.getTransactionCount());

        service.addTransaction("client1", 10);
        cagnotte = repository.findById("client1").orElse(null);

        assertNotNull(cagnotte);
        assertEquals(15, cagnotte.getTotalAmount());
        assertEquals(2, cagnotte.getTransactionCount());
    }

    @Test
    void testIsCagnotteAvailable() {
        CagnotteRepository repository = new CagnotteRepository();
        CagnotteService service = new CagnotteService(repository);

        service.addTransaction("client1", 5);
        service.addTransaction("client1", 5);
        service.addTransaction("client1", 5);

        Cagnotte cagnotte = repository.findById("client1").orElse(null);
        assertNotNull(cagnotte);
        assertTrue(cagnotte.isAvailable());
    }
}
