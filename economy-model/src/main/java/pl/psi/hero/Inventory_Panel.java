package pl.psi.hero;
import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.awt.AWTEventMulticaster.add;
import static javax.swing.text.StyleConstants.setBackground;

public class Inventory_Panel {
    /*
    private Inventory current_items;
     * This class represents the inventory panel in a game. It allows for drag-and-drop functionality
     * to manage items within the inventory. The items can be moved around and their effects can be triggered
     * when dropped in a valid location.

    public Inventory_Panel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setLayout(null); // Using absolute positioning for drag-and-drop

        // Example items in the inventory
        Inventory sword = new Inventory();

        // Create item icons
        JLabel swordLabel = createItemLabel(sword);

        // Add items to the panel
        add(swordLabel);

        // Set positions of items
        swordLabel.setBounds(50, 50, 100, 100);

        // Drag-and-drop handling
        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(swordLabel, DnDConstants.ACTION_COPY, new DragGestureListener() {
            public void dragGestureRecognized(DragGestureEvent dge) {
                Cursor cursor = null;
                JLabel label = (JLabel) dge.getComponent();
                current_items = sword;
                BufferedImage image = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = image.createGraphics();
                label.paint(g2);
                g2.dispose();
                cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
                dge.startDrag(cursor, image, new Point(0, 0), new Transferable() {
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[]{DataFlavor.imageFlavor};
                    }

                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return DataFlavor.imageFlavor.equals(flavor);
                    }

                    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                        if (!isDataFlavorSupported(flavor)) {
                            throw new UnsupportedFlavorException(flavor);
                        }
                        return image;
                    }
                });
            }
        });
    }

    private JLabel createItemLabel(Inventory item) {
        JLabel label = new JLabel(item.getName(), JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setTransferHandler(new TransferHandler("icon") {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.imageFlavor);
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }
                try {
                    ImageIcon icon = new ImageIcon((Image) support.getTransferable().getTransferData(DataFlavor.imageFlavor));
                    JLabel label = (JLabel) support.getComponent();
                    label.setIcon(icon);
                    triggerEffect(current_items);
                    return true;
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        });
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setPreferredSize(new Dimension(100, 100));
        return label;
    }

    private void triggerEffect(Inventory item) {
        // Here you would implement the logic to trigger the effect of the dropped item
        System.out.println("Triggering effect: " + item.getEffect());
        // Implement your game logic here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game Inventory");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Inventory_Panel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    */
}
