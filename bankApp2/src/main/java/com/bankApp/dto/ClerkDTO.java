package com.bankApp.dto;


public class ClerkDTO {
    private Integer clerkId;
    private String clerkName;

    // No-Args Constructor
    public ClerkDTO() {}

    // All-Args Constructor
    public ClerkDTO(Integer clerkId, String clerkName) {
        this.clerkId = clerkId;
        this.clerkName = clerkName;
    }

    // Getters and Setters
    public Integer getClerkId() { return clerkId; }
    public void setClerkId(Integer clerkId) { this.clerkId = clerkId; }

    public String getClerkName() { return clerkName; }
    public void setClerkName(String clerkName) { this.clerkName = clerkName; }
}