package ir.maktab.dto;

import ir.maktab.data.enums.UserRole;
import ir.maktab.data.enums.UserSituation;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto extends UserDto {
    private List<OrderDto> orders = new ArrayList<>();
    private List<CommentDto> comments = new ArrayList<>();

    public CustomerDto() {
        super.setCredit(0.0);
        super.setRole(UserRole.Customer);
        super.setSituation(UserSituation.New);
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public CustomerDto setOrders(List<OrderDto> orders) {
        this.orders = orders;
        return this;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public CustomerDto setComments(List<CommentDto> comments) {
        this.comments = comments;
        return this;
    }

}
