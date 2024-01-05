package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Main {

    private static char turn = 'X';
    private static JLabel title2;
    private static ArrayList<JLabel> labels = new ArrayList<JLabel>();
    private static String winner = " ";
    private static char[][] chars = new char[3][3];
    private static boolean enabled = true;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Tic Tac Toe Game :))");
        frame.setSize(320, 470);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.white);

        JLabel title = new JLabel("Tic Tac Toe");
        title.setBackground(Color.white);
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
        title2.setBackground(Color.white);
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
        p.setLayout(new GridLayout(2,1));
        p.add(title2);

        JButton button = new JButton("New Game");
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reboot();
            }
        });
        p.add(button);

        frame.add(p, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    private static JLabel getLabel(int i, int j) {
        JLabel label = new JLabel(" ");
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setFont(new Font("Tahoma", Font.BOLD, 60));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        int m = i;
        int n = j;
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(enabled){
                    if(label.getText().equals(" ")){
                        label.setText(String.valueOf(turn));
                        chars[m][n] = turn;
                        if(turn=='X'){
                            label.setForeground(Color.BLUE);
                            turn = 'O';
                            title2.setText("Player 2, now it's you!");
                        }
                        else{
                            label.setForeground(Color.RED);
                            turn = 'X';
                            title2.setText("Player 1, we're waiting!");
                        }
                    }
                    else{
                        title2.setText("Position Taken!");
                    }
                    if(checkWinner()){
                        winner = winner.equals("X") ? "1" : "2";
                        title2.setText("Player " + winner + " wins!" );
                        if(winner.equals("1")) title2.setForeground(Color.blue);
                        else title2.setForeground(Color.red);
                        enabled = false;
                    }
                    else if (!draw()){
                        title2.setText("Draw!");
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
        title2.setForeground(Color.black);
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