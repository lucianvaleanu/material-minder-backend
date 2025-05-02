package com.lucianvaleanu.validator;

import com.lucianvaleanu.model.Project;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

@Component
public class ProjectValidator {

    private final Validator validator;

    public ProjectValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void validate(Project project) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Project> violation : violations) {
                sb.append(violation.getMessage()).append("; ");
            }
            throw new ConstraintViolationException("Project validation failed: " + sb, violations);
        }

        if (project.getTitle() == null || project.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Project title must not be null or empty.");
        }

        if (project.getProjectDate() != null) {
            throw new IllegalArgumentException("Project date must not be null.");
        }
    }
}