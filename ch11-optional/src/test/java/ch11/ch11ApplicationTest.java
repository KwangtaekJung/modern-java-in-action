package ch11;

import ch11.domain.Car;
import ch11.domain.Person;
import ch11.service.InsuranceService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ch11ApplicationTest {

    private final InsuranceService insuranceService = new InsuranceService();

    @Test
    public void getCarInsuranceName() {

        Person person = new Person();
        Car car = new Car();

       insuranceService.nullSafeFindCheapestInsurance(Optional.of(person), Optional.of(car));

    }

}