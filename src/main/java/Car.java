public class Car {
    private String carBrand;
    private String carModel;
    private String carPlate;
    
    public Car() {}
    
    public Car(String newCarBrand, String newCarModel, String newCarPlate) {
        setCarBrand(newCarBrand);
        setCarModel(newCarModel);
        setCarPlate(newCarPlate);
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
