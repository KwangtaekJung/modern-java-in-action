package ch11;

import ch11.domain.Car;
import ch11.domain.Person;
import ch11.service.InsuranceService;
import ch11.utils.OptionalUtility;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ch11ApplicationTest {

    private final InsuranceService insuranceService = new InsuranceService();

    @Test
    public void getCarInsuranceName() {

        Person person = new Person();
        Car car = new Car();

       insuranceService.nullSafeFindCheapestInsurance(Optional.of(person), Optional.of(car));

    }

    @Test
    public void readDurationTest() {
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");

        assertEquals(5, readDuration(properties, "a"));
        assertEquals(0, readDuration(properties, "b"));
        assertEquals(0, readDuration(properties, "c"));
        assertEquals(0, readDuration(properties, "d"));

        assertEquals(5, readDurationWithOptional(properties, "a"));
        assertEquals(0, readDurationWithOptional(properties, "b"));
        assertEquals(0, readDurationWithOptional(properties, "c"));
        assertEquals(0, readDurationWithOptional(properties, "d"));
    }

    public int readDuration(Properties properties, String name) {
        String value = properties.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return 0;
    }

    public int readDurationWithOptional(Properties properties, String name) {
        return Optional.ofNullable(properties.getProperty(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0)
                ;
    }
}