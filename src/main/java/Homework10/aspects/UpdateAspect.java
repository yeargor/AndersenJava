package Homework10.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UpdateAspect {

    @Value("${updateAbility}")
    private boolean featureEnabled;

    @Before("execution(* Homework10.repository.TicketRepository.createTicket(..))")
    public void checkUpdateAbility() throws Exception {
        if(!featureEnabled) {
            throw new Exception("You can't update User because of configuration's property");
        }
    }
}
