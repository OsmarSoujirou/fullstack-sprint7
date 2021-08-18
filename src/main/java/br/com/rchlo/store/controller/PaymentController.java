package br.com.rchlo.store.controller;

import br.com.rchlo.store.controller.form.PaymentForm;
import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.dto.PaymentDto;
import br.com.rchlo.store.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<PaymentDto> paymentsAll() {
        return paymentRepository.findAll().stream().map(PaymentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> paymentById(@PathVariable Long id) {

        Optional<Payment> payment = paymentRepository.findById(id);

        if (payment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PaymentDto(payment.get()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentDto> createdPayment(@RequestBody @Valid PaymentForm payment, UriComponentsBuilder uriBuilder) {
        Payment newPayment = new Payment(payment.getValue(), payment.getStatus(), payment.getCard());
        paymentRepository.save(newPayment);
        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(newPayment.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaymentDto(newPayment));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id) {

        Optional<Payment> payment = paymentRepository.findById(id);

        if (payment.isEmpty()) return ResponseEntity.notFound().build();

        try {
            payment.get().updatePayment();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new PaymentDto(payment.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> canceledPayment(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isEmpty()) return ResponseEntity.notFound().build();

        try {
            payment.get().canceledPayment();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new PaymentDto(payment.get()));

    }

}
