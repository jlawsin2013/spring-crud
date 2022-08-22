package com.project.self.crud.apierror;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SubError {
	private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
