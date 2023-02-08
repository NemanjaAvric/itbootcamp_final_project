package utility;

import com.github.javafaker.Faker;

public class Utility {
    private Faker faker = new Faker();

    public String fakeEmail() {
        return faker.internet().emailAddress();
    }

    public String fakeCity() {
        return faker.address().cityName();
    }

    public String fakePassword() {
        return faker.internet().password();
    }

    public String fakeName() {
        return faker.name().fullName();
    }

    public String fakeCellPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    public String fakeCountry() {
        return faker.country().name();
    }

    public String fakeURL() {
        return faker.internet().url();
    }
}
