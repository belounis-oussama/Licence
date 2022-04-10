package com.example.epa51;

public class EtasArret_Model implements java.io.Serializable{

    private String StartDate;
    private String StartHour;
    private String EndDate;
    private String EndHour;
    private String Reason;

    public EtasArret_Model(String startDate, String startHour, String endDate, String endHour, String reason) {
        this.StartDate = startDate;
        this.StartHour = startHour;
        this.EndDate = endDate;
        this.EndHour = endHour;
        this.Reason = reason;
    }

    public EtasArret_Model()
    {

    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStartHour() {
        return StartHour;
    }

    public void setStartHour(String startHour) {
        StartHour = startHour;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getEndHour() {
        return EndHour;
    }

    public void setEndHour(String endHour) {
        EndHour = endHour;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    @Override
    public String toString() {
        return "EtasArret_Model{" +
                "StartDate='" + StartDate + '\'' +
                ", StartHour='" + StartHour + '\'' +
                ", EndDate='" + EndDate + '\'' +
                ", EndHour='" + EndHour + '\'' +
                ", Reason='" + Reason + '\'' +
                '}';
    }
}
