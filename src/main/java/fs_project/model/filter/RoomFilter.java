package fs_project.model.filter;

/**
 * The type Room filter. Used for filtering/searching/sorting/paging rooms.
 */
//fra sys2
public class RoomFilter {
    private RoomSearchCriteria roomSearchCriteria;
    private RoomPage roomPage;

    /**
     * Instantiates a new Room filter.
     *
     * @param roomSearchCriteria the room search criteria
     * @param roomPage           the room page
     */
    public RoomFilter(RoomSearchCriteria roomSearchCriteria, RoomPage roomPage) {
        this.roomSearchCriteria = roomSearchCriteria;
        this.roomPage = roomPage;
    }

    /**
     * Instantiates a new Room filter.
     */
    public RoomFilter() {
    }

    /**
     * Gets room search criteria.
     *
     * @return the room search criteria
     */
    public RoomSearchCriteria getRoomSearchCriteria() {
        return roomSearchCriteria;
    }

    /**
     * Sets room search criteria.
     *
     * @param roomSearchCriteria the room search criteria
     */
    public void setRoomSearchCriteria(RoomSearchCriteria roomSearchCriteria) {
        this.roomSearchCriteria = roomSearchCriteria;
    }

    /**
     * Gets room page.
     *
     * @return the room page
     */
    public RoomPage getRoomPage() {
        return roomPage;
    }

    /**
     * Sets room page.
     *
     * @param roomPage the room page
     */
    public void setRoomPage(RoomPage roomPage) {
        this.roomPage = roomPage;
    }


    @Override
    public String toString() {
        return "RoomFilter{" +
                "roomSearchCriteria=" + roomSearchCriteria +
                ", roomPage=" + roomPage +
                '}';
    }

}
