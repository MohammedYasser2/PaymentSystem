package com.example.fawry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final TransactionService transactionService;
    private final ServiceProviderService serviceProviderService;
    private final ServiceService serviceService;

    @Autowired
    public AdminService(TransactionService transactionService, ServiceProviderService serviceProviderService, ServiceService serviceService) {
        this.transactionService = transactionService;
        this.serviceProviderService = serviceProviderService;
        this.serviceService = serviceService;
    }

    public List<PaymentTransaction> listPaymentTransactions() {
        return transactionService.paymentTransactions();
    }

    public List<WalletTransaction> listWalletTransactions() {
        return transactionService.walletTransactions();
    }

    public List<RefundTransaction> listRefundTransactions() {
        return transactionService.refundTransactions();
    }

    public void addServiceProvider(ServiceProvider serviceProvider) {
        serviceProviderService.addServiceProvider(serviceProvider);
    }

    public com.example.fawry.Service findServiceById(Long id) {
        return serviceService.findById(id);
    }
}
