package liga.medical.medicalmessageanalyzer.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.common.service.model.Debug;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import liga.medical.common.service.debug.DebugService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingAdvice {

    private final DebugService debugService;

    @Pointcut(value = "execution(* liga.medical.medicalmessageanalyzer.core.controller.*.*(..))")
    public void restPointcut() { }

    @Around("restPointcut()")
    public Object restControllerLogger(ProceedingJoinPoint pjp) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Object[] array = pjp.getArgs();

        log.info(formatter.format(date) + " Вызван метод: " + methodName  + " из класса " + className + " с аргументами" + mapper.writeValueAsString(array));

        Debug newDebug = new Debug();

        newDebug.setSystemTypeId(1);
        newDebug.setMethodParams(" Вызван метод: " + methodName  + " из класса " + className + " с аргументами" + mapper.writeValueAsString(array));

        debugService.addDebug(newDebug);

        Object obj = null;

        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        log.info(className + ":" + methodName + "() " + "Response: " + mapper.writeValueAsString(obj));


        return obj;
    }
}
