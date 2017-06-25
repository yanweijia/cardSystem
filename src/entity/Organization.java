package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Organization implements Serializable {
    /**
     * 组织机构编号
     */
    private Integer organization_id;

    /**
     * 组织机构名称
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}