package i5b5.wajaty.hd.projekt.model.sources.localisation;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

public class District extends SubjectActionClass {
    private int districtId;
    private String districtName;
    private int provinceId;

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
