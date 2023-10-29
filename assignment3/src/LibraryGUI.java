import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;
import java.io.PrintWriter;
public class LibraryGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
//    private class ButtonEditor extends DefaultCellEditor {
//        protected JButton button;
//        private String label;
//        private boolean isPushed;
//        private int selectedRow;
//
//        public ButtonEditor(JCheckBox checkBox) {
//            super(checkBox);
//            button = new JButton();
//            button.setOpaque(true);
//            button.addActionListener(e -> {
//                fireEditingStopped();
//                if (selectedRow >= 0) {
//                    item libraryItem = items.get(selectedRow);
//                    openReadDialog(libraryItem);
//                }
//            });
//        }
//
//        @Override
//        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//            if (isSelected) {
//                button.setForeground(table.getSelectionForeground());
//                button.setBackground(table.getSelectionBackground());
//            } else {
//                button.setForeground(table.getForeground());
//                button.setBackground(table.getBackground());
//            }
//
//            label = (value == null) ? "Read" : value.toString();
//            button.setText(label);
//            isPushed = true;
//            selectedRow = row;
//            return button;
//        }
//
//        @Override
//        public Object getCellEditorValue() {
//            if (isPushed) {
//                // Button was clicked
//                // You can handle button click actions here
//            }
//            isPushed = false;
//            return label;
//        }
//
//        @Override
//        public boolean stopCellEditing() {
//            isPushed = false;
//            return super.stopCellEditing();
//        }
//
//        @Override
//        protected void fireEditingStopped() {
//            super.fireEditingStopped();
//        }
//        private void openReadDialog(item libraryItem) {
//            JDialog readDialog = new JDialog(this, "Read Item", true);
//            readDialog.setLayout(new BorderLayout());
//
//            JTextArea textArea = new JTextArea(libraryItem.getContent());
//            textArea.setWrapStyleWord(true);
//            textArea.setLineWrap(true);
//
//            JScrollPane scrollPane = new JScrollPane(textArea);
//            readDialog.add(scrollPane, BorderLayout.CENTER);
//
//            JButton closeButton = new JButton("Close");
//            closeButton.addActionListener(e -> {
//                readDialog.dispose();
//                promptExitNotification();
//            });
//            readDialog.add(closeButton, BorderLayout.SOUTH);
//
//            readDialog.pack();
//            readDialog.setVisible(true);
//        }
//
//        private void promptExitNotification() {
//            int option = JOptionPane.showConfirmDialog(this, "Do you want to exit reading the book?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
//            if (option == JOptionPane.YES_OPTION) {
//                // User wants to exit reading the book
//            }
//        }
//    }
//
//    class ButtonRenderer extends JButton implements TableCellRenderer {
//        public ButtonRenderer() {
//            setOpaque(true);
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            if (isSelected) {
//                setForeground(table.getSelectionForeground());
//                setBackground(table.getSelectionBackground());
//            } else {
//                setForeground(table.getForeground());
//                setBackground(UIManager.getColor("Button.background"));
//            }
//
//            setText((value == null) ? "Read" : value.toString());
//
//            return this;
//        }
//    }


    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private List<item> items;
    private void createAndShowPopularityChart() {
        PopularityChartScreen popularityChartScreen = new PopularityChartScreen(items);
    }

    private class ButtonRenderer extends DefaultTableCellRenderer {
        private final JButton button;

        public ButtonRenderer() {
            button = new JButton("Read");
            button.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    item libraryItem = items.get(row);
                    libraryItem.readItem();
                    openReadDialog(libraryItem);
                }
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private int selectedRow;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                fireEditingStopped();
                if (selectedRow >= 0) {
                    item libraryItem = items.get(selectedRow);
                    openReadDialog(libraryItem);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }

            label = (value == null) ? "Read" : value.toString();
            button.setText(label);
            isPushed = true;
            selectedRow = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    private void openReadDialog(item libraryItem) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\HP\\Desktop\\source.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            JTextArea textArea = new JTextArea(25, 60);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                textArea.append(line + "\n");
            }

            JScrollPane scrollPane = new JScrollPane(textArea);
            JFrame frame = new JFrame("Read " + libraryItem.getTitle());
            frame.getContentPane().add(scrollPane);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(frame, "Do you want to exit reading the book?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    }
                }
            });

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading the book content.");
        }

//        JButton closeButton = new JButton("Close");
//        closeButton.addActionListener(e -> {
//            readDialog.dispose();
//            promptExitNotification();
//        });
//        readDialog.add(closeButton, BorderLayout.SOUTH);
//
//        readDialog.pack();
//        readDialog.setVisible(true);
    }

    private void promptExitNotification() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to exit reading the book?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
        }
    }

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Library library = new Library();
        library.loadDataFromFile("C:\\Users\\HP\\IdeaProjects\\assignment1part2\\src\\types.txt");
        items = library.items;

        tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) {
                    return JButton.class;
                }
                return Object.class;
            }
        };

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Authors/Publisher");
        tableModel.addColumn("Publication Date/Year");
        tableModel.addColumn("Cost");
        tableModel.addColumn("Read Item");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add Item");
        editButton = new JButton("Edit Item");
        deleteButton = new JButton("Delete Item");


        JButton viewPopularityButton = new JButton("View Popularity");
        viewPopularityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowPopularityChart();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewPopularityButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    table.setRowSelectionInterval(row, row);
                    table.setSelectionBackground(Color.YELLOW);
                }
            }



            @Override
            public void mouseExited(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    table.clearSelection();
                }
            }
        });
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));



        displayLibraryItems();

        setVisible(true);
    };
    public void saveLibraryData() {
        try (PrintWriter writer = new PrintWriter("C:\\Users\\HP\\Desktop\\dest.txt")) {
            for (item libraryItem : items) {
                String itemString = libraryItem.toString();

                writer.println(itemString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private void displayLibraryItems() {
//        for (item libraryItem : items) {
//
//            JButton readButton = new JButton("Read");
//            tableModel.addRow(new Object[]{
//                    libraryItem.getId(), libraryItem.getTitle(), libraryItem.getAuthor(),
//                    libraryItem.getYear(), libraryItem.calculateCost()
//            });
//        }
//
//    }
private void displayLibraryItems() {
    for (item libraryItem : items) {
//        JButton readButton = new JButton("Read");
//        readButton.addActionListener(e -> {
//
//        });

        tableModel.addRow(new Object[]{libraryItem.getId(), libraryItem.getTitle(), libraryItem.getAuthor(), libraryItem.getYear(), libraryItem.calculateCost(), "Read"});
    }
}


    private void addBook() {
        JDialog addDialog = new JDialog(this, "Add Item", true);
        addDialog.setLayout(new GridLayout(5, 2));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField costField = new JTextField();

        addDialog.add(new JLabel("Title:"));
        addDialog.add(titleField);
        addDialog.add(new JLabel("Authors/Publisher:"));
        addDialog.add(authorField);
        addDialog.add(new JLabel("Publication Date/Year:"));
        addDialog.add(yearField);
        addDialog.add(new JLabel("Cost:"));
        addDialog.add(costField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                int year = Integer.parseInt(yearField.getText());
                double cost = Double.parseDouble(costField.getText());

                 item newBook = new book(title, author, year, cost);

                 items.add(newBook);

                 tableModel.addRow(new Object[]{newBook.getId(), newBook.getTitle(), newBook.getAuthor(), newBook.getYear(), newBook.calculateCost()});

                addDialog.dispose();
            }
        });

        addDialog.add(addButton);
        addDialog.pack();
        addDialog.setVisible(true);
        saveLibraryData();
    }

    private void editBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a library item to edit.");
            return;
        }

        item selectedItem = items.get(selectedRow);

        JDialog editDialog = new JDialog(this, "Edit Item", true);
        editDialog.setLayout(new GridLayout(5, 2));

        JTextField titleField = new JTextField(selectedItem.getTitle());
        JTextField authorField = new JTextField(selectedItem.getAuthor());
        JTextField yearField = new JTextField(selectedItem.getYear());
        JTextField costField = new JTextField(String.valueOf(selectedItem.calculateCost()));

        editDialog.add(new JLabel("Title:"));
        editDialog.add(titleField);
        editDialog.add(new JLabel("Authors/Publisher:"));
        editDialog.add(authorField);
        editDialog.add(new JLabel("Publication Date/Year:"));
        editDialog.add(yearField);
        editDialog.add(new JLabel("Cost:"));
        editDialog.add(costField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTitle = titleField.getText();
                String newAuthor = authorField.getText();
                int newYear = Integer.parseInt(yearField.getText());
                double newCost = Double.parseDouble(costField.getText());

                selectedItem.setTitle(newTitle);
                selectedItem.setAuthor(newAuthor);
                selectedItem.setYear(newYear);

                tableModel.setValueAt(newTitle, selectedRow, 1);
                tableModel.setValueAt(newAuthor, selectedRow, 2);
                tableModel.setValueAt(newYear, selectedRow, 3);

                editDialog.dispose();
            }
        });

        editDialog.add(saveButton);
        editDialog.pack();
        editDialog.setVisible(true);
        saveLibraryData();
    }

    private void deleteBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a library item to delete.");
            return;
        }

        items.remove(selectedRow);

        tableModel.removeRow(selectedRow);
        saveLibraryData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryGUI();
            }
        });
    }
}
