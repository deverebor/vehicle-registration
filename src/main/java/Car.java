import Utils.CarException;

public class Car {
    private String carBrand;
    private String carModel;
    private String carPlate;
    
    protected final String plateRegex = "^[A-Z]{3}[0-9]{4}$";
    
    public Car() {}
    
    public Car(String newCarBrand, String newCarModel, String newCarPlate) throws CarException {
        if(newCarBrand == null || newCarModel == null || newCarPlate == null) {
            throw new CarException("Marca, modelo e placa do carro não podem ser nulos");
        }else if(!Character.isUpperCase(newCarBrand.charAt(0)) || !Character.isUpperCase(newCarModel.charAt(0))) {
            throw new CarException("A marca ou modelo do carro deve começar com uma letra maiúscula");
        }else if(!newCarPlate.matches(plateRegex)) {
            throw new CarException("A sua placa deve conter o padrão Mercosul: AAA0000");
        }else {
            setCarBrand(newCarBrand);
            setCarModel(newCarModel);
            setCarPlate(newCarPlate);
        }
    }
    
    public String getCarBrand() {
        return carBrand;
    }
    
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    
    public String getCarModel() {
        return carModel;
    }
    
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    
    public String getCarPlate() {
        return carPlate;
    }
    
    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
}
