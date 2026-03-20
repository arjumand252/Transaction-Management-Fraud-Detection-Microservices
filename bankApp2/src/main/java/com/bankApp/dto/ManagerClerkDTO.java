package com.bankApp.dto;
import java.util.List;
import java.util.ArrayList;

public class ManagerClerkDTO {
    private Integer managerId;
    private String managerName;
    private List<ClerkDTO> clerks = new ArrayList<>();

    // No-Args Constructor
    public ManagerClerkDTO() {}

    // All-Args Constructor
    public ManagerClerkDTO(Integer managerId, String managerName, List<ClerkDTO> clerks) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.clerks = clerks;
    }

    // Getters and Setters
    public Integer getManagerId() { return managerId; }
    public void setManagerId(Integer managerId) { this.managerId = managerId; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public List<ClerkDTO> getClerks() { return clerks; }
    public void setClerks(List<ClerkDTO> clerks) { this.clerks = clerks; }
}