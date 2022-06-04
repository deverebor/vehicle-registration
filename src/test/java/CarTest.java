import Utils.CarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {
    
    @Test
    public void shouldCreateCar() {
        Car car = new Car();
        car.setCarBrand("Ford");
        car.setCarModel("Mustang");
        car.setCarPlate("ABC1234");
        assertEquals("Ford", car.getCarBrand());
        assertEquals("Mustang", car.getCarModel());
        assertEquals("ABC1234", car.getCarPlate());
    }
    
    @Test
    public void shouldCreateCarWithBrandModelPlate() throws CarException {
        Car carFully = new Car("Ford", "Mustang", "ABC1234");
        assertEquals("Ford", carFully.getCarBrand());
        assertEquals("Mustang", carFully.getCarModel());
        assertEquals("ABC1234", carFully.getCarPlate());
    }
    
    @Test
    public void shouldThrowCarException() {
        CarException carAllNullException = assertThrows(CarException.class, () -> {
            Car carNull = new Car(null, null, null);
        });
        
        assertEquals("Marca, modelo e placa do carro não podem ser nulos", carAllNullException.getMessage());
    
        CarException carUppercaseException = assertThrows(CarException.class, () -> {
            Car carUpper = new Car("car", "model", "ABC1234");
        });
    
        assertEquals("A marca ou modelo do carro deve começar com uma letra maiúscula", carUppercaseException.getMessage());
    
        CarException carRegexException = assertThrows(CarException.class, () -> {
            Car carUpper = new Car("Car", "Model", "ABC-1234");
        });
    
        assertEquals("A sua placa deve conter o padrão Mercosul: AAA0000", carRegexException.getMessage());
    
    }
}
