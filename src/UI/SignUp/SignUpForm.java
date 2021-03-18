package UI.SignUp;


import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;
import UI.FormFactory.FormFactoryInterface;
import UI.MainMenu.MenuOptionForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI class for signing up or logging in user.
 */
public class SignUpForm extends JFrame implements FormFactoryInterface, Form {

    private JPanel contentPane;
    private JTextField txtUniqueName;
    private JTextField passwordField;
    private JComboBox comboBox;

    /**
     * Opens the frame for the user to sign up.
     * @param guiPresenterInterface interface for UIPresenter class
     */
    public SignUpForm(GUIPresenterInterface guiPresenterInterface) {
        setTitle("Sign Up/Log In ");
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("Sign up as a new user");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("New User Name:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.gridx = 2;
        gbc_lblNewLabel_1.gridy = 3;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

        txtUniqueName = new JTextField("Unique Name");
        txtUniqueName.setForeground(Color.LIGHT_GRAY);
        //txtUniqueName.setText("Unique name");
        GridBagConstraints gbc_txtUniqueName = new GridBagConstraints();
        gbc_txtUniqueName.insets = new Insets(0, 0, 5, 0);
        gbc_txtUniqueName.anchor = GridBagConstraints.WEST;
        gbc_txtUniqueName.gridx = 3;
        gbc_txtUniqueName.gridy = 3;
        contentPane.add(txtUniqueName, gbc_txtUniqueName);
        txtUniqueName.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("New Password");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 2;
        gbc_lblNewLabel_2.gridy = 4;
        contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

        passwordField = new JTextField();
        passwordField.setColumns(10);
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField.anchor = GridBagConstraints.WEST;
        gbc_passwordField.gridx = 3;
        gbc_passwordField.gridy = 4;
        contentPane.add(passwordField, gbc_passwordField);

        JLabel lblNewLabel_3 = new JLabel("Sign Up As:");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 2;
        gbc_lblNewLabel_3.gridy = 5;
        contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

        String usertype[] = {"Attendee", "Speaker", "Organizer", "VIP Attendee"};
        comboBox = new JComboBox(usertype);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox.anchor = GridBagConstraints.WEST;
        gbc_comboBox.gridx = 3;
        gbc_comboBox.gridy = 5;
        contentPane.add(comboBox, gbc_comboBox);

        JButton btnNewButton = new JButton("Sign Up");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUp(guiPresenterInterface);
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 6;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_1 = new JButton("Log In");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login(guiPresenterInterface);
            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.gridx = 3;
        gbc_btnNewButton_1.gridy = 6;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
    }

    /**
     * Factory method, displays the frame for the current class.
     * @param username username of user
     * @param usertype type of user
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void display(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {

    }

    /**
     * Factory method, displays the frame for the current class.
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void display(GUIPresenterInterface guiPresenterInterface) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpForm frame = new SignUpForm(guiPresenterInterface);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Implemented from Form interface, gets username of user.
     * @return username of user
     */
    @Override
    public String getTxtUniqueName() {
        return txtUniqueName.getText();
    }

    /**
     * Implemented from Form interface, gets password of user.
     * @return
     */
    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    /**
     * Implemented from Form interface, gets type of user.
     * @return
     */
    @Override
    public String getUsertype(){
        return (String)comboBox.getSelectedItem();
    }

    /**
     * Implemented from Form interface, signs up the user.
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void signUp(GUIPresenterInterface guiPresenterInterface) {
        guiPresenterInterface.signUp(guiPresenterInterface, (Form) this);
    }

    /**
     * Implemented from Form interface, logs in user.
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void login(GUIPresenterInterface guiPresenterInterface){
        guiPresenterInterface.login(guiPresenterInterface,(Form) this);
    }

    /**
     * Implemented from Form interface, shows error message to user.
     * @param message error message to be shown
     */
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(getRootPane(),message);
    }

    /**
     * Gets the dropdown list.
     * @return dropdown list
     */
    public JComboBox getComboBox() {
        return comboBox;
    }

    /**
     * Sets the dropdown list.
     * @param comboBox dropdown list
     */
    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

}
