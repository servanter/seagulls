package com.crop.seagulls.entities;

import com.crop.seagulls.bean.Page;

/**
 * Product unit
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class ProductUnit extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = 2286800281408588996L;

    private Long id;

    private String title;

    public ProductUnit() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ProductUnit(int page, int pageSize) {
        super(page, pageSize);
        // TODO Auto-generated constructor stub
    }

    public ProductUnit(Long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProductUnit other = (ProductUnit) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

}
