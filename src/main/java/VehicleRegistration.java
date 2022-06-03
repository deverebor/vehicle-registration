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
                    memoryCar = validadteCarRegistration();
                    JOptionPane.showMessageDialog(vechicleRegistrationPanel,
                            "Novo veículo do proprietario (" + userName + ") cadastrado!"
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vechicleRegistrationPanel, "Erro ao cadastrar veículo");
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
                    JOptionPane.showMessageDialog(null, "Erro ao pesquisar veículo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public User validadeUserRegistration() {
        if (userName.isEmpty() || userPhone.isEmpty()) {
            JOptionPane.showMessageDialog(vechicleRegistrationPanel, "Preencha todos os campos");
        }
    
        return new User(userName, userPhone);
    }
    
    public Car validadteCarRegistration() {
        if (carBrand.isEmpty() || carModel.isEmpty() || carPlate.isEmpty()) {
            JOptionPane.showMessageDialog(vechicleRegistrationPanel, "Preencha todos os campos");
        }
        
        return new Car(carBrand, carModel, carPlate);
    }
    
    public void validateSearchedVehicle() {
        if (carPlate == null || carPlate.isEmpty() || !carPlate.equals(memoryCar.getCarPlate())) {
            JOptionPane.showMessageDialog(
                    null, "Por favor, digite o número de placa do veículo corretamente", "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            jtfCarBrand.setText(memoryCar.getCarBrand());
            jtfCarModel.setText(memoryCar.getCarModel());
            jtfUserName.setText(memoryUser.getUserName());
            jftfUserPhone.setText(memoryUser.getUserPhone());
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
