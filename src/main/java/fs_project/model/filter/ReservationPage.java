package fs_project.model.filter;

import org.springframework.data.domain.Sort;

/**
 * The type Reservation page. contains pagination data for reservations
 */
//fra sys2
public class ReservationPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "startTime";


    /**
     * Gets page number.
     *
     * @return the page number
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets page number.
     *
     * @param pageNumber the page number
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets sort direction.
     *
     * @return the sort direction
     */
    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    /**
     * Sets sort direction.
     *
     * @param sortDirection the sort direction
     */
    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    /**
     * Gets sort by.
     *
     * @return the sort by
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets sort by.
     *
     * @param sortBy the sort by
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public String toString() {
        return "ReservationPage{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", sortDirection=" + sortDirection +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }
}
