package com.project.autoserve.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.autoserve.dto.payment.PaymentRequestDTO;
import com.project.autoserve.dto.payment.PaymentResponseDTO;
import com.project.autoserve.entity.Invoice;
import com.project.autoserve.entity.Payment;
import com.project.autoserve.enums.InvoiceStatus;
import com.project.autoserve.enums.PaymentStatus;
import com.project.autoserve.exception.ResourceAlreadyExistsException;
import com.project.autoserve.exception.ResourceNotFoundException;
import com.project.autoserve.repository.InvoiceRepository;
import com.project.autoserve.repository.PaymentRepository;
import com.project.autoserve.service.PaymentService;
import com.project.autoserve.util.MapperUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public PaymentResponseDTO makePayment(PaymentRequestDTO request) {

        Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found with ID : "
                                + request.getInvoiceId()));

        if (paymentRepository.existsByInvoice(invoice)) {
            throw new ResourceAlreadyExistsException(
                    "Payment already exists for this invoice.");
        }

        Payment payment = Payment.builder()
                .invoice(invoice)
                .amount(invoice.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .transactionId(request.getTransactionId())
                .paymentStatus(PaymentStatus.SUCCESS)
                .paymentDate(LocalDateTime.now())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        invoice.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(invoice);

        return MapperUtil.toPaymentResponse(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDTO getPaymentById(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found with ID : " + paymentId));

        return MapperUtil.toPaymentResponse(payment);
    }
    
    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDTO getPaymentByInvoice(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found with ID : " + invoiceId));

        Payment payment = paymentRepository.findByInvoice(invoice)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found for Invoice ID : " + invoiceId));

        return MapperUtil.toPaymentResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(MapperUtil::toPaymentResponse)
                .toList();
    }

}