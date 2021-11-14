package ch11.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Car car;
    private int age;

    public Optional<Car> getCarOptional() {
        return  Optional.ofNullable(car);
    }

    public Person(Car car) {
        this.car = car;
    }
}
