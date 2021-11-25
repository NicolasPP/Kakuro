package kakuro.util;

import kakuro.KakuroApp;

import java.io.File;
import java.net.URISyntaxException;

public class Resources
{
    public static String getIconPath(String name)
    {
        return Thread.currentThread().getContextClassLoader().getResource("icons" + File.separator + name).toString();
    }

    public static File getPath(String name) throws URISyntaxException {

//        Thread.currentThread().getContextClassLoader().getResource("data" + "/" + name).toString();
        return new File(Thread.currentThread().getContextClassLoader().getResource("data" + "/" + name).toURI());
    }

}
