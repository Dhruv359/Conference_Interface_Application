package UI.UserSchedulingForms;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;
import UI.Events.EventsForm;
import UI.FormFactory.FormFactoryInterface;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * GUI form for the scheduling options for organizers.
 */
public class OrganizerSchedulingForm extends JFrame implements FormFactoryInterface, SchedulingFormInterface {

    private JPanel contentPane;
    private JTable table;
    private JTextField txtEnterNewRoom;
    private JTextField txtEventId;
    private JTextField txtNewEnd;
    private JTextField txtRoom;
    private JTextField txtNewStart;
    private JTextField txtCapacity;
    private JCheckBox chckbxNewCheckBox;
    private JTextField txtDate_1;
    private JButton btnNewButton_3;
    private JButton btnNewButton_1;
    private JLabel lblNewLabel_3;
    private JTextField txtNewSpeakerName;
    private JTextField passwordField;
    private JButton btnNewButton_4;
    private JLabel lblNewLabel_4;
    private JTextField txtEventId_1;
    private JTextField txtSpeakerName;
    private JButton btnNewButton_6;
    private JLabel lblNewLabel_5;
    private JTextField txtNewName;
    private JTextField passwordField_1;
    private JRadioButton rdbtnNewRadioButton;
    private JButton btnNewButton_5;
    private JButton btnNewButton_7;
    private JLabel lblNewLabel_6;
    private JTextField txtEventId_2;
    private JButton btnNewButton_8;
    private JComboBox comboBox;
    private JButton btnNewButton_9;
    private JLabel lblNewLabel_7;
    private JComboBox comboBox_1;
    private  JButton btnNewButton_10;
    private JLabel lblNewLabel_8;
    private JComboBox comboBox_2;

    /**
     * Opens the frame for the organizer scheduling options.
     * @param username of the organizer
     * @param guiPresenterInterface interface for UIPresenter class
     */
    public OrganizerSchedulingForm(String username, GUIPresenterInterface guiPresenterInterface) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 66, 96, 97, 83, 57, 0, 49, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("See all Scheduled Events ");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        JButton btnNewButton = new JButton("Show Events");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        String [] columnNames = {"Room", "Event Name", "ID", "Time", "Date", "Capacity", "Speakers", "VIP"};
                        organizerSchedulingSystem("getSchedule",guiPresenterInterface,"");
                    }
                });
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 3;
        gbc_btnNewButton.gridy = 1;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        btnNewButton_9 = new JButton("Generate PDF");
        GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
        gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_9.gridx = 4;
        gbc_btnNewButton_9.gridy = 1;
        contentPane.add(btnNewButton_9, gbc_btnNewButton_9);
        btnNewButton_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        organizerSchedulingSystem("GeneratePDF",guiPresenterInterface,"");
                    }
                });
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Add New Room:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 4;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);


        txtEnterNewRoom = new JTextField();
        txtEnterNewRoom.setEditable(true);
        txtEnterNewRoom.setForeground(Color.LIGHT_GRAY);
        txtEnterNewRoom.setText("Enter New Room");
        GridBagConstraints gbc_txtEnterNewRoom = new GridBagConstraints();
        gbc_txtEnterNewRoom.fill = GridBagConstraints.BOTH;
        gbc_txtEnterNewRoom.insets = new Insets(0, 0, 5, 5);
        gbc_txtEnterNewRoom.gridx = 3;
        gbc_txtEnterNewRoom.gridy = 4;
        contentPane.add(txtEnterNewRoom, gbc_txtEnterNewRoom);
        txtEnterNewRoom.setColumns(15);

        btnNewButton_1 = new JButton("Add Room");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder str = new StringBuilder();
                str.append(txtEnterNewRoom.getText());
                str.append(",");
                str.append(50);
                str.append(",");
                organizerSchedulingSystem("AddRoom",guiPresenterInterface,str.toString());
            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 4;
        gbc_btnNewButton_1.gridy = 4;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

        lblNewLabel_7 = new JLabel("See Conference Statistics");
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.gridx = 1;
        gbc_lblNewLabel_7.gridy = 9;
        contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

//        System.out.println("[1] App Traffic Statistics \n" + "[2] Top 5 Events - Highest attendance  \n" +
//                "[3] Top 5 Events - Lowest Attendance \n" + "[4] Basic Event Stats \n" +
//                "[5] Top 5 Speakers - Most Events Spoken At  \n" + "[6] Top 5 Speakers - Least Events Spoken At  \n");
//    }
        String statslist []= {"App Stats","AttendanceStats","EventStats","SpeakerStats"};
        comboBox_1 = new JComboBox(statslist);
        GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
        gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_1.gridx = 3;
        gbc_comboBox_1.gridy = 9;
        contentPane.add(comboBox_1, gbc_comboBox_1);

        btnNewButton_10 = new JButton("Show Statistics");
        GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
        gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_10.gridx = 4;
        gbc_btnNewButton_10.gridy = 9;
        contentPane.add(btnNewButton_10, gbc_btnNewButton_10);
        btnNewButton_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println((String)comboBox_1.getItemAt(comboBox_1.getSelectedIndex()));

                organizerSchedulingSystem("getStats",guiPresenterInterface, (String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex()));
            }
        });

        JLabel lblNewLabel_2 = new JLabel("Schedule Existing Events");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 6;
        contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

        JButton btnNewButton_2 = new JButton("See Events ");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                organizerSchedulingSystem("SeeEvents",guiPresenterInterface,"");
            }
        });
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_2.gridx = 3;
        gbc_btnNewButton_2.gridy = 6;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

        txtEventId = new JTextField();
        txtEventId.setText("Event ID");
        GridBagConstraints gbc_txtEventId = new GridBagConstraints();
        gbc_txtEventId.insets = new Insets(0, 0, 5, 5);
        gbc_txtEventId.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEventId.gridx = 3;
        gbc_txtEventId.gridy = 7;
        contentPane.add(txtEventId, gbc_txtEventId);
        txtEventId.setColumns(10);

        txtRoom = new JTextField();
        txtRoom.setText("Room");
        GridBagConstraints gbc_txtEndTime = new GridBagConstraints();
        gbc_txtEndTime.insets = new Insets(0, 0, 5, 5);
        gbc_txtEndTime.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEndTime.gridx = 4;
        gbc_txtEndTime.gridy = 7;
        contentPane.add(txtRoom, gbc_txtEndTime);
        txtRoom.setColumns(10);

        txtNewStart = new JTextField();
        txtNewStart.setText("Start Time");
        GridBagConstraints gbc_txtDate = new GridBagConstraints();
        gbc_txtDate.insets = new Insets(0, 0, 5, 5);
        gbc_txtDate.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDate.gridx = 5;
        gbc_txtDate.gridy = 7;
        contentPane.add(txtNewStart, gbc_txtDate);
        txtNewStart.setColumns(10);

        txtNewEnd = new JTextField();
        txtNewEnd.setText("End Time");
        GridBagConstraints gbc_txtStartTime = new GridBagConstraints();
        gbc_txtStartTime.anchor = GridBagConstraints.NORTH;
        gbc_txtStartTime.insets = new Insets(0, 0, 5, 5);
        gbc_txtStartTime.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtStartTime.gridx = 6;
        gbc_txtStartTime.gridy = 7;
        contentPane.add(txtNewEnd, gbc_txtStartTime);
        txtNewEnd.setColumns(10);

        txtCapacity = new JTextField();
        txtCapacity.setText("Capacity");
        GridBagConstraints gbc_txtCapacity = new GridBagConstraints();
        gbc_txtCapacity.insets = new Insets(0, 0, 5, 5);
        gbc_txtCapacity.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCapacity.gridx = 7;
        gbc_txtCapacity.gridy = 7;
        contentPane.add(txtCapacity, gbc_txtCapacity);
        txtCapacity.setColumns(10);

        txtDate_1 = new JTextField();
        txtDate_1.setText("Date");
        GridBagConstraints gbc_txtDate_1 = new GridBagConstraints();
        gbc_txtDate_1.insets = new Insets(0, 0, 5, 5);
        gbc_txtDate_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDate_1.gridx = 8;
        gbc_txtDate_1.gridy = 7;
        contentPane.add(txtDate_1, gbc_txtDate_1);
        txtDate_1.setColumns(10);

        String [] viplist = {"VIP Event","Not a VIP Event"};
        comboBox = new JComboBox(viplist);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 9;
        gbc_comboBox.gridy = 7;
        contentPane.add(comboBox, gbc_comboBox);


        btnNewButton_3 = new JButton("Add Event");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              StringBuilder str = new StringBuilder();
              str.append(txtEventId.getText());
              str.append(",");
                str.append(txtRoom.getText());
                str.append(",");
                str.append(txtNewStart.getText());
                str.append(",");
                str.append(txtNewEnd.getText());
                str.append(",");
                str.append(txtCapacity.getText());
                str.append(",");
                str.append(txtDate_1.getText());
                str.append(",");
                String vip = (String)comboBox.getItemAt(comboBox.getSelectedIndex());
                if(vip.equalsIgnoreCase("VIP Event"))
                {
                    str.append("VIP");
                }
                else
                {
                    str.append("Free");
                }
                organizerSchedulingSystem("ScheduleEvent",guiPresenterInterface,str.toString());
            }
        });
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_3.gridx = 6;
        gbc_btnNewButton_3.gridy = 8;
        contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

        lblNewLabel_3 = new JLabel("Create New Speaker Account");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 1;
        gbc_lblNewLabel_3.gridy = 10;
        contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

        txtNewSpeakerName = new JTextField();
        txtNewSpeakerName.setText("New name");
        GridBagConstraints gbc_txtNewSpeakerName = new GridBagConstraints();
        gbc_txtNewSpeakerName.insets = new Insets(0, 0, 5, 5);
        gbc_txtNewSpeakerName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNewSpeakerName.gridx = 3;
        gbc_txtNewSpeakerName.gridy = 10;
        contentPane.add(txtNewSpeakerName, gbc_txtNewSpeakerName);
        txtNewSpeakerName.setColumns(10);

        passwordField = new JTextField("Password For Speaker");
        passwordField.setToolTipText("Enter Password For Speaker");
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.gridx = 4;
        gbc_passwordField.gridy = 10;
        contentPane.add(passwordField, gbc_passwordField);

        btnNewButton_4 = new JButton("Add Speaker");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder str = new StringBuilder();
                str.append(txtNewSpeakerName.getText());
                str.append(",");
                str.append(passwordField.getText());
                organizerSchedulingSystem("AddSpeaker",guiPresenterInterface,str.toString());
            }
        });
        GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
        gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_4.gridx = 4;
        gbc_btnNewButton_4.gridy = 11;
        contentPane.add(btnNewButton_4, gbc_btnNewButton_4);

        lblNewLabel_4 = new JLabel("Assign Speaker to Event");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 1;
        gbc_lblNewLabel_4.gridy = 13;
        contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

        txtEventId_1 = new JTextField();
        txtEventId_1.setText("Event ID");
        GridBagConstraints gbc_txtEventId_1 = new GridBagConstraints();
        gbc_txtEventId_1.insets = new Insets(0, 0, 5, 5);
        gbc_txtEventId_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEventId_1.gridx = 3;
        gbc_txtEventId_1.gridy = 13;
        contentPane.add(txtEventId_1, gbc_txtEventId_1);
        txtEventId_1.setColumns(10);

        txtSpeakerName = new JTextField();
        txtSpeakerName.setText("Speaker Name");
        GridBagConstraints gbc_txtSpeakerName = new GridBagConstraints();
        gbc_txtSpeakerName.insets = new Insets(0, 0, 5, 5);
        gbc_txtSpeakerName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSpeakerName.gridx = 4;
        gbc_txtSpeakerName.gridy = 13;
        contentPane.add(txtSpeakerName, gbc_txtSpeakerName);
        txtSpeakerName.setColumns(10);

        btnNewButton_6 = new JButton("Assign Speaker");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder str = new StringBuilder();
                str.append(txtEventId_1.getText());
                str.append(",");
                str.append(txtSpeakerName.getText());
                organizerSchedulingSystem("AssignSpeaker",guiPresenterInterface,str.toString());
            }
        });
        GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
        gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_6.gridx = 4;
        gbc_btnNewButton_6.gridy = 14;
        contentPane.add(btnNewButton_6, gbc_btnNewButton_6);

        lblNewLabel_5 = new JLabel("Create New Attendee");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 1;
        gbc_lblNewLabel_5.gridy = 16;
        contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

        txtNewName = new JTextField();
        txtNewName.setText("New name");
        GridBagConstraints gbc_txtNewName = new GridBagConstraints();
        gbc_txtNewName.insets = new Insets(0, 0, 5, 5);
        gbc_txtNewName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNewName.gridx = 3;
        gbc_txtNewName.gridy = 16;
        contentPane.add(txtNewName, gbc_txtNewName);
        txtNewName.setColumns(10);

        passwordField_1 = new JTextField();
        passwordField_1.setToolTipText("New Password");
        GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
        gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField_1.gridx = 4;
        gbc_passwordField_1.gridy = 16;
        contentPane.add(passwordField_1, gbc_passwordField_1);

        String vip[] = {"No","Yes"};

        lblNewLabel_8 = new JLabel("VIP");
        GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
        gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_8.gridx = 5;
        gbc_lblNewLabel_8.gridy = 16;
        contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);

        comboBox_2 = new JComboBox(vip);
        GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
        gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_2.gridx = 6;
        gbc_comboBox_2.gridy = 16;
        contentPane.add(comboBox_2, gbc_comboBox_2);

        btnNewButton_5 = new JButton("Add Attendee");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder str = new StringBuilder();
                str.append(txtNewName.getText());
                str.append(",");
                str.append(passwordField_1.getText());
                str.append(",");
                String vip = (String)comboBox_2.getItemAt(comboBox_2.getSelectedIndex());
                System.out.println(vip);
                if(vip.equalsIgnoreCase("Yes"))
                {
                    str.append("VIP");
                }
                else
                {
                    str.append("Free");
                }
                System.out.println(str.toString());
                organizerSchedulingSystem("NewAttendee",guiPresenterInterface,str.toString());
            }
        });

        GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
        gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_5.gridx = 4;
        gbc_btnNewButton_5.gridy = 17;
        contentPane.add(btnNewButton_5, gbc_btnNewButton_5);

        lblNewLabel_6 = new JLabel("Cancel Event");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_6.gridx = 1;
        gbc_lblNewLabel_6.gridy = 18;
        contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

        txtEventId_2 = new JTextField();
        txtEventId_2.setText("Event ID");
        GridBagConstraints gbc_txtEventId_2 = new GridBagConstraints();
        gbc_txtEventId_2.insets = new Insets(0, 0, 0, 5);
        gbc_txtEventId_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEventId_2.gridx = 3;
        gbc_txtEventId_2.gridy = 18;
        contentPane.add(txtEventId_2, gbc_txtEventId_2);
        txtEventId_2.setColumns(10);

        btnNewButton_8 = new JButton("Cancel");
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                organizerSchedulingSystem("CancelEvent", guiPresenterInterface, txtEventId_2.getText());
            }
        });
        GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
        gbc_btnNewButton_8.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_8.gridx = 4;
        gbc_btnNewButton_8.gridy = 18;
        contentPane.add(btnNewButton_8, gbc_btnNewButton_8);


        btnNewButton_7 = new JButton("Main Menu");
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiPresenterInterface.openMenuForm(guiPresenterInterface);
                setVisible(true);
            }
        });
        GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
        gbc_btnNewButton_7.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_7.gridx = 7;
        gbc_btnNewButton_7.gridy = 18;
        contentPane.add(btnNewButton_7, gbc_btnNewButton_7);
    }

    /**
     * Run whenever a user clicks a button and/or puts in an input.
     * @param option name of the button being clicked
     * @param guiPresenterInterface interface for UIPresenter
     * @param userInput user input
     */
    private void organizerSchedulingSystem(String option, GUIPresenterInterface guiPresenterInterface,String userInput) {
        guiPresenterInterface.organizerSchedulingSystem(option, this, userInput);
    }

    /**
     * Implemented from SchedulingFormInterface, shows all events in the conference.
     * @param data list of events to be shown
     */
    @Override
    public void openEventsForm(String data) {
        EventsForm eventsForm = new EventsForm(data);
        eventsForm.setVisible(true);
    }

    /**
     * Factory method, displays form of the current class.
     * @param username username of user
     * @param usertype type of user
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void display(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrganizerSchedulingForm frame = new OrganizerSchedulingForm(username, guiPresenterInterface);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Implemented from SchedulingFormInterface, shows a popup message to the user.
     * @param message message to be popped up to user
     */
    @Override
    public void showMessage(String message){
        JOptionPane.showMessageDialog(getRootPane(),message);
    }

    /**
     * Factory method, displays form of the current class.
     * @param guiPresenterInterface interface for UIPresenter
     */
    @Override
    public void display(GUIPresenterInterface guiPresenterInterface) { }

}
