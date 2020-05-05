package xyd.programming.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long transactionId;
    private LocalDateTime date;
    private String title;
    private double amount;

    public Transaction(Long transactionId, LocalDateTime date, String title, double amount) {
        this.transactionId = transactionId;
        this.date = date;
        this.title = title;
        this.amount = amount;
    }


}
