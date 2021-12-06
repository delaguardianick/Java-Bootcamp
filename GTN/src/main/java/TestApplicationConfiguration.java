import Nickdlg.GTN.data.GTNDao;
import Nickdlg.GTN.service.GTNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
        value = CommandLineRunner.class))
@EnableAutoConfiguration
public class TestApplicationConfiguration {
        @Autowired
        GTNDao gtnDao;

       @Autowired
       GTNService gtnService;
}