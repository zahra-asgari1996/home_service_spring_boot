package ir.maktab.data.repository;

import ir.maktab.data.domain.Orders;
import ir.maktab.data.domain.Service;
import ir.maktab.data.domain.SubService;
import ir.maktab.dto.OrderHistoryFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface OrderSpecification {

    static Specification<Orders> filterOrders(OrderHistoryFilterDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<Orders> query = criteriaBuilder.createQuery(Orders.class);
            List<Predicate> predicates = new ArrayList<>();
            Join<Orders, SubService> subServiceJoin = root.join("subService");
            Join<SubService, Service> serviceJoin = subServiceJoin.join("service");

            if (dto.getSituation() != null) {
                predicates.add(criteriaBuilder.equal(root.get("situation"), dto.getSituation()));
            }
            if (dto.getSubServiceName() != null) {
                predicates.add(criteriaBuilder.equal(subServiceJoin.get("name"), dto.getSubServiceName()));
            }
            if (dto.getServiceName() != null) {
                predicates.add(criteriaBuilder.equal(serviceJoin.get("name"), dto.getServiceName()));
            }
            if (dto.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfOrderRegistration"), dto.getStartDate()));
            }
            if (dto.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateOfOrderRegistration"), dto.getEndDate()));
            }
            query.select(root).where(predicates.toArray(new Predicate[0]));
            return query.getRestriction();


        };
    }
}
