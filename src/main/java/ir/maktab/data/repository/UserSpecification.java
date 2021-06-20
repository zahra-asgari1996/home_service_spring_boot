package ir.maktab.data.repository;

import ir.maktab.data.domain.Customer;
import ir.maktab.data.domain.Expert;
import ir.maktab.data.domain.Users;
import ir.maktab.dto.FilterUsersDto;
import ir.maktab.dto.UserHistoryDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;

public interface UserSpecification {
    static Specification<Users> filterUsers(FilterUsersDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
            List<Predicate> predicates = new ArrayList<>();
            Root<Expert> expertRoot = criteriaBuilder.treat(root, Expert.class);
            if (dto.getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), dto.getName()));
            }
            if (dto.getLastName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), dto.getLastName()));
            }
            if (dto.getEmail() != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), dto.getEmail()));
            }
            if (dto.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("userRole"), dto.getRole()));
            }
            if (dto.getRate() != null) {
                predicates.add(criteriaBuilder.greaterThan(expertRoot.get("rate"), dto.getRate()));
            }
            if (dto.getField() != null) {
                predicates.add(criteriaBuilder.equal(expertRoot.get("field"), dto.getField()));
            }
//            if (dto.getField()!=null || dto.getRate()!=null){
//                query.select(expertRoot).where(predicates.toArray(new Predicate[0]));
//            }if (dto.getName()!=null || dto.getLastName()!=null || dto.getEmail()!=null || dto.getRole()!=null){
//                query.select(root).where(predicates.toArray(new Predicate[0]));
//            }
//            else {
//                query.select(root);
//            }
            query.select(root).where(predicates.toArray(new Predicate[0]));
            return query.getRestriction();

        };
    }

    static Specification<Users> userHistory(UserHistoryDto dto){
        return (root, criteriaQuery, criteriaBuilder) ->{
            CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
            List<Predicate> predicates=new ArrayList<>();
            Subquery<Customer> customerSubquery = query.subquery(Customer.class);
            Subquery<Expert> expertSubquery = query.subquery(Expert.class);
            Root<Customer> customerRoot = customerSubquery.from(Customer.class);
            Root<Expert> expertRoot = expertSubquery.from(Expert.class);
            Subquery<Customer> customerSelect = customerSubquery.select(customerRoot.get("id"));
            Subquery<Expert> expertSelect = expertSubquery.select(expertRoot.get("id"));
            Predicate customerPredicate = criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            Predicate expertPredicate = criteriaBuilder.isTrue(criteriaBuilder.literal(true));

            if (dto.getStartDate()!=null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"),dto.getStartDate()));
            }
            if (dto.getEndDate()!=null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"),dto.getEndDate()));
            }
            if (dto.getMaxNumberOfOrders()!=null){
                customerPredicate=criteriaBuilder.and(
                        customerPredicate,criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.size(customerRoot.get("orders")),dto.getMaxNumberOfOrders()));

            }if (dto.getMinNumberOfOrders()!=null){
                customerPredicate=criteriaBuilder.and(
                        customerPredicate,criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.size(customerRoot.get("orders")),dto.getMinNumberOfOrders()));
            }if (dto.getMaxNumberOfOffers()!=null){
                expertPredicate=criteriaBuilder.and(
                        expertPredicate,criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.size(expertRoot.get("offers")), dto.getMaxNumberOfOffers()));
            }
            if (dto.getMinNumberOfOffers() != null) {
                expertPredicate=criteriaBuilder.and(
                        expertPredicate,criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.size(expertRoot.get("offers")), dto.getMinNumberOfOffers())
                );
            }
            if (customerPredicate.getExpressions().size()>0){
                customerSelect.where(customerPredicate);
                predicates.add(root.get("id").in(customerSubquery));
            }
            if (expertPredicate.getExpressions().size()>0){
                expertSelect.where(expertPredicate);
                predicates.add(root.get("id").in(expertSubquery));
            }
            query.select(root).where(predicates.toArray(new Predicate[0]));
            return query.getRestriction();

        };
    }


}
