package UI.UserSchedulingForms;

import PresenterLayer.GUIPresenterInterface;
import UI.Events.EventsForm;
import UI.FormFactory.FormFactoryInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI form for the scheduling options for speakers.
 */
public class SpeakerSchedulingForm extends JFrame implements FormFactoryInterface, SpeakerFormInterface {

    /**
     * Factory method, displays form of the current class.
     * @param username username of user
     * @param usertype type of user
     * @param guiPresenterInterface inferface for UIPresenter
     */
    @Override
    public void display(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SpeakerSchedulingForm frame = new SpeakerSchedulingForm(username,guiPresenterInterface);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Factory method, displays form of the current class.
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void display(GUIPresenterInterface guiPresenterInterface) { }

    /**
     * New content pane.
     */
    private JPanel contentPane;

    /**
     * Opens the frame for the speaker scheduling options.
     * @param username of the speaker
     * @param guiPresenterInterface interface for UIPresenter class
     */
    public SpeakerSchedulingForm(String username, GUIPresenterInterface guiPresenterInterface) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 523, 237);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("Welcome Speaker : [Add Name]");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 5;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        JButton btnNewButton = new JButton("View Conference Schedule");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerSchedulingSystem("getSchedule", guiPresenterInterface);
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 5;
        gbc_btnNewButton.gridy = 3;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_1 = new JButton("View Your Schedule");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerSchedulingSystem("showSpeakerSchedule", guiPresenterInterface);

            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_1.gridx = 5;
        gbc_btnNewButton_1.gridy = 4;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Main Menu");
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_2.gridx = 5;
        gbc_btnNewButton_2.gridy = 5;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiPresenterInterface.openMenuForm(guiPresenterInterface);
                setVisible(true);
            }
        });
    }

    /**
     * Run whenever a user clicks a button and/or puts in an input.
     * @param option name of the button being clicked
     * @param guiPresenterInterface interface for UIPresenter
     */
    public void speakerSchedulingSystem(String option, GUIPresenterInterface guiPresenterInterface) {
        guiPresenterInterface.speakerSchedulingSystem(option,this);
    }

    /**
     * Implemented from SpeakerFormInterface, shows events pertaining to the speaker.
     * @param data list of events to be shown
     */
    public void openEventsForm(String data) {
        EventsForm eventsForm = new EventsForm(data);
        eventsForm.setVisible(true);
    }

    /**
     * Implemented from SpeakerFormInterface, shows a popup message to the user.
     * @param message message to be popped up to user
     */
    public void showMessage(String message){
        JOptionPane.showMessageDialog(getRootPane(),message);
    }

}
