package Famacy.service;

import Famacy.repository.InvoiceRepository;
import Famacy.repository.InvoiceRepository.Invoice;
import Famacy.repository.InvoiceRepository.Service;

import java.util.List;
import java.util.Optional;

public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService() {
        this.invoiceRepository = new InvoiceRepository();
    }

    public Optional<Invoice> getInvoiceDetails(int patientNo) {
        return invoiceRepository.findInvoiceByPatientNo(patientNo);
    }

    public List<Service> getServicesForInvoice(int patientNo) {
        return invoiceRepository.findServicesByPatientNo(patientNo);
    }
}
