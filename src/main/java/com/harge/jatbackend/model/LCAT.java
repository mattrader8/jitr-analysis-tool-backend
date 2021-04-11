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
@Table(name = "lcat")
public class LCAT 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_LCATID_LT", unique = true, nullable = false)
    private long lcatID;

    @Column(name = "V_LCATDescription_LT", unique = true, nullable = false)
    private String lcatDescription;

    @OneToMany(mappedBy = "lcat")
    private List<JITRLCATs> jitrLCATs;

    public LCAT()
    {

    }

    public LCAT(String lcatDescription)
    {
        this.lcatDescription = lcatDescription;
    }

    public long getLcatID() 
    {
        return lcatID;
    }

    public void setLcatID(long lcatID) 
    {
        this.lcatID = lcatID;
    }

    public String getLcatDescription() 
    {
        return lcatDescription;
    }

    public void setLcatDescription(String lcatDescription) 
    {
        this.lcatDescription = lcatDescription;
    }
}