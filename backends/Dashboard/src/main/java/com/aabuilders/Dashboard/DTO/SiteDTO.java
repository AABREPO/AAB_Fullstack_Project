package com.aabuilders.Dashboard.DTO;

public class SiteDTO {
    private Long sNo;
    private String siteName;

    public SiteDTO(Long sNo, String siteName) {
        this.sNo = sNo;
        this.siteName = siteName;
    }

    public Long getsNo() {
        return sNo;
    }

    public void setsNo(Long sNo) {
        this.sNo = sNo;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
