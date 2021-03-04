package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import backend.Application;
import net.miginfocom.swing.MigLayout;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;

    private JPanel panel = new JPanel();

    public MainGui(String input, boolean virgin)
    {
        createMainGuiMenu(input, virgin);
        setTitle("Crying");
        setIconImage(new ImageIcon("SadTruth\\images.jpg").getImage());
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public void createMainGuiMenu(String input, boolean virgin)
    {
        panel.setLayout(new MigLayout("align 50% 50%"));
        panel.setBackground(Color.BLACK);

        JLabel seeYourFailureLabel = new JLabel();
        seeYourFailureLabel.setForeground(Color.WHITE);
        panel.add(seeYourFailureLabel, "wrap");

        Thread thread = new Thread()
        {
            public void run() 
            {
                while (!Thread.currentThread().isInterrupted())
                {
                    if (virgin)
                    {
                        Application.calcYourFailure(input);
                        seeYourFailureLabel.setFont(new Font("Arial", Font.BOLD, 80)); 
                        seeYourFailureLabel.setText("Du bist seit "+String.valueOf(Application.calcYourFailure(input)) + " Sekunden eine Jungfrau.");
                    }
                    else
                    {
                        seeYourFailureLabel.setFont(new Font("Arial", Font.BOLD, 50)); 
                        seeYourFailureLabel.setText("Du hast deine Jüngfräulichkeit am "+input+" verloren. Gratuliere.");
                    }

                    repaint();
                }
            }
        };
        thread.start();

        if (virgin)
        {
            JLabel twoOptionsPanel = new JLabel("Du hast jetzt zwei Möglichkeiten:");
            twoOptionsPanel.setFont(new Font("Arial", Font.BOLD, 40));
            twoOptionsPanel.setForeground(Color.WHITE);
            panel.add(twoOptionsPanel, "gaptop 50, wrap");

            JButton rageQuitBtn = new JButton("Weinen gehen...");
            rageQuitBtn.setFont(new Font("Arial", Font.BOLD, 40));
            rageQuitBtn.setBackground(Color.RED);
            rageQuitBtn.setForeground(Color.WHITE);
    
            rageQuitBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    thread.stop();
                    System.exit(0);
                }
            });
    
            panel.add(rageQuitBtn, "gaptop 50, wrap");
    
            JButton rageWixBtn = new JButton("Auf pornhub.com gehen...");
            rageWixBtn.setFont(new Font("Arial", Font.BOLD, 40));
            rageWixBtn.setBackground(Color.RED);
            rageWixBtn.setForeground(Color.WHITE);
    
            rageWixBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    thread.stop();

                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) 
                    {
                        try 
                        {
							Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
						} 
                        catch (Exception ex) 
                        {
							ex.printStackTrace();
						} 
                    }

                    System.exit(0);
                }
            });
    
            panel.add(rageWixBtn);
        }
        else
        {
            JLabel beHappyLabel = new JLabel("Du kannst glücklich sein.");
            beHappyLabel.setFont(new Font("Arial", Font.BOLD, 40));
            beHappyLabel.setForeground(Color.WHITE);
            panel.add(beHappyLabel, "gaptop 50, wrap");

            JButton beHappyBtn = new JButton("Glücklich sein...");
            beHappyBtn.setFont(new Font("Arial", Font.BOLD, 40));
            beHappyBtn.setBackground(Color.RED);
            beHappyBtn.setForeground(Color.WHITE);
    
            beHappyBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    thread.stop();
                    System.exit(0);
                }
            });

            panel.add(beHappyBtn, "gaptop 50");
        }

        add(panel);
    }
}
