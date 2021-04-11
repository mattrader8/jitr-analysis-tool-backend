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
@Table(name = "jitr_rating")
public class JITRRating 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_RatingID_RA", unique = true, nullable = false)
    private long jitrRatingID;

    @Column(name = "V_RatingDescription_RA", nullable = false)
    private String ratingDescription;

    @OneToMany(mappedBy = "jitrRating")
    private List<JITR> jits;

    public JITRRating()
    {
        
    }

    public JITRRating(String ratingDescription)
    {
        this.ratingDescription = ratingDescription;
    }

    public long getJitrRatingID() 
    {
        return jitrRatingID;
    }

    public void setJitrRatingID(long jitrRatingID) 
    {
        this.jitrRatingID = jitrRatingID;
    }

    public String getRatingDescription() 
    {
        return ratingDescription;
    }

    public void setRatingDescription(String ratingDescription) 
    {
        this.ratingDescription = ratingDescription;
    }
}