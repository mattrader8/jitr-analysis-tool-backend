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
@Table(name = "jitr_organization")
public class JITROrganization 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_OrganizationID_OR", unique = true, nullable = false)
    private long jitrOrganizationID;

    @Column(name = "V_OrganizationName_OR", nullable = false)
    private String jitrOrganizationName;

    @OneToMany(mappedBy = "jitrOrganization")
    private List<JITR> jitrs;
    
    public JITROrganization()
    {

    }

    public JITROrganization(String jitrOrganizationName)
    {
        this.jitrOrganizationName = jitrOrganizationName;
    }

    public long getJitrOrganizationID() 
    {
        return jitrOrganizationID;
    }

    public void setJitrOrganizationID(long jitrOrganizationID) 
    {
        this.jitrOrganizationID = jitrOrganizationID;
    }

    public String getJitrOrganizationName() 
    {
        return jitrOrganizationName;
    }

    public void setJitrOrganizationName(String jitrOrganizationName) 
    {
        this.jitrOrganizationName = jitrOrganizationName;
    }
}