/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrowkeys;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author xBerWise
 */
public class pad extends javax.swing.JFrame {

    private static final int SPRITE_WIDTH = 1;
    private static final int SPRITE_HEIGHT = SPRITE_WIDTH;
    private static final String PRESSED = "Pressed";
    private static final String RELEASED = "Released";
    public static final int SPRITE_STEP = 2;
    private static final int SPRITE_STEP_PERIOD = 3;
    private int spriteX = 0;
    private int spriteY = 0;
    private final Map<Direction, Boolean> directionMap = new HashMap<>();

    Object object = null;
    Vector EVENT_LOG = new Vector();

    /**
     * Creates new form NewJFrame1
     */
    public pad() {
        initComponents();

        spriteX = (1 - SPRITE_WIDTH);
        spriteY = (1 - SPRITE_HEIGHT);
        for (Direction direction : Direction.values()) {
            directionMap.put(direction, Boolean.FALSE);
        }
        setBindings();
        Timer timer = new Timer(SPRITE_STEP_PERIOD, new GameLoopListener());
        timer.start();
    }

    private void setBindings() {
        int context = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getRootPane().getInputMap(context);
        ActionMap actionMap = getRootPane().getActionMap();
        for (Direction direction : Direction.values()) {
            inputMap.put(KeyStroke.getKeyStroke(direction.getKeyCode(), 0, false),
                    direction.getName() + PRESSED);
            inputMap.put(KeyStroke.getKeyStroke(direction.getKeyCode(), 0, true),
                    direction.getName() + RELEASED);
            actionMap.put(direction.getName() + PRESSED, new ArrowKeyAction(true, direction));
            actionMap.put(direction.getName() + RELEASED, new ArrowKeyAction(false, direction));

        }

    }

    private class ArrowKeyAction extends AbstractAction {

        private Boolean pressed;
        private Direction direction;

        public ArrowKeyAction(boolean pressed, Direction direction) {
            this.pressed = Boolean.valueOf(pressed);
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            directionMap.put(direction, pressed);
        }
    }

    private class GameLoopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int x0 = spriteX;
            int y0 = spriteY;

            for (Direction direction : Direction.values()) {
                if (directionMap.get(direction)) {
                    spriteX += SPRITE_STEP * direction.getVector().x;
                    spriteY += SPRITE_STEP * direction.getVector().y;
                }
            }
            int x = Math.min(x0, spriteX);
            int y = Math.min(y0, spriteY);
            int w = Math.max(x0, spriteX) - x + SPRITE_WIDTH;
            int h = Math.max(y0, spriteY) - y + SPRITE_HEIGHT;

            if (w == 45 && h == 3) {
                System.out.println("up");
                LogEvent("up");
                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/u.png")));
            } else {
                jLabel4.setIcon(null);
            }
            if (w == 75 && h == 3) {
                System.out.println("down");
                LogEvent("down");
                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/d.png")));
            } else {
                jLabel5.setIcon(null);
            }
            if (w == 133 && h == 1) {
                System.out.println("left");
                LogEvent("left");
                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/l.png")));
            } else {
                jLabel2.setIcon(null);
            }

            if (w == 3 && h == 1) {
                System.out.println("right");
                LogEvent("right");
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/r.png")));
            } else {
                jLabel3.setIcon(null);
            }
            if (w == 89 && h == 3) {
                System.out.println("up-left");
                LogEvent("up-left");
                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/u.png")));
                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/l.png")));
            }
            if (w == 47 && h == 3) {
                System.out.println("up-right");
                LogEvent("up-right");
                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/u.png")));
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/r.png")));
            }
            if (w == 59 && h == 3) {
                System.out.println("down-left");
                LogEvent("down-left");
                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/d.png")));
                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/l.png")));
            }
            if (w == 77 && h == 3) {
                System.out.println("down-right");
                LogEvent("down-right");
                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/d.png")));
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/r.png")));
            }

        }
    }

    enum Direction {

        UP("Up", KeyEvent.VK_UP, new Point(22, -1)),
        DOWN("Down", KeyEvent.VK_DOWN, new Point(37, 1)),
        LEFT("Left", KeyEvent.VK_LEFT, new Point(-66, 0)),
        Right("Right", KeyEvent.VK_RIGHT, new Point(1, 0));

        private final String name;
        private final int keyCode;
        private final Point vector;

        private Direction(String name, int keyCode, Point vector) {
            this.name = name;
            this.keyCode = keyCode;
            this.vector = vector;
        }

        public String getName() {
            return name;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public Point getVector() {
            return vector;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private void LogEvent(Object Event) {
        EVENT_LOG.add(Event);
        jList1.setListData(EVENT_LOG);
        int lastIndex = jList1.getModel().getSize() - 1;
        if (lastIndex >= 0) {
            jList1.ensureIndexIsVisible(lastIndex);
            jList1.setSelectedIndex(lastIndex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList1.setForeground(new java.awt.Color(0, 51, 255));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setFocusable(false);
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 270, 260));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("arrowkeys/Bundle"); // NOI18N
        jLabel1.setText(bundle.getString("pad.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/l.png"))); // NOI18N
        jLabel2.setText(bundle.getString("pad.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 104, 90, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/r.png"))); // NOI18N
        jLabel3.setText(bundle.getString("pad.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 104, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/u.png"))); // NOI18N
        jLabel4.setText(bundle.getString("pad.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 90));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/d.png"))); // NOI18N
        jLabel5.setText(bundle.getString("pad.jLabel5.text")); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 90, 90));

        jLabel6.setText(bundle.getString("pad.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 80, 70));

        setSize(new java.awt.Dimension(608, 382));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new pad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
