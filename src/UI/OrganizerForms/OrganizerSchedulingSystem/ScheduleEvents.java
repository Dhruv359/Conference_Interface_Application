package UI.OrganizerForms.OrganizerSchedulingSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ScheduleEvents extends JFrame {

    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScheduleEvents frame = new ScheduleEvents();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ScheduleEvents() {
        setTitle("Events to be scheduled");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 287, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        String [] coloumnNames  = { "Event Name", "ID" };
        String [][] data = {
                { "Event Name", "ID"},
                {"Event 1", "id26"}
        };


        table = new JTable(data,coloumnNames);
        contentPane.add(table, BorderLayout.CENTER);
    }

}
