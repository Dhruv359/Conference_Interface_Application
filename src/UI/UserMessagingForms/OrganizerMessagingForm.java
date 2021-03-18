package UI.UserMessagingForms;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;
import UI.Events.EventsForm;
import UI.FormFactory.FormFactoryInterface;

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
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrganizerMessagingForm extends JFrame implements FormFactoryInterface,OrganizerMessagingFormInterface {

    private JPanel contentPane;
    private JTextField txtInputUsername;
    private JTextField txtInputUsername_1;
    private JTextField textField;
    private JTextField txtInputUsername_2;
    private JTextField txtInputUsername_3;
    private JTextField txtInputMessage;
    private JTextArea txtrShowUsernames;
    private JTextArea txtrShowContactsHere;
    private JTextArea txtrShowConversationWith;
    private JComboBox comboBox;

    /**
     * Displays the conversation between two people in the GUI.
     * @param conversationWith conversation between two people
     */
    public void setTxtrShowConversationWith(String conversationWith) {
        this.txtrShowConversationWith.setText(conversationWith);
    }

    /**
     * Displays the contacts of the attendee in the GUI.
     * @param contacts contacts of the attendee
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
    public OrganizerMessagingForm(String name,GUIPresenterInterface guiPresenterInterface) {
        setResizable(false);
        setAlwaysOnTop(true);
        setType(Type.UTILITY);
        setAutoRequestFocus(false);
        setTitle("Organizer Messaging System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1001, 745);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 187, 0, 227, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 117, 0, 0, 0, 0, 0, 0, 0, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("View Messages:");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        JButton btnNewButton_8 = new JButton("See Messaged Users ");
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to get all the Contacts of an SPEAKER and display in text area - txtrShowUsernames.
                organizerMessagingSystem("ViewContactsMessaged",guiPresenterInterface,"");
            }
        });
        GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
        gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_8.gridx = 6;
        gbc_btnNewButton_8.gridy = 1;
        contentPane.add(btnNewButton_8, gbc_btnNewButton_8);

        txtrShowUsernames = new JTextArea();
        txtrShowUsernames.setBackground(Color.BLACK);
        txtrShowUsernames.setEnabled(false);
        txtrShowUsernames.setText("Show UserNames");
        txtrShowUsernames.setTabSize(14);
        txtrShowUsernames.setRows(6);
        txtrShowUsernames.setColumns(12);
        //txtrShowUsernames.setDropMode(DropMode.INSERT_ROWS);
        txtrShowUsernames.setLineWrap(true);
        txtrShowUsernames.setSize(300,300);
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

        JLabel lblNewLabel_3 = new JLabel("See Messeges with:");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 6;
        gbc_lblNewLabel_3.gridy = 2;
        contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

        JLabel lblNewLabel_1 = new JLabel("Recipient type");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 7;
        gbc_lblNewLabel_1.gridy = 5;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);


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
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Code to get all messages for this Attendee with the User provided and display in textField.
                organizerMessagingSystem("ShowMessages", guiPresenterInterface, txtInputUsername.getText());

            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 8;
        gbc_btnNewButton.gridy = 2;
        contentPane.add(btnNewButton, gbc_btnNewButton);

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

        JButton btnNewButton_1 = new JButton("Send ");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ADD CODE TO SEND MESSAGE TO GIVEN USER SPECIFIED BY THEIR USERNAME
                StringBuilder str = new StringBuilder();
                str.append(txtInputUsername_1.getText());
                str.append(",");
                str.append(comboBox.getSelectedItem().toString());
                str.append(",");
                str.append(textField.getText());
                str.append(",");
                organizerMessagingSystem("SendMessage", guiPresenterInterface, str.toString());

            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 9;
        gbc_btnNewButton_1.gridy = 6;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

        JLabel lblNewLabel_7 = new JLabel("See Contacts:");
        lblNewLabel_7.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.gridx = 3;
        gbc_lblNewLabel_7.gridy = 9;
        contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

        JButton btnNewButton_2 = new JButton("Show My Contacts ");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ADD CODE TO SHOW ALL CONTACTS OF THIS ORGANIZER
                organizerMessagingSystem("ShowContacts", guiPresenterInterface, txtInputUsername_2.getText());

            }
        });
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_2.gridx = 6;
        gbc_btnNewButton_2.gridy = 9;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

        txtrShowContactsHere = new JTextArea();
        txtrShowContactsHere.setWrapStyleWord(true);
        txtrShowContactsHere.setForeground(Color.WHITE);
        txtrShowContactsHere.setBackground(Color.BLACK);
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
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                organizerMessagingSystem("AddContact", guiPresenterInterface, txtInputUsername_2.getText());
            }
        });
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_3.gridx = 7;
        gbc_btnNewButton_3.gridy = 11;
        contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

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
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //CODE TO DELETE A PARTICULAR CONTACT.
                organizerMessagingSystem("DeleteContact", guiPresenterInterface, txtInputUsername_3.getText());

            }
        });
        GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
        gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_4.gridx = 7;
        gbc_btnNewButton_4.gridy = 13;
        contentPane.add(btnNewButton_4, gbc_btnNewButton_4);

        JLabel lblNewLabel_10 = new JLabel("Message All Attendees:");
        lblNewLabel_10.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
        gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_10.gridx = 3;
        gbc_lblNewLabel_10.gridy = 15;
        contentPane.add(lblNewLabel_10, gbc_lblNewLabel_10);

        txtInputMessage = new JTextField();
        txtInputMessage.setBackground(Color.LIGHT_GRAY);
        txtInputMessage.setText("Input Message");
        GridBagConstraints gbc_txtInputMessage = new GridBagConstraints();
        gbc_txtInputMessage.gridwidth = 2;
        gbc_txtInputMessage.gridheight = 3;
        gbc_txtInputMessage.insets = new Insets(0, 0, 5, 5);
        gbc_txtInputMessage.fill = GridBagConstraints.BOTH;
        gbc_txtInputMessage.gridx = 6;
        gbc_txtInputMessage.gridy = 15;
        contentPane.add(txtInputMessage, gbc_txtInputMessage);
        txtInputMessage.setColumns(10);

        JButton btnNewButton_5 = new JButton("Send");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder str = new StringBuilder();
                // str.append(USERTYPE)
                str.append(txtInputMessage.getText());
                //CODE TO SEND MESSAGE TO ALL ATTENDEES IN THE SYSTEM
                organizerMessagingSystem("MessageMultiple", guiPresenterInterface, str.toString());
            }
        });
        GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
        gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_5.gridx = 8;
        gbc_btnNewButton_5.gridy = 16;
        contentPane.add(btnNewButton_5, gbc_btnNewButton_5);

        JLabel lblNewLabel_11 = new JLabel("Message All Speakers:");
        lblNewLabel_11.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
        GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
        gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_11.gridx = 3;
        gbc_lblNewLabel_11.gridy = 19;
        contentPane.add(lblNewLabel_11, gbc_lblNewLabel_11);

        JTextArea txtrInputMessage = new JTextArea();
        txtrInputMessage.setTabSize(5);
        txtrInputMessage.setRows(10);
        txtrInputMessage.setLineWrap(true);
        txtrInputMessage.setColumns(10);
        txtrInputMessage.setText("Input Message");
        txtrInputMessage.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_txtrInputMessage = new GridBagConstraints();
        gbc_txtrInputMessage.gridheight = 2;
        gbc_txtrInputMessage.gridwidth = 2;
        gbc_txtrInputMessage.insets = new Insets(0, 0, 0, 5);
        gbc_txtrInputMessage.fill = GridBagConstraints.BOTH;
        gbc_txtrInputMessage.gridx = 6;
        gbc_txtrInputMessage.gridy = 19;
        contentPane.add(txtrInputMessage, gbc_txtrInputMessage);

        JButton btnNewButton_6 = new JButton("Send");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder str = new StringBuilder();
                str.append(txtInputUsername_1.getText());
                // str.append(USERTYPE);
                str.append(",");
                str.append(textField.getText());
                str.append(",");
                organizerMessagingSystem("SendMessage", guiPresenterInterface, str.toString());
            }
        });
        GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
        gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_6.gridx = 8;
        gbc_btnNewButton_6.gridy = 19;
        contentPane.add(btnNewButton_6, gbc_btnNewButton_6);

        JButton btnNewButton_7 = new JButton("Main Menu");
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiPresenterInterface.openMenuForm(guiPresenterInterface);
                setVisible(false);
            }
        });
        btnNewButton_7.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
        gbc_btnNewButton_7.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_7.gridx = 9;
        gbc_btnNewButton_7.gridy = 19;
        contentPane.add(btnNewButton_7, gbc_btnNewButton_7);
    }

    /**
     * Runs the button option chosen by the user.
     * @param option button option chosen
     * @param guiPresenterInterface the presenter interface of the GUI
     * @param userinput details inputed by the user
     */
    private void organizerMessagingSystem(String option, GUIPresenterInterface guiPresenterInterface, String userinput) {
        guiPresenterInterface.organizerMessagingSystem(option,(OrganizerMessagingFormInterface) this,userinput);
    }

    /**
     * Shows a message in the GUI.
     * @param message message content
     */
    public void showMessage(String message){
        JOptionPane.showMessageDialog(getRootPane(),message);
    }

    /**
     * Opens the event form.
     * @param data list of events to be shown
     */
    public void openEventsForm(String data)
    {
        EventsForm eventsForm = new EventsForm(data);
        eventsForm.setVisible(true);
    }

    public void printToTxtAre(){

    }

    /**
     * Displays the organizerMessagingForm.
     * @param username username of the user
     * @param usertype usertype of the user
     * @param guiPresenterInterface presenter Interface of the GUI
     */
    @Override
    public void display(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrganizerMessagingForm frame = new OrganizerMessagingForm(username,guiPresenterInterface);
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
