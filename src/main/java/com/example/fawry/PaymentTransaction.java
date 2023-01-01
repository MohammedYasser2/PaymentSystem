package com.example.fawry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class PaymentTransaction extends Transaction {
    @ManyToOne
    private ServiceProvider serviceProvider;

    public PaymentTransaction(User user, Double amount, ServiceProvider serviceProvider) {
        super(user, amount);
        this.serviceProvider = serviceProvider;
    }
}
