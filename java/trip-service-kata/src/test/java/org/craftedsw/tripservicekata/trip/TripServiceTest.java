package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TripServiceTest {
    
    private final User user = new User();
    private final TripServiceWithMocks tripService = new TripServiceWithMocks()
            .withLoggedUser(user);

    @Before
    public void setUp() {
    }

    @Test
    public void implement_me() {
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
