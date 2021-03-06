package info.doseamigos.doseevents;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import info.doseamigos.amigousers.AmigoUser;
import info.doseamigos.meds.Med;

/**
 * Dao for creating, getting, and updating DoseEvents.
 */
public interface DoseEventDao {

    /**
     * Creates a dose event in the DataBase
     * @param doseEvent The DoseEvent to save to the DB.
     * @return The newly
     */
    Long create(DoseEvent doseEvent) throws SQLException;

    /**
     * Gets a list of dose events scheduled for after the begin date.  This is used to prevent duplicate dose events.
     * @param med The Med to get dose events for.
     * @param beginDate The earliest date to look at, all dose events are scheduled after this.
     * @return The list of dose events.
     */
    List<DoseEvent> getDoseEventForMedAfter(Med med, Date beginDate);

    /**
     * Gets a list of dose events scheduled where the last one is today at midnight.
     * @param amigoUser The user to look it up for.
     * @return The list of dose events.
     */
    List<DoseEvent> getEventsForUserToday(AmigoUser amigoUser);

    /**
     * Gets a list of dose events for the upcoming week that the user has for scheduling reasons.
     * @param amigoUser The amigo to get the events for
     * @return The upcoming week's doses to schedule.
     */
    List<DoseEvent> getEventsForUserWeekly(AmigoUser amigoUser);

    /**
     * Updates the list of dose events passed in with the action/actionDateTime.
     * @param doseEvents The events to update.
     */
    void updateDoseEvents(List<DoseEvent> doseEvents) throws SQLException;

    List<DoseEvent> getEventsForUser(AmigoUser amigoUser, Date startDate, String dir);

    /**
     * Single transaction that marks all events as missed if they're overdue by an hour.
     */
    void markMissedEvents() throws SQLException;

    /**
     * Creates all events passed in in the same connection.
     * @param eventsToAdd The events to add.
     * @param user The user getting new events, used to update them in the DB.
     */
    void createMultiple(List<DoseEvent> eventsToAdd, AmigoUser user) throws SQLException;
}
