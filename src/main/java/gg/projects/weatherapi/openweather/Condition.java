package gg.projects.weatherapi.openweather;

public enum Condition {

    Thunderstorm,
    Drizzle,
    Rain;

    public static boolean umbrella(String condition) {
        return condition.equals(Thunderstorm.name())
                || condition.equals(Drizzle.name())
                || condition.equals(Rain.name());
    }
}
