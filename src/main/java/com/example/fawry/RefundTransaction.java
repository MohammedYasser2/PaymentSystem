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
public class RefundTransaction extends Transaction {
    @ManyToOne
    private ServiceProvider serviceProvider;
    private Boolean accepted;
}
