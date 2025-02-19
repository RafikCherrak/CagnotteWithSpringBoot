package io.bforbank.cagnotte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Optional;

@RestController
@RequestMapping("/cagnotte")
public class CagnotteController {

    @Autowired
    private CagnotteService cagnotteService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCagnotte(@RequestBody @Valid CagnotteTransaction transaction) {
        cagnotteService.addTransaction(transaction.getClientId(), transaction.getAmount());
    }

    @GetMapping("/{clientId}")
    public Cagnotte getCagnotte(@PathVariable String clientId) {
        return cagnotteService.getCagnotte(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
    }

    @GetMapping("/available/{clientId}")
    public boolean isCagnotteAvailable(@PathVariable String clientId) {
        return cagnotteService.getCagnotte(clientId)
                .map(Cagnotte::isAvailable)
                .orElse(false);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClientNotFound(ClientNotFoundException ex) {
        return ex.getMessage();
    }
}

class CagnotteTransaction {
    @NotNull
    private String clientId;
    @Min((long) 0.01)
    private double amount;

    public CagnotteTransaction() {}

    public CagnotteTransaction(String clientId, double amount) {
        this.clientId = clientId;
        this.amount = amount;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
