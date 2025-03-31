import javax.swing.*;

public class BattleshipMessages
{
    public static void showMessageDialog(String title, String message){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean showConfirmationDialog(String title, String message){
        int response = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }
}
