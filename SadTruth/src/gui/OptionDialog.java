package gui;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.event.DocumentListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.plaf.DimensionUIResource;

import backend.Application;
import net.miginfocom.swing.MigLayout;

public class OptionDialog extends JDialog
{
    private static final long serialVersionUID = 3325463766607869978L;
 
    private JPanel panel = new JPanel();

    public OptionDialog()
    {
        panel.setLayout(new MigLayout());
        panel.setBackground(Color.BLACK);
        setIconImage(new ImageIcon("SadTruth\\images.jpg").getImage());
        setMinimumSize(new DimensionUIResource(650, 650));
        setSize(650, 650);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);

        JLabel birthdayLabel = new JLabel("Was ist dein Geburtstag? (TT.MM.JJJJ)");
        birthdayLabel.setFont(new Font("Arial", Font.BOLD, 20));
        birthdayLabel.setForeground(Color.WHITE);
        panel.add(birthdayLabel, "gapleft 50, wrap");
        
        JTextField birthdayField = new JTextField(10);
        panel.add(birthdayField, "gapleft 50, wrap");

        JLabel firstTimeSexLabel = new JLabel("Wann hast du deine Jungfräulichkeit verloren?");
        firstTimeSexLabel.setFont(new Font("Arial", Font.BOLD, 20));
        firstTimeSexLabel.setForeground(Color.WHITE);
        panel.add(firstTimeSexLabel, "gapleft 50, gaptop 20, wrap");

        JTextField firstTimeSexField = new JTextField(10);
        panel.add(firstTimeSexField, "gapleft 50, wrap");

        JButton confirmBtn = new JButton("Eingaben bestätigen");
        confirmBtn.setFont(new Font("Arial", Font.BOLD, 40));   
        confirmBtn.setForeground(Color.WHITE);    
        confirmBtn.setBackground(Color.RED);
        confirmBtn.setEnabled(false);

        confirmBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);

                if (!birthdayField.getText().isEmpty() && firstTimeSexField.getText().isEmpty())
                {
                    Application.mainGui = new MainGui(birthdayField.getText(), true);
                }
                else if (!firstTimeSexField.getText().isEmpty())
                {
                    Application.mainGui = new MainGui(firstTimeSexField.getText(), false);
                }

            }
        });

        panel.add(confirmBtn, "gapleft 50, gaptop 50, wrap");
        
        JButton tooMuchBtn = new JButton("Ich möchte gehen!");
        tooMuchBtn.setFont(new Font("Arial", Font.BOLD, 20));
        tooMuchBtn.setForeground(Color.WHITE);
        tooMuchBtn.setBackground(Color.RED);

        tooMuchBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        panel.add(tooMuchBtn, "gapleft 50, gaptop 50, wrap");

        birthdayField.getDocument().addDocumentListener(new DocumentListener() 
        {
            public void changedUpdate(DocumentEvent e) 
            {
                confirmBtn.setEnabled(true);
            }
            public void removeUpdate(DocumentEvent e) 
            {
                if (birthdayField.getText().isEmpty())
                {
                    confirmBtn.setEnabled(false);
                }
            }
            public void insertUpdate(DocumentEvent e) 
            {
                confirmBtn.setEnabled(true);
            }
        });

        firstTimeSexField.getDocument().addDocumentListener(new DocumentListener() 
        {
            public void changedUpdate(DocumentEvent e) 
            {
                confirmBtn.setEnabled(true);
            }
            public void removeUpdate(DocumentEvent e) 
            {
                if (birthdayField.getText().isEmpty())
                {
                    confirmBtn.setEnabled(false);
                }
            }
            public void insertUpdate(DocumentEvent e) 
            {
                confirmBtn.setEnabled(true);
            }
        });

        add(panel);
        revalidate();
        repaint();
    }
}
