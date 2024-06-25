package com.libraryManagementProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class LibraryManagementGUI {
    private JFrame frame;
    private BookDAO bookDAO;
    private PatronDAO patronDAO;
    private BorrowingRecordDAO borrowingRecordDAO;
    private FineDAO fineDAO;
    private SearchFunctionality searchFunctionality;
    private Reports reports;
    private AdminDAO adminDAO;
    private Admin loggedInAdmin;

    public LibraryManagementGUI() {
        bookDAO = new BookDAO();
        patronDAO = new PatronDAO();
        borrowingRecordDAO = new BorrowingRecordDAO();
        fineDAO = new FineDAO();
        searchFunctionality = new SearchFunctionality();
        reports = new Reports();
        adminDAO = new AdminDAO();

        frame = new JFrame("Library Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        showLoginScreen();
        frame.setVisible(true);
    }

    private void showLoginScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 150, 30);
        panel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 50, 200, 30);
        panel.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 150, 30);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 100, 200, 30);
        panel.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                loggedInAdmin = adminDAO.loginAdmin(username, password);
                if (loggedInAdmin != null) {
                    showMainScreen();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials");
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(250, 150, 100, 30);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(password);
                if (adminDAO.registerAdmin(admin)) {
                    JOptionPane.showMessageDialog(frame, "Registration successful");
                } else {
                    JOptionPane.showMessageDialog(frame, "Registration failed");
                }
            }
        });

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showMainScreen() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Books", createBookPanel());
        tabbedPane.add("Patrons", createPatronPanel());
        tabbedPane.add("Borrowing", createBorrowingPanel());
        tabbedPane.add("Fines", createFinePanel());
        tabbedPane.add("Search", createSearchPanel());
        tabbedPane.add("Reports", createReportPanel());

        frame.setContentPane(tabbedPane);
        frame.revalidate();
        frame.repaint();
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(50, 50, 150, 30);
        panel.add(addBookButton);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Book Title:");
                String author = JOptionPane.showInputDialog("Enter Book Author:");
                String genre = JOptionPane.showInputDialog("Enter Book Genre:");
                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setGenre(genre);
                book.setAvailable(true);
                bookDAO.addBook(book);
            }
        });

        JButton viewBooksButton = new JButton("View Books");
        viewBooksButton.setBounds(50, 100, 150, 30);
        panel.add(viewBooksButton);

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Book> books = bookDAO.getAllBooks();
                StringBuilder booksList = new StringBuilder("Books:\n");
                for (Book book : books) {
                    booksList.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, booksList.toString());
            }
        });

        return panel;
    }

    private JPanel createPatronPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton addPatronButton = new JButton("Add Patron");
        addPatronButton.setBounds(50, 50, 150, 30);
        panel.add(addPatronButton);

        addPatronButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Patron Name:");
                String contactInfo = JOptionPane.showInputDialog("Enter Patron Contact Info:");
                Patron patron = new Patron();
                patron.setName(name);
                patron.setContactInfo(contactInfo);
                patronDAO.addPatron(patron);
            }
        });

        JButton viewPatronsButton = new JButton("View Patrons");
        viewPatronsButton.setBounds(50, 100, 150, 30);
        panel.add(viewPatronsButton);

        viewPatronsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Patron> patrons = patronDAO.getAllPatrons();
                StringBuilder patronsList = new StringBuilder("Patrons:\n");
                for (Patron patron : patrons) {
                    patronsList.append(patron.getName()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, patronsList.toString());
            }
        });

        return panel;
    }

    private JPanel createBorrowingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.setBounds(50, 50, 150, 30);
        panel.add(borrowBookButton);

        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookID = Integer.parseInt(JOptionPane.showInputDialog("Enter Book ID:"));
                int patronID = Integer.parseInt(JOptionPane.showInputDialog("Enter Patron ID:"));
                BorrowingRecord record = new BorrowingRecord();
                record.setBookID(bookID);
                record.setPatronID(patronID);
                record.setBorrowDate(new Date(new java.util.Date().getTime()));
                record.setReturned(false);
                borrowingRecordDAO.addBorrowingRecord(record);
                Book book = bookDAO.getAllBooks().stream().filter(b -> b.getBookID() == bookID).findFirst().orElse(null);
                if (book != null) {
                    book.setAvailable(false);
                    bookDAO.updateBook(book);
                }
            }
        });

        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(50, 100, 150, 30);
        panel.add(returnBookButton);

        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int recordID = Integer.parseInt(JOptionPane.showInputDialog("Enter Record ID:"));
                BorrowingRecord record = borrowingRecordDAO.getAllBorrowingRecords().stream()
                        .filter(r -> r.getRecordID() == recordID).findFirst().orElse(null);
                if (record != null) {
                    record.setReturnDate(new Date(new java.util.Date().getTime()));
                    record.setReturned(true);
                    borrowingRecordDAO.updateBorrowingRecord(record);
                    Book book = bookDAO.getAllBooks().stream().filter(b -> b.getBookID() == record.getBookID()).findFirst().orElse(null);
                    if (book != null) {
                        book.setAvailable(true);
                        bookDAO.updateBook(book);
                    }
                    // Calculate fine if overdue (assuming a fine of $1 per day)
                    long daysOverdue = (record.getReturnDate().getTime() - record.getBorrowDate().getTime()) / (1000 * 60 * 60 * 24) - 14;
                    if (daysOverdue > 0) {
                        Fine fine = new Fine();
                        fine.setRecordID(recordID);
                        fine.setAmount(daysOverdue);
                        fine.setPaid(false);
                        fineDAO.addFine(fine);
                    }
                }
            }
        });

        return panel;
    }

    private JPanel createFinePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton viewFinesButton = new JButton("View Fines");
        viewFinesButton.setBounds(50, 50, 150, 30);
        panel.add(viewFinesButton);

        viewFinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Fine> fines = fineDAO.getAllFines();
                StringBuilder finesList = new StringBuilder("Fines:\n");
                for (Fine fine : fines) {
                    finesList.append("Record ID: ").append(fine.getRecordID())
                            .append(", Amount: ").append(fine.getAmount())
                            .append(", Paid: ").append(fine.isPaid() ? "Yes" : "No").append("\n");
                }
                JOptionPane.showMessageDialog(frame, finesList.toString());
            }
        });

        JButton payFineButton = new JButton("Pay Fine");
        payFineButton.setBounds(50, 100, 150, 30);
        panel.add(payFineButton);

        payFineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fineID = Integer.parseInt(JOptionPane.showInputDialog("Enter Fine ID:"));
                Fine fine = fineDAO.getAllFines().stream().filter(f -> f.getFineID() == fineID).findFirst().orElse(null);
                if (fine != null) {
                    fine.setPaid(true);
                    fineDAO.updateFine(fine);
                }
            }
        });

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(50, 50, 100, 30);
        panel.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(150, 50, 200, 30);
        panel.add(searchField);

        JButton searchBooksButton = new JButton("Search Books");
        searchBooksButton.setBounds(50, 100, 150, 30);
        panel.add(searchBooksButton);

        searchBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                List<Book> books = searchFunctionality.searchBooks(keyword);
                StringBuilder booksList = new StringBuilder("Books:\n");
                for (Book book : books) {
                    booksList.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, booksList.toString());
            }
        });

        JButton searchPatronsButton = new JButton("Search Patrons");
        searchPatronsButton.setBounds(220, 100, 150, 30);
        panel.add(searchPatronsButton);

        searchPatronsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                List<Patron> patrons = searchFunctionality.searchPatrons(keyword);
                StringBuilder patronsList = new StringBuilder("Patrons:\n");
                for (Patron patron : patrons) {
                    patronsList.append(patron.getName()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, patronsList.toString());
            }
        });

        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton bookAvailabilityReportButton = new JButton("Book Availability Report");
        bookAvailabilityReportButton.setBounds(50, 50, 200, 30);
        panel.add(bookAvailabilityReportButton);

        bookAvailabilityReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Book> books = reports.generateBookAvailabilityReport();
                StringBuilder report = new StringBuilder("Available Books:\n");
                for (Book book : books) {
                    report.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, report.toString());
            }
        });

        JButton borrowingHistoryReportButton = new JButton("Borrowing History Report");
        borrowingHistoryReportButton.setBounds(50, 100, 200, 30);
        panel.add(borrowingHistoryReportButton);

        borrowingHistoryReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<BorrowingRecord> records = reports.generateBorrowingHistoryReport();
                StringBuilder report = new StringBuilder("Borrowing Records:\n");
                for (BorrowingRecord record : records) {
                    report.append("Record ID: ").append(record.getRecordID())
                            .append(", Book ID: ").append(record.getBookID())
                            .append(", Patron ID: ").append(record.getPatronID())
                            .append(", Borrow Date: ").append(record.getBorrowDate())
                            .append(", Return Date: ").append(record.getReturnDate()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, report.toString());
            }
        });

        JButton finesReportButton = new JButton("Fines Report");
        finesReportButton.setBounds(50, 150, 200, 30);
        panel.add(finesReportButton);

        finesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Fine> fines = reports.generateFinesReport();
                StringBuilder report = new StringBuilder("Fines:\n");
                for (Fine fine : fines) {
                    report.append("Record ID: ").append(fine.getRecordID())
                            .append(", Amount: ").append(fine.getAmount())
                            .append(", Paid: ").append(fine.isPaid() ? "Yes" : "No").append("\n");
                }
                JOptionPane.showMessageDialog(frame, report.toString());
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        new LibraryManagementGUI();
    }
}