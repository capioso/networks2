package networksTwo.domain;

public record Address (String country, String state, String city, String street, int number) {
    @Override
    public String toString() {
        return "{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
