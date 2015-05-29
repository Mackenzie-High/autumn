package example;

import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.Autumn;
import autumn.lang.compiler.AutumnParser;
import autumn.lang.compiler.ast.nodes.Module;
import java.io.File;

/**
 * This is a demonstration of using Autumn as a plugin from within a Java program. 
 * In other words, theis Java program will use Autumn as a scripting language. 
 *
 * Remember: The Jar file containing the Autumn compiler must be on the class-path
 *           during both compilation and execution of this Java program. 
 *
 */
public final class Main
{
    /**
     * This program compiles and executes a script written in Autumn.
     * 
     * @param args are ignored. 
     */
    public static void main (final String[] args) throws Throwable
    {
        /**
         * This is the string of Autumn source code that will be dynamically compiled and executed.
         */
        final String code = script();

        /**
         * This object will be used to report any compilation errors via standard-output. 
         */
        final BasicErrorReporter reporter = new BasicErrorReporter(System.out);

        /**
         * Create a parser that can parse Autumn source-code. 
         */
        final AutumnParser parser = new AutumnParser(reporter); 

        /**
         * This file object does *not* refer to an actual file on the file-system. 
         * The parser requires a file object for the puspose of reporting syntax-errors.
         */
        final File fake = new File("<script>");

        /**
         * Parse the scipt in order to obtain an Abstract-Syntax-Tree. 
         */
        final Module tree = parser.parse(code, fake);

        /**
         * This object will control the compilation and execution of the Autumn program. 
         */
        final Autumn autumn = new Autumn();
        autumn.setErrorReporter(reporter);
        autumn.src(tree);

        /**
         * Compile and execute the script that is written in Autumn.
         */
        System.out.println("<Before Script>");
        autumn.run(new String[0]);
        System.out.println("<After Script>");
    }

    /**
     * This method creates the string of Autumn source code that will be executed.
     *
     * @return the script that is written in Autumn.  
     */
    private static String script()
    {
        return "module MyScript in project;                                       \n"
             + "                                                                  \n"
             + "@Start                                                            \n"
             + "defun main (args : String[]) : void                               \n"
             + "{                                                                 \n"
             + "    F::println(\"Hello World from inside an Autumn script!\");    \n"
             + "}                                                                 \n";
    }
}




