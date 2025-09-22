package org.kavin.sbecom.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    String field;
    String ResourceName;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String field, String resourceName, String fieldName) {
        super(String.format("%s not found with %s : %s",resourceName,field,fieldName));
        this.field = field;
        ResourceName = resourceName;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String field, Long fieldId, String resourceName) {
        super(String.format("%s not found with %s : %d",resourceName,field,fieldId));
        this.field = field;
        this.fieldId = fieldId;
        ResourceName = resourceName;
    }
}
