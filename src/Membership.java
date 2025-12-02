import java.util.Calendar;
import java.util.Date;

public class Membership {
    private Reader reader;
    private Date startDate;
    private Date endDate;
    private MembershipType membershipType;
    public Membership(Reader reader, Date startDate, Date endDate) {
        this.setReader(reader);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public Membership(Reader reader, Date startDate, Date endDate,  MembershipType membershipType) {
        this.setReader(reader);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setMembershipType(membershipType);
    }

    public static Membership createStandardAnnual(Reader reader) {
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, 1); // Додаємо один рік
        Date endDate = calendar.getTime();
        return new Membership(reader, startDate, endDate);
    }

    public static Membership createLifetime(Reader reader, Date startDate) {
        return new Membership(reader, startDate, null);
    }

    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        if(endDate != null && endDate.before(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.endDate = endDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "Membership of " + reader + " from " + startDate + " til " + endDate;
    }
}
