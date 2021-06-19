package ir.maktab.data.repository;

import ir.maktab.data.domain.Customer;
import ir.maktab.data.domain.Expert;
import ir.maktab.data.domain.Offers;
import ir.maktab.data.domain.Orders;
import ir.maktab.dto.OfferHistoryDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public interface OfferSpecification {

    static Specification<Offers> filterOffers(OfferHistoryDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<Offers> query = criteriaBuilder.createQuery(Offers.class);
            List<Predicate> predicateList = new ArrayList<>();
            Join<Offers, Expert> expertJoin = root.join("expert");
            Join<Offers, Orders> ordersJoin = root.join("orders");
            Join<Orders, Customer> customerJoin = ordersJoin.join("customer");
            if (dto.getSituation() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("offerSituation"), dto.getSituation()));
            }
            if (dto.getExpertEmail() != null) {
                predicateList.add(criteriaBuilder.equal(expertJoin.get("email"), dto.getExpertEmail()));
            }
            if (dto.getCustomerEmail() != null) {
                predicateList.add(criteriaBuilder.equal(customerJoin.get("email"), dto.getCustomerEmail()));
            }
            if (dto.getEndDate() != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("startTime"), dto.getEndDate()));
            }
            if (dto.getStartDate() != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), dto.getStartDate()));
            }
            if (dto.getMaxOfferPrice() != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("offerPrice"), dto.getMaxOfferPrice()));
            }
            if (dto.getMiOfferPrice() != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("offerPrice"), dto.getMiOfferPrice()));
            }
            query.select(root).where(predicateList.toArray(new Predicate[0]));
            return query.getRestriction();

        };
    }

}
