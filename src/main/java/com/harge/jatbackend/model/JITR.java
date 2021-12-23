package com.harge.jatbackend.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jitrs")
public class JITR 
{
    @Id
    @Column(name = "C_JITRNumber_JI", unique = true, nullable = false)
    private int jitrNumber;

    @Column(name = "D_JITRDate_JI", nullable = false)
    private LocalDate jitrDate;

    @Column(name = "I_NumberOfFTE_JI", nullable = false)
    private int numberOfFTE;

    @ManyToOne
    @JoinColumn(name = "C_StatusID_JI", nullable = false)
    private JITRStatus jitrStatus;

    @ManyToOne
    @JoinColumn(name = "C_RatingID_JI", nullable = false)
    private JITRRating jitrRating;

    @Column(name = "DE_PraxisEstimatedCost_JI", nullable = true)
    private double praxisEstimatedCost; 

    @Column(name = "DE_WinningPrimeEstimatedCost_JI", nullable = true)
    private double winningPrimeEstimatedCost; 

    @ManyToOne
    @JoinColumn(name = "C_OrganizationID_JI", nullable = false)
    private JITROrganization jitrOrganization;

    @OneToMany(mappedBy = "jitr", cascade = CascadeType.ALL)
    private List<JITRPositions> jitrPositions;

    public JITR()
    {

    }

    public JITR(int numberOfFTE, LocalDate jitrDate, JITRStatus jitrStatus, JITRRating jitrRating, double praxisEstimatedCost, double winningPrimeEstimatedCost,
        JITROrganization jitrOrganization)
    {
        this.numberOfFTE = numberOfFTE;
        this.jitrDate = jitrDate;
        this.jitrStatus = jitrStatus;
        this.jitrRating = jitrRating;
        this.praxisEstimatedCost = praxisEstimatedCost;
        this.winningPrimeEstimatedCost = winningPrimeEstimatedCost;
        this.jitrOrganization = jitrOrganization;
    }

    public int getJitrNumber() {
        return jitrNumber;
    }

    public void setJitrNumber(int jitrNumber) {
        this.jitrNumber = jitrNumber;
    }

    public LocalDate getJitrDate() {
        return jitrDate;
    }

    public void setJitrDate(LocalDate jitrDate) {
        this.jitrDate = jitrDate;
    }

    public int getNumberOfFTE() 
    {
        return numberOfFTE;
    }

    public void setNumberOfFTE(int numberOfFTE) 
    {
        this.numberOfFTE = numberOfFTE;
    }

    public JITRStatus getJitrStatus() 
    {
        return jitrStatus;
    }

    public void setJitrStatus(JITRStatus jitrStatus)
    {
        this.jitrStatus = jitrStatus;
    }

    public JITRRating getJitrRating() 
    {
        return jitrRating;
    }

    public void setJitrRating(JITRRating jitrRating) 
    {
        this.jitrRating = jitrRating;
    }

    public double getPraxisEstimatedCost() 
    {
        return praxisEstimatedCost;
    }

    public void setPraxisEstimatedCost(double praxisEstimatedCost) 
    {
        this.praxisEstimatedCost = praxisEstimatedCost;
    }

    public double getWinningPrimeEstimatedCost() 
    {
        return winningPrimeEstimatedCost;
    }

    public void setWinningPrimeEstimatedCost(double winningPrimeEstimatedCost) 
    {
        this.winningPrimeEstimatedCost = winningPrimeEstimatedCost;
    }

    public JITROrganization getJitrOrganization() 
    {
        return jitrOrganization;
    }

    public void setJitrOrganization(JITROrganization jitrOrganization) 
    {
        this.jitrOrganization = jitrOrganization;
    }
}