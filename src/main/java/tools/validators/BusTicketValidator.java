package tools.validators;

import tools.validators.subValidators.DateValidator;
import tools.validators.subValidators.EvenValidator;
import tools.validators.subValidators.NullValidator;
import tools.validators.subValidators.TypeValidator;

import java.util.ArrayList;
import java.util.List;

public class BusTicketValidator {

    private final List<IValidator> validators;

    public BusTicketValidator() {
        this.validators = new ArrayList<>();
        initializeValidators();
    }

    private void initializeValidators() {
        validators.add(new EvenValidator());
        validators.add(new TypeValidator());
        validators.add(new NullValidator());
        validators.add(new DateValidator());
    }

    public List<String> validate(Object obj) {
        List<String> messages = new ArrayList<>();
        for (IValidator validator : validators) {
            String message = validator.validate(obj);
            if (message != null) {
                messages.add(message);
            }
        }

        return messages;
    }
}
