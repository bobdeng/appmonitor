# Import Bean
@ComponentScan(basePackages = {"io.github.bobdeng"})
# Add io.github.bobdeng.appmonitor.annotation.MonitorMethod annotation to your bean's method
```
    @MonitorMethod
    public BaseResult countryList(){
```

#Get monitor data
http://yourip:yourport/monitor/list

# Maven
```
	<dependency>
            <groupId>io.github.bobdeng</groupId>
            <artifactId>appmonitor</artifactId>
            <version>0.4.0-SNAPSHOT</version>
   </dependency>
```
