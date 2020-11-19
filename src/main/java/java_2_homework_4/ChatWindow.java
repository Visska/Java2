package java_2_homework_4;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.awt.*;


public class ChatWindow extends JFrame {
    public ChatWindow() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 500, 500, 500);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;


        JPanel jpNickNames = new JPanel();
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.ipady = 500;
        constraints.ipadx = 150;
        add(jpNickNames, constraints);
        jpNickNames.setBackground(new Color(124, 124, 124));


        JPanel jpChat = new JPanel();
        constraints.gridx = 1;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.ipady = 450;
        constraints.ipadx = 350;
        add(jpChat, constraints);
        jpChat.setBackground(new Color(255, 200, 255));


        jpChat.setLayout(new BorderLayout());
        JTextPane jta = new JTextPane();
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        jpChat.add(jsp);


        JPanel jpForm = new JPanel();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.ipady = 50;
        constraints.ipadx = 350;
        add(jpForm, constraints);
        jpForm.setBackground(new Color(200, 200, 255));


        JTextField textField = new JTextField();

        textField.addActionListener(e -> sendMessage(textField, jta));

        JButton btnSend = new JButton("Send");

        btnSend.addActionListener(e -> sendMessage(textField, jta));

        GroupLayout layout = new GroupLayout(jpForm);
        jpForm.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        layout.setHorizontalGroup(
                layout
                .createSequentialGroup()
                .addGroup(
                        layout
                        .createParallelGroup(LEADING)
                        .addComponent(textField)
                )
                .addGroup(
                        layout
                        .createParallelGroup(LEADING)
                        .addComponent(btnSend)
                )
        );


        layout.setVerticalGroup(
                layout
                .createSequentialGroup()
                .addGroup(
                        layout
                        .createParallelGroup(BASELINE)
                        .addComponent(textField)
                        .addComponent(btnSend)
                )
        );

        setVisible(true);
    }

    private void sendMessage(JTextField textField, JTextPane target)
    {
        System.out.println("Your message: " + textField.getText());

        target.setText(target.getText() + "You: "+textField.getText()+"\n");

        textField.setText("");
    }


}
