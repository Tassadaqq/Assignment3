import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PopularityChartScreen extends JFrame {
    private List<item> items;

    public PopularityChartScreen(List<item> items) {
        this.items = items;
        setTitle("Popularity Chart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new PopularityChartPanel());
        setVisible(true);
    }

    class PopularityChartPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int totalPopularity = 0;
            for (item libraryItem : items) {
                totalPopularity += libraryItem.getPopularity();
            }

            int x = 50;
            int y = 50;
            int barWidth = 30;

            for (item libraryItem : items) {
                int itemPopularity = libraryItem.getPopularity();
                int barHeight = (int) (itemPopularity / (double) totalPopularity * 300); 

                g.setColor(Color.gray);
                g.fillRect(x, y, barWidth, barHeight);

                g.setColor(Color.black);
                g.drawString(libraryItem.getTitle(), x - 10, y + barHeight + 20);

                g.setColor(Color.black);
                g.drawString(String.valueOf(itemPopularity), x - 10, y - 10);

                x += 100;
            }
        }
    }


}
