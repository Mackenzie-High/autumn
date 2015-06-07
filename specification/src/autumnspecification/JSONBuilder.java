package autumnspecification;

import autumn.util.F;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mackenzie
 */
public class JSONBuilder
{
    public static final File SPECIFICATION = new File("/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/out/");

    public static final File FUNCTIONS = new File(SPECIFICATION, "functions/");

    public static final File STATIC_CHECKS = new File(SPECIFICATION, "typechecks/");

    public static final String MARKDOWN = "/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/markdown/";

    public static final String EXAMPLES = "/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/examples/";

    public static final File AUTUMN_UTIL_F = new File("/media/disk/Code/NetBeansProjects/autumn/src/autumn/util/F.java");

    private static final Map<String, String> statements = Maps.newHashMap();

    private static final Map<String, String> expressions = Maps.newHashMap();

    public static void write()
            throws Exception
    {
        Index.write();

        FunctionIndex.write();

        TypeCheckIndex.write();

        FunctionIndex.writeClassF();

        for (Construct c : Construct.instances)
        {
            c.write();
        }

        for (Page p : Page.instances)
        {
            p.write();
        }

        for (ExamplePage p : ExamplePage.instances)
        {
            p.write();
        }

        for (Function p : FunctionIndex.functions())
        {
            p.write();
        }

    }

    public static String highlight(String s)
    {
        s = keyword(s, "module");
        s = keyword(s, "in");
        s = keyword(s, "include");
        s = keyword(s, "import");

        s = keyword(s, "annotation");
        s = keyword(s, "exception");
        s = keyword(s, "enum");
        s = keyword(s, "tuple");
        s = keyword(s, "functor");
        s = keyword(s, "struct");
        s = keyword(s, "design");
        s = keyword(s, "extends");
        s = keyword(s, "data");
        s = keyword(s, "method");
        s = keyword(s, "defun");

        s = keyword(s, "void");
        s = keyword(s, "boolean");
        s = keyword(s, "char");
        s = keyword(s, "byte");
        s = keyword(s, "short");
        s = keyword(s, "int");
        s = keyword(s, "long");
        s = keyword(s, "float");
        s = keyword(s, "double");
        s = keyword(s, "class");

        s = keyword(s, "when");
        s = keyword(s, "then");
        s = keyword(s, "if");
        s = keyword(s, "elif");
        s = keyword(s, "else");
        s = keyword(s, "goto");
        s = keyword(s, "marker");
        s = keyword(s, "switch");
        s = keyword(s, "branch");
        s = keyword(s, "default");
        s = keyword(s, "do");
        s = keyword(s, "break");
        s = keyword(s, "continue");
        s = keyword(s, "redo");
        s = keyword(s, "for");
        s = keyword(s, "foreach");
        s = keyword(s, "forever");
        s = keyword(s, "until");
        s = keyword(s, "while");
        s = keyword(s, "assert");
        s = keyword(s, "assume");
        s = keyword(s, "echo");
        s = keyword(s, "throw");
        s = keyword(s, "try");
        s = keyword(s, "catch");
        s = keyword(s, "var");
        s = keyword(s, "val");
        s = keyword(s, "let");
        s = keyword(s, "setter");
        s = keyword(s, "getter");
        s = keyword(s, "method");
        s = keyword(s, "nop");
        s = keyword(s, "yield");
        s = keyword(s, "return");
        s = keyword(s, "recur");

        s = keyword(s, "as");
        s = keyword(s, "is");

        s = keyword(s, "class");
        s = keyword(s, "null");
        s = keyword(s, "true");
        s = keyword(s, "false");

        s = keyword(s, "funcall");
        s = keyword(s, "dispatch");
        s = keyword(s, "new");
        s = keyword(s, "call");
        s = keyword(s, "field");
        s = keyword(s, "instanceof");
        s = keyword(s, "create");
        s = keyword(s, "progn");

        s = keyword(s, "lambda");
        s = keyword(s, "delegate");

        s = keyword(s, "locals");

        s = keyword(s, "debug");

        return s;
    }

    public static String expand(String s)
    {
        s = s.trim();

        s = s.replace("%", "&#37;");
        s = s.replace("\"", "%22");
        s = s.replace("\n", "%0A");
        s = s.replace("\\", "&#92");
        s = s.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");

        s = highlight(s);

        s = expandlinks(s);

        return s;
    }

    public static String expandlinks(String s)
    {
        /**
         * Types
         */
        s = s.replace("$AutumnLangDelegate$", link("Delegate", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Delegate.html"));
        s = s.replace("$AutumnLangFunctor$", link("Functor", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Functor.html"));
        s = s.replace("$AutumnLangTypedFunctor$", link("TypedFunctor", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/TypedFunctor.html"));
        s = s.replace("$AutumnLangDefinedFunctor$", link("DefinedFunctor", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/DefinedFunctor.html"));
        s = s.replace("$AutumnLangRecord$", link("Record", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html"));
        s = s.replace("$AutumnLangSpecialMethods$", link("SpecialMethods", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/SpecialMethods.html"));
        s = s.replace("$AutumnLangStruct$", link("Struct", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Struct.html"));
        s = s.replace("$AutumnLangTuple$", link("Tuple", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Tuple.html"));
        s = s.replace("$AutumnLangModule$", link("Module", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Module.html"));
        s = s.replace("$AutumnLangModuleInfo$", link("ModuleInfo", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/ModuleInfo.html"));

        s = s.replace("$AutumnLangAnnotationsInfer$", link("Infer", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Infer.html"));
        s = s.replace("$AutumnLangAnnotationsMemoize$", link("Memoize", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Memoize.html"));
        s = s.replace("$AutumnLangAnnotationsStart$", link("Start", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Start.html"));
        s = s.replace("$AutumnLangAnnotationsSetup$", link("Setup", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Setup.html"));
        s = s.replace("$AutumnLangAnnotationsSync$", link("Sync", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Sync.html"));
        s = s.replace("$AutumnLangAnnotationsTest$", link("Test", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Test.html"));

        s = s.replace("$AutumnLangExceptionsAssertionFailedException$", link("AssertionFailedException", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/AssertionFailedException.html"));
        s = s.replace("$AutumnLangExceptionsAssumptionFailedException$", link("AssumptionFailedException", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/AssumptionFailedException.html"));
        s = s.replace("$AutumnLangExceptionsCheckedException$", link("CheckedException", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/CheckedException.html"));
        s = s.replace("$AutumnLangExceptionsDispatchException$", link("DispatchException", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/DispatchException.html"));
        s = s.replace("$AutumnLangExceptionsUnexpectedTerminationException$", link("UnexpectedTerminationException", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/UnexpectedTerminationException.html"));

        s = s.replace("$AutumnLangInternalsArgumentStack$", link("ArgumentStack", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/ArgumentStack.html"));
        s = s.replace("$AutumnLangInternalsAbstractDefinedFunctor$", link("AbstractDefinedFunctor", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractDefinedFunctor.html"));
        s = s.replace("$AutumnLangInternalsAbstractModule$", link("AbstractModule", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractModule.html"));
        s = s.replace("$AutumnLangInternalsAbstractRecord$", link("AbstractRecord", "http://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html"));

        s = s.replace("$AutumnUtilTestTestCase$", link("TestCase", "http://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/TestCase.html"));

        s = s.replace("$JavaLangObject$", link("Object", "http://docs.oracle.com/javase/7/docs/api/java/lang/Object.html"));
        s = s.replace("$JavaLangBoolean$", link("Boolean", "http://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html"));
        s = s.replace("$JavaLangCharacter$", link("Character", "http://docs.oracle.com/javase/7/docs/api/java/lang/Character.html"));
        s = s.replace("$JavaLangByte$", link("Byte", "http://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html"));
        s = s.replace("$JavaLangShort$", link("Short", "http://docs.oracle.com/javase/7/docs/api/java/lang/Short.html"));
        s = s.replace("$JavaLangInteger$", link("Integer", "http://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html"));
        s = s.replace("$JavaLangLong$", link("Long", "http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html"));
        s = s.replace("$JavaLangFloat$", link("Float", "http://docs.oracle.com/javase/7/docs/api/java/lang/Float.html"));
        s = s.replace("$JavaLangDouble$", link("Double", "http://docs.oracle.com/javase/7/docs/api/java/lang/Double.html"));
        s = s.replace("$JavaLangClass$", link("Class", "http://docs.oracle.com/javase/7/docs/api/java/lang/Class.html"));
        s = s.replace("$JavaLangComparable$", link("Comparable", "http://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html"));
        s = s.replace("$JavaLangNumber$", link("Number", "http://docs.oracle.com/javase/7/docs/api/java/lang/Number.html"));
        s = s.replace("$JavaLangEnum$", link("Enum", "http://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html"));
        s = s.replace("$JavaLangIterable$", link("Iterable", "http://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html"));
        s = s.replace("$JavaLangString$", link("String", "http://docs.oracle.com/javase/7/docs/api/java/lang/String.html"));
        s = s.replace("$JavaLangThrowable$", link("Throwable", "http://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html"));
        s = s.replace("$JavaLangClassCastException$", link("ClassCastException", "http://docs.oracle.com/javase/7/docs/api/java/lang/ClassCastException.html"));
        s = s.replace("$JavaLangIllegalArgumentException$", link("IllegalArgumentException", "http://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html"));
        s = s.replace("$JavaLangNullPointerException$", link("NullPointerException", "http://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html"));

        s = s.replace("$JavaLangAnnotationAnnotation$", link("Annotation", "http://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html"));
        s = s.replace("$JavaLangAnnotationRetentionPolicy$", link("RetentionPolicy", "http://docs.oracle.com/javase/7/docs/api/java/lang/annotation/RetentionPolicy.html"));

        s = s.replace("$JavaMathBigInteger$", link("BigInteger", "http://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html"));
        s = s.replace("$JavaMathBigDecimal$", link("BigDecimal", "http://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html"));

        s = s.replace("$JavaUtilMap$", link("Map", "http://docs.oracle.com/javase/7/docs/api/java/util/Map.html"));
        s = s.replace("$JavaUtilList$", link("List", "http://docs.oracle.com/javase/7/docs/api/java/util/List.html"));
        s = s.replace("$JavaUtilLinkedList$", link("LinkedList", "http://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html"));

        s = s.replace("$JavaIoSerializable$", link("Serializable", "http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html"));

        /**
         * Flags
         */
        s = s.replace("$public$", link("public", "https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC"));
        s = s.replace("$private$", link("private", "https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PRIVATE"));
        s = s.replace("$static$", link("static", "https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#STATIC"));
        s = s.replace("$final$", link("final", "https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL"));
        s = s.replace("$abstract$", link("abstract", "https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#ABSTRACT"));
        s = s.replace("$synchronized$", link("synchronized", "https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#SYNCHRONIZED"));

        /**
         * Other Web Pages
         */
        s = s.replace("$big$", link("big(Object)", "http://www.mackenziehigh.me/autumn/FunctionPage.html?function=big%20(value%20:%20Object)%20:%20BigDecimal"));
        s = s.replace("$format$", link("format(String, Object[])", "http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#format(java.lang.String,%20java.lang.Object...)"));
        s = s.replace("$enable-assume$", link("enableAssume()", "http://www.mackenziehigh.me/autumn/javadoc/autumn/lang/compiler/Autumn.html#enableAssume()"));
        s = s.replace("$disable-assume$", link("disableAssume()", "http://www.mackenziehigh.me/autumn/javadoc/autumn/lang/compiler/Autumn.html#disableAssume()"));
        s = s.replace("$subtyping$", link("subtyping", "http://www.mackenziehigh.me/autumn/TextPage.html?page=Type%20Conversions%20and%20Assignability"));
        s = s.replace("$assignability$", link("assignability", "http://www.mackenziehigh.me/autumn/TextPage.html?page=Type%20Conversions%20and%20Assignability"));

        /**
         * Syntax Components
         */
        s = placeholder(s, "$module-member$", "Module Member");

        s = placeholder(s, "$expression$", "Expression");
        s = placeholder(s, "$condition$", "Expression");
        s = placeholder(s, "$left$", "Expression");
        s = placeholder(s, "$right$", "Expression");
        s = placeholder(s, "$value$", "Expression");
        s = placeholder(s, "$owner$", "Expression");
        s = placeholder(s, "$argument$", "Expression");
        s = placeholder(s, "$iterable$", "Expression");
        s = placeholder(s, "$initializer$", "Expression");
        s = placeholder(s, "$modifier$", "Expression");
        s = placeholder(s, "$transform$", "Expression");
        s = placeholder(s, "$index$", "Expression");
        s = placeholder(s, "$message$", "Expression");

        s = placeholder(s, "$statement$", "Statement");
        s = placeholder(s, "$body$", "Statement");
        s = placeholder(s, "$handler$", "Statement");

        s = placeholder2(s, "$namespace$", "Namespace");

        s = placeholder2(s, "$element$", "Element");

        s = placeholder2(s, "$variable$", "Variable");
        s = placeholder2(s, "$parameter$", "Variable");

        s = placeholder2(s, "$param$", "FormalParameter");

        s = placeholder2(s, "$label$", "Label");

        s = placeholder2(s, "$name$", "Name");
        s = placeholder2(s, "$constant$", "Name");

        s = placeholder2(s, "$Owner$", "TypeSpecifier");
        s = placeholder2(s, "$type$", "TypeSpecifier");
        s = placeholder2(s, "$return-type$", "TypeSpecifier");
        s = placeholder2(s, "$super$", "TypeSpecifier");

        s = placeholder2(s, "$assignee$", "Variable");

        /**
         * Language Constructs
         */
        s = placeholder2(s, "$Module$", "Module");
        s = placeholder2(s, "$Module Directive$", "Module Directive");
        s = placeholder2(s, "$Import Directive$", "Import Directive");
        s = placeholder2(s, "$Annotation Definition$", "Annotation Definition");
        s = placeholder2(s, "$Exception Definition$", "Exception Definition");
        s = placeholder2(s, "$Enum Definition$", "Enum Definition");
        s = placeholder2(s, "$Design Definition$", "Design Definition");
        s = placeholder2(s, "$Struct Definition$", "Struct Definition");
        s = placeholder2(s, "$Tuple Definition$", "Tuple Definition");
        s = placeholder2(s, "$Functor Definition$", "Functor Definition");
        s = placeholder2(s, "$Function Definition$", "Function Definition");
        s = placeholder2(s, "$Forever Statement$", "Forever Statement");
        s = placeholder2(s, "$While Statement$", "While Statement");
        s = placeholder2(s, "$Until Statement$", "Until Statement");
        s = placeholder2(s, "$Do-While Statement$", "Do-While Statement");
        s = placeholder2(s, "$Do-Until Statement$", "Do-Until Statement");
        s = placeholder2(s, "$For Statement$", "For Statement");
        s = placeholder2(s, "$Foreach Statement$", "Foreach Statement");
        s = placeholder2(s, "$Try-Catch Statement$", "Try-Catch Statement");
        s = placeholder2(s, "$Throw Statement$", "Throw Statement");
        s = placeholder2(s, "$Assert Statement$", "Assert Statement");
        s = placeholder2(s, "$Assume Statement$", "Assume Statement");
        s = placeholder2(s, "$Doc Comment$", "Doc Comment");
        s = placeholder2(s, "$Doc Comment Line$", "Doc Comment Line");

        /**
         * Pages
         */
        s = placeholder(s, "$Generator Functions$", "Generator Functions");
        s = placeholder(s, "$Infer Functions$", "Infer Functions");
        s = placeholder(s, "$Memoized Functions$", "Memoized Functions");
        s = placeholder(s, "$Setup Functions$", "Setup Functions");
        s = placeholder(s, "$Start Functions$", "Start Functions");
        s = placeholder(s, "$Sync Functions$", "Sync Functions");
        s = placeholder(s, "$Test Functions$", "Test Functions");

        s = placeholder(s, "$Constructor Resolution Algorithm$", "Resolution");
        s = placeholder(s, "$Instance Method Resolution Algorithm$", "Resolution");
        s = placeholder(s, "$Static Method Resolution Algorithm$", "Resolution");
        s = placeholder(s, "$Instance Field Resolution Algorithm$", "Resolution");
        s = placeholder(s, "$Static Field Resolution Algorithm$", "Resolution");

        /**
         * External Links
         */
        s = s.replace("$short-circuit$", link("short-circuit", "http://en.wikipedia.org/wiki/Short-circuit_evaluation"));

        return s;
    }

    public static String keyword(String s,
                                 final String keyword)
    {
        s = s.replace("$" + keyword + "$", "<span class=\\\"keyword\\\">" + keyword + "</span>");

        return s;
    }

    public static String link(final String text,
                              final String href)
    {
        return "<a href=\\\"" + href + "\\\">" + text + "</a>";
    }

    public static String link(final Class klass)
    {
        if (klass.isArray())
        {
            return link(klass.getComponentType()) + "[]";
        }
        else if (klass.isPrimitive())
        {
            return klass.getName();
        }
        else
        {
            final String href = pathToJavadoc(klass);

            final String link = "<a href='HREF'>NAME</a>".replace("HREF", href).replace("NAME", klass.getSimpleName());

            return link;
        }
    }

    public static String pathToJavadoc(final Class klass)
    {
        final String name = klass.getName().replace('.', '/');

        if (name.startsWith("java/"))
        {
            return "https://docs.oracle.com/javase/7/docs/api/" + name + ".html";
        }
        else if (name.startsWith("autumn/"))
        {
            return "http://mackenzie-high.github.io/autumn/javadoc/" + name + ".html";
        }
        else if (name.startsWith("high/mackenzie/autumn/"))
        {
            return "http://mackenzie-high.github.io/autumn/javadoc/" + name + ".html";
        }
        else
        {
            throw new IllegalArgumentException(name);
        }
    }

    public static String link(final Method method)
    {
        final List<String> formals = Lists.newLinkedList();

        final List<String> params = Lists.newLinkedList();

        for (Class formal : method.getParameterTypes())
        {
            formals.add(formal.getName());
            params.add(link(formal));
        }

        final String tail = ".html#"
                            + method.getName()
                            + F.str(formals, "(", ", ", ")");

        final String href = pathToJavadoc(method.getDeclaringClass()).replace(".html", tail);

        final String text = method.getName() + " " + F.str(params, "(", ", ", ")");

        final String link = "<a href='PATH'>TEXT</a>".replace("PATH", href).replace("TEXT", text);

        return link;
    }

    public static String placeholder(final String s,
                                     final String alias,
                                     final String page)
    {
        final String text = alias.replace("$", "");

        final String href = "TextPage.html?page=" + page;

        return s.replace(alias, link(text, href));
    }

    public static String placeholder2(final String s,
                                      final String alias,
                                      final String construct)
    {
        final String text = alias.replace("$", "");

        final String href = "ConstructPage.html?construct=" + construct;

        return s.replace(alias, link(text, href));
    }
}
