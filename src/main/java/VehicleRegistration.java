import Utils.CarException;
import Utils.UserException;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class VehicleRegistration extends JFrame {
    private JPanel vechicleRegistrationPanel;
    private JLabel jlCarBrand;
    private JTextField jtfCarBrand;
    private JLabel jlCarModel;
    private JTextField jtfCarModel;
    private JLabel jlCarPlate;
    private JTextField jftCarPlate;
    private JLabel jlCarInformation;
    private JLabel jlUserName;
    private JLabel jlUserPhone;
    private JTextField jtfUserName;
    private JFormattedTextField jftfUserPhone;
    private JButton jbRegisterNewVeicle;
    private JButton jbSearchCarPlate;
    private JLabel jlUserInformation;
    private JButton jbExitButton;
    private String carBrand, carModel, carPlate, userName, userPhone;
    private User memoryUser;
    private Car memoryCar;
    
    public VehicleRegistration() {
        JFrame vechicleRegistrationFrame = new JFrame("Bem-vindo! Cadastro de veículos");
        vechicleRegistrationFrame.setContentPane(vechicleRegistrationPanel);
        vechicleRegistrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vechicleRegistrationFrame.pack();
        vechicleRegistrationFrame.setVisible(true);
        
        memoryCar = new Car();
        memoryUser = new User();
    
        userRegistration();
        searchVehicle();
        maskFields();
        quitApplication();
    }
    
    public void userRegistration() {
        jbRegisterNewVeicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carBrand = jtfCarBrand.getText();
                carModel = jtfCarModel.getText();
                carPlate = jftCarPlate.getText();
                userName = jtfUserName.getText();
                userPhone = jftfUserPhone.getText();
                
                try {
                    memoryUser = validadeUserRegistration();
                    memoryCar = validateCarRegistration();
                    JOptionPane.showMessageDialog(vechicleRegistrationPanel,
                            "Novo veículo do proprietario (" + userName + ") cadastrado!"
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            vechicleRegistrationPanel,
                            "Erro ao cadastrar veículo, tente novamente!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    clearFormFields();
                }
            }
        });
    }
    
    public void searchVehicle() {
        jbSearchCarPlate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateSearchedVehicle();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            vechicleRegistrationPanel,
                            "Erro ao pesquisar veículo",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
    public User validadeUserRegistration() throws UserException {
        String phoneRegex = "^\\([0-9]{2}\\)[0-9]{5}-[0-9]{4}$";
        
        if (userName.isEmpty() || userPhone.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "O nome do usuário e o telefone são obrigatórios!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else if (!Character.isUpperCase(userName.charAt(0))) {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "O nome do usuário deve começar com uma letra maiúscula!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else if (!userPhone.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "O telefone deve conter o padrão (00)00000-0000",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    
        return new User(userName, userPhone);
    }
    
    public Car validateCarRegistration() throws CarException {
        String plateRegex = "^[A-Z]{3}[0-9]{4}$";
        
        if (carBrand.isEmpty() || carModel.isEmpty() || carPlate.isEmpty()) {
            JOptionPane.showMessageDialog(vechicleRegistrationPanel, "Preencha todos os campos");
        } else if(!Character.isUpperCase(carBrand.charAt(0)) || !Character.isUpperCase(carModel.charAt(0))) {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "A marca ou modelo do carro deve começar com uma letra maiúscula",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else if(!carPlate.matches(plateRegex)) {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "A sua placa deve conter o padrão Mercosul: AAA0000",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        return new Car(carBrand, carModel, carPlate);
    }
    
    public void validateSearchedVehicle() {
        if (carPlate == null || carPlate.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "Por favor, digite o número de placa do veículo corretamente",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else if(!carPlate.equals(memoryCar.getCarPlate())) {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "Não encontramos o veículo com a placa " + carPlate,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    vechicleRegistrationPanel,
                    "Os dados para a placa informada foram encontrados: \n"
                            + "Marca: " + memoryCar.getCarBrand()
                            + "\nModelo: " + memoryCar.getCarModel()
                            + "\nPlaca do veículo: " + memoryCar.getCarPlate()
                            + "\nProprietário: " + memoryUser.getUserName()
                            + "\nTelefone: " + memoryUser.getUserPhone(),
                    "Veículo encontrado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    public void clearFormFields() {
        jtfCarBrand.setText(null);
        jtfCarModel.setText(null);
        jftCarPlate.setText(null);
        jtfUserName.setText(null);
        jftfUserPhone.setText(null);
    }
    
    public void maskFields(){
        try {
            MaskFormatter numberMask = new MaskFormatter("(##)9####-####");
            
            jftfUserPhone.setFormatterFactory(new DefaultFormatterFactory(numberMask));
        } catch (ParseException error) {
            error.getMessage();
        }
        
    }
    
    public void quitApplication() {
        jbExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
