package i5b5.wajaty.hd.projekt.model.dwh;

public class Locality extends TechnicalDwhClass {
    private Integer localityId;
    private String localityName;
    private Integer districtId;

    public Integer getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Integer localityId) {
        this.localityId = localityId;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
