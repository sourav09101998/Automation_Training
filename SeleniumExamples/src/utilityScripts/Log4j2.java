/*All — This level of logging      will log everything ( it turns all the logs on )
DEBUG — print the debugging      information and is helpful in development stage
INFO — print informational      message that highlights the progress of the application
WARN — print information      regarding faulty and unexpected system behavior.
ERROR — print error message      that might allow system to continue
FATAL — print system critical      information which are causing the application to crash
OFF — No logging
* Logger class: log information at different logging levels.
* 6 Log levels: TRACE < DEBUG < INFO < WARN < ERROR < FATAL  
* Appenders class: help Logger objects write logs to different outputs.
* Text (log), Html and Console.
* Layout class: help define how the log information should appear in the outputs.
*/

package utilityScripts;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class Log4j2 {
        private  static final String  path="C:\\Users\\sakha\\eclipse-workspace1\\SeleniumExamples\\src\\";
        static {
//        PropertyConfigurator.configure(path+"log4j2.properties"); 
                System.setProperty("log4j.configurationFile", path+"log4j2.properties");
        }
    //Initialize Log4j instance
//    private static final Logger Log =  LogManager.getLogger(Log4j2.class);
//    private static final Logger Log =  LogManager.getLogger(Log4j2.class.getName());
    private static final Logger Log =  LogManager.getLogger();

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}