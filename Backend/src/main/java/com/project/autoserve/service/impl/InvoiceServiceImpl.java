package com.project.autoserve.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.autoserve.dto.invoice.InvoiceResponseDTO;
import com.project.autoserve.entity.Invoice;
import com.project.autoserve.entity.JobCard;
import com.project.autoserve.entity.JobCardPart;
import com.project.autoserve.enums.InvoiceStatus;
import com.project.autoserve.exception.ResourceAlreadyExistsException;
import com.project.autoserve.exception.ResourceNotFoundException;
import com.project.autoserve.repository.InvoiceRepository;
import com.project.autoserve.repository.JobCardPartRepository;
import com.project.autoserve.repository.JobCardRepository;
import com.project.autoserve.service.InvoiceService;
import com.project.autoserve.util.MapperUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	private static final BigDecimal GST_PERCENTAGE = BigDecimal.valueOf(18);
    private final InvoiceRepository invoiceRepository;
    private final JobCardRepository jobCardRepository;
    private final JobCardPartRepository jobCardPartRepository;

    @Override
    public InvoiceResponseDTO generateInvoice(Long jobId) {

        JobCard jobCard = jobCardRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Card not found with ID : " + jobId));

        if (invoiceRepository.existsByJobCard(jobCard)) {
            throw new ResourceAlreadyExistsException("Invoice already generated for this Job Card.");
        }

        List<JobCardPart> parts = jobCardPartRepository.findByJobCard(jobCard);

        BigDecimal partsTotal = parts.stream()
                .map(JobCardPart::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal laborCost = jobCard.getLaborCost();

        if (laborCost == null) {
            laborCost = BigDecimal.ZERO;
        }

        BigDecimal subTotal = partsTotal.add(laborCost);

        BigDecimal gstPercentage = GST_PERCENTAGE;

        BigDecimal gstAmount = subTotal
                .multiply(gstPercentage)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal totalAmount = subTotal.add(gstAmount);

        Invoice invoice = Invoice.builder()
                .jobCard(jobCard)
                .partsTotal(partsTotal)
                .laborCost(laborCost)
                .subTotal(subTotal)
                .gstPercentage(gstPercentage)
                .gstAmount(gstAmount)
                .totalAmount(totalAmount)
                .invoiceDate(LocalDate.now())
                .status(InvoiceStatus.GENERATED)
                .build();

        invoiceRepository.save(invoice);

        return MapperUtil.toInvoiceResponse(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponseDTO getInvoiceById(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found with ID : " + invoiceId));

        return MapperUtil.toInvoiceResponse(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponseDTO getInvoiceByJobCard(Long jobId) {

        JobCard jobCard = jobCardRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Card not found with ID : " + jobId));

        Invoice invoice = invoiceRepository.findByJobCard(jobCard)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found for Job Card : " + jobId));

        return MapperUtil.toInvoiceResponse(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponseDTO> getAllInvoices() {

        return invoiceRepository.findAll()
                .stream()
                .map(MapperUtil::toInvoiceResponse)
                .toList();
    }

}