package com.example.tripmanagement.validation;

import com.example.tripmanagement.web.dto.TripRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class EndDateAfterStartDateValidator implements ConstraintValidator<EndDateAfterStartDate, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value instanceof TripRequest request) {
            LocalDate start = request.getStartDate();
            LocalDate end = request.getEndDate();
            if (start == null || end == null) {
                return true;
            }
            return end.isAfter(start);
        }

        return true;
    }
}


