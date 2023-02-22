package com.e.gasserviceapp.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryDefaultDataResponse {

    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;
    @SerializedName("data")
    @Expose
    private List<CategoryDefaultData> categoryDefaultData;

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public List<CategoryDefaultData> getCategoryDefaultData() {
        return categoryDefaultData;
    }

    public void setCategoryDefaultData(List<CategoryDefaultData> categoryDefaultData) {
        this.categoryDefaultData = categoryDefaultData;
    }

    public class CategoryDefaultData{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("attachment")
        @Expose
        private String attachment;
        @SerializedName("file_path")
        @Expose
        private Object filePath;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("meta_title")
        @Expose
        private Object metaTitle;
        @SerializedName("meta_description")
        @Expose
        private Object metaDescription;
        @SerializedName("meta_keywords")
        @Expose
        private Object metaKeywords;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("priority")
        @Expose
        private Object priority;
        @SerializedName("log_date_created")
        @Expose
        private String logDateCreated;
        @SerializedName("created_by")
        @Expose
        private Object createdBy;
        @SerializedName("log_date_modified")
        @Expose
        private String logDateModified;
        @SerializedName("modified_by")
        @Expose
        private Object modifiedBy;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public Object getFilePath() {
            return filePath;
        }

        public void setFilePath(Object filePath) {
            this.filePath = filePath;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getMetaTitle() {
            return metaTitle;
        }

        public void setMetaTitle(Object metaTitle) {
            this.metaTitle = metaTitle;
        }

        public Object getMetaDescription() {
            return metaDescription;
        }

        public void setMetaDescription(Object metaDescription) {
            this.metaDescription = metaDescription;
        }

        public Object getMetaKeywords() {
            return metaKeywords;
        }

        public void setMetaKeywords(Object metaKeywords) {
            this.metaKeywords = metaKeywords;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getPriority() {
            return priority;
        }

        public void setPriority(Object priority) {
            this.priority = priority;
        }

        public String getLogDateCreated() {
            return logDateCreated;
        }

        public void setLogDateCreated(String logDateCreated) {
            this.logDateCreated = logDateCreated;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public String getLogDateModified() {
            return logDateModified;
        }

        public void setLogDateModified(String logDateModified) {
            this.logDateModified = logDateModified;
        }

        public Object getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(Object modifiedBy) {
            this.modifiedBy = modifiedBy;
        }
    }

}
