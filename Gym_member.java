
/**
 *The Gym_member class is an abstract class representing a gym member's details and membership status. It stores personal information 
such as name, contact details, and membership data like attendance and loyalty points, while providing methods to activate/
deactivate memberships, reset member data, and display their information.
 * @author (Priya Thapa)
 * @version (25/2/2025)
 */
abstract class Gym_member
{
    protected int id;
    protected String Name;
    protected String Location;
    protected String Phone;
    protected String email;
    protected String Gender;
    protected String DOB;
    protected String MembershipStartDate;
    protected int Attendance;
    protected double LoyalPoints;
    protected boolean ActiveStatus;

    /*Constructor to initialize the gym member's details*/
    public Gym_member(int id1,String Name1,String Location1,String Phone1,String email1,String Gender1,
    String DOB1,String MembershipStartDate1){
        this.Attendance=0;
        this.ActiveStatus=false;
        this.LoyalPoints=0.0;
        this.id=id1;
        this.Name=Name1;
        this.Location=Location1;
        this.Phone=Phone1;
        this.email=email1;
        this.Gender=Gender1;
        this.DOB=DOB1;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        this.MembershipStartDate=MembershipStartDate1;

    }

    /* Getter methods for all the attributes (to access values of private fields)*/
    public int get_id(){
        return this.id;
    }

    public String get_name(){
        return this.Name;
    }

    public String get_Location(){
        return this.Location;
    }

    public String get_Phone(){
        return this.Phone;
    }

    public String get_email(){
        return this.email;
    }

    public String get_Gender(){
        return this.Gender;
    }

    public String get_DOB(){
        return this.DOB;
    }

    public String get_MembershipStartDate(){
        return this.MembershipStartDate;
    }

    public int get_Attendance()
    {
        return this.Attendance;
    }

    public boolean get_ActiveStatus()
    {
        return this.ActiveStatus;
    }

    public double get_LoyalPoints() {
        return this.LoyalPoints;
    }

    public abstract  void markAttendance ();

    /* Method to activate the membership */
    public void activateMembership() {
        if(this.ActiveStatus==false){
            this.ActiveStatus=true;
            System.out.println("The membership"+this.id+" is successfully activated");
        }
        else
        {
            System.out.println("The membership"+this.id+" is already activated"); 
        }   
    }

    /* Method to deactivate the membership if it's active*/
    public void deactiveMembership() {
        if(this.ActiveStatus==true){
            this.ActiveStatus=false;
            System.out.println("The membership"+this.id+" is successfully deactivated");
        }
        else{
            System.out.println("The membership"+this.id+" is already deactivated");
        }
    }

    /* Method to reset the members data */
    public void resetMember()
    {
        this.ActiveStatus=false;
        this.Attendance=0;
        this.LoyalPoints=0.0;
        System.out.println("Membership"+this.id+"is reset successfully");
    }

    /* Method to display the member's details*/
    public void display(){
        System.out.println("Member ID:"+get_id());
        System.out.println("Member Name:"+get_name());
        System.out.println("Member Location:"+get_Location());
        System.out.println("Member phone number:"+get_Phone());
        System.out.println("Member Email:"+get_email());
        System.out.println("Gender of member:"+get_Gender());
        System.out.println("Date of birth of member"+get_DOB()); 
        System.out.println("Membership start date"+get_MembershipStartDate()); 
        System.out.println("Membership attendace"+get_Attendance());
        System.out.println("Membership loyal point"+get_LoyalPoints());
        System.out.println("Membership active status"+get_ActiveStatus());
    }
}
