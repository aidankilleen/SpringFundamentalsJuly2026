package ie.pt.springbootwebexploration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {

    @Around("@annotation(audit))")
    public void auditBeforeAndAfter(
            ProceedingJoinPoint joinPoint,
            Audit audit
    ) throws Throwable {
        System.out.println("****Audit");
        String methodName = joinPoint.getSignature().getName();

        System.out.println("About to call " + methodName);

        joinPoint.proceed();

        System.out.println("***Audit: After the call" + methodName);
    }

    /*
    @AfterReturning("@annotation(audit)")
    public void logAudit(
            JoinPoint joinPoint,
            Audit audit
    ) {

        String methodName = joinPoint.getSignature().getName();
        String arguments = Arrays.toString(joinPoint.getArgs());
        System.out.println("*********AUDIT:" + audit.value());
        System.out.println(methodName);
        System.out.println(arguments);
    }

     */
}
