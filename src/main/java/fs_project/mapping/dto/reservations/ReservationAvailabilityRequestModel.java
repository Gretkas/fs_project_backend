package fs_project.mapping.dto.reservations;

import fs_project.model.dataEntity.Item;

import java.util.List;


/**
 * The type Reservation availability request model. Used to check for available time for a given set of items.
 */
public class ReservationAvailabilityRequestModel {
    private List<Item> items;

    /**
     * Instantiates a new Reservation availability request model.
     */
    public ReservationAvailabilityRequestModel() {
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReservationAvailabilityRequestModel{" +
                "items=" + items +
                '}';
    }
}
