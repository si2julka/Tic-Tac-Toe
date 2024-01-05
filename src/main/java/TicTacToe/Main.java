package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Main {

    private static char turn = 'X';
    private static JLabel title2;
    private static final ArrayList<JLabel> labels = new ArrayList<JLabel>();
    private static String winner = " ";
    private static char[][] chars = new char[3][3];
    private static boolean enabled = true;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Tic Tac Toe :))");
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.white);

        JLabel title = new JLabel("Tic Tac Toe");
        title.setBackground(new Color(0xb8c8bb));
        title.setForeground(Color.black);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tahoma", Font.BOLD, 30));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 10, 10));
        panel.setBackground(Color.black);

        title2 = new JLabel("Player 1, it's your turn");
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setBackground(new Color(0xb8c8bb));
        title2.setForeground(new Color(0x301c02));
        title2.setOpaque(true);
        title2.setFont(new Font("Tahoma", Font.BOLD, 20));
        title2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                JLabel label = getLabel(i, j);
                labels.add(label);
                panel.add(label);
            }
        }

        frame.add(panel, BorderLayout.CENTER);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(title2);

        JButton button = new JButton("| New Game |");
        button.setBackground(new Color(0xb8c8bb));
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.addActionListener(e -> reboot());
        p.add(button);
        frame.add(p, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    private static JLabel getLabel(int i, int j) {
        JLabel label = new JLabel(" ");
        label.setOpaque(true);
        label.setBackground(new Color(0xb8c8bb));
        label.setFont(new Font("Tahoma", Font.BOLD, 60));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(enabled){
                    if(label.getText().equals(" ")){
                        label.setText(String.valueOf(turn));
                        chars[i][j] = turn;
                        if(turn=='X'){
                            label.setForeground(new Color(0x301c02));
                            turn = 'O';
                            title2.setForeground(new Color(0x386642));
                            title2.setText("Player 2, now it's you!");
                        }
                        else{
                            label.setForeground(new Color(0x386642));
                            turn = 'X';
                            title2.setText("Player 1, we're waiting!");
                            title2.setForeground(new Color(0x301c02));
                        }
                    }
                    else{
                        title2.setText("Position Taken!");
                    }
                    if(checkWinner()){
                        if(winner.equals("X")){
                            title2.setForeground(new Color(0x301c02));
                            winner = "1";
                        }
                        else{
                            title2.setForeground(new Color(0x386642));
                            winner = "2";
                        }
                        title2.setText("!! Player " + winner + " wins !!" );
                        enabled = false;
                    }
                    else if (!draw()){
                        title2.setText("!! Draw !!");
                        enabled = false;
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return label;
    }

    private static boolean checkWinner() {
        boolean win = false;
        if(chars[0][0] == chars[0][1] && chars[0][0] == chars[0][2] && chars[0][0] != 0 ){
            win = true;
            winner = String.valueOf(chars[0][0]);
        }
        else if(chars[1][0] == chars[1][1] && chars[1][0] == chars[1][2] && chars[1][0] != 0){
            win = true;
            winner = String.valueOf(chars[1][0]);
        }
        else if(chars[2][0] == chars[2][1] && chars[2][0] == chars[2][2] && chars[2][0] != 0){
            win = true;
            winner = String.valueOf(chars[2][0]);
        }
        else if(chars[0][0] == chars[1][0] && chars[0][0] == chars[2][0] && chars[0][0] != 0){
            win = true;
            winner = String.valueOf(chars[0][0]);
        }
        else if(chars[0][1] == chars[1][1] && chars[0][1] == chars[2][1] && chars[0][1] != 0){
            win = true;
            winner = String.valueOf(chars[0][1]);
        }
        else if(chars[0][2] == chars[1][2] && chars[0][2] == chars[2][2] && chars[0][2] != 0){
            win = true;
            winner = String.valueOf(chars[0][2]);
        }
        else if(chars[0][0] == chars[1][1] && chars[0][0] == chars[2][2] && chars[0][0] != 0){
            win = true;
            winner = String.valueOf(chars[0][0]);
        }
        else if(chars[2][0] == chars[1][1] && chars[0][2] == chars[2][0] && chars[2][0] != 0){
            win = true;
            winner = String.valueOf(chars[2][0]);
        }
        return win;
    }

    private static void reboot(){
        chars = new char[3][3];
        winner = " ";
        title2.setText("Player 1, Let's begin!");
        turn = 'X';
        enabled = true;
        for( JLabel label : labels) {
            label.setText(" ");
        }
    }

    private static boolean draw(){
        boolean draw = false;
        for(char[] a : chars) {
            for(char b : a){
                if(b == 0){
                    draw = true;
                    break;
                }
            }
        }
        return draw;
    }
}