package UI.MainMenu;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;
import UI.FormFactory.FormFactoryInterface;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuOptionForm extends JFrame implements FormFactoryInterface {

    private JPanel contentPane;

    /**
     * Launch the application.
     */



//
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
////                    UIPresenter uip = new UIPresenter();
////                    MenuOptionFormnuOptionForm frame = new MenuOptionForm("Attendee", "Attendee", uip);
////                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public MenuOptionForm(String usertype, String username, GUIPresenterInterface guiPresenterInterface) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 504, 197);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel_1 = new JLabel("Sign In Successful");
        lblNewLabel_1.setForeground(Color.GRAY);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 0;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("What would you like to do ?");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 2;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        JButton btnNewButton = new JButton("Access Messaging System");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accessMessagingSystem(username,usertype,guiPresenterInterface);
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 3;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_1 = new JButton("Access Scheduling System");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accessSchedulingSystem(username,usertype,guiPresenterInterface);
            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.gridx = 2;
        gbc_btnNewButton_1.gridy = 4;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);


        JButton btnNewButton_2 = new JButton("Log out");
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.gridx = 2;
        gbc_btnNewButton_2.gridy = 5;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiPresenterInterface.logOut(guiPresenterInterface);
                System.exit(0);
            }
        });


    }

    /**
     * Opens the scheduling form associated with each usertype
     * @param username username of the user
     * @param usertype user type of the user
     * @param guiPresenterInterface presenter interface of the GUI
     */
    public void accessSchedulingSystem(String username, String usertype, GUIPresenterInterface guiPresenterInterface)
    {
        guiPresenterInterface.openAccessSchedulingSystem(username,usertype,guiPresenterInterface);
    }


    /**
     * Opens the messaging form associated with each usertype
     * @param username username of the user
     * @param usertype user type of the user
     * @param guiPresenterInterface presenter interface of the GUI
     */
    public void accessMessagingSystem(String username, String usertype, GUIPresenterInterface guiPresenterInterface)
    {
        guiPresenterInterface.openAccessMessagingSystem(username,usertype,guiPresenterInterface);
    }


    /**
     * Displays the menu option form.
     * @param username username of the user
     * @param usertype user type of the user
     * @param guiPresenterInterface presenter interface of the GUI
     */
    @Override
    public void display(String username, String usertype,GUIPresenterInterface guiPresenterInterface) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuOptionForm frame = new MenuOptionForm(usertype, username, guiPresenterInterface);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void display(GUIPresenterInterface guiPresenterInterface) {

    }
}
