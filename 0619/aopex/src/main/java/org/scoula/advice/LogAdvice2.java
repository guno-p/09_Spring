package org.scoula.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LogAdvice2 {

    // 1. 기본 로깅 - 메서드 실행 전
    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logBefore() {
        log.info("==================== METHOD START ====================");
    }

    // 2. 파라미터 추적 - 특정 메서드의 파라미터 캡처
    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("📥 INPUT PARAMETERS:");
        log.info("  ├─ str1: {}", str1);
        log.info("  └─ str2: {}", str2);
    }

    // 3. 정상 완료 시 결과 로깅
    @AfterReturning(
            pointcut = "execution(* org.scoula.sample.service.SampleService*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(Object result) {
        log.info("✅ SUCCESS RESULT: {}", result);
    }

    // 4. 예외 발생 시 처리
    @AfterThrowing(
            pointcut = "execution(* org.scoula.sample.service.SampleService*.*(..))",
            throwing = "exception"
    )
    public void logException(Exception exception) {
        log.error("❌ EXCEPTION OCCURRED:");
        log.error("  ├─ Type: {}", exception.getClass().getSimpleName());
        log.error("  └─ Message: {}", exception.getMessage());
    }

    // 5. 메서드 완료 시 (정상/예외 무관)
    @After("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logAfter() {
        log.info("==================== METHOD END ===================");
    }

    // 6. 종합 모니터링 - 실행 시간 및 상세 정보
    @Around("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        log.info("🚀 STARTING EXECUTION:");
        log.info("  ├─ Class: {}", className);
        log.info("  ├─ Method: {}", methodName);
        log.info("  └─ Arguments: {}", Arrays.toString(args));

        long startTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = pjp.proceed(); // 실제 메서드 실행
            return result;
        } catch (Throwable e) {
            log.error("💥 EXECUTION FAILED: {}", e.getMessage());
            throw e; // 예외를 다시 던져서 정상적인 예외 처리 흐름 유지
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            log.info("⏱️  EXECUTION TIME: {}ms", executionTime);

            // 성능 경고 (100ms 이상 소요 시)
            if (executionTime > 100) {
                log.warn("🐌 SLOW EXECUTION DETECTED: {}.{} took {}ms",
                        className, methodName, executionTime);
            }
        }
    }
}
