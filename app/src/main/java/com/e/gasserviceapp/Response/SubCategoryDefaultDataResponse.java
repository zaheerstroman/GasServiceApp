package com.e.gasserviceapp.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategoryDefaultDataResponse {

    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;
    @SerializedName("data")
    @Expose
    private List<SubCategoryDefaultData> data;

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public List<SubCategoryDefaultData> getData() {
        return data;
    }

    public void setData(List<SubCategoryDefaultData> data) {
        this.data = data;
    }

    public class SubCategoryDefaultData{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("category_id")
        @Expose
        private Integer categoryId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("attachment")
        @Expose
        private Object attachment;
        @SerializedName("big_image")
        @Expose
        private Object bigImage;
        @SerializedName("file_path")
        @Expose
        private Object filePath;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("instruction")
        @Expose
        private Object instruction;
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
        private Object logDateCreated;
        @SerializedName("created_by")
        @Expose
        private Object createdBy;
        @SerializedName("log_date_modified")
        @Expose
        private Object logDateModified;
        @SerializedName("modified_by")
        @Expose
        private Object modifiedBy;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAttachment() {
            return attachment;
        }

        public void setAttachment(Object attachment) {
            this.attachment = attachment;
        }

        public Object getBigImage() {
            return bigImage;
        }

        public void setBigImage(Object bigImage) {
            this.bigImage = bigImage;
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

        public Object getInstruction() {
            return instruction;
        }

        public void setInstruction(Object instruction) {
            this.instruction = instruction;
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

        public Object getLogDateCreated() {
            return logDateCreated;
        }

        public void setLogDateCreated(Object logDateCreated) {
            this.logDateCreated = logDateCreated;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getLogDateModified() {
            return logDateModified;
        }

        public void setLogDateModified(Object logDateModified) {
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
