package data_access;

import entity.NutritionGoal;
import entity.UserProfile;
import use_case.gateway.ProfileRepository;

import java.util.Optional;

/**
 * Keeps the profile and goal in memory (two fields) — no files, no database.
 *
 * <p>An implementation (role: data access) of the {@link ProfileRepository}
 * gateway, matching {@link InMemoryFridgeRepository}. The fields start null,
 * which is exactly the "nothing saved yet" case the gateway reports as an empty
 * {@link Optional}. Swapping in a file-backed version later changes no interactor.
 */
public class InMemoryProfileRepository implements ProfileRepository {

    private UserProfile profile;
    private NutritionGoal goal;

    @Override
    public Optional<UserProfile> load() {
        return Optional.ofNullable(profile);
    }

    @Override
    public Optional<NutritionGoal> loadGoal() {
        return Optional.ofNullable(goal);
    }

    @Override
    public void save(UserProfile profile, NutritionGoal goal) {
        this.profile = profile;
        this.goal = goal;
    }
}
