package fs_project.repo;

import fs_project.model.dataEntity.Room;
import fs_project.model.filter.RoomPage;
import fs_project.model.filter.RoomSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//fra sys2
@Repository
public class RoomCriteriaRepo {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public RoomCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    /**
     * Yields a page containing activities which conform to the criteria.
     * @param roomPage Specifies how returned page should be formatted.
     * @param roomSearchCriteria Criteria for which activities to retreive.
     * @return Page of various activities.
     */

    public Page<Room> findAllWithFilters(RoomPage roomPage,
                                         RoomSearchCriteria roomSearchCriteria) {
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> roomRoot = criteriaQuery.from(Room.class);
        Predicate predicate = getPredicate(roomSearchCriteria, roomRoot);
        criteriaQuery.where(predicate);
        setOrder(roomPage, criteriaQuery, roomRoot);

        TypedQuery<Room> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(roomPage.getPageNumber() * roomPage.getPageSize());
        typedQuery.setMaxResults(roomPage.getPageSize());

        Pageable pageable = getPageable(roomPage);

        long roomsCount = getRoomsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, roomsCount);
    }

    /**
     * Constructs the predicate used to query the database.
     * @param roomSearchCriteria The search criteria.
     * @param roomRoot The initial part of the query to build.
     * @return The predicate used to query the database.
     */

    private Predicate getPredicate(RoomSearchCriteria roomSearchCriteria,
                                   Root<Room> roomRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(roomSearchCriteria.getName()) && !roomSearchCriteria.getName().trim().equals("")) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(roomRoot.get("name")),
                    "%" + roomSearchCriteria.getName().toLowerCase() + "%"));
        }


        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    /**
     * Mutates a query to specify a sorting strategy.
     * @param roomPage Page containing the sorting strategy.
     * @param criteriaQuery The current query.
     * @param roomRoot The root of the query.
     */

    private void setOrder(RoomPage roomPage, CriteriaQuery<Room> criteriaQuery, Root<Room> roomRoot) {
        if(roomPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(roomRoot.get(roomPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(roomRoot.get(roomPage.getSortBy())));
        }
    }

    /**
     * Retrieves page information from room page implementation.
     * @param roomPage Any room page.
     * @return The page information.
     */

    private Pageable getPageable(RoomPage roomPage) {
        Sort sort = Sort.by(roomPage.getSortDirection(), roomPage.getSortBy());
        return PageRequest.of(roomPage.getPageNumber(),roomPage.getPageSize(), sort);
    }

    /**
     * The room-count from a query formed by the provided predicate.
     * @param predicate The predicate to find count for.
     * @return The count.
     */

    private long getRoomsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Room> countRoot = countQuery.from(Room.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}

