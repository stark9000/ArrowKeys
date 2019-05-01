/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arrowkeys;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author xBerWise
 */
public class ArrowKeys {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {
            LookAndFeel win = new WindowsLookAndFeel();
            UIManager.setLookAndFeel(win);
        } catch (UnsupportedLookAndFeelException e) {
        }
        //new sscm().setVisible(true);
        new pad().setVisible(true);
    }
    
}
