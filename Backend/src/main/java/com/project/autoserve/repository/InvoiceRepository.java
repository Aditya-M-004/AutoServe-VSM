package com.project.autoserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.autoserve.entity.Invoice;
import com.project.autoserve.entity.JobCard;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByJobCard(JobCard jobCard);

}