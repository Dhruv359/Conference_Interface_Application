package UI.UserSchedulingForms;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;
import UI.Events.EventsForm;
import UI.FormFactory.FormFactoryInterface;
import UI.MainMenu.MenuOptionForm;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * GUI form for the scheduling options for attendees.
 */
public class AttendeeSchedulingForm extends JFrame implements FormFactoryInterface, AttendeeFormInterface {

    private JPanel contentPane;
    private JButton btnNewButton_1;
    private JTextField txtEventId;
    private JButton btnNewButton_3;
    private JLabel lblNewLabel_3;
    private JTextField txtNewSpeakerName;
    private JButton btnNewButton_4;
    private JButton btnNewButton_7;
    private JTextArea txtrMessagesucessfailureerror;
    private JTextArea txtrMessagesucessfailureerror_1;
    private JTextField txtSetTruefalse;
    private JButton btnNewButton_2;

    /**
     * Opens the frame for the attendee scheduling options.
     * @param username of the attendee
     * @param guiPresenterInterface interface for UIPresenter class
     */
    public AttendeeSchedulingForm(String username, GUIPresenterInterface guiPresenterInterface) {
        setTitle("Scheduling System");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 624, 575);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 135, 66, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("View Conference Schedule");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        JButton btnNewButton = new JButton("Show Schedule");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Change Coloumns Names as per need
                String [] columnNames = {"Room", "Event Name", "ID", "Time", "Date", "Capacity", "Speakers", "VIP"};
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        attendeeSchedulingSystem("getSchedule",guiPresenterInterface,"");

                    }
                });
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 3;
        gbc_btnNewButton.gridy = 1;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        JLabel lblNewLabel_1 = new JLabel("View your Schedule");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 3;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

        btnNewButton_1 = new JButton("Show my Schedule");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attendeeSchedulingSystem("showAttendeeSchedule", guiPresenterInterface, "");
            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 3;
        gbc_btnNewButton_1.gridy = 3;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("Sign Up For Event");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 5;
        contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

        txtEventId = new JTextField();
        txtEventId.setText("Event ID");
        GridBagConstraints gbc_txtEventId = new GridBagConstraints();
        gbc_txtEventId.insets = new Insets(0, 0, 5, 5);
        gbc_txtEventId.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEventId.gridx = 3;
        gbc_txtEventId.gridy = 5;
        contentPane.add(txtEventId, gbc_txtEventId);
        txtEventId.setColumns(10);

        btnNewButton_3 = new JButton("Add Event");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                attendeeSchedulingSystem("Add Event", guiPresenterInterface, txtEventId.getText());
            }
        });
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_3.gridx = 4;
        gbc_btnNewButton_3.gridy = 5;
        contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

        txtrMessagesucessfailureerror = new JTextArea();
        txtrMessagesucessfailureerror.setText("Message (Sucess/Failure/Error)");
        GridBagConstraints gbc_txtrMessagesucessfailureerror = new GridBagConstraints();
        gbc_txtrMessagesucessfailureerror.insets = new Insets(0, 0, 5, 0);
        gbc_txtrMessagesucessfailureerror.fill = GridBagConstraints.BOTH;
        gbc_txtrMessagesucessfailureerror.gridx = 4;
        gbc_txtrMessagesucessfailureerror.gridy = 6;
        contentPane.add(txtrMessagesucessfailureerror, gbc_txtrMessagesucessfailureerror);

        lblNewLabel_3 = new JLabel("Remove Event");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 1;
        gbc_lblNewLabel_3.gridy = 8;
        contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

        txtNewSpeakerName = new JTextField();
        txtNewSpeakerName.setText("Event ID");
        GridBagConstraints gbc_txtNewSpeakerName = new GridBagConstraints();
        gbc_txtNewSpeakerName.insets = new Insets(0, 0, 5, 5);
        gbc_txtNewSpeakerName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNewSpeakerName.gridx = 3;
        gbc_txtNewSpeakerName.gridy = 8;
        contentPane.add(txtNewSpeakerName, gbc_txtNewSpeakerName);
        txtNewSpeakerName.setColumns(10);

        btnNewButton_4 = new JButton("Remove");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attendeeSchedulingSystem("RemoveEvent", guiPresenterInterface, txtNewSpeakerName.getText());
            }
        });
        GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
        gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_4.gridx = 4;
        gbc_btnNewButton_4.gridy = 8;
        contentPane.add(btnNewButton_4, gbc_btnNewButton_4);

        txtrMessagesucessfailureerror_1 = new JTextArea();
        txtrMessagesucessfailureerror_1.setText("Message (Sucess/Failure/Error)");
        GridBagConstraints gbc_txtrMessagesucessfailureerror_1 = new GridBagConstraints();
        gbc_txtrMessagesucessfailureerror_1.insets = new Insets(0, 0, 5, 0);
        gbc_txtrMessagesucessfailureerror_1.fill = GridBagConstraints.BOTH;
        gbc_txtrMessagesucessfailureerror_1.gridx = 4;
        gbc_txtrMessagesucessfailureerror_1.gridy = 9;
        contentPane.add(txtrMessagesucessfailureerror_1, gbc_txtrMessagesucessfailureerror_1);

        btnNewButton_7 = new JButton("Main Menu");
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        guiPresenterInterface.openMenuForm(guiPresenterInterface);
                        setVisible(true);
                    }
                });
            }
        });

        btnNewButton_2 = new JButton("Check VIP Status");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attendeeSchedulingSystem("CheckVIP", guiPresenterInterface, "");
            }
        });
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_2.gridx = 1;
        gbc_btnNewButton_2.gridy = 11;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

        txtSetTruefalse = new JTextField();
        txtSetTruefalse.setText("Set True/False");
        GridBagConstraints gbc_txtSetTruefalse = new GridBagConstraints();
        gbc_txtSetTruefalse.insets = new Insets(0, 0, 5, 5);
        gbc_txtSetTruefalse.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSetTruefalse.gridx = 3;
        gbc_txtSetTruefalse.gridy = 11;
        contentPane.add(txtSetTruefalse, gbc_txtSetTruefalse);
        txtSetTruefalse.setColumns(10);
        GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
        gbc_btnNewButton_7.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_7.gridx = 3;
        gbc_btnNewButton_7.gridy = 13;
        contentPane.add(btnNewButton_7, gbc_btnNewButton_7);
    }

    /**
     * Run whenever a user clicks a button and/or puts in an input.
     * @param option name of the button that is clicked
     * @param guiPresenterInterface interface for UIPresenter class
     * @param userInput user input from text field
     */
    private void attendeeSchedulingSystem(String option, GUIPresenterInterface guiPresenterInterface, String userInput) {
        guiPresenterInterface.attendeeSchedulingSystem(option, (AttendeeFormInterface) this, userInput);
    }

    /**
     * Implemented from AttendeeFormInterface, shows the scheduled events corresponding to the user.
     * @param data list of events to be shown
     */
    @Override
    public void openEventsForm(String data) {
        EventsForm eventsForm = new EventsForm(data);
        eventsForm.setVisible(true);
    }

    /**
     * Implemented from AttendeeFormInterface, shows a popup message to the user.
     * @param message message to be popped up to user
     */
    @Override
    public void showMessage(String message) {
            JOptionPane.showMessageDialog(getRootPane(),message);
    }

    /**
     * Factory method, displays the form of the current class.
     * @param username username of the user
     * @param usertype type of the user
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void display(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AttendeeSchedulingForm frame = new AttendeeSchedulingForm(username, guiPresenterInterface);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Factory method, displays the form of the current class.
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void display(GUIPresenterInterface guiPresenterInterface) { }

}
