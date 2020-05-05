package xyd.programming.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Account {
    private Long accountId;
    private Long ownerId;
    private double balance;
    private Set<Transaction> transactions;

    public Account(Long owner) {
        this.ownerId = owner;
        this.balance = 0.0;
        this.transactions = new HashSet<>();
    }
}