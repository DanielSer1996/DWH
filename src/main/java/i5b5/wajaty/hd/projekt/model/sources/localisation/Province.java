package i5b5.wajaty.hd.projekt.model.sources.localisation;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

public class Province extends SubjectActionClass {
    private int provinceId;
    private String provinceName;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setIsDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }
}
