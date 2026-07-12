package com.projects.ims.common;

import lombok.Data;

@Data
public class ApiResponse<DTO> {
    private boolean success;
    private String message;
    private DTO data;
}
