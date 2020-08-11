package com.lls;

import com.lls.entity.PersonFact;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsFlowTest {
//    @Autowired
//    private KieSession kSession;

    @Test
    public void testOne(){
        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-process");
            PersonFact person = new PersonFact();
            person.setName("李某某");
            person.setBirthday(DateUtils.parseDate("2000-01-01","yyyy-MM-dd"));

            kSession.insert(person);
            kSession.startProcess("process.myProcess");
            kSession.fireAllRules();
//            kSession.dispose();
            kSession.destroy();
            System.out.println(person.getName()+"的年龄是："+person.getAge());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
