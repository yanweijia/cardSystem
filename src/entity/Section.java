package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Section implements Serializable {
    /**
     * 编号
     */
    private Integer sectionId;

    /**
     * 所属组织机构编号
     */
    private Integer organizationId;

    /**
     * 科室/班级名称
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}