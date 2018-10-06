package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TripServiceTest {
    
    private final User userWithFriend = new User();
    private final User userWithNoFriends = new User();
    private final User notAFriend = new User();
    private final User friend = new User();

    private final TripServiceWithMocks tripService = new TripServiceWithMocks()
            .withLoggedUser(userWithFriend);

    @Before
    public void setUp() {
        friend.addFriend(userWithFriend);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void fail_if_user_is_not_logged() {
        notAFriend.addTrip(new Trip());

        List<Trip> trips = new TripServiceWithMocks().withLoggedUser(null)
                .getTripsByUser(userWithFriend);

        assertTrue(trips.isEmpty());
    }

    @Test
    public void return_no_trips_if_user_is_not_a_friend() {
        notAFriend.addTrip(new Trip());

        List<Trip> trips = tripService.getTripsByUser(notAFriend);

        assertTrue(trips.isEmpty());
    }

    @Test
    public void return_no_trips_if_user_has_no_friends() {

        List<Trip> trips = tripService.getTripsByUser(userWithNoFriends);

        assertTrue(trips.isEmpty());
    }

    @Test
    public void return_trips_if_user_is_a_friend() {
        final Trip trip = new Trip();
        friend.addTrip(trip);

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertTrue(trips.contains(trip));
    }

    private static class TripServiceWithMocks extends TripService {

        private User loggedUser;

        TripServiceWithMocks withLoggedUser(User loggedUser) {
            this.loggedUser = loggedUser;
            return this;
        }

        @Override
        User loggedUser() {
            return loggedUser;
        }

        @Override
        List<Trip> tripsByUser(User user) {
            return user.trips();
        }
    }
}
