package ch11.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Insurance insurance;

    public Optional<Insurance> getInsuranceOptional() {
        return Optional.ofNullable(insurance);
    }
}
