package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TripServiceTest {
    
    private final User user = new User();
    private final User notAFriend = new User();

    private final TripServiceWithMocks tripService = new TripServiceWithMocks()
            .withLoggedUser(user);

    @Test
    public void return_no_trips_if_user_is_not_a_friend() {
        notAFriend.addTrip(new Trip());

        List<Trip> trips = tripService.getTripsByUser(notAFriend);

        assertTrue(trips.isEmpty());
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
