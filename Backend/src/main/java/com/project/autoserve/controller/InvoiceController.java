package com.project.autoserve.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.autoserve.dto.invoice.InvoiceResponseDTO;
import com.project.autoserve.service.InvoiceService;
import com.project.autoserve.util.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/generate/{jobId}")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> generateInvoice(
            @PathVariable Long jobId) {

        InvoiceResponseDTO response = invoiceService.generateInvoice(jobId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<InvoiceResponseDTO>builder()
                        .success(true)
                        .message("Invoice generated successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> getInvoiceById(
            @PathVariable Long invoiceId) {

        InvoiceResponseDTO response = invoiceService.getInvoiceById(invoiceId);

        return ResponseEntity.ok(
                ApiResponse.<InvoiceResponseDTO>builder()
                        .success(true)
                        .message("Invoice fetched successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping("/jobcard/{jobId}")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> getInvoiceByJobCard(
            @PathVariable Long jobId) {

        InvoiceResponseDTO response = invoiceService.getInvoiceByJobCard(jobId);

        return ResponseEntity.ok(
                ApiResponse.<InvoiceResponseDTO>builder()
                        .success(true)
                        .message("Invoice fetched successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InvoiceResponseDTO>>> getAllInvoices() {

        List<InvoiceResponseDTO> response = invoiceService.getAllInvoices();

        return ResponseEntity.ok(
                ApiResponse.<List<InvoiceResponseDTO>>builder()
                        .success(true)
                        .message("Invoices fetched successfully.")
                        .data(response)
                        .build());
    }
}