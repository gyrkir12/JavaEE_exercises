package no.jenjon13.reeddit.ejb.abstracts;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class EntityEJBTest {
    private static EJBContainer ec;
    private static Context ctx;

    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ctx != null)
            ctx.close();
        if (ec != null)
            ec.close();
    }

    public static <T> T getEJB( Class<T> clazz ) {
        try {
            return (T) ctx.lookup("java:global/classes/" + clazz.getSimpleName() + "!" + clazz.getName());
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
