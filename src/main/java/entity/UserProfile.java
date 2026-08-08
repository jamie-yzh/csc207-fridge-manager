package entity;

import java.util.Objects;

/**
 * The user's body information, entered in the goal popout and used by
 * CalculateGoal to propose a daily target.
 */
public final class UserProfile {

    private final double weight;
    private final double height;
    private final int age;
    private final Sex sex;
    private final ActivityLevel activityLevel;
    private final Objective objective;

    /**
     * Constructs a UserProfile.
     * @param weight body weight in kilograms
     * @param height height in centimetres
     * @param age age in years
     * @param sex biological sex
     * @param activityLevel how active the user is
     * @param objective what the user is eating toward
     */
    public UserProfile(double weight, double height, int age, Sex sex,
                       ActivityLevel activityLevel, Objective objective) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = Objects.requireNonNull(sex, "sex");
        this.activityLevel = Objects.requireNonNull(activityLevel, "activityLevel");
        this.objective = Objects.requireNonNull(objective, "objective");
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public Objective getObjective() {
        return objective;
    }
}
