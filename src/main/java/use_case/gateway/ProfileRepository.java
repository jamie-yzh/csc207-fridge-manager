package use_case.gateway;

import entity.NutritionGoal;
import entity.UserProfile;

import java.util.Optional;

/**
 * Saves and loads the user's profile and the goal calculated from it.
 *
 * <p>A gateway, like {@link FridgeRepository}: the use-case layer depends on this
 * interface and the outer layer supplies the implementation (in memory now, a file
 * later, a mock in tests). Unlike the fridge — which always has a sensible empty
 * state — a profile either exists or it does not, so both loads return
 * {@link Optional}. That makes "no profile yet" a case the caller must handle
 * rather than a silent zero.
 */
public interface ProfileRepository {

    /** The saved profile, or empty if the user has never entered one. */
    Optional<UserProfile> load();

    /** The saved goal, or empty if nothing has been calculated yet. */
    Optional<NutritionGoal> loadGoal();

    /** Save a profile together with the goal calculated from it. */
    void save(UserProfile profile, NutritionGoal goal);
}
