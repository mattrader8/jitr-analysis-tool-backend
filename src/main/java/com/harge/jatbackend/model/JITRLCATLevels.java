package com.harge.jatbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jitr_lcat_levels")
public class JITRLCATLevels 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_JITRLCATLevelID_LJ", unique = true, nullable = false)
    private long jitrLCATLevelID;

    @ManyToOne
    @JoinColumn(name = "C_JITRNumber_LJ", nullable = false)
    JITR jitr;

    @ManyToOne
    @JoinColumn(name = "C_LCATLevelID_LJ", nullable = false)
    LCATLevel lcatLevel;

    public JITRLCATLevels()
    {

    }

    public JITRLCATLevels(JITR jitr, LCATLevel lcatLevel)
    {
        this.jitr = jitr;
        this.lcatLevel = lcatLevel;
    }

    public long getJitrLCATLevelID() 
    {
        return jitrLCATLevelID;
    }

    public void setJitrLCATLevelID(long jitrLCATLevelID) 
    {
        this.jitrLCATLevelID = jitrLCATLevelID;
    }

    public JITR getJitr() 
    {
        return jitr;
    }

    public void setJitr(JITR jitr) 
    {
        this.jitr = jitr;
    }

    public LCATLevel getLcatLevel() 
    {
        return lcatLevel;
    }

    public void setLcatLevel(LCATLevel lcatLevel) 
    {
        this.lcatLevel = lcatLevel;
    }
}