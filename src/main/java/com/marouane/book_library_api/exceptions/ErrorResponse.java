package com.marouane.book_library_api.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int         status;
    private String      error;
    private String      message;
    private String      path;
    private Instant     timestamp;
}
