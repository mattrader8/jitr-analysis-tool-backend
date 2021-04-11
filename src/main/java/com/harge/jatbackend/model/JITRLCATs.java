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
@Table(name = "jitr_lcats")
public class JITRLCATs 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_JITRLCATID_JL", unique = true, nullable = false)
    private long jitrLCATID;

    @ManyToOne
    @JoinColumn(name = "C_JITRNumber_JL", nullable = false)
    JITR jitr;

    @ManyToOne
    @JoinColumn(name = "C_LCATID_JL", nullable = false)
    LCAT lcat;

    public JITRLCATs()
    {

    }

    public JITRLCATs(JITR jitr, LCAT lcat)
    {
        this.jitr = jitr;
        this.lcat = lcat;
    }

    public long getJitrLCATID() 
    {
        return jitrLCATID;
    }

    public void setJitrLCATID(long jitrLCATID) 
    {
        this.jitrLCATID = jitrLCATID;
    }

    public JITR getJitr() 
    {
        return jitr;
    }

    public void setJitr(JITR jitr) 
    {
        this.jitr = jitr;
    }

    public LCAT getLcat() 
    {
        return lcat;
    }

    public void setLcat(LCAT lcat) 
    {
        this.lcat = lcat;
    }
}