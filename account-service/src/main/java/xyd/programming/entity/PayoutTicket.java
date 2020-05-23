package xyd.programming.entity;

import lombok.Data;

@Data
public class PayoutTicket {

    private Long assignor;
    private Long assignee;
    private double payout;
    private Long choreID;


    public PayoutTicket(Long assignor, Long assignee, double payout, Long choreID) {
        this.assignor = assignor;
        this.assignee = assignee;
        this.payout = payout;
        this.choreID = choreID;
    }
}
