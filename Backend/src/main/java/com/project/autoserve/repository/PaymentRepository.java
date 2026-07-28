package com.project.autoserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.autoserve.entity.Invoice;
import com.project.autoserve.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByInvoice(Invoice invoice);

}