package i5b5.wajaty.hd.projekt.model.sources.localisation;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

public class Locality extends SubjectActionClass {
    private int localityId;
    private String localityName;
    private int districtId;

    public int getLocalityId() {
        return localityId;
    }

    public void setLocalityId(int localityId) {
        this.localityId = localityId;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
}
