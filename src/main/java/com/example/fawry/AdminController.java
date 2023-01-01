package com.example.fawry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/paymentTransactions")
    @ResponseBody
    public List<PaymentTransaction> listPaymentTransactions() {
        return adminService.listPaymentTransactions();
    }

    @GetMapping("/walletTransactions")
    @ResponseBody
    public List<WalletTransaction> listWalletTransactions() {
        return adminService.listWalletTransactions();
    }

    @GetMapping("/refundTransactions")
    @ResponseBody
    public List<RefundTransaction> listRefundTransactions() {
        return adminService.listRefundTransactions();
    }

    @PostMapping("/addServiceProvider")
    @ResponseBody
    public String addServiceProvider(@RequestBody ServiceProviderDTO serviceProvider) {
        adminService.addServiceProvider(new ServiceProvider(serviceProvider.getName(), adminService.findServiceById(serviceProvider.getServiceId()), serviceProvider.getSupportsCashOnDelivery(), serviceProvider.getFormAttributes()));
        return String.format("Service provider %s added successfully!", serviceProvider.getName());
    }
}
