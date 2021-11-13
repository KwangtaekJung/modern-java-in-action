package ch11;

import ch11.domain.Car;
import ch11.domain.Insurance;
import ch11.domain.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ch11Application {
    public static void main(String[] args) {

        Insurance insurance = new Insurance("Test Name for Insurance");

        //1차원 객체의 필드 정보를 꺼낼때 Optional을 사용한다.
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optionalInsurance.map(Insurance::getName);
        System.out.println(name.orElse("Unknown"));

        //Test1) 정상 케이스
        Insurance insurance1 = new Insurance("TestName for Insurance");
        Car car1 = new Car(insurance1);
        Person person1 = new Person(car1);

        String carInsuranceName1 = getCarInsuranceName(person1);
        System.out.println("person1.carInsuranceName = " + carInsuranceName1);


        //Test2) Insurance.name = null
        Insurance insurance2 = new Insurance();
        Car car2 = new Car(insurance2);
        Person person2 = new Person(car2);

        String carInsuranceName2 = getCarInsuranceName(person2);
        System.out.println("person2.carInsuranceName = " + carInsuranceName2);

        //Test3) Insurance = null
        Car car3 = new Car();
        Person person3 = new Person(car3);

        String carInsuranceName3 = getCarInsuranceName(person3);
        System.out.println("person3.carInsuranceName = " + carInsuranceName3);

        //Test4) Car = null
        Person person4 = new Person();

        String carInsuranceName4 = getCarInsuranceName(person4);
        System.out.println("person4.carInsuranceName = " + carInsuranceName4);

        //Optional 스트림 조작
        Set<String> names = getCarInsuranceNames(Arrays.asList(person1, person2, person3, person4));
        System.out.println("names = " + names);
    }

    /**
     * Optional을 사용함으로써 깊은 의심(deep doubt)처럼 중첩된 if null 체크할 필요가 없다.
     * @param person Person
     * @return String
     */
    public static String getCarInsuranceName(Person person) {
        Optional<Person> optionalPerson = Optional.ofNullable(person);
        return optionalPerson
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown")
                ;
    }

    public static Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCarOptional)
                .map(optCar -> optCar.map(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
//                .flatMap(Optional::stream) //Java9 이전에는 아래와 같이 구현해야 함.
                .filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toSet())
                ;
    }
}
