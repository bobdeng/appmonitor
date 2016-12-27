# Use AOP to intercept your methods need to monitor

```
@Aspect
@Component
public class PerformanceAop {
    @Autowired
    Monitor monitor;
    
	@Around("@annotation(MonitorMethod)")
    public Object whenMethodCall(ProceedingJoinPoint joinPoint){
        try {
            return monitor.newInvoke(joinPoint);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
```

```
@MonitorMethod
public void methodNeedToMonitor(){
}
```
# Import Bean
@ComponentScan(basePackages = {"io.github.bobdeng"})

#Get monitor data
http://yourip:yourport/monitor/list

# Maven
```
	<dependency>
            <groupId>io.github.bobdeng</groupId>
            <artifactId>appmonitor</artifactId>
            <version>0.3.1</version>
   </dependency>
```
