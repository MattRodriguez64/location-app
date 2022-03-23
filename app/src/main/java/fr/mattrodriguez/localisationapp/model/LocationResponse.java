package fr.mattrodriguez.localisationapp.model;

import java.util.List;

public class LocationResponse {

    private String type;
    private String version;
    private List<Feature> features;
    private String attribution;
    private String licence;
    private String limit;

    public LocationResponse(String type, String version, List<Feature> features, String attribution, String licence, String limit) {
        this.type = type;
        this.version = version;
        this.features = features;
        this.attribution = attribution;
        this.licence = licence;
        this.limit = limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
