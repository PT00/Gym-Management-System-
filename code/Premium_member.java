
/**
 * Write a description of class Premium_member here.
 *The Premium_member class extends Gym_member and represents a premium gym member who has additional features like a personaltrainer,
  full payment status, and premium charges. It includes methods for marking attendance, paying dues, calculating discounts for full
  payments, and reverting member details. The class tracks payment amounts, calculates remaining dues, and displays detailed 
  information such as the personal trainer and discount amount.
 * @author (Priya_Thapa)
 * @version (28/02/2025)
 */
class Premium_member extends Gym_member
{
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    public Premium_member(int id1,String Name1,String Location1,String Phone1,String email1,String Gender1,
   String DOB1,String MembershipStartDate1, String personalTrainer1){
       super(id1, Name1, Location1, Phone1, email1, Gender1,DOB1, MembershipStartDate1);
       this.premiumCharge=50000.0;
       this.paidAmount=0.0;
       this.isFullPayment=false;
       this.discountAmount=0.0;
       this.personalTrainer=personalTrainer1;
   }
   public double get_premiumCharge(){
       return this.premiumCharge;
   }
     public double get_paidAmount(){
       return this.paidAmount;
   }
   public boolean get_isFullPayment(){
        return this.isFullPayment;
   }
     public double get_discountAmount(){
       return this.discountAmount;
   }
     public String get_personalTrainer(){
       return this.personalTrainer;
   }
   
   public void markAttendance(){
       if(super.ActiveStatus==true){
       super.Attendance++;
       super.LoyalPoints +=10.0;
       System.out.println("Attendance marked for Premium Member: " + super.Name);
    System.out.println("Total Attendance: " + super.Attendance);
    System.out.println("Loyalty Points: " +super.LoyalPoints);
}
else{
    System.out.println("Account is deactive" );
}
}

   
   /*Method to handle payment of dues and check full payment status*/
   public String payDueAmount(double paidAmount) {
    this.paidAmount = paidAmount;
    
    if(this.isFullPayment) {
        return "Amount had been paid already";
    }
    else if(this.premiumCharge < paidAmount) {
        this.isFullPayment = true;
        return "Amount exceeds premium charge. Full payment recorded.";
    }
    else if(this.premiumCharge > paidAmount) {
        double remainingAmount = this.premiumCharge - paidAmount;
        this.isFullPayment = false;
        return "Remaining amount to pay: Rs. " + remainingAmount;
    }
    else {
        this.isFullPayment = true;
        return "Full payment received";
    }
}
   /*Method to calculate discount for full payment*/
   public void calculateDiscount(){
      if(this.isFullPayment==true){
          this.discountAmount=0.1*this.premiumCharge;
         System.out.println("Your discount is: " +this.discountAmount);
      }
      else{
          System.out.println("No discount");
      }
   }
   
   public void revertPremiumMember()
    {
        super.resetMember();
        this.personalTrainer="";
        this.isFullPayment=false;
        this.paidAmount=0.0;
        this.discountAmount=0.0;
    }
    
   public void display(){
    super.display(); /*Display basic member details*/
    System.out.println("Personal Trainer : " + this.personalTrainer);
    System.out.println("Paid Amount : " + this.paidAmount);
    System.out.println("Full Payment : " + this.isFullPayment);
    double remainingAmount=this.premiumCharge-paidAmount;
    System.out.println("Remaining Amount : " + remainingAmount);
    if(isFullPayment == true)
      {
     System.out.println("Discount Amount : " + this.discountAmount);
     }
   }
    }
   

