package ch11.service;

import ch11.domain.Car;
import ch11.domain.Insurance;
import ch11.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceService {

    private Person person;
    private Car car;

    public Insurance findCheapestInsurance(Person person, Car car) {
        //다양한 보험회사가 제공하는 서비스 조회
        //모든 결과 데이터 비교
        Insurance cheapestCompany = new Insurance();

        return cheapestCompany;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance2(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)))
                ;
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown")
                ;
    }
}
