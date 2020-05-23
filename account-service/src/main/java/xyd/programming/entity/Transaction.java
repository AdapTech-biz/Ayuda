package xyd.programming.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

@Data
public class Transaction {


    private static final Random random = new Random();
    private Long transactionId;
    private LocalDateTime date;
    private Long choreID;
    private double amount;

    public Transaction(PayoutTicket payoutTicket, boolean isPositive) {
       this.transactionId = random.nextLong();
       this.date = LocalDateTime.now();
       this.choreID = payoutTicket.getChoreID();
       this.amount = isPositive ? payoutTicket.getPayout() : (payoutTicket.getPayout() * -1);

    }



}
