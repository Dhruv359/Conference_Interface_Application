package UI.Events;

import PresenterLayer.UIPresenter;
import UI.UserSchedulingForms.OrganizerSchedulingForm;
import UI.UserSchedulingForms.SpeakerSchedulingForm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventsForm extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextArea textArea;


    /**
     * Launch the application
     */


    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Create the frame.
     */
    public EventsForm(String data) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 659, 334);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        textArea = new JTextArea();
        textArea.setText(data);
        contentPane.add(textArea, BorderLayout.CENTER);
    }

}
