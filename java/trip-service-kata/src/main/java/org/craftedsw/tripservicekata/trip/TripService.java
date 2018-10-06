package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = loggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		} else {
			boolean isFriend = false;
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				return tripsByUser(user);
			}
			return new ArrayList<Trip>();
		}
	}

	User loggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

	List<Trip> tripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}
	
}
