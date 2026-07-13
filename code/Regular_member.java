
/**
 * Write a description of class Regular_member here.
 *The Regular_member class extends Gym_member and represents a regular gym member with features like attendance tracking, loyalty 
points, and plan upgrades. It includes methods to mark attendance, check eligibility for plan upgrades, update plan prices, and 
revert the membership details. The class also manages member-specific attributes like attendance limit, plan, price, and referral 
source.
 * @author (Priya_Thapa)
 * @version (26/02/2025)
 */
class Regular_member extends Gym_member{
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String Plan;
    private double Price;
    public Regular_member(int id1,String Name1,String Location1,String Phone1,String email1,String Gender1,
    String DOB1,String MembershipStartDate1,String referralSource1){
        super(id1, Name1, Location1, Phone1, email1, Gender1,DOB1, MembershipStartDate1);
        this.isEligibleForUpgrade=false;
        this.attendanceLimit=30;
        this.Plan="Basic";
        this.Price=6500.0;
        this.removalReason="";
        this.referralSource=referralSource1;

    }

    public boolean get_isEligibleForUpgrade(){
        return this.isEligibleForUpgrade;
    }

    public int get_attendanceLimit(){
        return this.attendanceLimit;
    }

    public String get_Plan(){
        return this.Plan;
    }

    public double get_Price(){
        return this.Price;
    }

    public String get_removalReason(){
        return this.removalReason;
    }

    public String get_referralSource(){
        return this.referralSource;
    }

    public void markAttendance(){
        if(this.ActiveStatus==true){
            super.Attendance++;
            super.LoyalPoints +=5.0;
            System.out.println("Attendance marked for Regular Member: " + super.Name);
            System.out.println("Total Attendance: " + super.Attendance);
            System.out.println("Loyalty Points: " +super.LoyalPoints);
        }
        else{
            System.out.println("Account is deactive" );
        }
    }

    /* Method to get the price based on the selected plan*/
    public double getPlanPrice(String Plan){
        switch(Plan.toLowerCase()){
            case "basic":
                System.out.println("Price of this"+this.Plan+"plan is 6500");
                this.Price = 6500.0;
                return this.Price;

            case "standard":
                System.out.println("Price of this"+this.Plan+"plan is 12500");
                this.Price = 12500.0;
                return this.Price;

            case "deluxe":
                System.out.println("Price of this"+this.Plan+"plan is 18500");
                this.Price = 18500.0;
                return this.Price;

            default:
                return -1;

        }
    }

    public String upgradePlan(String newPlan) {
        // Check eligibility
        if (this.Attendance >= this.attendanceLimit) {
            this.isEligibleForUpgrade = true;
        } else {
            this.isEligibleForUpgrade = false;
        }

        if (!this.isEligibleForUpgrade) {
            return "You are not eligible for an upgrade yet.";
        }

        if (this.Plan.equalsIgnoreCase(newPlan)) {
            return "You are already subscribed to this plan.";
        }

        // Assign new plan first
        this.Plan = newPlan;

        // Get and update price
        double newPrice = getPlanPrice(newPlan);

        if (newPrice == -1) {
            return "Invalid plan selected.";
        }

        this.Price = newPrice;  // Correctly update price
        return "Plan upgraded successfully to " + newPlan + " with price " + newPrice;
    }

    public void revertRegularMember(String removalReason) {
        super.resetMember();  // Reset member details to default state
        this.isEligibleForUpgrade = false;  
        this.Plan = "basic";
        this.Price = 6500;  
        this.removalReason = removalReason;  
    }

    public void display(){
        super.display();
        System.out.println("Plan: " + this.Plan);
        System.out.println("Price: " + this.Price);

        if (!this.removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + this.removalReason);
        }
    }

}
