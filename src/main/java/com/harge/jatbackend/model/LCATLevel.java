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
@Table(name = "lcat_level")
public class LCATLevel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_LCATLevelID_LL", unique = true, nullable = false)
    private long lcatLevelID;

    @Column(name = "V_LCATLevelDescription_LL", unique = true, nullable = false)
    private String lcatLevelDescription;

    @OneToMany(mappedBy = "lcatLevel")
    private List<JITRLCATLevels> jitrLCATLevels;

    public LCATLevel()
    {

    }

    public LCATLevel(String lcatLevelDescription)
    {
        this.lcatLevelDescription = lcatLevelDescription;
    }

    public long getLcatLevelID() 
    {
        return lcatLevelID;
    }

    public void setLcatLevelID(long lcatLevelID) 
    {
        this.lcatLevelID = lcatLevelID;
    }

    public String getLcatLevelDescription() 
    {
        return lcatLevelDescription;
    }

    public void setLcatLevelDescription(String lcatLevelDescription) 
    {
        this.lcatLevelDescription = lcatLevelDescription;
    }
}