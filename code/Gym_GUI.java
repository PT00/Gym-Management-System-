/**
 *The Gym GUI is a Java Swing application for managing gym members. It allows adding regular and premium members, tracking payments, applying discounts, and viewing member details.
 *Features include membership activation/deactivation, payment processing, and a display table. The interface is user-friendly with organized input fields and buttons for easy navigation.
 * @author (Priya_Thapa)
 * @version (1/03/2025)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

public class Gym_GUI {
    protected ArrayList<Gym_member> arr = new ArrayList<Gym_member>();
    protected JFrame GymFrame, RegularFrame, PremiumFrame;
    protected JPanel GymPanel, RegularPanel, PremiumPanel;
    protected JTextField ID,ID1,Name ,Name1, Location ,Location1,Phone1, Phone, Email,Email1,
    ReferralSource,markAttendanceIdField,markAttendanceIdField1 ,displayMemberIdField,displayMemberIdField1,revertMemberIdField,PersonalTrainer,revertPremiumIdField,deactivateIdField,
    activateIdField,deactivateIdField1,activatePremiumIdField;
    protected JButton addRegular, addPremium,addPremium2, addRegular2, activateBtn,   
    deactivateBtn,regularClearButton,backregularButton,DisplayButton,backPremiumButton,DisplayButton1,activateBtn1,markAttendanceBtn1,revertPremiumBtn, 
    deactivateBtn1,PremiumClearButton;
    protected JRadioButton radioButton1, radioButton2, radioButton3,radioButtonn1, radioButtonn2, radioButtonn3;
    protected JComboBox<Integer> dayBox, yearBox, msdDayBox, msdYearBox,dayBox1, yearBox1, msdDayBox1, msdYearBox1;
    protected JComboBox<String> monthBox, msdMonthBox,planBox,monthBox1, msdMonthBox1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Gym_GUI window = new Gym_GUI();
                        window.GymFrame.setVisible(true);
                    } catch (Exception e) {e.printStackTrace();
                    }
                }
            });
    }

    public Gym_GUI() {
        arr = new ArrayList<>();

        // Main Frame
        GymFrame = new JFrame("Gym Management System");
        GymFrame.setBounds(100, 100, 400, 200);
        GymFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GymPanel = new JPanel();
        GymPanel.setLayout(new GridLayout(1, 2, 10, 10));

        addRegular = new JButton("Add Regular Member");
        addRegular.addActionListener(new RegularButtonListener(this));

        addPremium = new JButton("Add Premium Member");
        addPremium.addActionListener(new PremiumButtonListener(this));

        GymPanel.add(addRegular);
        GymPanel.add(addPremium);
        GymFrame.getContentPane().add(GymPanel, BorderLayout.CENTER);

        // Regular Member Frame
        RegularFrame = new JFrame("Regular Member");
        RegularFrame.setBounds(150, 170, 600, 750);
        RegularFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        RegularPanel = new JPanel();
        RegularPanel.setLayout(null);
        RegularPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Personal Information Section
        JLabel personalInfoLabel = new JLabel("Personal Information");
        personalInfoLabel.setBounds(10, 10, 200, 20);
        personalInfoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        RegularPanel.add(personalInfoLabel);

        // ID
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 40, 80, 25);
        RegularPanel.add(idLabel);
        ID = new JTextField();
        ID.setBounds(100, 40, 120, 25);
        RegularPanel.add(ID);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(250, 40, 80, 25);
        RegularPanel.add(nameLabel);
        Name = new JTextField();
        Name.setBounds(330, 40, 200, 25);
        RegularPanel.add(Name);

        // Location
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(20, 80, 80, 25);
        RegularPanel.add(locationLabel);
        Location = new JTextField();
        Location.setBounds(100, 80, 120, 25);
        RegularPanel.add(Location);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(250, 80, 80, 25);
        RegularPanel.add(phoneLabel);
        Phone = new JTextField();
        Phone.setBounds(330, 80, 200, 25);
        RegularPanel.add(Phone);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 120, 80, 25);
        RegularPanel.add(emailLabel);
        Email = new JTextField();
        Email.setBounds(100, 120, 430, 25);
        RegularPanel.add(Email);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 160, 80, 25);
        RegularPanel.add(genderLabel);

        radioButton1 = new JRadioButton("Male");
        radioButton1.setBounds(80, 160, 60, 25);
        radioButton2 = new JRadioButton("Female");
        radioButton2.setBounds(150, 160, 80, 25);
        radioButton3 = new JRadioButton("Other");
        radioButton3.setBounds(240, 160, 80, 25);

        // Plan Selection for Regular Members
        JLabel planLabel = new JLabel("Plan:");
        planLabel.setBounds(15, 650, 80, 25);
        RegularPanel.add(planLabel);

        String[] plans = {"Basic", "Standard", "Deluxe"};
        planBox = new JComboBox<>(plans);
        planBox.setBounds(70, 650, 150, 25);
        RegularPanel.add(planBox);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);
        genderGroup.add(radioButton3);

        RegularPanel.add(radioButton1);
        RegularPanel.add(radioButton2);
        RegularPanel.add(radioButton3);

        // DOB
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(20, 200, 100, 25);
        RegularPanel.add(dobLabel);

        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) 
            days[i] = i + 1;
        dayBox = new JComboBox<>(days);
        dayBox.setBounds(120, 200, 50, 25);
        RegularPanel.add(dayBox);

        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            };
        monthBox = new JComboBox<>(months);
        monthBox.setBounds(180, 200, 100, 25);
        RegularPanel.add(monthBox);

        int currentYear = java.time.Year.now().getValue();
        Integer[] years = new Integer[currentYear - 1960 + 1];
        for (int i = 0; i < years.length; i++) 
            years[i] = 1960 + i;
        yearBox = new JComboBox<>(years);
        yearBox.setBounds(290, 200, 80, 25);
        RegularPanel.add(yearBox);

        // Referral Source
        JLabel referralLabel = new JLabel("Referral Source:");
        referralLabel.setBounds(20, 240, 120, 25);
        RegularPanel.add(referralLabel);
        ReferralSource = new JTextField();
        ReferralSource.setBounds(140, 240, 390, 25);
        RegularPanel.add(ReferralSource);

        // Membership Start Date
        JLabel msdLabel = new JLabel("Membership Start Date:");
        msdLabel.setBounds(20, 280, 180, 25);
        RegularPanel.add(msdLabel);

        msdDayBox = new JComboBox<>(days);
        msdDayBox.setBounds(200, 280, 50, 25);
        RegularPanel.add(msdDayBox);

        msdMonthBox = new JComboBox<>(months);
        msdMonthBox.setBounds(260, 280, 100, 25);
        RegularPanel.add(msdMonthBox);

        msdYearBox = new JComboBox<>(years);
        msdYearBox.setBounds(370, 280, 80, 25);
        RegularPanel.add(msdYearBox);

        // Add Member Button
        addRegular2 = new JButton("Add Regular Member");
        addRegular2.setBounds(20, 330, 190, 30);
        addRegular2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addRegularMember();
                }
            });
        RegularPanel.add(addRegular2);

        // Membership Management Section
        JLabel membershipLabel = new JLabel("Membership Management");
        membershipLabel.setBounds(10, 380, 200, 20);
        membershipLabel.setFont(new Font("Arial", Font.BOLD, 14));
        RegularPanel.add(membershipLabel);

        // Activate Membership
        JLabel activateLabel = new JLabel("Member ID:");
        activateLabel.setBounds(20, 420, 80, 25);
        RegularPanel.add(activateLabel);

        activateIdField = new JTextField();
        activateIdField.setBounds(100, 420, 100, 25);
        RegularPanel.add(activateIdField);

        activateBtn = new JButton("Activate Membership");
        activateBtn.setBounds(220, 420, 180, 25);
        activateBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = activateIdField.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);
                        if (member != null) {
                            member.activateMembership();
                            JOptionPane.showMessageDialog(RegularFrame, "Membership Activated for Member ID: " + memberId);
                        } else {
                            JOptionPane.showMessageDialog(RegularFrame, "Member ID not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(RegularFrame, "Invalid Member ID entered.");
                    }
                }
            });
        RegularPanel.add(activateBtn);

        // Deactivate Membership
        JLabel deactivateLabel = new JLabel("Member ID:");
        deactivateLabel.setBounds(20, 460, 80, 25);
        RegularPanel.add(deactivateLabel);

        deactivateIdField = new JTextField();
        deactivateIdField.setBounds(100, 460, 100, 25);
        RegularPanel.add(deactivateIdField);

        deactivateBtn = new JButton("Deactivate");
        deactivateBtn.setBounds(220, 460, 180, 25);
        deactivateBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = deactivateIdField.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);
                        if (member != null) {
                            member.deactiveMembership();
                            JOptionPane.showMessageDialog(RegularFrame, "Membership Deactivated for Member ID: " + memberId);
                        } else {
                            JOptionPane.showMessageDialog(RegularFrame, "Member ID not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(RegularFrame, "Invalid Member ID entered.");
                    }
                }
            });
        RegularPanel.add(deactivateBtn);

        JLabel markAttendanceLabel = new JLabel("Member ID:");
        markAttendanceLabel.setBounds(20, 500, 80, 25);
        RegularPanel.add(markAttendanceLabel);

        markAttendanceIdField = new JTextField();
        markAttendanceIdField.setBounds(100, 500, 100, 25);
        RegularPanel.add(markAttendanceIdField);

        JButton markAttendanceBtn = new JButton("Mark Attendance");
        markAttendanceBtn.setBounds(220, 500, 150, 25);
        markAttendanceBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = markAttendanceIdField.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);

                        if (member != null && member instanceof Regular_member) {
                            if (member.get_ActiveStatus()) {
                                member.markAttendance();
                                JOptionPane.showMessageDialog(RegularFrame, 
                                    "Attendance marked for Regular Member ID: " + memberId +
                                    "\nTotal Attendance: " + member.get_Attendance() +
                                    "\nLoyalty Points: " + member.get_LoyalPoints(),
                                    "Attendance Recorded", 
                                    JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(RegularFrame, 
                                    "Cannot mark attendance - membership is inactive",
                                    "Inactive Membership", 
                                    JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(RegularFrame, 
                                "Regular Member ID not found",
                                "Not Found", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(RegularFrame, 
                            "Please enter a valid numeric Member ID",
                            "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        RegularPanel.add(markAttendanceBtn);

        JLabel revertMemberLabel = new JLabel("Member ID:");
        revertMemberLabel.setBounds(20, 540, 80, 25);
        RegularPanel.add(revertMemberLabel);

        revertMemberIdField = new JTextField();
        revertMemberIdField.setBounds(100, 540, 100, 25);
        RegularPanel.add(revertMemberIdField);

        JButton revertMemberBtn = new JButton("Revert Member");
        revertMemberBtn.setBounds(220, 540, 150, 25);
        revertMemberBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = revertMemberIdField.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);

                        if (member != null && member instanceof Regular_member) {
                            ((Regular_member)member).revertRegularMember("Admin request");
                            JOptionPane.showMessageDialog(RegularFrame, 
                                "Regular Member ID " + memberId + " has been reverted\n" +
                                "All data has been reset to default values",
                                "Member Reverted", 
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(RegularFrame, 
                                "Regular Member ID not found",
                                "Not Found", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(RegularFrame, 
                            "Please enter a valid numeric Member ID",
                            "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        RegularPanel.add(revertMemberBtn);

        // Clear Button
        regularClearButton = new JButton("Clear");
        regularClearButton.setBounds(270, 330, 95, 30);
        regularClearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ID.setText("");
                    Name.setText("");
                    Location.setText("");
                    Phone.setText("");
                    Email.setText("");
                    ReferralSource.setText("");
                    planBox.setSelectedIndex(-1);  

                    radioButton1.setSelected(false);
                    radioButton2.setSelected(false);
                    radioButton3.setSelected(false);

                    dayBox.setSelectedIndex(0);
                    monthBox.setSelectedIndex(0);
                    yearBox.setSelectedIndex(0);
                    msdDayBox.setSelectedIndex(0);
                    msdMonthBox.setSelectedIndex(0);
                    msdYearBox.setSelectedIndex(0);
                }
            });
        RegularPanel.add(regularClearButton);

        // Back Button
        backregularButton = new JButton("Back");
        backregularButton.setBounds(380, 330, 95, 30);
        backregularButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RegularFrame.setVisible(false);
                    GymFrame.setVisible(true);
                }
            });
        RegularPanel.add(backregularButton);

        JLabel displayMemberLabel = new JLabel("Member ID:");
        displayMemberLabel.setBounds(20, 580, 80, 25); 
        RegularPanel.add(displayMemberLabel);

        final JTextField displayMemberIdField = new JTextField();
        displayMemberIdField.setBounds(100, 580, 100, 25); 
        RegularPanel.add(displayMemberIdField);

        DisplayButton = new JButton("Display");
        DisplayButton.setBounds(220, 580, 150, 25); 
        DisplayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputId = displayMemberIdField.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);

                        if (member != null && member instanceof Regular_member) {
                            Regular_member regularMember = (Regular_member) member;
                            String info = "ID: " + regularMember.get_id() + "\n" +
                                "Name: " + regularMember.get_name() + "\n" +
                                "Location: " + regularMember.get_Location() + "\n" +
                                "Phone: " + regularMember.get_Phone()+ "\n" +
                                "Email: " + regularMember.get_Gender() + "\n" +
                                "Referral Source: " + regularMember.get_referralSource() + "\n" +
                                "Gender: " + regularMember.get_Gender() + "\n" +
                                "Date of Birth: " + regularMember.get_DOB() + "\n" +
                                "Membership Start Date: " + regularMember.get_MembershipStartDate() + "\n" +
                                "Active Status: " + regularMember.get_ActiveStatus() + "\n" +
                                "Attendance: " + regularMember.get_Attendance() + "\n" +
                                "Loyalty Points: " + regularMember.get_LoyalPoints() + "\n" +
                                "Plan: " + regularMember.get_Plan(); 

                            JOptionPane.showMessageDialog(RegularFrame, info, "Regular Member Information", JOptionPane.INFORMATION_MESSAGE);
                        } else if (member != null) {
                            JOptionPane.showMessageDialog(RegularFrame, "Member with ID " + memberId + " is not a Regular Member.", "Not a Regular Member", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(RegularFrame, "Member with ID " + memberId + " not found.", "Not Found", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(RegularFrame, "Invalid Member ID format. Please enter a number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        RegularPanel.add(DisplayButton);

        JButton upgradeBtn = new JButton("Upgrade Plan");
        upgradeBtn.setBounds(220, 650, 150, 25);
        upgradeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the member ID from the ID field
                    String memberIdStr = ID.getText().trim();

                    if (memberIdStr.isEmpty()) {
                        JOptionPane.showMessageDialog(RegularFrame, 
                            "Please enter a Member ID first",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        int memberId = Integer.parseInt(memberIdStr);
                        String plan = (String) planBox.getSelectedItem();

                        if (plan == null || plan.isEmpty()) {
                            JOptionPane.showMessageDialog(RegularFrame, 
                                "Please select a plan to upgrade to",
                                "Input Error",
                                JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        Gym_member member = findMemberById(memberId);

                        if (member == null) {
                            JOptionPane.showMessageDialog(RegularFrame, 
                                "Member with ID " + memberId + " not found",
                                "Not Found",
                                JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (!(member instanceof Regular_member)) {
                            JOptionPane.showMessageDialog(RegularFrame, 
                                "Member with ID " + memberId + " is not a Regular Member",
                                "Wrong Member Type",
                                JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (!member.get_ActiveStatus()) {
                            JOptionPane.showMessageDialog(RegularFrame, 
                                "Cannot upgrade plan - membership is inactive",
                                "Inactive Membership",
                                JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        Regular_member regularMember = (Regular_member) member;
                        String result = regularMember.upgradePlan(plan);

                        JOptionPane.showMessageDialog(RegularFrame, 
                            result,
                            "Plan Upgrade Status",
                            JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(RegularFrame, 
                            "Please enter a valid numeric Member ID",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        RegularPanel.add(upgradeBtn);

        // Add these to your RegularPanel setup (place them where you want)
        JButton regularSaveBtn = new JButton("Save to File");
        regularSaveBtn.setBounds(20, 620, 150, 25);
        regularSaveBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    save(arr);  // Call the save method with the member list
                }
            });

        JButton regularLoadBtn = new JButton("Read from File");
        regularLoadBtn.setBounds(200, 620, 150, 25);
        regularLoadBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    read();  // Call the read method
                }
            });

        // Add to RegularPanel
        RegularPanel.add(regularSaveBtn);
        RegularPanel.add(regularLoadBtn);

        RegularFrame.getContentPane().add(RegularPanel);

        // Premium Member Frame
        PremiumFrame = new JFrame("Premium Member");
        PremiumFrame.setBounds(150, 150, 600, 680);
        PremiumFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        PremiumPanel = new JPanel();
        PremiumPanel.setLayout(null);
        PremiumPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Personal Information Section
        JLabel personalInfoLabel1 = new JLabel("Personal Information");
        personalInfoLabel1.setBounds(10, 10, 200, 20);
        personalInfoLabel1.setFont(new Font("Arial", Font.BOLD, 14));
        PremiumPanel.add(personalInfoLabel1);

        // ID
        JLabel idLabel1 = new JLabel("ID:");
        idLabel1.setBounds(20, 40, 80, 25);
        PremiumPanel.add(idLabel1);
        ID1 = new JTextField();
        ID1.setBounds(100, 40, 120, 25);
        PremiumPanel.add(ID1);

        // Name
        JLabel nameLabel1 = new JLabel("Name:");
        nameLabel1.setBounds(250, 40, 80, 25);
        PremiumPanel.add(nameLabel1);
        Name1 = new JTextField();
        Name1.setBounds(330, 40, 200, 25);
        PremiumPanel.add(Name1);

        // Location
        JLabel locationLabel1 = new JLabel("Location:");
        locationLabel1.setBounds(20, 80, 80, 25);
        PremiumPanel.add(locationLabel1);
        Location1 = new JTextField();
        Location1.setBounds(100, 80, 120, 25);
        PremiumPanel.add(Location1);

        // Phone
        JLabel phoneLabel1 = new JLabel("Phone:");
        phoneLabel1.setBounds(250, 80, 80, 25);
        PremiumPanel.add(phoneLabel1);
        Phone1 = new JTextField();
        Phone1.setBounds(330, 80, 200, 25);
        PremiumPanel.add(Phone1);

        // Email
        JLabel emailLabel1 = new JLabel("Email:");
        emailLabel1.setBounds(20, 120, 80, 25);
        PremiumPanel.add(emailLabel1);
        Email1 = new JTextField();
        Email1.setBounds(100, 120, 120, 25);
        PremiumPanel.add(Email1);

        // Gender
        JLabel genderLabel1 = new JLabel("Gender:");
        genderLabel1.setBounds(250, 120, 80, 25);
        PremiumPanel.add(genderLabel1);

        radioButtonn1 = new JRadioButton("Male");
        radioButtonn1.setBounds(320, 120, 60, 25);
        radioButtonn2 = new JRadioButton("Female");
        radioButtonn2.setBounds(400, 120, 80, 25);
        radioButtonn3 = new JRadioButton("Other");
        radioButtonn3.setBounds(480, 120, 80, 25);

        ButtonGroup genderGroup1 = new ButtonGroup();
        genderGroup1.add(radioButtonn1);
        genderGroup1.add(radioButtonn2);
        genderGroup1.add(radioButtonn3);

        PremiumPanel.add(radioButtonn1);
        PremiumPanel.add(radioButtonn2);
        PremiumPanel.add(radioButtonn3);

        JLabel dobLabel1 = new JLabel("Date of Birth:");
        dobLabel1.setBounds(20, 160, 100, 25);
        PremiumPanel.add(dobLabel1);

        Integer[] days1 = new Integer[31];
        for (int i = 0; i < 31; i++) 
            days[i] = i + 1;
        dayBox1 = new JComboBox<>(days);
        dayBox1.setBounds(120, 160, 50, 25);
        PremiumPanel.add(dayBox1);

        String[] months1 = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            };
        monthBox1 = new JComboBox<>(months1);
        monthBox1.setBounds(180, 160, 85, 25);
        PremiumPanel.add(monthBox1);

        int currentYear1 = java.time.Year.now().getValue();
        Integer[] years1 = new Integer[currentYear1 - 1960 + 1];
        for (int i = 0; i < years.length; i++) 
            years1[i] = 1960 + i;
        yearBox1 = new JComboBox<>(years);
        yearBox1.setBounds(280, 160, 80, 25);
        PremiumPanel.add(yearBox1);

        JLabel Personal = new JLabel("Personal Trainer:");
        Personal.setBounds(385, 160, 120, 25);
        PremiumPanel.add(Personal);
        PersonalTrainer = new JTextField();
        PersonalTrainer.setBounds(490, 160, 90, 25);
        PremiumPanel.add(PersonalTrainer);

        // Membership Start Date
        JLabel msdLabel1 = new JLabel("Membership Start Date:");
        msdLabel1.setBounds(20, 200, 180, 25);
        PremiumPanel.add(msdLabel1);

        msdDayBox1 = new JComboBox<>(days);
        msdDayBox1.setBounds(200, 200, 50, 25);
        PremiumPanel.add(msdDayBox1);

        msdMonthBox1 = new JComboBox<>(months1);
        msdMonthBox1.setBounds(260, 200, 100, 25);
        PremiumPanel.add(msdMonthBox1);

        msdYearBox1 = new JComboBox<>(years1);
        msdYearBox1.setBounds(370, 200, 80, 25);
        PremiumPanel.add(msdYearBox1);

        // Add Member Button
        addPremium2 = new JButton("Add Premium Member");
        addPremium2.setBounds(20, 250, 190, 30);
        addPremium2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addPremiumMember();
                }
            });
        PremiumPanel.add(addPremium2);

        // Clear Button
        PremiumClearButton = new JButton("Clear");
        PremiumClearButton.setBounds(270, 250, 95, 30);
        PremiumClearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ID1.setText("");
                    Name1.setText("");
                    Location1.setText("");
                    Phone1.setText("");
                    Email1.setText(""); 
                    PersonalTrainer.setText("");
                    radioButtonn1.setSelected(false);
                    radioButtonn2.setSelected(false);
                    radioButtonn3.setSelected(false);

                    dayBox1.setSelectedIndex(0);
                    monthBox1.setSelectedIndex(0);
                    yearBox1.setSelectedIndex(0);
                    msdDayBox1.setSelectedIndex(0);
                    msdMonthBox1.setSelectedIndex(0);
                    msdYearBox1.setSelectedIndex(0);
                }
            });
        PremiumPanel.add(PremiumClearButton);

        backPremiumButton = new JButton("Back");
        backPremiumButton.setBounds(380, 250, 95, 30);
        backPremiumButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremiumFrame.setVisible(false);  // Hide the premium frame
                    GymFrame.setVisible(true);      // Show the main frame
                }
            });
        PremiumPanel.add(backPremiumButton);

        //Active and Deactive for premium

        // Active and Deactive for premium
        // Active and Deactive for premium
        JLabel activateLabel1 = new JLabel("Member ID:");
        activateLabel1.setBounds(20, 300, 130, 25);
        PremiumPanel.add(activateLabel1);

        activatePremiumIdField = new JTextField();  // New separate field for premium
        activatePremiumIdField.setBounds(100, 300, 100, 25);
        PremiumPanel.add(activatePremiumIdField);

        activateBtn1 = new JButton("Activate");
        activateBtn1.setBounds(220, 300, 180, 25);
        activateBtn1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId1 = activatePremiumIdField.getText().trim();  // Use the new field
                    try {
                        int memberId1 = Integer.parseInt(inputId1);
                        Gym_member member1 = findMemberById(memberId1);
                        if (member1 != null) {
                            member1.activateMembership();
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Membership Activated for Member ID: " + memberId1);
                        } else {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PremiumFrame, 
                            "Invalid Member ID entered.");
                    }
                }
            });
        PremiumPanel.add(activateBtn1);

        // Deactivate Membership
        JLabel deactivateLabel1 = new JLabel("Member ID:");
        deactivateLabel1.setBounds(20, 340, 80, 25);
        PremiumPanel.add(deactivateLabel1);

        deactivateIdField1 = new JTextField();  // Changed from deactivateBtn2
        deactivateIdField1.setBounds(100, 340, 100, 25);
        PremiumPanel.add(deactivateIdField1);

        // In your Premium Frame section, replace the deactivate button code with this:

        deactivateBtn1 = new JButton("Deactivate");
        deactivateBtn1.setBounds(220, 340, 180, 25);
        deactivateBtn1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId1 = deactivateIdField1.getText().trim();
                    try {
                        int memberId1 = Integer.parseInt(inputId1);
                        Gym_member member1 = findMemberById(memberId1);

                        if (member1 == null) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId1 + " not found in system.",
                                "Not Found", 
                                JOptionPane.WARNING_MESSAGE);
                        } 
                        else if (!(member1 instanceof Premium_member)) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId1 + " is not a Premium Member.",
                                "Wrong Member Type", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            if (member1.get_ActiveStatus()) {
                                member1.deactiveMembership();
                                JOptionPane.showMessageDialog(PremiumFrame, 
                                    "Membership Deactivated for Premium Member ID: " + memberId1,
                                    "Success", 
                                    JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(PremiumFrame, 
                                    "Membership is already inactive for ID: " + memberId1,
                                    "Already Inactive", 
                                    JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PremiumFrame, 
                            "Please enter a valid numeric Member ID",
                            "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        PremiumPanel.add(deactivateBtn1);;

        JLabel markAttendanceLabel1 = new JLabel("Member ID:");
        markAttendanceLabel1.setBounds(20, 380, 80, 25);
        PremiumPanel.add(markAttendanceLabel1);

        markAttendanceIdField1 = new JTextField();
        markAttendanceIdField1.setBounds(100, 380, 100, 25);
        PremiumPanel.add(markAttendanceIdField1);
        markAttendanceBtn1 = new JButton("Mark Attendance");
        markAttendanceBtn1.setBounds(220, 380, 150, 25);
        markAttendanceBtn1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = markAttendanceIdField1.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);

                        if (member == null) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId + " not found",
                                "Not Found", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else if (!(member instanceof Premium_member)) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId + " is not a Premium Member",
                                "Wrong Member Type", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else if (!member.get_ActiveStatus()) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Cannot mark attendance - membership is inactive",
                                "Inactive Membership", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            member.markAttendance();
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Attendance marked for Premium Member ID: " + memberId +
                                "\nTotal Attendance: " + member.get_Attendance() +
                                "\nLoyalty Points: " + member.get_LoyalPoints(),
                                "Attendance Recorded", 
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PremiumFrame, 
                            "Please enter a valid numeric Member ID",
                            "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        PremiumPanel.add(markAttendanceBtn1);

        JLabel revertPremiumLabel = new JLabel("Member ID:");
        revertPremiumLabel.setBounds(20, 420, 80, 25);  // Adjusted position
        PremiumPanel.add(revertPremiumLabel);

        revertPremiumIdField = new JTextField();
        revertPremiumIdField.setBounds(100, 420, 100, 25);
        PremiumPanel.add(revertPremiumIdField);
        revertPremiumBtn = new JButton("Revert Premium Member");
        revertPremiumBtn.setBounds(220, 420, 180, 25);
        revertPremiumBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = revertPremiumIdField.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);

                        if (member == null) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId + " not found",
                                "Not Found", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else if (!(member instanceof Premium_member)) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId + " is not a Premium Member",
                                "Wrong Member Type", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            ((Premium_member)member).revertPremiumMember();
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Premium Member ID " + memberId + " has been reverted\n" +
                                "All data has been reset to default values",
                                "Premium Member Reverted", 
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PremiumFrame, 
                            "Please enter a valid numeric Member ID",
                            "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        PremiumPanel.add(revertPremiumBtn);

        // Calculate Discount Section
        JLabel discountLabel = new JLabel("Member ID:");
        discountLabel.setBounds(20, 460, 80, 25);
        PremiumPanel.add(discountLabel);

        JTextField discountIdField = new JTextField();
        discountIdField.setBounds(100, 460, 100, 25);
        PremiumPanel.add(discountIdField);

        JButton calculateDiscountBtn = new JButton("Calculate Discount");
        calculateDiscountBtn.setBounds(220, 460, 180, 25);
        calculateDiscountBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = discountIdField.getText().trim();
                    try {
                        int memberId = Integer.parseInt(inputId);
                        Gym_member member = findMemberById(memberId);

                        if (member != null && member instanceof Premium_member) {
                            ((Premium_member)member).calculateDiscount();
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Discount calculated for Premium Member ID: " + memberId +
                                "\nDiscount Amount: Rs. " + ((Premium_member)member).get_discountAmount(),
                                "Discount Calculation",
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Premium Member ID not found",
                                "Not Found",
                                JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PremiumFrame, 
                            "Invalid Member ID format",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        PremiumPanel.add(calculateDiscountBtn);

        // Pay Due Amount Section
        JLabel paymentLabel = new JLabel("Amount:");
        paymentLabel.setBounds(20, 500, 80, 25);
        PremiumPanel.add(paymentLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(100, 500, 100, 25);
        PremiumPanel.add(amountField);

        JButton payAmountBtn = new JButton("Pay Due Amount");
        payAmountBtn.setBounds(220, 500, 180, 25);
        payAmountBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputId = discountIdField.getText().trim();
                    String amountStr = amountField.getText().trim();

                    try {
                        int memberId = Integer.parseInt(inputId);
                        double amount = Double.parseDouble(amountStr);

                        if (amount <= 0) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Payment amount must be positive",
                                "Invalid Amount",
                                JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        Gym_member member = findMemberById(memberId);

                        if (member == null) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId + " not found",
                                "Not Found",
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else if (!(member instanceof Premium_member)) {
                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Member ID " + memberId + " is not a Premium Member",
                                "Wrong Member Type",
                                JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            Premium_member pm = (Premium_member)member;
                            String result = pm.payDueAmount(amount);

                            JOptionPane.showMessageDialog(PremiumFrame, 
                                "Payment for Member ID: " + memberId + "\n" +
                                "Premium Charge: Rs. " + pm.get_premiumCharge() + "\n" +
                                "Amount Paid: Rs. " + amount + "\n" +
                                result + "\n" +
                                "Remaining Balance: Rs. " + (pm.get_premiumCharge() - pm.get_paidAmount()),
                                "Payment Status", 
                                JOptionPane.INFORMATION_MESSAGE);

                            amountField.setText("");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PremiumFrame, 
                            "Please enter valid numbers for both fields",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        PremiumPanel.add(payAmountBtn);

        // Display All Premium Members Button
        JButton displayAllBtn = new JButton("Display All Premium Members");
        displayAllBtn.setBounds(20, 540, 380, 25);
        displayAllBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    StringBuilder allMembersInfo = new StringBuilder();
                    int premiumCount = 0;

                    for (Gym_member member : arr) {
                        if (member instanceof Premium_member) {
                            Premium_member pm = (Premium_member) member;
                            allMembersInfo.append("ID: ").append(pm.get_id()).append("\n")
                            .append("Name: ").append(pm.get_name()).append("\n")
                            .append("Personal Trainer: ").append(pm.get_personalTrainer()).append("\n")
                            .append("Paid Amount: Rs. ").append(pm.get_paidAmount()).append("\n")
                            .append("Full Payment: ").append(pm.get_isFullPayment() ? "Yes" : "No").append("\n");
                            premiumCount++;
                        }
                    }

                    if (premiumCount > 0) {
                        JOptionPane.showMessageDialog(PremiumFrame, 
                            allMembersInfo.toString(),
                            "All Premium Members (" + premiumCount + ")",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(PremiumFrame,
                            "No premium members found",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
        PremiumPanel.add(displayAllBtn);

        //save file
        JButton saveToFileBtn = new JButton("Save to File");
        saveToFileBtn.setBounds(20, 590, 150, 25);
        saveToFileBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    save(arr);  // Call the save method with the member list
                }
            });
        //read file
        JButton readFromFileBtn = new JButton("Read from File");
        readFromFileBtn.setBounds(200, 590, 150, 25);
        readFromFileBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    read();  // Call the read method
                }
            });

        // Add them to your panel
        PremiumPanel.add(saveToFileBtn);
        PremiumPanel.add(readFromFileBtn);
        // Add Premium Panel to Frame
        PremiumFrame.getContentPane().add(PremiumPanel);
    }

    public Gym_member findMemberById(int id) {
        for (Gym_member member : arr) {
            if (member.get_id() == id) {
                return member;
            }
        }
        return null;
    }

    public void addRegularMember() {
        try {
            int id = Integer.parseInt(this.ID.getText().trim());
            if (findMemberById(id) != null) {
                JOptionPane.showMessageDialog(RegularFrame,
                    "Member ID already exists!",
                    "Duplicate ID",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            String name = this.Name.getText().trim();
            String location = this.Location.getText().trim();
            String phone = this.Phone.getText().trim();
            String email = this.Email.getText().trim();
            String referralSource = this.ReferralSource.getText().trim();
            String plan = (String) planBox.getSelectedItem();

            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
            email.isEmpty() || referralSource.isEmpty()) {
                JOptionPane.showMessageDialog(RegularFrame, 
                    "All fields must be filled out!", 
                    "Input Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(RegularFrame,
                    "Please enter a valid email address",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!phone.matches("^[0-9]{10,15}$")) {
                JOptionPane.showMessageDialog(RegularFrame,
                    "Phone number must be 10-15 digits",
                    "Invalid Phone",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            String gender = this.radioButton1.isSelected() ? "Male" :
                this.radioButton2.isSelected() ? "Female" : "Other";

            int day = (Integer) dayBox.getSelectedItem();
            String month = (String) monthBox.getSelectedItem();
            int year = (Integer) yearBox.getSelectedItem();
            String DOB = day + " " + month + " " + year;

            int msdDay = (Integer) msdDayBox.getSelectedItem();
            String msdMonth = (String) msdMonthBox.getSelectedItem();
            int msdYear = (Integer) msdYearBox.getSelectedItem();
            String MembershipStartDate = msdDay + " " + msdMonth + " " + msdYear;

            Regular_member regularMember = new Regular_member( id,name, location, phone, email,gender,
                    DOB,MembershipStartDate,referralSource);
            arr.add(regularMember);

            JOptionPane.showMessageDialog(RegularFrame, 
                "Regular Member Added Successfully!\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Plan: " + plan,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(RegularFrame, 
                "ID must be a valid number!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(RegularFrame, 
                "Error adding member: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); 
        }
    }

    public void addPremiumMember() {
        try {
            // Validate numeric ID first
            int id = Integer.parseInt(this.ID1.getText().trim());

            // Validate required text fields
            String name = this.Name1.getText().trim();
            String location = this.Location1.getText().trim();
            String phone = this.Phone1.getText().trim();
            String email = this.Email1.getText().trim();
            String trainer = this.PersonalTrainer.getText().trim();

            if (name.isEmpty() || location.isEmpty() ||  trainer.isEmpty()) {
                JOptionPane.showMessageDialog(PremiumFrame, 
                    "All fields must be filled out!", 
                    "Input Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get gender selection
            String gender = this.radioButtonn1.isSelected() ? "Male" :
                this.radioButtonn2.isSelected() ? "Female" : "Other";

            // Get dates from combo boxes (no parsing needed)
            int day = (Integer) dayBox1.getSelectedItem();
            String month = (String) monthBox1.getSelectedItem();
            int year = (Integer) yearBox1.getSelectedItem();
            String DOB = day + " " + month + " " + year;

            int msdDay = (Integer) msdDayBox1.getSelectedItem();
            String msdMonth = (String) msdMonthBox1.getSelectedItem();
            int msdYear = (Integer) msdYearBox1.getSelectedItem();
            String MembershipStartDate = msdDay + " " + msdMonth + " " + msdYear;
            if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(PremiumFrame,
                    "Please enter a valid email address",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!phone.matches("^[0-9]{10,15}$")) {
                JOptionPane.showMessageDialog(PremiumFrame,
                    "Phone number must be 10-15 digits",
                    "Invalid Phone",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create and add member (include the ID in constructor)
            Premium_member premiumMember = new Premium_member(id, name, location, phone, email, 
                    gender, DOB, MembershipStartDate, trainer);
            arr.add(premiumMember);

            JOptionPane.showMessageDialog(PremiumFrame, 
                "Premium Member Added Successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(PremiumFrame, 
                "ID must be a valid number!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(PremiumFrame, 
                "Error adding member: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }

    }

    public void showRegularFrame() {
        RegularFrame.setVisible(true);
    }

    public void showPremiumFrame() {
        PremiumFrame.setVisible(true);
    }

    public void save(ArrayList<Gym_member> arr)
    {
        File file = new File("MemberDetails.txt");
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            try(FileWriter writer = new FileWriter(file))
            {
                if(file.length() == 0)
                {
                    String header = String.format("%-4s %-15s %-10s %-12s %-22s %-7s %-10s %-8s %-19s %-21s %-13s %-17s %-15s %-11s %-13s %-13s%n",
                            "ID","Name", "Location", "Phone", "Email","Gender", "Trainer's name", "Plan", "Date of birth", "Membership start date", "Attendance", "Loyalty points",
                            "Active status", "Discount", "Full payment", "Paid Amount" );

                    String line = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
                    writer.write(header);
                    writer.write(line);
                }
                for(Gym_member member : arr)
                {
                    if(member instanceof Regular_member)
                    {
                        Regular_member regularMember = (Regular_member) member;

                        String line = String.format("%-4s %-15s %-12s %-12s %-22s %-7s %-15s %-8s %-19s %-21s %-13s %-17s %-15s %-11s %-13s %-13s%n",
                                regularMember.get_id(),
                                regularMember.get_name(),
                                regularMember.get_Location(),
                                regularMember.get_Phone(),
                                regularMember.get_email(),
                                regularMember.get_Gender(),
                                "-",
                                regularMember.get_Plan(),
                                regularMember.get_DOB(),
                                regularMember.get_MembershipStartDate(),
                                regularMember.get_Attendance(),
                                regularMember.get_LoyalPoints(),
                                regularMember.get_ActiveStatus(),
                                "-",
                                "-",
                                "-");
                        writer.write(line);
                    }
                    else if(member instanceof Premium_member)
                    {
                        Premium_member premiumMember = (Premium_member) member;

                        String line = String.format("%-4s %-15s %-12s %-12s %-22s %-7s %-15s %-8s %-19s %-21s %-13s %-17s %-15s %-11s %-13s %-13s%n",
                                premiumMember.get_id(),
                                premiumMember.get_name(),
                                premiumMember.get_Location(),
                                premiumMember.get_Phone(),
                                premiumMember.get_email(),
                                premiumMember.get_Gender(),
                                premiumMember.get_personalTrainer(),
                                "-",
                                premiumMember.get_DOB(),
                                premiumMember.get_MembershipStartDate(),
                                premiumMember.get_Attendance(), 
                                premiumMember.get_LoyalPoints(),
                                premiumMember.get_ActiveStatus(),
                                premiumMember.get_discountAmount(),
                                premiumMember.get_isFullPayment(),
                                premiumMember.get_paidAmount());

                        writer.write(line);
                    }
                }
                JOptionPane.showMessageDialog(Name, "Data stored successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(Name, "Unexpected error!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void read()
    {
        java.util.List<String> lines = new ArrayList<>();
        String line;

        File file = new File("MemberDetails.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        JFrame frame = new JFrame("Read");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1540, 500);
        frame.setLayout(null);
        frame.setResizable(true);
        int y = 5;
        for (String text : lines)
        {
            JLabel label = new JLabel(text);
            label.setFont(new Font("monospaced", Font.PLAIN, 10));
            label.setBounds(10, y, 1540, 20);
            frame.add(label);
            y+=25;
        }
        frame.setVisible(true);
    }
}

class RegularButtonListener implements ActionListener {
    private Gym_GUI gui;

    public RegularButtonListener(Gym_GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.showRegularFrame();
    }
}

class PremiumButtonListener implements ActionListener {
    private Gym_GUI gui;

    public PremiumButtonListener(Gym_GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.showPremiumFrame();
    }
}

