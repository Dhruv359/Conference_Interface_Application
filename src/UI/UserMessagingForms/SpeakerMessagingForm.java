package UI.UserMessagingForms;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;
import UI.Events.EventsForm;
import UI.FormFactory.FormFactoryInterface;
import UI.UserSchedulingForms.SpeakerFormInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class SpeakerMessagingForm extends JFrame implements FormFactoryInterface, SpeakerMessagingFormInterface {

    private JPanel contentPane;
    private JTextField txtInputUsername;
    private JTextField txtInputUsername_1;
    private JTextField textField;
    private JTextField txtInputUsertype;
    private JTextField txtInputUsername_2;
    private JTextField txtInputUsername_3;
    private JTextArea txtrShowUsernames;
    private JTextArea txtrShowConversationWith;
    private JTextArea txtrShowContactsHere;
    private JComboBox comboBox;

    /**
     * Launch the application.
     */


    /**
     * Displays the conversation between two people in the GUI.
     * @param conversationWith conversation between two people
     */
    public void setTxtrShowConversationWith(String conversationWith) {
        this.txtrShowConversationWith.setText(conversationWith);
    }

    /**
     * Displays the contacts of the speaker in the GUI.
     * @param contacts contacts of the speaker
     */
    public void setTxtrShowContactsHere(String contacts) {
        this.txtrShowContactsHere.setText(contacts);
    }

    /**
     * Displays the usernames of the contacts in the GUI.
     * @param showUsernames usernames of the contacts
     */
    public void setTxtrShowUsernames(String showUsernames) {
        this.txtrShowUsernames.setText(showUsernames);
    }


    /**
     * Create the frame.
     */
    public SpeakerMessagingForm(String name, GUIPresenterInterface guiPresenterInterface) {
        setAlwaysOnTop(true);
        setType(Type.UTILITY);
        setAutoRequestFocus(false);
        setTitle("Speaker Messaging System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1001, 599);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 183, 0, 0, 0, 187, 0, 227, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 117, 0, 0, 0, 0, 0, 0, 0, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("View Messages:");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        JButton btnNewButton_8 = new JButton("See Messaged Users ");
        GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
        gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_8.gridx = 6;
        gbc_btnNewButton_8.gridy = 1;
        contentPane.add(btnNewButton_8, gbc_btnNewButton_8);

        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerMessagingSystem("ViewContacts", guiPresenterInterface, "");
            }
        });

        txtrShowUsernames = new JTextArea("Display MessagedUsers");
        txtrShowUsernames.setBackground(Color.LIGHT_GRAY);
        txtrShowUsernames.setEnabled(false);
        txtrShowUsernames.setText("Show UserNames");
        txtrShowUsernames.setTabSize(14);
        txtrShowUsernames.setRows(6);
        txtrShowUsernames.setColumns(12);
        //txtrShowUsernames.setDropMode(DropMode.INSERT_ROWS);
        txtrShowUsernames.setLineWrap(true);
        txtrShowUsernames.setSize(300, 300);
        GridBagConstraints gbc_txtrShowUsernames = new GridBagConstraints();
        gbc_txtrShowUsernames.insets = new Insets(0, 0, 5, 5);
        gbc_txtrShowUsernames.fill = GridBagConstraints.BOTH;
        gbc_txtrShowUsernames.gridx = 7;
        gbc_txtrShowUsernames.gridy = 1;
        contentPane.add(txtrShowUsernames, gbc_txtrShowUsernames);

        txtrShowConversationWith = new JTextArea();
        txtrShowConversationWith.setBackground(Color.LIGHT_GRAY);
        txtrShowConversationWith.setText("Show Conversation with user");
        txtrShowConversationWith.setRows(6);
        txtrShowConversationWith.setLineWrap(true);
        txtrShowConversationWith.setColumns(12);
        GridBagConstraints gbc_txtrShowConversationWith = new GridBagConstraints();
        gbc_txtrShowConversationWith.fill = GridBagConstraints.BOTH;
        gbc_txtrShowConversationWith.insets = new Insets(0, 0, 5, 5);
        gbc_txtrShowConversationWith.gridx = 9;
        gbc_txtrShowConversationWith.gridy = 1;
        contentPane.add(txtrShowConversationWith, gbc_txtrShowConversationWith);

        JLabel lblNewLabel_3 = new JLabel("See Messages with:");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 6;
        gbc_lblNewLabel_3.gridy = 2;
        contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);



        String usertype[] = {"Attendee", "Speaker", "Organizer"};
        comboBox = new JComboBox(usertype);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 8;
        gbc_comboBox.gridy = 5;
        contentPane.add(comboBox, gbc_comboBox);


        txtInputUsername = new JTextField();
        txtInputUsername.setText("Input UserName");
        GridBagConstraints gbc_txtInputUsername = new GridBagConstraints();
        gbc_txtInputUsername.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtInputUsername.insets = new Insets(0, 0, 5, 5);
        gbc_txtInputUsername.gridx = 7;
        gbc_txtInputUsername.gridy = 2;
        contentPane.add(txtInputUsername, gbc_txtInputUsername);
        txtInputUsername.setColumns(10);

        JButton btnNewButton = new JButton("Show Messages");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 8;
        gbc_btnNewButton.gridy = 2;
        contentPane.add(btnNewButton, gbc_btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerMessagingSystem("ShowMessages", guiPresenterInterface, txtInputUsername.getText());
            }
        });

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBackground(Color.BLACK);
        GridBagConstraints gbc_separator = new GridBagConstraints();
        gbc_separator.gridwidth = 7;
        gbc_separator.insets = new Insets(0, 0, 5, 5);
        gbc_separator.gridx = 3;
        gbc_separator.gridy = 3;
        contentPane.add(separator, gbc_separator);

        JLabel lblNewLabel_4 = new JLabel("Send Message: ");
        lblNewLabel_4.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 3;
        gbc_lblNewLabel_4.gridy = 6;
        contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("To:");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 5;
        gbc_lblNewLabel_5.gridy = 6;
        contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

        txtInputUsername_1 = new JTextField();
        txtInputUsername_1.setText("Input Username");
        GridBagConstraints gbc_txtInputUsername_1 = new GridBagConstraints();
        gbc_txtInputUsername_1.insets = new Insets(0, 0, 5, 5);
        gbc_txtInputUsername_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtInputUsername_1.gridx = 6;
        gbc_txtInputUsername_1.gridy = 6;
        contentPane.add(txtInputUsername_1, gbc_txtInputUsername_1);
        txtInputUsername_1.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Message String:");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_6.gridx = 7;
        gbc_lblNewLabel_6.gridy = 6;
        contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 8;
        gbc_textField.gridy = 6;
        contentPane.add(textField, gbc_textField);
        textField.setColumns(10);

        JLabel lblNewLabel_x = new JLabel(("Recipient Type:"));
        GridBagConstraints gbc_lblNewLabel_x = new GridBagConstraints();
        gbc_lblNewLabel_x.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_x.insets = new Insets(0,0,5,5);
        gbc_lblNewLabel_x.gridx = 7;
        gbc_lblNewLabel_x.gridy = 5;
        contentPane.add(lblNewLabel_x,gbc_lblNewLabel_x);




        JButton btnNewButton_1 = new JButton("Send ");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 9;
        gbc_btnNewButton_1.gridy = 6;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //code to send Message to Particular Contact
                StringBuilder str = new StringBuilder();
                str.append(txtInputUsername_1.getText());
                str.append(",");
                str.append(comboBox.getSelectedItem().toString());
                str.append(",");
                str.append(textField.getText());
                str.append(",");

                speakerMessagingSystem("SendMessage", guiPresenterInterface, str.toString());
            }
        });

        JLabel lblNewLabel_7 = new JLabel("See Contacts:");
        lblNewLabel_7.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.gridx = 3;
        gbc_lblNewLabel_7.gridy = 9;
        contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

        JButton btnNewButton_2 = new JButton("Show My Contacts ");
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_2.gridx = 6;
        gbc_btnNewButton_2.gridy = 9;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerMessagingSystem("ShowContacts", guiPresenterInterface, "");
            }
        });

        txtrShowContactsHere = new JTextArea();
        txtrShowContactsHere.setWrapStyleWord(true);
        txtrShowContactsHere.setForeground(Color.LIGHT_GRAY);
        txtrShowContactsHere.setBackground(Color.LIGHT_GRAY);
        txtrShowContactsHere.setText("Show Contacts Here ");
        GridBagConstraints gbc_txtrShowContactsHere = new GridBagConstraints();
        gbc_txtrShowContactsHere.gridheight = 2;
        gbc_txtrShowContactsHere.insets = new Insets(0, 0, 5, 5);
        gbc_txtrShowContactsHere.fill = GridBagConstraints.BOTH;
        gbc_txtrShowContactsHere.gridx = 7;
        gbc_txtrShowContactsHere.gridy = 8;
        contentPane.add(txtrShowContactsHere, gbc_txtrShowContactsHere);

        JLabel lblNewLabel_8 = new JLabel("Add Contacts:");
        lblNewLabel_8.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
        gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_8.gridx = 3;
        gbc_lblNewLabel_8.gridy = 11;
        contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);

        txtInputUsername_2 = new JTextField();
        txtInputUsername_2.setText("Input UserName");
        GridBagConstraints gbc_txtInputUsername_2 = new GridBagConstraints();
        gbc_txtInputUsername_2.insets = new Insets(0, 0, 5, 5);
        gbc_txtInputUsername_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtInputUsername_2.gridx = 6;
        gbc_txtInputUsername_2.gridy = 11;
        contentPane.add(txtInputUsername_2, gbc_txtInputUsername_2);
        txtInputUsername_2.setColumns(10);

        JButton btnNewButton_3 = new JButton("Add");
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_3.gridx = 7;
        gbc_btnNewButton_3.gridy = 11;
        contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerMessagingSystem("AddContact", guiPresenterInterface, txtInputUsername_2.getText());
            }
        });

        JLabel lblNewLabel_9 = new JLabel("Delete Contact :");
        lblNewLabel_9.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
        gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_9.gridx = 3;
        gbc_lblNewLabel_9.gridy = 13;
        contentPane.add(lblNewLabel_9, gbc_lblNewLabel_9);

        txtInputUsername_3 = new JTextField();
        txtInputUsername_3.setText("Input UserName");
        GridBagConstraints gbc_txtInputUsername_3 = new GridBagConstraints();
        gbc_txtInputUsername_3.insets = new Insets(0, 0, 5, 5);
        gbc_txtInputUsername_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtInputUsername_3.gridx = 6;
        gbc_txtInputUsername_3.gridy = 13;
        contentPane.add(txtInputUsername_3, gbc_txtInputUsername_3);
        txtInputUsername_3.setColumns(10);

        JButton btnNewButton_4 = new JButton("Delete");
        GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
        gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_4.gridx = 7;
        gbc_btnNewButton_4.gridy = 13;
        contentPane.add(btnNewButton_4, gbc_btnNewButton_4);
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerMessagingSystem("DeleteContact", guiPresenterInterface, txtInputUsername_3.getText());
            }
        });

        JLabel lblNewLabel_22 = new JLabel("Message All Attendees");
        lblNewLabel_22.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints();
        gbc_lblNewLabel_22.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_22.gridx = 3;
        gbc_lblNewLabel_22.gridy = 15;
        contentPane.add(lblNewLabel_22, gbc_lblNewLabel_22);

        JTextArea txtrInputMessageString = new JTextArea();
        txtrInputMessageString.setText("Input Message String");
        txtrInputMessageString.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_txtrInputMessageString = new GridBagConstraints();
        gbc_txtrInputMessageString.gridheight = 3;
        gbc_txtrInputMessageString.gridwidth = 2;
        gbc_txtrInputMessageString.insets = new Insets(0, 0, 5, 5);
        gbc_txtrInputMessageString.fill = GridBagConstraints.BOTH;
        gbc_txtrInputMessageString.gridx = 6;
        gbc_txtrInputMessageString.gridy = 15;
        contentPane.add(txtrInputMessageString, gbc_txtrInputMessageString);

        JButton btnNewButton_5 = new JButton("Send Message");
        GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
        gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_5.gridx = 8;
        gbc_btnNewButton_5.gridy = 16;
        contentPane.add(btnNewButton_5, gbc_btnNewButton_5);
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakerMessagingSystem("MessageAllAttendees", guiPresenterInterface, txtrInputMessageString.getText());
                setVisible(false);
            }
        });

        JButton btnNewButton_6 = new JButton("Main Menu");
        btnNewButton_6.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        btnNewButton_6.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
        gbc_btnNewButton_6.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_6.gridx = 9;
        gbc_btnNewButton_6.gridy = 17;
        contentPane.add(btnNewButton_6, gbc_btnNewButton_6);
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiPresenterInterface.openMenuForm(guiPresenterInterface);
                setVisible(false);
            }
        });
    }


    /**
     * Displays the speakerMessagingForm.
     * @param username username of the user
     * @param usertype usertype of the user
     * @param guiPresenterInterface presenter Interface of the GUI
     */
    @Override
    public void display(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SpeakerMessagingForm frame = new SpeakerMessagingForm(username, guiPresenterInterface);
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

    /**
     * Runs the button option chosen by the user.
     * @param option button option chosen
     * @param guiPresenterInterface the presenter interface of the GUI
     * @param userinput details inputed by the user
     */
    private void speakerMessagingSystem(String option, GUIPresenterInterface guiPresenterInterface, String userinput) {
        guiPresenterInterface.speakerMessagingSystem(option,(SpeakerMessagingFormInterface) this, userinput);
    }

    /**
     * Shows a message in the GUI.
     * @param message message content
     */
    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(getRootPane(), message);
    }

    /**
     * Opens the event form.
     * @param data list of events to be shown
     */
    @Override
    public void openEventsForm(String data) {
        EventsForm eventsForm = new EventsForm(data);
        eventsForm.setVisible(true);
    }
}
