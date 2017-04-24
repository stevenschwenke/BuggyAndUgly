package de.stevenschwenke.java.buggyandugly.importer;

import java.util.Date;

public class ImportMetadata {

    private String country;
    private String type;
    private String someInternalNumber;
    private Date periodFrom;
    private Date periodUntil;
    private String filename;
    private Date creationDate;
    private Boolean archive;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSomeInternalNumber() {
        return someInternalNumber;
    }

    public void setSomeInternalNumber(String someInternalNumber) {
        this.someInternalNumber = someInternalNumber;
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodUntil() {
        return periodUntil;
    }

    public void setPeriodUntil(Date periodUntil) {
        this.periodUntil = periodUntil;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }
}
