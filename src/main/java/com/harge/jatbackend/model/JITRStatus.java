package com.harge.jatbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jitr_status")
public class JITRStatus 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_StatusID_ST", unique = true, nullable = false)
    private long jitrStatusID;

    @Column(name = "V_StatusDescription_ST", nullable = false)
    private String statusDescription;

    @OneToMany(mappedBy = "jitrStatus")
    private List<JITR> jitrs;

    public JITRStatus()
    {
        
    }

    public JITRStatus(String statusDescription)
    {
        this.statusDescription = statusDescription;
    }

    public long getJitrStatusID() 
    {
        return jitrStatusID;
    }

    public void setJitrStatusID(long jitrStatusID) 
    {
        this.jitrStatusID = jitrStatusID;
    }

    public String getStatusDescription() 
    {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) 
    {
        this.statusDescription = statusDescription;
    }
}