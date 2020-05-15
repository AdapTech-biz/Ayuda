package xyd.programming.entity;

import lombok.Data;

@Data
public class PayoutTicket {

    private Long assignor;
    private Long assignee;
    private double payout;

    public PayoutTicket(Long assignor, Long assignee, double payout) {
        this.assignor = assignor;
        this.assignee = assignee;
        this.payout = payout;
    }
}
