package fs_project.repo;

import fs_project.model.dataEntity.Reservation;
import fs_project.model.filter.ReservationPage;
import fs_project.model.filter.ReservationSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//fra sys2
@Repository
public class ReservationCriteriaRepo {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ReservationCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    /**
     * Yields a page containing activities which conform to the criteria.
     * @param reservationPage Specifies how returned page should be formatted.
     * @param reservationSearchCriteria Criteria for which activities to retreive.
     * @return Page of various activities.
     */

    public Page<Reservation> findAllWithFilters(ReservationPage reservationPage,
                                         ReservationSearchCriteria reservationSearchCriteria) {
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> reservationRoot = criteriaQuery.from(Reservation.class);
        Predicate predicate = getPredicate(reservationSearchCriteria, reservationRoot);
        criteriaQuery.where(predicate);
        setOrder(reservationPage, criteriaQuery, reservationRoot);

        TypedQuery<Reservation> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(reservationPage.getPageNumber() * reservationPage.getPageSize());
        typedQuery.setMaxResults(reservationPage.getPageSize());

        Pageable pageable = getPageable(reservationPage);

        long reservationsCount = getReservationsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, reservationsCount);
    }

    /**
     * Constructs the predicate used to query the database.
     * @param reservationSearchCriteria The search criteria.
     * @param reservationRoot The initial part of the query to build.
     * @return The predicate used to query the database.
     */

    private Predicate getPredicate(ReservationSearchCriteria reservationSearchCriteria,
                                   Root<Reservation> reservationRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(reservationSearchCriteria.getTitle()) && !reservationSearchCriteria.getTitle().trim().equals("")) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(reservationRoot.get("title")),
                    "%" + reservationSearchCriteria.getTitle().toLowerCase() + "%"));
        }
        if (!reservationSearchCriteria.isShowPreviousReservations()) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(reservationRoot.get("endTime"), LocalDateTime.now()));
        }



        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    /**
     * Mutates a query to specify a sorting strategy.
     * @param reservationPage Page containing the sorting strategy.
     * @param criteriaQuery The current query.
     * @param reservationRoot The root of the query.
     */

    private void setOrder(ReservationPage reservationPage, CriteriaQuery<Reservation> criteriaQuery, Root<Reservation> reservationRoot) {
        if(reservationPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(reservationRoot.get(reservationPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(reservationRoot.get(reservationPage.getSortBy())));
        }
    }

    /**
     * Retrieves page information from reservation page implementation.
     * @param reservationPage Any reservation page.
     * @return The page information.
     */

    private Pageable getPageable(ReservationPage reservationPage) {
        Sort sort = Sort.by(reservationPage.getSortDirection(), reservationPage.getSortBy());
        return PageRequest.of(reservationPage.getPageNumber(),reservationPage.getPageSize(), sort);
    }

    /**
     * The reservation-count from a query formed by the provided predicate.
     * @param predicate The predicate to find count for.
     * @return The count.
     */

    private long getReservationsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Reservation> countRoot = countQuery.from(Reservation.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}

