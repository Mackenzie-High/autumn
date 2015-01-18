package autumnspecification;

import autumn.lang.LocalsMap;
import autumn.lang.compiler.ast.nodes.*;
import autumn.lang.compiler.errors.ErrorCode;
import autumn.lang.internals.AbstractRecord;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.compilers.Importer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * This program is used to generate the web-site for the Autumn programming language.
 *
 * @author Mackenzie High
 */
public class Main
{
    private static final int EXAMPLE_1 = 1;

    private static final int EXAMPLE_2 = 2;

    private static final int EXAMPLE_3 = 3;

    private static final int EXAMPLE_4 = 4;

    private static final int EXAMPLE_5 = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
            throws FileNotFoundException,
                   IOException,
                   Exception
    {
        /**
         * Construct an object representation of the web-site.
         */
        build();

        /**
         * Convert the object representation to a single JSON file.
         */
        JSONBuilder.write();
    }

    private static void build()
            throws ClassNotFoundException
    {
        Construct c;
        Page p;

        Index.add("<b>GitHub Page</b>", "https://github.com/Mackenzie-High/autumn");

        p = Page.newInstance();
        p.name = "Downloads";
        p.markdown = new File("Download.md");
        Index.add(p);

        p = Page.newInstance();
        p.name = "Introduction";
        p.markdown = new File("Introduction.md");
        Index.add(p);

        p = Page.newInstance();
        p.name = "Special Thanks";
        p.markdown = new File("Thanks.md");
        Index.add(p);

        Index.add("<b>Standard Library</b>");
        ++Index.indent;
        Index.add("API Documentation", "javadoc/index.html");
        Index.add("Special Functions", "http://www.mackenziehigh.me/autumn/FunctionIndexPage.html");
        --Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Constructs
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Language Constructs</b>");
        ++Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Modules
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Module";
        c.klass = Module.class;
        c.summary = "A module creates a new module-type in the enclosing package.";
        c.addSyntax(0, "<i>$module-member$<sub>1</sub></i>");
        c.addSyntax(0, "<i>$module-member$<sub>2</sub></i>");
        c.addSyntax(0, "<i>$module-member$<sub>n</sub></i>");
        c.addDetail(0, "A module is a component of a compilation-unit that usually corresponds to a source file.");
        c.addDetail(0, "Regarding the module-type T of created by a definition M:");
        c.addDetail(1, "T is both $public$ and $final$.");
        c.addDetail(1, "T's fully-qualified name is obtained from the one and only module-directive in M.");
        c.addDetail(1, "T is a subtype of $AutumnLangModule$.");
        c.addDetail(1, "T is a subtype of $AutumnLangInternalsAbstractModule$.");
        c.addDetail(1, "T does not define any $public$ constructors.");
        c.addDetail(2, "This is because an instance of a module is a singleton object.");
        c.addDetail(1, "T defines a special method: instance() : T");
        c.addDetail(2, "This method is $public$ and $static$.");
        c.addDetail(2, "This method always returns the singleton instance of the module.");
        c.addDetail(1, "T defines a special method: moduleInfo () : $AutumnLangModuleInfo$");
        c.addDetail(2, "This method is $public$ and $final$.");
        c.addDetail(2, "This method returns a singleton object that describes the module.");
        c.addDetail(1, "T defines a special method: moduleInvokeFunction ($AutumnLangInternalsArgumentStack$, int) : $JavaLangObject$");
        c.addDetail(2, "This method is $public$ and $final$.");
        c.addDetail(3, "This method is a low-level method that is not intended for direct use by programmers.");
        c.addDetail(3, "This method is used to implement delegates.");
        c.addDetail(2, "For each function F in M, there is a #public# $static$ method in T.");
        specialAnnotation(c, autumn.lang.internals.annotations.ModuleDefinition.class);
        c.addCheck(ErrorCode.MISSING_MODULE_DIRECTIVE, "A module must contain a module-directive.");
        c.addCheck(ErrorCode.DUPLICATE_MODULE_DIRECTIVE, "A module can only contain one module-directive.");
        typedec(c);
        c.addCheck(ErrorCode.FUNCTION_IN_ANONYMOUS_MODULE, "An anonymous module cannot contain any functions.");
        c.addCheck(ErrorCode.DUPLICATE_FUNCTION, "No two functions in the same module can share their name and parameter-list descriptor.");
        c.addCheck(ErrorCode.NAME_CONFLICT, "The name of a user-defined function cannot also be the name of a predefined function.");
        c.addExample(EXAMPLE_1, 0);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Directives
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Directives</b>");
        ++Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Module Directive
        ////////////////////////////////////////////////////////////////////////////////////////////

        // Finished!
        c = Construct.newInstance();
        c.name = "Module Directive";
        c.klass = ModuleDirective.class;
        c.summary = "A module-directive is used to specify the name and namespace of the enclosing module.";
        annotationList(c);
        c.addSyntax(0, "$module$ <i>$name$</i> $in$ <i>$namespace$</i> ;");
        c.addSyntaxHR();
        annotationList(c);
        c.addSyntax(0, "$module$ * $in$ <i>$namespace$</i> ;");
        c.addDetail(0, "There are two forms of module-directive, as indicated syntactically above.");
        c.addDetail(1, "The first form indicates that the enclosing module is a named module.");
        c.addDetail(1, "The second form indicates that the enclosing module is an anonymous module.");
        c.addDetail(2, "An anonymous module is simply a module with a compiler generated name.");
        c.addDetail(0, "The annotations applied to the directive will be applied to the type of the enclosing module.");
        c.addExample(EXAMPLE_1, 108);
        c.addExample(EXAMPLE_2, 109);
        c.addExample(EXAMPLE_3, 110);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Import Directive
        ////////////////////////////////////////////////////////////////////////////////////////////

        // Finished!
        c = Construct.newInstance();
        c.name = "Import Directive";
        c.klass = ImportDirective.class;
        c.summary = "An import-directive simplifies access to a type within the enclosing module.";
        c.addSyntax(0, "$import$ <i>$type$</i> ;");
        c.addDetail(0, "After importation, the imported type is accessible using only its simple-name.");
        c.addDetail(0, "Import Directives are processed by the compiler linearly from the top of a module to the bottom.");
        c.addDetail(1, "If an import directive <i>X</i> occurs after an import directive <i>Y</i> and <i>X</i> and <i>Y</i> collide, then <i>X</i> overrides <i>Y</i>.");
        c.addDetail(2, "Two import-directives collide, if the simple-names of the types they import are the same.");
        c.addDetail(1, "If an import-directive <i>X</i> collides with a type <i>T</i> declared in the enclosing module, then <i>T</i> overrides <i>X</i>.");
        c.addDetail(2, "An import-directive <i>X</i> collides with a type <i>T</i>, if the simple-name of <i>X</i> is the same as that of <i>T</i>.");
        c.addDetail(0, "The type of the enclosing module is automatically imported twice.");
        c.addDetail(1, "First, the module's type is imported using its simple name.");
        c.addDetail(1, "Second, the module's type is imported using the name <i>My</i>.");
        predefinedImports(c);
        c.addExample(EXAMPLE_1, 111);
        c.addExample(EXAMPLE_2, 112);
        c.addExample(EXAMPLE_3, 113);
        c.addExample(EXAMPLE_4, 114);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        --Index.indent;
        Index.add("<b>Definitions</b>");
        ++Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Annotation Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        // Finished!
        c = Construct.newInstance();
        c.name = "Annotation Definition";
        c.klass = AnnotationDefinition.class;
        c.summary = "An annotation-definition creates a new annotation-type in the enclosing package.";
        annotationList(c);
        c.addSyntax(0, "$annotation$ <i>$name$</i> ;");
        typedec(c);
        c.addDetail(0, "Regarding the annotation-type T defined by a definition:");
        c.addDetail(1, "T is $public$.");
        c.addDetail(1, "T is a subtype of class $JavaLangObject$.");
        c.addDetail(1, "T is a subtype of interface $JavaLangAnnotationAnnotation$.");
        c.addDetail(1, "T has a $JavaLangAnnotationRetentionPolicy$ of RUNTIME.");
        c.addDetail(1, "T does not declare any fields.");
        c.addDetail(1, "T does not declare any constructors.");
        c.addDetail(1, "T declares a single method.");
        c.addDetail(2, "The name of the method is 'value'.");
        c.addDetail(2, "The method does not take any formal-parameters.");
        c.addDetail(2, "The return-type of the method is $JavaLangString$[].");
        c.addDetail(2, "The method is an annotation-method.");
        specialAnnotation(c, autumn.lang.internals.annotations.AnnotationDefinition.class);
        c.addExample(EXAMPLE_1, 52);
        c.addExample(EXAMPLE_2, 51);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Exception Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        // Finished!
        c = Construct.newInstance();
        c.name = "Exception Definition";
        c.klass = ExceptionDefinition.class;
        c.summary = "An exception-definition creates a new exception-type in the enclosing package.";
        annotationList(c);
        c.addSyntax(0, "$exception$ <i>$name$</i> $extends$ <i>$super$</i> ;");
        c.addDetail(0, "Regarding the exception-type T created by a definition:");
        c.addDetail(1, "T is a form of class-type.");
        c.addDetail(1, "T is $public$.");
        c.addDetail(1, "T must be a subtype of $JavaLangThrowable$.");
        c.addDetail(1, "T inherits all the $public$ constructors of its direct supertype.");
        c.addDetail(2, "Really, T declares equivalent constructors that simply invoke the super constructors.");
        c.addDetail(2, "Consequently, T is not instantiatable, if no constructors are actually inherited.");
        c.addDetail(1, "T does not have any direct superinterfaces.");
        c.addDetail(1, "T does not declare any fields or methods.");
        specialAnnotation(c, autumn.lang.internals.annotations.ExceptionDefinition.class);
        typedec(c);
        usetype(c, "super");
        c.addCheck(ErrorCode.EXPECTED_CLASS_TYPE, "The type of <i>super</i> must be a class-type.");
        c.addCheck(ErrorCode.EXPECTED_THROWABLE, "The <i>super</i> must be a subtype of $JavaLangThrowable$.");
        c.addCheck(ErrorCode.CIRCULAR_INHERITANCE, "The new type cannot be a subtype of itself either directly or indirectly.");
        c.addExample(EXAMPLE_1, 53);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Enum Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        // Finished!
        c = Construct.newInstance();
        c.name = "Enum Definition";
        c.klass = EnumDefinition.class;
        c.summary = "An enum-definition creates a new enum-type in the enclosing package.";
        annotationList(c);
        c.addSyntax(0, "$enum$ <i>$name$</i> ( <i>$constant$<sub>1</sub></i> , ... , <i>$constant$<sub>n</sub></i> ) ;");
        typedec(c);
        c.addDetail(0, "Regarding the enum-type T created by a definition:");
        c.addDetail(1, "T is both $public$ and $final$.");
        c.addDetail(1, "T is a subtype of class $JavaLangEnum$.");
        c.addDetail(1, "T does not have any direct superinterfaces.");
        c.addDetail(1, "For each enum-constant X in T, there is a field F in T such that:");
        c.addDetail(2, "The name of F is the name of X.");
        c.addDetail(2, "The type of F is T.");
        c.addDetail(2, "F is $public$, $static$, and $final$.");
        c.addDetail(2, "F is an enum-constant, according to reflection.");
        c.addDetail(1, "T declares two $public$ $static$ methods:");
        c.addDetail(2, "values() : T[]");
        c.addDetail(3, "This method creates an array of the enum-constants declared in T.");
        c.addDetail(3, "Each invocation of the method creates a new array instance.");
        c.addDetail(3, "The elements in the array are in declaration order.");
        c.addDetail(2, "valueOf(name : String) : T");
        c.addDetail(3, "This method retrieves an enum-constant based on its name.");
        c.addDetail(3, "This method throws a $JavaLangNullPointerException$, if name is null.");
        c.addDetail(3, "This method throws a $JavaLangIllegalArgumentException$, if the named enum-constant cannot be found.");
        specialAnnotation(c, autumn.lang.internals.annotations.EnumDefinition.class);
        c.addCheck(ErrorCode.DUPLICATE_CONSTANT, "Enum constants cannot share their name.");
        c.addExample(EXAMPLE_1, 54);
        c.addExample(EXAMPLE_2, 55);
        c.addExample(EXAMPLE_3, 56);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Design Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Design Definition";
        c.klass = DesignDefinition.class;
        c.summary = "A design-definition creates a new design-type in the enclosing package.";
        annotationList(c);
        c.addSyntax(0, "$design$ <i>$name$</i> ( <i>$element$<sub>1</sub></i> , ... , <i>$element$<sub>n</sub></i> ) ;");
        c.addSyntaxHR();
        annotationList(c);
        c.addSyntax(0, "$design$ <i>$name$</i> ( <i>$element$<sub>1</sub></i> , ... , <i>$element$<sub>n</sub></i> ) $extends$ <i>$super$<sub>1</sub></i> & ... & <i>$super$<sub>n</sub></i>;");
        c.addDetail(0, "A design is an abstract record-style user-defined datatype.");
        c.addDetail(0, "Regarding the design-type T created by a definition:");
        c.addDetail(1, "T is form of interface-type.");
        c.addDetail(1, "T is both $public$ and $abstract$.");
        c.addDetail(1, "T is a subtype of interface $AutumnLangRecord$.");
        c.addDetail(1, "T inherits all the elements that are declared in its supertypes.");
        c.addDetail(1, "T declares the following methods:");
        c.addDetail(2, "For each element E in an instance I of T:");
        c.addDetail(3, "T contains a setter method S for element E.");
        c.addDetail(4, "The name of S is the name of E.");
        c.addDetail(4, "S takes a single formal-parameter P.");
        c.addDetail(5, "The static-type of P is the static-type of E.");
        c.addDetail(4, "The return-type of S is T.");
        c.addDetail(3, "T contains a getter method G for element E.");
        c.addDetail(4, "The name of G is the name of E.");
        c.addDetail(4, "G does not take any formal-parameters.");
        c.addDetail(4, "The return-type of G is the static-type of element E.");
        c.addDetail(2, "T declares bridge methods for method set(int, $JavaLangObject$).");
        c.addDetail(3, "For X, where X is T or a supertype thereof, such that X is also a subtype of $AutumnLangRecord$:");
        c.addDetail(4, "set(int, $JavaLangObject$) : X is a bridge method in T.");
        specialAnnotation(c, autumn.lang.internals.annotations.DesignDefinition.class);
        c.addDetail(2, "T inherits the following method declarations from its supertypes.");
        inheritMethods(c, 3, AbstractRecord.class);
        typedec(c);
        c.addCheck(ErrorCode.DUPLICATE_ELEMENT, "The <i>name</i> of each element must be unique within the enclosing definition.");
        usetype(c, "element.type");
        c.addCheck(ErrorCode.EXPECTED_VARIABLE_TYPE, "The <i>type</i> of each <i>element</i> must be a variable-type.");
        c.addCheck(ErrorCode.RETYPED_ELEMENT, "The type of an element must be the same in all the declarations of the element.");
        c.addCheck(ErrorCode.NAME_CONFLICT, "The name of an element cannot be the name of a predefined method.");
        c.addExample(EXAMPLE_1, 123);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Struct Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Struct Definition";
        c.klass = StructDefinition.class;
        c.summary = "A struct-definition creates a new struct-type in the enclosing package.";
        annotationList(c);
        c.addSyntax(0, "$struct$ <i>$name$</i> ( <i>$element$<sub>1</sub></i> , ... , <i>$element$<sub>n</sub></i> ) ;");
        c.addSyntaxHR();
        annotationList(c);
        c.addSyntax(0, "$struct$ <i>$name$</i> ( <i>$element$<sub>1</sub></i> , ... , <i>$element$<sub>n</sub></i> ) $extends$ <i>$super$<sub>1</sub></i> & ... & <i>$super$<sub>n</sub></i>;");
        c.addDetail(0, "A struct is an immutable user-defined datatype.");
        c.addDetail(0, "Regarding the struct-type T created by a definition:");
        c.addDetail(1, "T is form of class-type.");
        c.addDetail(1, "T is both $public$ and $final$.");
        c.addDetail(1, "T is a subtype of interface $AutumnLangRecord$.");
        c.addDetail(1, "T is a subtype of class $AutumnLangInternalsAbstractRecord$.");
        c.addDetail(1, "T inherits all the elements that are declared in its supertypes.");
        c.addDetail(2, "The order of the elements in T is lexicographical.");
        c.addDetail(3, "On the other hand, the order of the elements in a tuple is user-defined.");
        c.addDetail(3, "As a consequence, T can implicitly inherit elements.");
        c.addDetail(1, "T declares the following methods and constructors:");
        c.addDetail(2, "Let C denote the sole constructor declared by T:");
        c.addDetail(3, "C creates an immutable instance of T.");
        c.addDetail(3, "C takes one formal-parameter P for each element E.");
        c.addDetail(4, "P's static-type is the static-type of the element E.");
        c.addDetail(4, "C will assign the value of P to element E in the new instance.");
        c.addDetail(3, "The order of C's formal-parameters is the same as the elements in the list of elements.");
        c.addDetail(4, "This is a consequence of the aforedescribed user-defined total-ordering of the elements.");
        c.addDetail(2, "For each element E in an instance I of T:");
        c.addDetail(3, "T contains a setter method S for element E.");
        c.addDetail(4, "The name of S is the name of E.");
        c.addDetail(4, "S takes a single formal-parameter P.");
        c.addDetail(5, "The static-type of P is the static-type of E.");
        c.addDetail(4, "S obtains a copy M of I.");
        c.addDetail(4, "S assigns the value of P to element E in instance M.");
        c.addDetail(4, "The return-type of S is T.");
        c.addDetail(4, "S returns M.");
        c.addDetail(3, "T contains a getter method G for element E.");
        c.addDetail(4, "The name of G is the name of E.");
        c.addDetail(4, "G does not take any formal-parameters.");
        c.addDetail(4, "The return-type of G is the static-type of element E.");
        c.addDetail(4, "G returns the value stored in element E in instance I.");
        c.addDetail(2, "T provides bridge methods for method set(int, $JavaLangObject$).");
        c.addDetail(3, "For X, where X is T or a supertype thereof, such that X is also a subtype of $AutumnLangRecord$:");
        c.addDetail(4, "set(int, $JavaLangObject$) : X is a bridge method in T.");
        c.addDetail(4, "set(int, $JavaLangObject$) : $AutumnLangRecord$ is invoked by the bridge method.");
        c.addDetail(4, "Although the bridge method must downcast the return-value, the cast always succeeds.");
        c.addDetail(2, "T provides a special static method instance() : T.");
        c.addDetail(3, "The method returns an instance of T in which each element is set to is default value.");
        c.addDetail(3, "The method always returns the same object.");
        specialAnnotation(c, autumn.lang.internals.annotations.StructDefinition.class);
        c.addDetail(2, "T inherits the following method declarations from its supertypes.");
        inheritMethods(c, 3, AbstractRecord.class);
        typedec(c);
        c.addCheck(ErrorCode.DUPLICATE_ELEMENT, "The <i>name</i> of each element must be unique within the enclosing definition.");
        usetype(c, "element.type");
        c.addCheck(ErrorCode.EXPECTED_VARIABLE_TYPE, "The <i>type</i> of each <i>element</i> must be a variable-type.");
        c.addCheck(ErrorCode.RETYPED_ELEMENT, "The type of an element must be the same in all the declarations of the element.");
        c.addCheck(ErrorCode.NAME_CONFLICT, "The name of an element cannot be the name of a predefined method.");
        c.addExample(EXAMPLE_1, 120);
        c.addExample(EXAMPLE_2, 121);
        c.addExample(EXAMPLE_3, 122);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Tuple Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Tuple Definition";
        c.klass = TupleDefinition.class;
        c.summary = "A tuple-definition creates a new tuple-type in the enclosing package.";
        annotationList(c);
        c.addSyntax(0, "$tuple$ <i>$name$</i> ( <i>$element$<sub>1</sub></i> , ... , <i>$element$<sub>n</sub></i> ) ;");
        c.addSyntaxHR();
        annotationList(c);
        c.addSyntax(0, "$tuple$ <i>$name$</i> ( <i>$element$<sub>1</sub></i> , ... , <i>$element$<sub>n</sub></i> ) $extends$ <i>$super$<sub>1</sub></i> & ... & <i>$super$<sub>n</sub></i> ;");
        c.addDetail(0, "A tuple is an immutable user-defined datatype.");
        c.addDetail(0, "Regarding the tuple-type T created by a definition:");
        c.addDetail(1, "T is form of class-type.");
        c.addDetail(1, "T is both $public$ and $final$.");
        c.addDetail(1, "T is a subtype of interface $AutumnLangRecord$.");
        c.addDetail(1, "T is a subtype of class $AutumnLangInternalsAbstractRecord$.");
        c.addDetail(1, "T inherits all the elements that are declared in its supertypes.");
        c.addDetail(2, "Because T is a tuple-type, there must exist an explicit declaration <i>element<sub>i</sub></i> in T for every element.");
        c.addDetail(3, "As a consequence, a tuple imposes a user-defined total-ordering on its elements.");
        c.addDetail(4, "The order of the elements in a tuple is the same as in the list of elements.");
        c.addDetail(4, "On the other hand, the order of the elements in a struct is lexicographical.");
        c.addDetail(1, "T declares the following methods and constructors:");
        c.addDetail(2, "Let C denote the sole constructor declared by T:");
        c.addDetail(3, "C creates an immutable instance of T.");
        c.addDetail(3, "C takes one formal-parameter P for each element E.");
        c.addDetail(4, "P's static-type is the static-type of the element E.");
        c.addDetail(4, "C will assign the value of P to element E in the new instance.");
        c.addDetail(3, "The order of C's formal-parameters is the same as the elements in the list of elements.");
        c.addDetail(4, "This is a consequence of the aforedescribed user-defined total-ordering of the elements.");
        c.addDetail(2, "For each element E in an instance I of T:");
        c.addDetail(3, "T contains a setter method S for element E.");
        c.addDetail(4, "The name of S is the name of E.");
        c.addDetail(4, "S takes a single formal-parameter P.");
        c.addDetail(5, "The static-type of P is the static-type of E.");
        c.addDetail(4, "S obtains a copy M of I.");
        c.addDetail(4, "S assigns the value of P to element E in instance M.");
        c.addDetail(4, "The return-type of S is T.");
        c.addDetail(4, "S returns M.");
        c.addDetail(3, "T contains a getter method G for element E.");
        c.addDetail(4, "The name of G is the name of E.");
        c.addDetail(4, "G does not take any formal-parameters.");
        c.addDetail(4, "The return-type of G is the static-type of element E.");
        c.addDetail(4, "G returns the value stored in element E in instance I.");
        c.addDetail(2, "T provides bridge methods for method set(int, $JavaLangObject$).");
        c.addDetail(3, "For X, where X is T or a supertype thereof, such that X is also a subtype of $AutumnLangRecord$:");
        c.addDetail(4, "set(int, $JavaLangObject$) : X is a bridge method in T.");
        c.addDetail(4, "set(int, $JavaLangObject$) : $AutumnLangRecord$ is invoked by the bridge method.");
        c.addDetail(4, "Although the bridge method must downcast the return-value, the cast always succeeds.");
        c.addDetail(2, "T provides a special static method instance() : T.");
        c.addDetail(3, "The method returns an instance of T in which each element is set to is default value.");
        c.addDetail(3, "The method always returns the same object.");
        specialAnnotation(c, autumn.lang.internals.annotations.TupleDefinition.class);
        c.addDetail(2, "T inherits the following method declarations from its supertypes.");
        inheritMethods(c, 3, AbstractRecord.class);
        typedec(c);
        c.addCheck(ErrorCode.DUPLICATE_ELEMENT, "The <i>name</i> of each element must be unique within the enclosing definition.");
        usetype(c, "element.type");
        c.addCheck(ErrorCode.EXPECTED_VARIABLE_TYPE, "The <i>type</i> of each <i>element</i> must be a variable-type.");
        c.addCheck(ErrorCode.RETYPED_ELEMENT, "The type of an element must be the same in all the declarations of the element.");
        c.addCheck(ErrorCode.TOTAL_ORDERING_REQUIRED, "A tuple must explicitly declare all of its elements, including inherited elements. ");
        c.addCheck(ErrorCode.NAME_CONFLICT, "The name of an element cannot be the name of a predefined method.");
        c.addExample(EXAMPLE_1, 117);
        c.addExample(EXAMPLE_2, 118);
        c.addExample(EXAMPLE_3, 119);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Functor Definitions
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Functor Definition";
        c.klass = FunctorDefinition.class;
        c.summary = "A functor-definition creates a new functor-type in the enclosing package.";
        annotationList(c);
        c.addSyntax(0, "$functor$ <i>$name$</i> ( <i>$param$<sub>1</sub></i> , ... , <i>$param$<sub>n</sub></i> ) : <i>$return-type$</i> ;");
        c.addSyntaxHR();
        annotationList(c);
        c.addSyntax(0, "$functor$ <i>$name$</i> ( <i>$param$<sub>1</sub></i> , ... , <i>$param$<sub>n</sub></i> ) : <i>$return-type$</i> $extends$ </i>$super$</i> ;");
        c.addDetail(0, "Regarding the type T created by a functor-definition:");
        c.addDetail(1, "T is $public$.");
        c.addDetail(1, "T is a subtype of interface $AutumnLangDefinedFunctor$");
        c.addDetail(1, "T is a subtype of interface $AutumnLangTypedFunctor$");
        c.addDetail(1, "T is a subtype of interface $AutumnLangFunctor$");
        c.addDetail(1, "T is a subtype of class $AutumnLangInternalsAbstractDefinedFunctor$");
        c.addDetail(1, "T is a subtype of the functor-type specified by <i>super</i>, if <i>super</i> is present.");
        c.addDetail(1, "T defines the following methods and constructors:");
        c.addDetail(2, "constructor: T ($AutumnLangTypedFunctor$)");
        c.addDetail(3, "parameter - inner : $AutumnLangTypedFunctor$");
        c.addDetail(3, "This constructor creates a new instance of T that wraps another functor.");
        c.addDetail(2, "method: invoke (<i>parameter-types</i>) : <i>return-type</i>");
        c.addDetail(3, "This method provides a type-safe mechanism for invoking the functor.");
        c.addDetail(3, "This is the method that programmers will use most frequently on the functor.");
        c.addDetail(2, "method: parameterTypes () : $JavaUtilList$&lt;$JavaLangClass$&gt;");
        c.addDetail(3, "This method creates a list containing the <i>parameter-types</i> as provided in the declaration.");
        c.addDetail(2, "method: returnType () : $JavaLangClass$");
        c.addDetail(3, "This method retrieves the <i>return-type</i> that was provided in the declaration.");
        c.addDetail(2, "method: inner () : $AutumnLangTypedFunctor$");
        c.addDetail(3, "This method retrieves the functor that the T functor wraps");
        c.addDetail(2, "method: apply ($AutumnLangInternalsArgumentStack$) : void");
        c.addDetail(3, "This method is the low-level method that handles invocations of the functor.");
        c.addDetail(3, "T is covariant in terms of a supertype S, iff:");
        c.addDetail(4, "Ǝ <i>i</i> such that T.formals<sub>i</sub> is a subtype of S.formals<sub>i</sub>");
        c.addDetail(4, "T.return-type is a subtype of S.return-type");
        c.addDetail(3, "Subtyping Requirements:");
        c.addDetail(4, "Let S be any of the super functor-types of T.");
        c.addDetail(4, "T.formals.length must equal S.formals.length");
        c.addDetail(4, "T.formals<sub>i</sub> must be a subtype of S.formals<sub>i</sub> ∀ <i>i</i>");
        c.addDetail(4, "T.return-type must be a subtype of S.return-type");
        specialAnnotation(c, autumn.lang.internals.annotations.FunctorDefinition.class);
        typedec(c);
        usetype(c, "super");
        usetype(c, "param<sub>i</sub>");
        usetype(c, "return-type");
        c.addCheck(ErrorCode.EXPECTED_CLASS_TYPE, "The type of <i>super</i> must be a class-type.");
        c.addCheck(ErrorCode.EXPECTED_DEFINED_FUNCTOR_TYPE, "The type of <i>super</i> must be a subtype of $AutumnLangFunctor$.");
        c.addCheck(ErrorCode.CIRCULAR_INHERITANCE, "The new type cannot be a subtype of itself either directly or indirectly.");
        c.addCheck(ErrorCode.COVARIANCE_VIOLATION, "The type of <i>return-type</i> must be a subtype of the super-type's return-type.");
        c.addExample(EXAMPLE_1, 91);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Functions
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Function Definition";
        c.klass = FunctionDefinition.class;
        c.summary = "An function-definition creates a new function within the enclosing module.";
        annotationList(c);
        c.addSyntax(0, "$defun$ <i>$name$</i> ( <i>$param$<sub>1</sub></i> , ... , <i>$param$<sub>n</sub></i> ) : <i>$return-type$</i>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>body</i>");
        c.addSyntax(0, "}");
        c.addDetail(0, "Special Topics:");
        c.addDetail(1, "$Infer Functions$");
        c.addDetail(1, "$Start Functions$");
        c.addDetail(1, "$Setup Functions$");
        c.addDetail(1, "$Sync Functions$");
        c.addDetail(1, "$Test Functions$");
        c.addDetail(0, "Let T denote the type system representation of a function F.");
        c.addDetail(1, "T is the type of a $public$ $static$ method.");
        c.addDetail(1, "T is $synchronized$, if F is a sync-function.");
        c.addDetail(1, "T's throws clause implicitly includes $JavaLangThrowable$.");
        c.addDetail(1, "T is a member of the enclosing module-type.");
        specialAnnotation(c, autumn.lang.internals.annotations.FunctionDefinition.class);
        c.addDetail(0, "Scopes:");
        c.addDetail(1, "A function creates a new scope for variables.");
        c.addDetail(1, "A function creates a new scope for labels.");
        c.addDetail(0, "Runtime Checks:");
        c.addDetail(1, "A $AutumnLangExceptionsUnexpectedTerminationException$ is thrown automatically, if execution reaches the end of a function.");
        c.addCheck(ErrorCode.TOO_MANY_STARTS, "A compilation-unit can only contain one @Start function.");
        c.addCheck(ErrorCode.WRONG_SIGNATURE_FOR_START, "A start-function must have a signature of: main(String[]) : void");
        c.addCheck(ErrorCode.WRONG_SIGNATURE_FOR_SETUP, "A setup-function must have a signature of: () : void");
        c.addCheck(ErrorCode.WRONG_SIGNATURE_FOR_TEST, "A test-function must have a signature of: ($AutumnUtilTestTestCase$) : void");
        c.addCheck(ErrorCode.WRONG_SIGNATURE_FOR_INFER, "An infer-function must have a signature of: (<i>T</i>, ...) : <i>T</i>, where <i>T</i> is some reference-type.");
        usetype(c, "<i>param<sub>i</sub></i>.type");
        c.addCheck(ErrorCode.EXPECTED_VARIABLE_TYPE, "The type of each parameter must be a variable-type.");
        usetype(c, "<i>return-type</i>");
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Statements
        ////////////////////////////////////////////////////////////////////////////////////////////

        --Index.indent;
        Index.add("<b>Statements</b>");
        ++Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Sequence Statement
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Flow Control</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Sequence Statement";
        c.klass = SequenceStatement.class;
        c.summary = "A sequence-statement executes a series of statements one after another.";
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$statement$<sub>1</sub></i>");
        c.addSyntax(1, "    <i>$statement$<sub>2</sub></i>");
        c.addSyntax(1, "    <i>$statement$<sub>n</sub></i>");
        c.addSyntax(0, "}");
        c.addDetail(0, "There can be zero or more statements in the sequence.");
        c.addExample(EXAMPLE_1, 43);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Flow Control
        ////////////////////////////////////////////////////////////////////////////////////////////

        // Finished!
        c = Construct.newInstance();
        c.name = "If-Then Statement";
        c.klass = IfStatement.class;
        c.summary = "An if-statement conditionally executes one or more other statements.";
        c.addSyntax(0, "$if$ ( <i>$condition$<sub>1</sub></i> )");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>body<sub>1</sub></i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$elif$ ( <i>$condition$<sub>2</sub></i> )<sub>1</sub>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>body<sub>2</sub></i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$elif$ ( <i>$condition$<sub>3</sub></i> )<sub>2</sub>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>body<sub>3</sub></i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$elif$ ( <i>$condition$<sub>4</sub></i> )<sub>n</sub>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>body<sub>4</sub></i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$else$<sub>opt</sub>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>body<sub>5</sub></i>");
        c.addSyntax(0, "}");
        c.addDetail(0, "An if-statement can have zero or more elif-clauses.");
        c.addDetail(0, "An if-statement can omit the else-clause.");
        c.addDetail(0, "For all <i>i</i> &gt; 1, <i>condition<sub>i</sub></i> will be evaluated, only if <i>condition<sub>i - 1</sub></i> produces false.");
        c.addDetail(0, "For all <i>i</i> &gt; 0, <i>body<sub>i</sub></i> will only be executed, only if <i>condition<sub>i</sub></i> produces true.");
        c.addDetail(0, "The body of the else-clause will be executed, only if all of the conditions produce false.");
        c.addDetail(0, "The conditions will be unboxed, if necessary.");
        conditions(c);
        c.addExample(EXAMPLE_1, 1);
        c.addExample(EXAMPLE_2, 44);
        c.addExample(EXAMPLE_3, 45);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "When Statement";
        c.klass = WhenStatement.class;
        c.summary = "A when-statement makes the execution of another statement conditional.";
        c.addSyntax(0, "$when$ ( <i>$condition$</i> ) $then$ <i>$statement$</i>");
        c.addDetail(0, "The condition will be unboxed, if necessary.");
        c.addDetail(0, "The statement will be executed, only if the <i>condition</i> produces true.");
        condition(c, 19);
        c.addExample(EXAMPLE_1, 46);
        c.addExample(EXAMPLE_2, 47);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Goto Statement";
        c.klass = GotoStatement.class;
        c.summary = "A goto-statement causes execution to immediately jump to a labeled location.";
        c.addSyntax(0, "$goto$ <i>$label$</i> ;");
        c.addDetail(0, "A goto-statement cannot be used to jump out of a function.");
        c.addCheck(ErrorCode.NO_SUCH_LABEL, "The <i>label</i> must be declared somewhere in the enclosing function.");
        c.addExample(EXAMPLE_1, 50);
        c.addExample(EXAMPLE_2, 49);
        c.addExample(EXAMPLE_3, 48);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Marker Statement";
        c.klass = MarkerStatement.class;
        c.summary = "A marker-statement declares a label in the enclosing function.";
        c.addSyntax(0, "$marker$ <i>$label$</i> ;");
        c.addDetail(0, "The <i>label</i> will be visible everywhere in the enclosing function.");
        c.addDetail(0, "Labels and variables are in distinct namespaces.");
        c.addDetail(1, "In other words, a variable can have the same name as a label.");
        c.addCheck(ErrorCode.DUPLICATE_LABEL, "No two labels in the same function can share a name.");
        c.addExample(EXAMPLE_1, 50);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Branch Statement";
        c.klass = BranchStatement.class;
        c.summary = "A branch-statement creates an unstructured jump-table.";
        c.addSyntax(0, "$branch$ ( $index$ ) ( <i>$label$<sub>0</sub></i> , ... , <i>$label$<sub>n - 1</sub></i> ) $default$ <i>$label$<sub>n</sub></i>;");
        c.addDetail(0, "A branch-statement is a low-level operation that should usually be avoided by programmers.");
        c.addDetail(0, "A branch-statement cannot be used to jump out of a function.");
        c.addDetail(0, "The <i>index</i> will be unboxed, if necessary.");
        c.addDetail(0, "The <i>index</i> will be coerced, if necessary.");
        c.addDetail(0, "Algorithm:");
        c.addDetail(1, "Let <i>X</i> be the result of evaluating the <i>index</i>.");
        c.addDetail(1, "Let <i>N</i> be the number of labels in the branch-statement, including the default label, minus one.");
        c.addDetail(1, "Unbox <i>X</i>, if necessary.");
        c.addDetail(1, "Coerce <i>X</i>, if necessary.");
        c.addDetail(1, "If <i>X</i> &lt; 0, then jump to the location denoted by <i>$label$<sub>N</sub></i>, which is the default label.");
        c.addDetail(1, "If <i>X</i> &gt= <i>N</i>, then jump to the location denoted by <i>$label$<sub>N</sub></i>, which is the default label.");
        c.addDetail(1, "Otherwise, jump to the location denoted by <i>$label$<sub>X</sub></i>.");
        expectType(c, ErrorCode.EXPECTED_INTEGER, "index", int.class);
        c.addCheck(ErrorCode.NO_SUCH_LABEL, "Each <i>label<sub>i</sub></i> must be declared somewhere in the enclosing function.");
        c.addExample(EXAMPLE_1, 107);
        Index.add(c);

        Index.add("<b>Looping</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "While Statement";
        c.klass = WhileStatement.class;
        c.summary = "A while-statement is a loop that iterates while a condition holds true.";
        c.addSyntax(0, "$while$ ( <i>$condition$</i> )");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        condition(c);
        loop(c);
        c.addExample(EXAMPLE_1, 2);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Until Statement";
        c.klass = UntilStatement.class;
        c.summary = "An until-statement is a loop that iterates until a condition becomes true.";
        c.addSyntax(0, "$until$ ( <i>$condition$</i> )");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        condition(c);
        loop(c);
        c.addExample(EXAMPLE_1, 3);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Do-While Statement";
        c.klass = DoWhileStatement.class;
        c.summary = "A do-while-statement is a loop that iterates while a postcondition holds true.";
        c.addSyntax(0, "$do$");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$while$ ( <i>$condition$</i> )");
        c.addDetail(0, "The <i>body</i> will be executed at least once.");
        condition(c);
        loop(c);
        c.addExample(EXAMPLE_1, 4);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Do-Until Statement";
        c.klass = DoUntilStatement.class;
        c.summary = "A do-until-statement is a loop that iterates until a postcondition becomes true.";
        c.addSyntax(0, "$do$");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$until$ ( <i>$condition$</i> )");
        c.addDetail(0, "The <i>body</i> will be executed at least once.");
        condition(c);
        loop(c);
        c.addExample(EXAMPLE_1, 5);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Forever Statement";
        c.klass = ForeverStatement.class;
        c.summary = "A forever-statement is an infinite loop.";
        c.addSyntax(0, "$forever$");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        loop(c);
        c.addExample(EXAMPLE_1, 57);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "For Statement";
        c.klass = ForStatement.class;
        c.summary = "A for-statement is a loop that iterates based on a control variable and a condition.";
        c.addSyntax(0, "$for$ ( <i>$assignee$</i> = <i>$initializer$</i> ; <i>$condition$</i> ; <i>$modifier$</i> )");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        variables(c, "assignee");
        c.addDetail(0, "The type of the <i>assignee</i> variable will be primitive-type int.");
        c.addDetail(0, "The <i>assignee</i> is a readonly variable.");
        expectType(c, ErrorCode.EXPECTED_INTEGER, "initializer", int.class);
        unbox(c, "initializer");
        condition(c);
        expectType(c, ErrorCode.EXPECTED_INTEGER, "modifier", int.class);
        unbox(c, "modifier");
        loop(c);
        c.addExample(EXAMPLE_1, 6);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Foreach Statement";
        c.klass = ForeachStatement.class;
        c.summary = "A foreach-statement is a loop that iterates over the elements in an iterable entity, such as a data-structure.";
        c.addSyntax(0, "$foreach$ ( <i>$assignee$</i> : <i>$type$</i> $in$ <i>$iterable$</i> )");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        variables(c, "assignee");
        c.addDetail(0, "The <i>assignee</i> is a readonly variable.");
        usetype(c, "<i>type</i>");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type specified by the <i>type</i> must be a reference-type.");
        loop(c);
        c.addDetail(0, "A $JavaLangClassCastException$ will result at runtime, if the value returned by the iterator cannot be cast to the specified <i>type</i>.");
        c.addCheck(ErrorCode.EXPECTED_ITERABLE, "The type of the <i>iterable</i> must be a subtype of $JavaLangIterable$.");
        c.addExample(EXAMPLE_1, 7);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Break Statement";
        c.klass = BreakStatement.class;
        c.summary = "A break-statement causes execution to immediately exit the nearest enclosing loop.";
        c.addSyntax(0, "$break$ ;");
        c.addDetail(0, "A break-statement cannot be used to exit an invocation.");
        typesOfLoops(c);
        c.addCheck(ErrorCode.BREAK_OUTSIDE_OF_LOOP, "A break-statement must be in the <i>body</i> of a loop.");
        c.addExample(EXAMPLE_1, 8);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Continue Statement";
        c.klass = ContinueStatement.class;
        c.summary = "A continue-statement causes execution to immediately enter the next iteration of the nearest enclosing loop.";
        c.addSyntax(0, "$continue$ ;");
        c.addDetail(0, "A continue-statement cannot be used to exit an invocation.");
        typesOfLoops(c);
        c.addCheck(ErrorCode.CONTINUE_OUTSIDE_OF_LOOP, "A continue-statement must be in the <i>body</i> of a loop.");
        c.addExample(EXAMPLE_1, 58);
        c.addExample(EXAMPLE_2, 9);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Redo Statement";
        c.klass = RedoStatement.class;
        c.summary = "A redo-statement causes execution to immediately restart the current iteration of the nearest enclosing loop.";
        c.addSyntax(0, "$redo$ ;");
        c.addDetail(0, "A redo-statement cannot be used to exit an invocation.");
        typesOfLoops(c);
        c.addCheck(ErrorCode.REDO_OUTSIDE_OF_LOOP, "A redo-statement must be in the <i>body</i> of a loop.");
        c.addExample(EXAMPLE_1, 59);
        Index.add(c);

        --Index.indent; // Loops
        --Index.indent; // Flow Control

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variable Related
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Variable Related</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Var Statement";
        c.klass = VarStatement.class;
        c.summary = "A var-statement declares a new mutable local variable.";
        c.addSyntax(0, "$var$ <i>$assignee$</i> = <i>$value$</i> ;");
        variables(c, "assignee");
        c.addDetail(0, "The type of the newly declared variable is the type of the <i>value</i>.");
        c.addDetail(1, "In other words, the type of the variable is inferred.");
        c.addCheck(ErrorCode.EXPECTED_VARIABLE_TYPE, "The type of the <i>value</i> must be a variable-type.");
        c.addExample(EXAMPLE_1, 10);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Val Statement";
        c.klass = ValStatement.class;
        c.summary = "A val-statement declares a new readonly local variable.";
        c.addSyntax(0, "$val$ <i>$assignee$</i> = <i>$value$</i> ;");
        c.addDetail(0, "The value stored in the variable will be reassigned, if the val-statement is executed again.");
        variables(c, "assignee");
        c.addDetail(0, "The type of the newly declared variable is the type of the <i>value</i>.");
        c.addDetail(1, "In other words, the type of the variable is inferred.");
        c.addCheck(ErrorCode.EXPECTED_VARIABLE_TYPE, "The type of the <i>value</i> must be a variable-type.");
        c.addExample(EXAMPLE_1, 94);
        c.addExample(EXAMPLE_2, 95);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Let Statement";
        c.klass = LetStatement.class;
        c.summary = "A let-statement assigns a value to a local variable.";
        c.addSyntax(0, "$let$ <i>$assignee$</i> = <i>$value$</i> ;");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>$assignee$</i> = <i>$value$</i> ;");
        c.addDetail(0, "The <i>value</i> will be boxed, if necessary.");
        c.addDetail(0, "The <i>value</i> will be unboxed, if necessary.");
        c.addDetail(0, "The <i>value</i> will be coerced, if necessary.");
        c.addCheck(ErrorCode.NO_SUCH_VARIABLE, "The <i>assignee</i> must be declared somewhere in the enclosing function.");
        c.addCheck(ErrorCode.MUTABLE_VARIABLE_REQUIRED, "The <i>assignee</i> must be a mutable variable.");
        c.addCheck(ErrorCode.IMPOSSIBLE_ASSIGNMENT, "The type of the <i>value</i> must be assignable to the type of the <i>assignee</i>.");
        c.addExample(EXAMPLE_1, 11);
        Index.add(c);

        --Index.indent; // Variables

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Exception Handling
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Exception Handling</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Throw Statement";
        c.klass = ThrowStatement.class;
        c.summary = "A throw-statement throws an exception that is provided as an argument thereto.";
        c.addSyntax(0, "$throw$ <i>$argument$</i> ;");
        c.addDetail(0, "In order to catch an exception, use a $Try-Catch Statement$.");
        c.addDetail(0, "An uncaught exception will cause the enclosing invocation to terminate.");
        c.addDetail(1, "An uncaught exception will propogate until it is caught.");
        c.addDetail(1, "If an exception is caught by the runtime, then the program will be terminated.");
        c.addDetail(0, "Runtime Check: A $JavaLangNullPointerException$ will be thrown, if the <i>argument</i> is null.");
        expectType(c, ErrorCode.EXPECTED_THROWABLE, "argument", Throwable.class);
        c.addExample(EXAMPLE_1, 96);
        c.addExample(EXAMPLE_2, 97);
        c.addExample(EXAMPLE_3, 98);
        c.addExample(EXAMPLE_4, 99);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Try-Catch Statement";
        c.klass = TryCatchStatement.class;
        c.summary = "A try-statement provides a mechanism for catching exceptions.";
        c.addSyntax(0, "$try$");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$body$</i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$catch$ ( <i>$variable$<sub>1</sub></i> : <i>$type$<sub>1</sub></i> )<sub>1</sub>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$handler$<sub>1</sub></i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$catch$ ( <i>$variable$<sub>2</sub></i> : <i>$type$<sub>2</sub></i> )<sub>2</sub>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$handler$<sub>2</sub></i>");
        c.addSyntax(0, "}");
        c.addSyntax(0, "$catch$ ( <i>$variable$<sub>n</sub></i> : <i>$type$<sub>n</sub></i> )<sub>n</sub>");
        c.addSyntax(0, "{");
        c.addSyntax(1, "    <i>$handler$<sub>n</sub></i>");
        c.addSyntax(0, "}");
        c.addDetail(0, "Exceptions can be thrown using a $Throw Statement$, $Assert Statement$, etc.");
        c.addDetail(0, "At least one handler is syntactically required.");
        c.addDetail(0, "An exception will only be caught when an applicable handler is available.");
        c.addDetail(1, "A handler is applicable when the exception's type is a subtype of the handler's <i>type</i>.");
        variables(c, "<i>variable<sub>i</sub></i>");
        c.addDetail(0, "The compiler will automatically reorder the handlers based on their specificity.");
        c.addDetail(1, "Reordering ensures that the handler that best matches an exception will be used to handle it.");
        c.addDetail(1, "A handler <i>X</i> is more specific than a handler <i>Y</i>, if <i>type<sub>X</sub></i> is a subclass of <i>type<sub>Y</sub></i>.");
        usetype(c, "<i>type<sub>i</sub></i>");
        c.addCheck(ErrorCode.EXPECTED_THROWABLE, "Each <i>type<sub>i</sub></i> must be a subtype of $JavaLangThrowable$.");
        c.addCheck(ErrorCode.DUPLICATE_EXCEPTION_HANDLER, "No two handlers can catch exactly the same type.");
        c.addExample(EXAMPLE_1, 100);
        c.addExample(EXAMPLE_2, 101);
        c.addExample(EXAMPLE_3, 102);
        Index.add(c);

        Index.add("<b>Assertions</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Assert Statement";
        c.klass = AssertStatement.class;
        c.summary = "An assert-statement enforces an invariant.";
        c.addSyntax(0, "$assert$ <i>$condition$</i> ;");
        c.addSyntaxHR();
        c.addSyntax(0, "$assert$ <i>$condition$</i> $echo$ <i>$message$</i> ;");
        c.addDetail(0, "Unlike an $Assume Statement$, assert-statements cannot be disabled.");
        c.addDetail(0, "If the <i>condition</i> evaluates to false, then an $AutumnLangExceptionsAssertionFailedException$ will be thrown.");
        c.addDetail(0, "If the <i>condition</i> evaluates to true, then execution simply continues onward.");
        condition(c);
        expectType(c, ErrorCode.EXPECTED_STRING, "message", String.class);
        c.addExample(EXAMPLE_1, 103);
        c.addExample(EXAMPLE_2, 105);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Assume Statement";
        c.klass = AssertStatement.class;
        c.summary = "An assume-statement enforces an invariant.";
        c.addSyntax(0, "$assume$ <i>$condition$</i> ;");
        c.addSyntaxHR();
        c.addSyntax(0, "$assume$ <i>$condition$</i> $echo$ <i>$message$</i> ;");
        c.addDetail(0, "Unlike an $Assert Statement$, assume-statements can be disabled.");
        c.addDetail(1, "By default, assume-statements are enabled.");
        c.addDetail(1, "If assume-statements are disabled:");
        c.addDetail(2, "The runtime simply ignores all assume-statements.");
        c.addDetail(2, "The <i>condition</i> is not evaluated.");
        c.addDetail(2, "The <i>message</i> is not evaluated.");
        c.addDetail(1, "To enable assume-statements use: $enable-assume$");
        c.addDetail(1, "To disable assume-statements use: $disable-assume$");
        c.addDetail(0, "If the <i>condition</i> evaluates to false, then an $AutumnLangExceptionsAssumptionFailedException$ will be thrown.");
        c.addDetail(0, "If the <i>condition</i> evaluates to true, then execution simply continues onward.");
        condition(c);
        expectType(c, ErrorCode.EXPECTED_STRING, "message", String.class);
        c.addExample(EXAMPLE_1, 104);
        c.addExample(EXAMPLE_2, 106);
        Index.add(c);

        --Index.indent; // Assertions
        --Index.indent; // Exception Handling

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Anonymous Functions
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Anonymous Functions</b>");
        ++Index.indent;

        c = Construct.newInstance();
        c.name = "Delegate Statement";
        c.klass = DelegateStatement.class;
        c.summary = "A delegate-statement creates function-object that is essentially a type-safe function-pointer.";
        c.addSyntax(0, "$delegate$ <i>$assignee$</i> : <i>$type$</i> => <i>$Owner$</i>::<i>$name$</i> ;");
        c.addDetail(0, "The <i>assignee</i> will be declared in the enclosing scope as a readonly local variable.");
        variables(c, "assignee");
        c.addDetail(0, "A delegate-statement can create a delegate that refers to the special instance() method.");
        c.addDetail(0, "The function <i>X</i> that the delegate will refer to must be compatible with the functor-type <i>T</i>.");
        c.addDetail(1, "<i>T</i> is the functor-type specified by the <i>type</i>.");
        c.addDetail(1, "The return-type of <i>X</i> must be a subtype of the return-type of <i>T</i>.");
        c.addDetail(1, "The number of parameters of <i>X</i> must match the number of parameters of <i>T</i>.");
        c.addDetail(1, "The type of each parameter<sub>i</sub> of <i>X</i> must be a subtype of parameter<sub>i</sub> of <i>T</i>.");
        returns(c, "type of <i>type</i>", "a $AutumnLangDelegate$ that refers to the specified function");
        usetype(c, "type");
        c.addCheck(ErrorCode.EXPECTED_DEFINED_FUNCTOR_TYPE, "The type specified by <i>type</i> must be a subtype of $AutumnLangDefinedFunctor$.");
        c.addCheck(ErrorCode.EXPECTED_CLASS_TYPE, "The type specified by <i>type</i> must be a class-type.");
        usetype(c, "Owner");
        c.addCheck(ErrorCode.EXPECTED_MODULE_TYPE, "The type specified by <i>Owner</i> must be a subtype of $AutumnLangModule$.");
        c.addCheck(ErrorCode.NO_SUCH_METHOD, "The type specified by <i>Owner</i> must contain a function with the given <i>name</i>.");
        c.addCheck(ErrorCode.OVERLOADED_METHOD, "The <i>name</i> can only refer to one function in the type specified by <i>Owner</i>.");
        c.addCheck(ErrorCode.INCOMPATIBLE_DELEGATE, "The signature of the targeted function must be compatible with the functor-type specified by <i>type</i>.");
        c.addExample(EXAMPLE_1, 116);
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Lambda Statement";
        c.klass = LambdaStatement.class;
        c.summary = "A lambda-statement creates an anonymous function.";
        c.addSyntax(0, "$lambda$ <i>$variable$</i> : <i>$type$</i> => <i>$expression$</i> ;");
        c.addDetail(0, "The newly created anonymous function captures the state of the variables declared in the enclosing function.");
        c.addDetail(1, "In other words, the lambda function closes over the enclosing scope.");
        c.addDetail(1, "Changes to the captured variables are not visible to the lambda function after its creation.");
        usetype(c, "type");
        Index.add(c);

        --Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Sepecial Statements
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Special</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Nop Statement";
        c.klass = NopStatement.class;
        c.summary = "A nop-statement does not perform any operation.";
        c.addSyntax(0, "$nop$ ;");
        c.addDetail(0, "A nop-statement should generate a nop bytecode instruction.");
        c.addDetail(0, "An optimizer is free to remove nop bytecode instructions.");
        c.addExample(EXAMPLE_1, 74);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Expression Statement";
        c.klass = ExpressionStatement.class;
        c.summary = "An expression-statement facilitates the use of an expression as a statement.";
        c.addSyntax(0, "<i>$expression$</i> ;");
        c.addDetail(0, "The return-value, if any, of the expression is simply ignored.");
        c.addExample(EXAMPLE_1, 75);
        Index.add(c);

        --Index.indent; // Special

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Return Statements
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Return</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Return Void Statement";
        c.klass = ReturnVoidStatement.class;
        c.summary = "A return-statement causes execution to immediately exit the invocation of a function.";
        c.addSyntax(0, "$return$ ;");
        c.addDetail(0, "A return-void statement can only be used in a function whose return-type is void.");
        c.addCheck(ErrorCode.EXPECTED_VOID, "The <i>return-type</i> of the enclosing function must be void.");
        c.addExample(EXAMPLE_1, 92);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Return Value Statement";
        c.klass = ReturnValueStatement.class;
        c.summary = "A return-value statement causes execution to immediately exit the invocation of a function.";
        c.addSyntax(0, "$return$ <i>$value$</i> ;");
        c.addDetail(0, "A return-value statement cannot be used in a function whose return-type is void.");
        c.addDetail(0, "The <i>value</i> will be boxed, if necessary.");
        c.addDetail(0, "The <i>value</i> will be unboxed, if necessary.");
        c.addDetail(0, "The <i>value</i> will be coerced, if necessary.");
        expression(c, "value", true);
        c.addCheck(ErrorCode.WRONG_TYPE, "The type of the <i>value</i> must be assignable to the <i>return-type</i> of the enclosing function.");
        c.addExample(EXAMPLE_1, 93);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Recur Statement";
        c.klass = RecurStatement.class;
        c.summary = "A recur-statement performs a tail-recursive invocation of the enclosing function.";
        c.addSyntax(0, "$recur$ <i>$argument$<sub>1</sub></i> , <i>$argument$<sub>2</sub></i> , ... , <i>$argument$<sub>n</sub></i> ;");
        c.addDetail(0, "A recur-statement can be thought of as a combination of a return-statement and a call-expression.");
        c.addDetail(0, "Each <i>argument</i> will be boxed, if necessary.");
        c.addDetail(0, "Each <i>argument</i> will be unboxed, if necessary.");
        c.addDetail(0, "Each <i>argument</i> will be coerced, if necessary.");
        c.addDetail(0, "The <i>return-type</i> of the enclosing function can be void.");
        c.addDetail(0, "The <i>return-type</i> of the enclosing function can be non-void.");
        c.addCheck(ErrorCode.BAD_ARGUMENT_COUNT, "There must be exactly one <i>argument</i> for each <i>parameter</i> in the enclosing function.");
        c.addCheck(ErrorCode.IMPOSSIBLE_ASSIGNMENT, "The type of <i>argument<sub>i</sub></i> must be assignable to the type of <i>parameter<sub>i</sub></i> of the enclosing function.");
        c.addExample(EXAMPLE_1, 115);
        Index.add(c);

        --Index.indent; // Return

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Expressions
        ////////////////////////////////////////////////////////////////////////////////////////////

        --Index.indent;
        Index.add("<b>Expressions</b>");
        ++Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Datums
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Datums</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Boolean Datum";
        c.klass = BooleanDatum.class;
        c.summary = "A boolean-datum is a literal boolean value.";
        c.addSyntax(0, "$true$");
        c.addSyntaxHR();
        c.addSyntax(0, "$false$");
        returns(c, boolean.class, "the value of the constant");
        relatedBoxedType(c, Boolean.class);
        c.addExample(EXAMPLE_1, 60);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Char Datum";
        c.klass = CharDatum.class;
        c.summary = "A char-datum is a literal character.";
        c.addSyntax(0, "<b>'</b><i>character</i><b>'</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>digits</i><b class='keyword'>C</b>");
        returns(c, char.class, "the value of the constant");
        c.addDetail(0, "Minimum Value = " + (int) Character.MIN_VALUE);
        c.addDetail(0, "Maximum Value = " + (int) Character.MAX_VALUE);
        relatedBoxedType(c, Character.class);
        c.addCheck(ErrorCode.INACCURATE_NUMERIC_LITERAL, "The value must be inclusively between the minimum and maximum.");
        c.addExample(EXAMPLE_1, 61);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Byte Datum";
        c.klass = ByteDatum.class;
        c.summary = "A byte-datum is a literal byte value.";
        c.addSyntax(0, "<i>digits</i><b class='keyword'>B</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b class='keyword'>B</b>");
        returns(c, byte.class, "the value of the constant");
        c.addDetail(0, "Minimum Value = " + Byte.MIN_VALUE);
        c.addDetail(0, "Maximum Value = " + Byte.MAX_VALUE);
        relatedBoxedType(c, Byte.class);
        c.addCheck(ErrorCode.INACCURATE_NUMERIC_LITERAL, "The value must be inclusively between the minimum and maximum.");
        c.addExample(EXAMPLE_1, 62);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Short Datum";
        c.klass = ShortDatum.class;
        c.summary = "A short-datum is a literal short value.";
        c.addSyntax(0, "<i>digits</i><b class='keyword'>S</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b class='keyword'>S</b>");
        returns(c, short.class, "the value of the constant");
        c.addDetail(0, "Minimum Value = " + Short.MIN_VALUE);
        c.addDetail(0, "Maximum Value = " + Short.MAX_VALUE);
        relatedBoxedType(c, Short.class);
        c.addCheck(ErrorCode.INACCURATE_NUMERIC_LITERAL, "The value must be inclusively between the minimum and maximum.");
        c.addExample(EXAMPLE_1, 63);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Int Datum";
        c.klass = IntDatum.class;
        c.summary = "A int-datum is a literal int value.";
        c.addSyntax(0, "<i>digits</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i>");
        returns(c, int.class, "the value of the constant");
        c.addDetail(0, "Minimum Value = " + Integer.MIN_VALUE);
        c.addDetail(0, "Maximum Value = " + Integer.MAX_VALUE);
        relatedBoxedType(c, Integer.class);
        c.addCheck(ErrorCode.INACCURATE_NUMERIC_LITERAL, "The value must be inclusively between the minimum and maximum.");
        c.addExample(EXAMPLE_1, 64);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Long Datum";
        c.klass = LongDatum.class;
        c.summary = "A long-datum is a literal long value.";
        c.addSyntax(0, "<i>digits</i><b class='keyword'>L</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b class='keyword'>L</b>");
        returns(c, long.class, "the value of the constant");
        c.addDetail(0, "Minimum Value = " + Long.MIN_VALUE);
        c.addDetail(0, "Maximum Value = " + Long.MAX_VALUE);
        relatedBoxedType(c, Long.class);
        c.addCheck(ErrorCode.INACCURATE_NUMERIC_LITERAL, "The value must be inclusively between the minimum and maximum.");
        c.addExample(EXAMPLE_1, 65);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Float Datum";
        c.klass = FloatDatum.class;
        c.summary = "A float-datum is a literal float value.";
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i><b class='keyword'>F</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b>.</b><i>digits</i><b class='keyword'>F</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i><b class='keyword'>e</b><i>digits</i><b class='keyword'>F</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b>.</b><i>digits</i><b class='keyword'>e</b><i>digits</i><b class='keyword'>F</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i><b class='keyword'>E</b><i>digits</i><b class='keyword'>F</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b>.</b><i>digits</i><b class='keyword'>E</b><i>digits</i><b class='keyword'>F</b>");
        returns(c, float.class, "the value of the constant");
        relatedBoxedType(c, Float.class);
        c.addExample(EXAMPLE_1, 66);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Double Datum";
        c.klass = DoubleDatum.class;
        c.summary = "A double-datum is a literal double value.";
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b>.</b><i>digits</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i><b class='keyword'>e</b><i>digits</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b>.</b><i>digits</i><b class='keyword'>e</b><i>digits</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i><b class='keyword'>E</b><i>digits</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b>.</b><i>digits</i><b class='keyword'>E</b><i>digits</i>");
        returns(c, double.class, "the value of the constant");
        relatedBoxedType(c, Double.class);
        c.addExample(EXAMPLE_1, 67);
        Index.add(c);


        c = Construct.newInstance();
        c.name = "Big Integer Datum";
        c.klass = BigIntegerDatum.class;
        c.summary = "A big-integer datum is a literal $BigInteger$ value.";
        c.addSyntax(0, "<i>digits</i><b class='keyword'>BI</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b class='keyword'>BI</b>");
        returns(c, BigInteger.class, "the value of the constant");
        //c.addExample(EXAMPLE_1, 65);
        Index.add(c);

        // TODO: Fix syntax description - needs signed exponents
        c = Construct.newInstance();
        c.name = "Big Decimal Datum";
        c.klass = BigDecimalDatum.class;
        c.summary = "A big-decimal datum is a literal $BigDecimal$ value.";
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>-</b><i>digits</i><b>.</b><i>digits</i><b class='keyword'>BD</b>");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>digits</i><b>.</b><i>digits</i><b class='keyword'>e</b><i>digits</i><b class='keyword'>BD</b>");
        returns(c, BigInteger.class, "the value of the constant");
        //c.addExample(EXAMPLE_1, 65);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "String Datum";
        c.klass = StringDatum.class;
        c.summary = "A string-datum is a literal text value.";
        c.addSyntax(0, "\"<i>string of characters</i>\"");
        c.addSyntaxHR();
        c.addSyntax(0, "@ \"<i>string of characters</i>\"");
        c.addSyntaxHR();
        c.addSyntax(0, "'''<i>string of characters</i>'''");
        c.addSyntaxHR();
        c.addSyntax(0, "@ '''<i>string of characters</i>'''");
        c.addDetail(0, "Escape Sequences");
        c.addDetail(1, "    Tab: &$92t");
        c.addDetail(1, "    Backspace: &$92b");
        c.addDetail(1, "    Newline: &$92n");
        c.addDetail(1, "    Carriage Return: &$92r");
        c.addDetail(1, "    Form Feed: &$92f");
        c.addDetail(1, "    Single Quote: &$92'");
        c.addDetail(1, "    Double Quote: &$92&$34;");
        c.addDetail(1, "    Backslash: &$92&$92");
        c.addDetail(1, "    Character Code: &$92<i>D<sub>1</sub>D<sub>2</sub>D<sub>3</sub>D<sub>4</sub>D<sub>5</sub></i> (where <i>D<sub>i</sub></i> is a decimal digit)");
        c.addDetail(2, "        The character-code must be between 0 and 65536.");
        c.addDetail(0, "The two syntactic forms that are prefixed with an '@' are verbose-strings.");
        c.addDetail(0, "Escape sequences are ignored in verbose strings.");
        c.addDetail(0, "String-literals can span multiple lines.");
        returns(c, String.class, "the value of the constant");
        c.addCheck(ErrorCode.MALFORMED_STRING_LITERAL, "A non-verbatim string cannot contain malformed escape-sequences.");
        c.addExample(EXAMPLE_1, 70);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Class Datum";
        c.klass = ClassDatum.class;
        c.summary = "A class-datum is a literal $JavaLangClass$ value.";
        c.addSyntax(0, "( $class$ <i>type</i> )");
        usetype(c, "<i>type</i>");
        returns(c, Class.class, "the Class object representation of the <i>type</i>");
        c.addCheck(ErrorCode.EXPECTED_RETURN_TYPE, "The type specified by <i>type</i> must be a return-type.");
        c.addExample(EXAMPLE_1, 68);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Null Datum";
        c.klass = NullDatum.class;
        c.summary = "A null-datum is the literal value null.";
        c.addSyntax(0, "$null$");
        returns(c, "null-type", "the value of the constant");
        c.addExample(EXAMPLE_1, 71);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Variable Datum";
        c.klass = VariableDatum.class;
        c.summary = "A variable-datum retrieves the values stored in a variable.";
        c.addSyntax(0, "<i>$variable$</i>");
        c.addDetail(0, "A variable will return its default-value, if it is accessed before its first assignment.");
        returns(c, "(type of the referenced variable per the variable's declaration)", "the value stored in the referenced variable");
        c.addCheck(ErrorCode.NO_SUCH_VARIABLE, "The <i>variable</i> must be declared somewhere within the enclosing scope.");
        c.addCheck(ErrorCode.VARIABLE_OUTSIDE_OF_SCOPE, "The <i>variable</i> cannot be used outside of the scope in which it was declared.");
        c.addExample(EXAMPLE_1, 72);
        c.addExample(EXAMPLE_2, 73);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Operators
        ////////////////////////////////////////////////////////////////////////////////////////////

        --Index.indent;
        Index.add("<b>Operators</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "Negate Operation";
        c.klass = NegateOperation.class;
        c.summary = "This operator performs an arithmetic negation operation.";
        c.addSyntax(0, "- <i>$value$</i>");
        c.addDetail(0, "Precedence: 1");
        c.addDetail(0, "Predefined Overloads:");
        c.addDetail(1, "(- byte) &$8614; byte");
        c.addDetail(1, "(- short) &$8614; short");
        c.addDetail(1, "(- int) &$8614; int");
        c.addDetail(1, "(- long) &$8614; long");
        c.addDetail(1, "(- float) &$8614; float");
        c.addDetail(1, "(- double) &$8614; double");
        c.addDetail(1, "(- $JavaMathBigInteger$) => $JavaMathBigInteger$");
        c.addDetail(1, "(- $JavaMathBigDecimal$) => $JavaMathBigDecimal$");
        c.addDetail(0, "The overload that best matches the operand will be selected.");
        c.addDetail(1, "Unboxing will be performed, if necessary.");
        c.addDetail(1, "Coercion will be performed, if necessary.");
        returns(c, "Return-Type of Selected Overload", "the result of the operation");
        c.addCheck(ErrorCode.NO_SUCH_UNARY_OPERATOR, "None of the overloads will accept the operand due to its type.");
        c.addExample(EXAMPLE_1, 18);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Not Operation";
        c.klass = NotOperation.class;
        c.summary = "This operator performs a logical-NOT operation.";
        c.addSyntax(0, "! <i>$value$</i>");
        c.addDetail(0, "Precedence: 1");
        c.addDetail(0, "Predefined Overload:");
        c.addDetail(1, "(! boolean) &$8614; boolean");
        c.addDetail(0, "Unboxing will be performed, if necessary.");
        c.addCheck(ErrorCode.NO_SUCH_UNARY_OPERATOR, "The overload will not accept the operand due to its type.");
        returns(c, boolean.class, "the result of the operation");
        c.addExample(EXAMPLE_1, 19);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Divide Operation";
        c.klass = DivideOperation.class;
        c.summary = "This operator performs an arithmetic division operation.";
        c.addSyntax(0, "<i>$left$</i> / <i>$right$</i>");
        c.addDetail(0, "Precedence: 2");
        c.addDetail(0, "Associativity: Left");
        arithmeticOverloads(c, "/");
        binaryoperator(c);
        returns(c, "Return-Type of Selected Overload", "the result of the operation");
        c.addExample(EXAMPLE_1, 14);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Modulo Operation";
        c.klass = ModuloOperation.class;
        c.summary = "This operator performs an arithmetic modulus operation.";
        c.addSyntax(0, "<i>$left$</i> &$37; <i>$right$</i>");
        c.addDetail(0, "Precedence: 2");
        c.addDetail(0, "Associativity: Left");
        arithmeticOverloads(c, "&$37;");
        binaryoperator(c);
        returns(c, "Return-Type of Selected Overload", "the result of the operation");
        c.addExample(EXAMPLE_1, 20);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Multiply Operation";
        c.klass = MultiplyOperation.class;
        c.summary = "This operator performs an arithmetic multiplication operation.";
        c.addSyntax(0, "<i>$left$</i> * <i>$right$</i>");
        c.addDetail(0, "Precedence: 3");
        c.addDetail(0, "Associativity: Left");
        arithmeticOverloads(c, "*");
        binaryoperator(c);
        returns(c, "Return-Type of Selected Overload", "the result of the operation");
        c.addExample(EXAMPLE_1, 15);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Add Operation";
        c.klass = AddOperation.class;
        c.summary = "This operator performs an arithmetic addition operation.";
        c.addSyntax(0, "<i>$left$</i> + <i>$right$</i>");
        c.addDetail(0, "Precedence: 4");
        c.addDetail(0, "Associativity: Left");
        arithmeticOverloads(c, "+");
        binaryoperator(c);
        returns(c, "Return-Type of Selected Overload", "the result of the operation");
        c.addExample(EXAMPLE_1, 16);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Subtract Operation";
        c.klass = SubtractOperation.class;
        c.summary = "This operator performs an arithmetic subtraction operation.";
        c.addSyntax(0, "<i>$left$</i> - <i>$right$</i>");
        c.addDetail(0, "Precedence: 4");
        c.addDetail(0, "Associativity: Left");
        arithmeticOverloads(c, "-");
        binaryoperator(c);
        returns(c, "Return-Type of Selected Overload", "the result of the operation");
        c.addExample(EXAMPLE_1, 17);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Concat Operation";
        c.klass = ConcatOperation.class;
        c.summary = "This operator performs a string concatenation operation.";
        c.addSyntax(0, "<i>$left$</i> .. <i>$right$</i>");
        c.addDetail(0, "Precedence: 5");
        c.addDetail(0, "Associativity: Left");
        c.addDetail(0, "Predefined Overload:");
        c.addDetail(1, "($JavaLangObject$ .. $JavaLangObject$) &$8614; String");
        c.addDetail(0, "The operands will be boxed when necessary.");
        binaryoperator(c);
        returns(c, String.class, "the string representation of the left-operand prepended onto the string-representation of the right-operand.");
        c.addExample(EXAMPLE_1, 42);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Identity Equality Operation";
        c.klass = IdentityEqualsOperation.class;
        c.summary = "This operator performs an equality comparison operation based on object identity.";
        c.addSyntax(0, "<i>$left$</i> === <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        greedyOperands(c);
        returns(c, boolean.class, "true when the identities of the two operands are equal");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type of the left-operand must be a reference-type.");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type of the right-operand must be a reference-type.");
        c.addExample(EXAMPLE_1, 31);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Identity Inequality Operation";
        c.klass = IdentityNotEqualsOperation.class;
        c.summary = "This operator performs an inequality comparison operation based on object identity.";
        c.addSyntax(0, "<i>$left$</i> !== <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        greedyOperands(c);
        returns(c, boolean.class, "true when the identities of the two operands are unequal");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type of the left-operand must be a reference-type.");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type of the right-operand must be a reference-type.");
        c.addExample(EXAMPLE_1, 32);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Equality Operation";
        c.klass = EqualsOperation.class;
        c.summary = "This operator performs an equality comparison operation.";
        c.addSyntax(0, "<i>$left$</i> == <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        equalityOverloads(c, "==");
        binaryoperator(c);
        returns(c, boolean.class, "true when the two operands are equal");
        c.addExample(EXAMPLE_1, 35);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Inequality Operation";
        c.klass = NotEqualsOperation.class;
        c.summary = "This operator performs an inequality comparison operation.";
        c.addSyntax(0, "<i>$left$</i> != <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        equalityOverloads(c, "!=");
        binaryoperator(c);
        returns(c, boolean.class, "true when the two operands are unequal");
        c.addExample(EXAMPLE_1, 36);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Greater-Than-OR-Equals Operation";
        c.klass = GreaterThanOrEqualsOperation.class;
        c.summary = "This operator performs a greater-or-equals comparison operation.";
        c.addSyntax(0, "<i>$left$</i> &gt;= <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        comparisonOverloads(c, ">=");
        binaryoperator(c);
        returns(c, boolean.class, "true when the left-operand is greater-than-or-equal to the right-operand");
        c.addExample(EXAMPLE_1, 27);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Less-Than-OR-Equals Operation";
        c.klass = LessThanOrEqualsOperation.class;
        c.summary = "This operator performs a less-or-equals comparison operation.";
        c.addSyntax(0, "<i>$left$</i> &lt;= <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        comparisonOverloads(c, "<=");
        binaryoperator(c);
        returns(c, boolean.class, "true when the left-operand is less-than-or-equal to the right-operand");
        c.addExample(EXAMPLE_1, 28);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Greater-Than Operation";
        c.klass = GreaterThanOperation.class;
        c.summary = "This operator performs a greater-than comparison operation.";
        c.addSyntax(0, "<i>$left$</i> &gt; <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        comparisonOverloads(c, ">");
        binaryoperator(c);
        returns(c, boolean.class, "true when the left-operand is greater-than the right-operand");
        c.addExample(EXAMPLE_1, 29);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Less-Than Operation";
        c.klass = LessThanOperation.class;
        c.summary = "This operator performs a less-than comparison operation.";
        c.addSyntax(0, "<i>$left$</i> &lt; <i>$right$</i>");
        c.addDetail(0, "Precedence: 6");
        c.addDetail(0, "Associativity: Left");
        comparisonOverloads(c, "<");
        binaryoperator(c);
        returns(c, boolean.class, "true when the left-operand is less-than to the right-operand");
        c.addExample(EXAMPLE_1, 30);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "And Operation";
        c.klass = AndOperation.class;
        c.summary = "This operator performs a non-short-circuit logical AND-operation.";
        c.addSyntax(0, "<i>$left$</i> & <i>$right$</i>");
        c.addDetail(0, "Precedence: 7");
        c.addDetail(0, "Associativity: Left");
        c.addDetail(0, "This is a $short-circuit$ operator:");
        c.addDetail(1, "The left-operand is always evaluated.");
        c.addDetail(1, "The right-operand is only evaluated when the left-operand produces true.");
        returns(c, boolean.class, "true when both the left-operand <b>and</b> the right-operand produces true");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the left-operand must be either boolean or $JavaLangBoolean$.");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the right-operand must be either boolean or $JavaLangBoolean$.");
        c.addExample(EXAMPLE_1, 21);
        c.addExample(EXAMPLE_2, 25);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Or Operation";
        c.klass = OrOperation.class;
        c.summary = "This operator performs a non-short-circuit logical OR-operation.";
        c.addSyntax(0, "<i>$left$</i> | <i>$right$</i>");
        c.addDetail(0, "Precedence: 7");
        c.addDetail(0, "Associativity: Left");
        c.addDetail(0, "This is a $short-circuit$ operator:");
        c.addDetail(1, "The left-operand is always evaluated.");
        c.addDetail(1, "The right-operand is only evaluated when the left-operand produces false.");
        returns(c, boolean.class, "true when either the left-operand <b>or</b> the right-operand produces true");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the left-operand must be either boolean or $JavaLangBoolean$.");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the right-operand must be either boolean or $JavaLangBoolean$.");
        c.addExample(EXAMPLE_1, 22);
        c.addExample(EXAMPLE_2, 26);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Xor Operation";
        c.klass = XorOperation.class;
        c.summary = "This operator performs a logical XOR-operation.";
        c.addSyntax(0, "<i>$left$</i> ^ <i>$right$</i>");
        c.addDetail(0, "Precedence: 7");
        c.addDetail(0, "Associativity: Left");
        greedyOperands(c);
        returns(c, boolean.class, "true when the left-operand is true or the right-operand is true");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the left-operand must be either boolean or $JavaLangBoolean$.");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the right-operand must be either boolean or $JavaLangBoolean$.");
        c.addExample(EXAMPLE_1, 23);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Implies Operation";
        c.klass = XorOperation.class;
        c.summary = "This operator performs a  logical implication operation.";
        c.addSyntax(0, "<i>$left$</i> -> <i>$right$</i>");
        c.addDetail(0, "Precedence: 7");
        c.addDetail(0, "Associativity: Left");
        c.addDetail(0, "This is a $short-circuit$ operator:");
        c.addDetail(1, "The left-operand is always evaluated.");
        c.addDetail(1, "The right-operand is only evaluated when the left-operand produces true.");
        returns(c, boolean.class, "true when the left-operand implies the right-operand");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the left-operand must be either boolean or $JavaLangBoolean$.");
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of the right-operand must be either boolean or $JavaLangBoolean$.");
        c.addExample(EXAMPLE_1, 24);
        c.addExample(EXAMPLE_2, 88);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Null Coalescing Operation";
        c.klass = NullCoalescingOperation.class;
        c.summary = "A null-coalescing provides an alternate value when a primary value is null.";
        c.addSyntax(0, "<i>$left$</i> ?? <i>$right$</i>");
        c.addDetail(0, "Precedence: 8");
        c.addDetail(0, "Associativity: Left");
        c.addDetail(0, "The left-operand is always evaluated.");
        c.addDetail(0, "The right-operand is only evaluated when the left-operand produces null.");
        returns(c, "widest(typeof(<i>left</i>), typeof(<i>right</i>))", "the left-operand, if the value is not null; otherwise, the right-operand");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type of the left-operand must be a reference-type.");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type of the right-operand must be a reference-type.");
        c.addCheck(ErrorCode.INCOMPATIBLE_OPERANDS, "The type of one of the operands must be a subtype of the other.");
        c.addExample(EXAMPLE_1, 33);
        c.addExample(EXAMPLE_2, 34);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "As Operation";
        c.klass = AsOperation.class;
        c.summary = "An as-operation converts a value to another type.";
        c.addSyntax(0, "<i>value</i> $as$ <i>$type$</i>");
        c.addDetail(0, "Precedence: 9");
        c.addDetail(0, "For every conversion provided by this operation, there is a similar conversion provided by the is-operation.");
        c.addDetail(1, "More formally, as-operations are symmetric to is-operations.");
        usetype(c, "type");
        conversions(c);
        returns(c, "<i>type</i>", "the result of the predefined conversion or cast");
        c.addDetail(0, "If the conversion is an unsuccessful cast, then null is returned.");
        c.addExample(EXAMPLE_1, 37);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Is Operation";
        c.klass = IsOperation.class;
        c.summary = "An is-operation converts a value to another type after performing a runtime check.";
        c.addSyntax(0, "<i>value</i> $is$ <i>$type$</i>");
        c.addDetail(0, "Precedence: 9");
        c.addDetail(0, "For every conversion provided by this operation, there is a similar conversion provided by the as-operation.");
        c.addDetail(1, "More formally, as-operations are symmetric to is-operations.");
        usetype(c, "type");
        conversions(c);
        c.addDetail(0, "Runtime Check: If the conversion is an unsuccessful cast, then a $JavaLangClassCastException$ is thrown.");
        returns(c, "<i>type</i>", "the result of the predefined conversion or cast");
        c.addExample(EXAMPLE_1, 38);
        c.addExample(EXAMPLE_2, 39);
        Index.add(c);

        --Index.indent; // Operators

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Expressions
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.add("<b>Object Orientation</b>");
        ++Index.indent;

        // Finished!
        c = Construct.newInstance();
        c.name = "New Expression";
        c.klass = NewExpression.class;
        c.summary = "A new-expression creates a new instance of a specified class-type.";
        c.addSyntax(0, "$new$ <i>$type$</i> ( <i>$argument$<sub>1</sub></i> , ... , <i>$argument$<sub>n</sub></i> )");
        c.addDetail(0, "The constructor overload will be selected using the $Constructor Resolution Algorithm$.");
        c.addDetail(0, "The constructor overload is selected at compile-time.");
        c.addDetail(0, "Boxing of the arguments will be performed, when necessary.");
        c.addDetail(0, "Unboxing of the arguments will be performed, when necessary.");
        c.addDetail(0, "Coercion of the arguments will be performed, when necessary.");
        returns(c, "type of <i>type</i>", "a new instance of the <i>type</i>");
        usetype(c, "<i>type</i>");
        c.addCheck(ErrorCode.EXPECTED_CLASS_TYPE, "The <i>type</i> must be a class-type.");
        c.addCheck(ErrorCode.NO_SUCH_CONSTRUCTOR, "No acceptable constructor overload was found.");
        c.addExample(EXAMPLE_1, 69);
        Index.add(c);

        // Finished!
        // TODO: Add info about @InferReturnType
        //       Also, add an example of using @InferReturnType
        c = Construct.newInstance();
        c.name = "Call Static Method Expression";
        c.klass = CallStaticMethodExpression.class;
        c.summary = "A call-static-expression performs an invocation of a static method or function.";
        c.addSyntax(0, "$call$ <i>$Owner$</i>::<i>$name$</i> ( <i>$argument$<sub>1</sub></i> , ... , <i>$argument$<sub>n</sub></i> )");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>$Owner$</i>::<i>$name$</i> ( <i>$argument$<sub>1</sub></i> , ... , <i>$argument$<sub>n</sub></i> )");
        c.addDetail(0, "The method overload will be selected using the $Static Method Resolution Algorithm$.");
        c.addDetail(0, "The method overload is selected at compile-time.");
        c.addDetail(0, "Boxing of the arguments will be performed, when necessary.");
        c.addDetail(0, "Unboxing of the arguments will be performed, when necessary.");
        c.addDetail(0, "Coercion of the arguments will be performed, when necessary.");
        c.addDetail(0, "Remember, a function is technically a static method.");
        returns(c, "[return-type of the selected method overload]", "the value returned by the invoked method");
        usetype(c, "<i>owner</i>");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type specified by <i>owner</i> must be a declared-type.");
        c.addCheck(ErrorCode.NO_SUCH_METHOD, "No acceptable method overload was found.");
        c.addExample(EXAMPLE_1, 76);
        Index.add(c);

        // Finished!
        // TODO: discuss @Infer
        c = Construct.newInstance();
        c.name = "Call Method Expression";
        c.klass = CallMethodExpression.class;
        c.summary = "A call-expression performs an invocation of an instance method.";
        c.addSyntax(0, "$call$ <i>$owner$</i>.<i>$name$</i> ( <i>$argument$<sub>1</sub></i> , ... , <i>$argument$<sub>n</sub></i> )");
        c.addSyntaxHR();
        c.addSyntax(0, "<i>$owner$</i>.<i>$name$</i> ( <i>$argument$<sub>1</sub></i> , ... , <i>$argument$<sub>n</sub></i> )");
        c.addDetail(0, "The method overload will be selected using the $Instance Method Resolution Algorithm$.");
        c.addDetail(0, "The method overload is selected at compile-time.");
        c.addDetail(0, "Boxing of the arguments will be performed, when necessary.");
        c.addDetail(0, "Unboxing of the arguments will be performed, when necessary.");
        c.addDetail(0, "Coercion of the arguments will be performed, when necessary.");
        c.addDetail(0, "Runtime Check: If <i>owner</i> is null, then a $JavaLangNullPointerException$ will be thrown.");
        returns(c, "[return-type of the selected method overload]", "the value returned by the invoked method");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type of <i>owner</i> must be a declared-type.");
        c.addCheck(ErrorCode.NO_SUCH_METHOD, "No acceptable method overload was found.");
        c.addExample(EXAMPLE_1, 77);
        c.addExample(EXAMPLE_2, 78);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Set Static Field Expression";
        c.klass = SetStaticFieldExpression.class;
        c.summary = "A set-static-field-expression sets the value of a static field.";
        c.addSyntax(0, "$field$ <i>$Owner$</i>::<i>$name$</i> = <i>$value$</i>");
        c.addDetail(0, "The field will be selected using the $Static Field Resolution Algorithm$.");
        c.addDetail(0, "Boxing of the value will be performed, when necessary.");
        c.addDetail(0, "Unboxing of the value will be performed, when necessary.");
        c.addDetail(0, "Coercion of the value will be performed, when necessary.");
        returns(c, void.class, "nothing, because the return-type is void");
        usetype(c, "<i>owner</i>");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type of <i>owner</i> must be a declared-type.");
        c.addCheck(ErrorCode.NO_SUCH_FIELD, "No acceptable field was found.");
        c.addCheck(ErrorCode.ASSIGNMENT_TO_READONLY, "A value cannot be assigned to a final field, because it is readonly.");
        c.addCheck(ErrorCode.IMPOSSIBLE_ASSIGNMENT, "The type of the <i>value</i> must be assignable to the <i>type</i> of the selected field.");
        c.addExample(EXAMPLE_1, 79);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Get Static Field Expression";
        c.klass = GetStaticFieldExpression.class;
        c.summary = "A get-static-field-expression gets the value of a static field.";
        c.addSyntax(0, "$field$ <i>$Owner$</i>::<i>$name$</i> = <i>$value$</i>");
        c.addDetail(0, "The field will be selected using the $Static Field Resolution Algorithm$.");
        returns(c, "[type of the selected field]", "the value stored in the field");
        usetype(c, "<i>owner</i>");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type of <i>owner</i> must be a declared-type.");
        c.addCheck(ErrorCode.NO_SUCH_FIELD, "No acceptable field was found.");
        c.addExample(EXAMPLE_1, 80);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Set Field Expression";
        c.klass = SetFieldExpression.class;
        c.summary = "A set-field-expression sets the value of an instance field.";
        c.addSyntax(0, "$field$ <i>$owner$</i>.<i>$name$</i> = <i>$value$</i>");
        c.addDetail(0, "The field will be selected using the $Instance Field Resolution Algorithm$.");
        c.addDetail(0, "Boxing of the value will be performed, when necessary.");
        c.addDetail(0, "Unboxing of the value will be performed, when necessary.");
        c.addDetail(0, "Coercion of the value will be performed, when necessary.");
        c.addDetail(0, "Runtime Check: If <i>owner</i> is null, then a $JavaLangNullPointerException$ will be thrown.");
        returns(c, "void", "nothing, because the return-type is void");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type of <i>owner</i> must be a declared-type.");
        c.addCheck(ErrorCode.NO_SUCH_FIELD, "No acceptable field was found.");
        c.addCheck(ErrorCode.ASSIGNMENT_TO_READONLY, "A value cannot be assigned to a final field, because it is readonly.");
        c.addCheck(ErrorCode.IMPOSSIBLE_ASSIGNMENT, "The type of the <i>value</i> must be assignable to the <i>type</i> of the selected field.");
        c.addExample(EXAMPLE_1, 81);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Get Field Expression";
        c.klass = GetFieldExpression.class;
        c.summary = "A get-field-expression gets the value of an instance field.";
        c.addSyntax(0, "$field$ <i>$owner$</i>.<i>$name$</i>");
        c.addDetail(0, "The field will be selected using the $Instance Field Resolution Algorithm$.");
        returns(c, "[type of the selected field]", "the value stored in the field");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type of <i>owner</i> must be a declared-type.");
        c.addCheck(ErrorCode.NO_SUCH_FIELD, "No acceptable field was found.");
        c.addExample(EXAMPLE_1, 82);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Instance-Of Expression";
        c.klass = InstanceOfExpression.class;
        c.summary = "An instance-of-expression determines whether a value is an instance of a particular type.";
        c.addSyntax(0, "$instanceof$ <i>$value$</i> : <i>$type$</i>");
        c.addDetail(0, "An instance-of operation is viable, iff:");
        c.addDetail(1, "The type of <i>value</i> is not the null-type.");
        c.addDetail(1, "and:");
        c.addDetail(2, "The type of <i>value</i> is a subtype of the type specified by <i>type</i>.");
        c.addDetail(2, "The type specified  by <i>type</i> is a subtype of the type of <i>value</i>.");
        returns(c, boolean.class, "Return true, iff the <i>value</i> at runtime is both non-null and a subtype of the <i>type</i>");
        usetype(c, "<i>type</i>");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type of <i>value</i> must be a declared-type.");
        c.addCheck(ErrorCode.EXPECTED_DECLARED_TYPE, "The type of <i>type</i> must be a declared-type.");
        c.addCheck(ErrorCode.NON_VIABLE_INSTANCEOF, "The operation must be viable at compile-time.");
        c.addExample(EXAMPLE_1, 83);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Dispatch Expression";
        c.klass = DispatchExpression.class;
        c.summary = "A dispatch-expression dispatches an invocation to a nearby function using multiple dispatch.";
        c.addSyntax(0, "$dispatch$ $name$ ( <i>$argument$<sub>1</sub></i> , ... , <i>$argument$<sub>n</sub></i> )");
        c.addDetail(0, "At compile-time, the compiler creates a dispatch table containing the overloads of the named function.");
        c.addDetail(1, "The overloads will be sorted topologically from the most specific to the most generalized.");
        c.addDetail(1, "In order for an overload to be included in the dispatch table:");
        c.addDetail(2, "The number of provided arguments must equal the number of parameters.");
        c.addDetail(2, "The type of each parameter must be a reference-type.");
        c.addDetail(0, "At runtime, the overload to invoke is selected as follows:");
        c.addDetail(1, "Let A<sub>1</sub> ... A<sub>n</sub> denote the arguments.");
        c.addDetail(1, "Select the first overload from the sorted list of overloads, where each argument matches the related parameter.");
        c.addDetail(2, "Let P<sub>1</sub> ... P<sub>n</sub> denote the types of an overload's parameters.");
        c.addDetail(3, "A<sub>i</sub> matches P<sub>i</sub>, iff:");
        c.addDetail(3, "A<sub>i</sub> is null.");
        c.addDetail(3, "A<sub>i</sub> is an instance of P<sub>i</sub>.");
        c.addDetail(0, "Boxing of the arguments will be performed, when necessary.");
        c.addDetail(0, "Unboxing of the arguments will not be performed.");
        c.addDetail(0, "Coercion of the arguments will not be performed.");
        c.addDetail(0, "Return Type: " + JSONBuilder.link(Object.class));
        c.addDetail(1, "If the return-type of the dynamically selected overload is the void-type, then return null.");
        c.addDetail(1, "Otherwise, return the value returned by invoking the dynamically selected overload.");
        c.addDetail(2, "Box the return-value, if the return-type is a primitive-type.");
        c.addDetail(0, "A $AutumnLangExceptionsDispatchException$ will be thrown, if none of the selected overloads will accept the arguments at runtime.");
        c.addCheck(ErrorCode.NO_SUCH_METHOD, "No applicable function overload(s) exist.");
        c.addExample(EXAMPLE_1, 217);
        c.addExample(EXAMPLE_2, 218);
        Index.add(c);

        --Index.indent; // OOP

        // Finished!
        c = Construct.newInstance();
        c.name = "Ternary Conditional Expression";
        c.klass = TernaryConditionalExpression.class;
        c.summary = "A ternary-conditional-expression conditionally chooses one of two values.";
        c.addSyntax(0, "( $if$ <i>$condition$</i> $then$ <i>$left$</i> $else$ <i>$right$</i>)");
        c.addDetail(0, "The <i>left</i> expression is only evaluated, if the <i>condition</i> produces true.");
        c.addDetail(0, "The <i>right</i> expression is only evaluated, if the <i>condition</i> produces false.");
        condition(c);
        expression(c, "left", true);
        expression(c, "right", true);
        returns(c, "widest(typeof(<i>left</i>), typeof(<i>right</i>))", "either the value of <i>left</i> or the value of <i>right</i>, depending on the value produced by the <i>condition</i>");
        c.addCheck(ErrorCode.INCOMPATIBLE_OPERANDS, "The type of one of the operands must be a subtype of the other.");
        c.addExample(EXAMPLE_1, 85);
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Once Expression";
        c.klass = TernaryConditionalExpression.class; // TODO
        c.summary = "A once-expression can be used to cache a value.";
        c.addSyntax(0, "once $value$");
        c.addDetail(0, "Behavior:");
        c.addDetail(1, "During the first time the once-expression is evaluated:");
        c.addDetail(2, "Evaluate the <i>value</i>.");
        c.addDetail(2, "Store the result for later.");
        c.addDetail(2, "Return the result.");
        c.addDetail(1, "During all subsequent evaluations of the once-expression:");
        c.addDetail(2, "Return the previously stored result.");
        c.addDetail(0, "Only one thread can be evaluating the once-expression at a time.");
        c.addDetail(1, "In other words, the once-expression is synchronized.");
        c.addDetail(0, "The result obtained from the <i>value</i> may be void.");
        c.addDetail(0, "The result is stored for the lifetime of the enclosing module.");
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Locals Expression";
        c.klass = LocalsExpression.class;
        c.summary = "A locals-expression creates an object that describes the local variables in the enclosing scope.";
        c.addSyntax(0, "( $locals$ )");
        c.addDetail(0, "All user-visible variables in the enclosing scope will be captured.");
        c.addDetail(1, "This includes user-visible variables in outer scopes, if any.");
        c.addDetail(1, "This excludes temporary variables created by the compiler.");
        returns(c, LocalsMap.class, "an object that describes the local variables in the enclosing scope");
        c.addExample(EXAMPLE_1, 86);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "Progn Expression";
        c.klass = PrognExpression.class;
        c.summary = "A progn-expression executes a series of expressions sequentially.";
        c.addSyntax(0, "$progn$ ( <i>$argument$<sub>1</sub></i> , ... , <i>$argument$<sub>n</sub></i> )");
        returns(c, "type of <i>$argument$<sub>n</sub></i>", "the value produced by the last argument");
        c.addCheck(ErrorCode.EMPTY_PROGN, "There must be at least one element in the sequence.");
        expression(c, "argument", false);
        c.addExample(EXAMPLE_1, 87);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "List Expression";
        c.klass = ListExpression.class;
        c.summary = "A list-expression creates a new mutable list from a list of expressions.";
        c.addSyntax(0, "[ <i>$value$<sub>1</sub></i> , <i>$value$<sub>2</sub></i> , ... , <i>$value$<sub>n</sub></i> ]");
        c.addDetail(0, "The values of the elements will be boxed when necessary.");
        returns(c, List.class, "a new mutable list object is returned that contains the values of the arguments");
        expression(c, "element", false);
        c.addExample(EXAMPLE_1, 40);
        c.addExample(EXAMPLE_2, 41);
        Index.add(c);

        // Finished!
        c = Construct.newInstance();
        c.name = "List Comprehension Expression";
        c.klass = ListComprehensionExpression.class;
        c.summary = "A list-comprehension creates a new mutable list based on another iterable data-structure.";
        c.addSyntax(0, "[ <i>$modifier$</i> $for$ $assignee$ : $type$ in $iterable$ ]");
        c.addSyntaxHR();
        c.addSyntax(0, "[ <i>$modifier$</i> $for$ $assignee$ : $type$ in $iterable$ $if$ $condition$ ]");
        c.addDetail(0, "Algorithm:");
        c.addDetail(1, "Let <i>L</i> be a new $JavaUtilLinkedList$.");
        c.addDetail(1, "Let <i>E</i> denote the value produced by evaluating <i>iterable</i>.");
        c.addDetail(1, "If <i>E</i> is null, throw a $JavaLangNullPointerException$.");
        c.addDetail(1, "For each value <i>X</i> in <i>E</i> do:");
        c.addDetail(2, "Let <i>T</i> be the type specified by <i>type</i>.");
        c.addDetail(2, "If <i>X</i> cannot be cast to <i>T</i>, throw a $JavaLangClassCastException$.");
        c.addDetail(2, "Let <i>V</i> be the local variable specified by <i>assignee</i>.");
        c.addDetail(2, "Assign <i>X</i> to <i>V</i>.");
        c.addDetail(2, "If there is a <i>condition</i> then:");
        c.addDetail(3, "Let <i>C</i> be the value produced by evaluating the condition.");
        c.addDetail(3, "Unbox <i>C</i>, if necessary.");
        c.addDetail(3, "If <i>C</i> is false, then immediately start the next iteration of the loop.");
        c.addDetail(2, "Let <i>M</i> be the value produced by evaluating the <i>modifier</i>.");
        c.addDetail(2, "Box <i>M</i>, if necessary.");
        c.addDetail(2, "Append <i>M</i> to <i>L</i>.");
        c.addDetail(1, "Return <i>L</i>.");
        expression(c, "modifier", true);
        variables(c, "assignee");
        c.addDetail(0, "The <i>assignee</i> is a readonly variable.");
        usetype(c, "<i>type</i>");
        c.addCheck(ErrorCode.EXPECTED_REFERENCE_TYPE, "The type specified by the <i>type</i> must be a reference-type.");
        c.addCheck(ErrorCode.EXPECTED_ITERABLE, "The type of the <i>iterable</i> must be a subtype of $JavaLangIterable$.");
        condition(c);
        returns(c, List.class, "a new mutable linked-list data-structure.");
        c.addExample(EXAMPLE_1, 89);
        c.addExample(EXAMPLE_2, 90);
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Components
        ////////////////////////////////////////////////////////////////////////////////////////////

        --Index.indent;
        --Index.indent;
        Index.add("<b>Components</b>");
        ++Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Annotations
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Annotation List";
        c.klass = AnnotationList.class;
        c.summary = "An annotation-list is a set of annotations.";
        c.addSyntax(0, "<i>$annotation$<sub>1</sub></i>");
        c.addSyntax(0, "<i>$annotation$<sub>2</sub></i>");
        c.addSyntax(0, "<i>$annotation$<sub>n</sub></i>");
        c.addCheck(ErrorCode.DUPLICATE_ANNOTATION, "An annotation can oly appear once in a single annotation-list.");
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Annotation";
        c.klass = Annotation.class;
        c.summary = "An annotation is a metadata applied to code.";
        c.addSyntax(0, "@<i>$type$</i>");
        usetype(c, "<i>type</i>");
        c.addCheck(ErrorCode.EXPECTED_ANNOTATION, "The <i>type</i> must be an annotation-type.");
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Element Lists
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Element List";
        c.klass = ElementList.class;
        c.summary = "An element-list is a list of elements.";
        c.addSyntax(0, "( <i>$element$<sub>1</sub></i> , ... , <i>$element$<sub>n</sub></i> )");
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Element";
        c.klass = Element.class;
        c.summary = "An element declares an entry in a user-defined data-type.";
        c.addSyntax(0, "<i>$name$</i> : <i>$type$</i>");
        usetype(c, "<i>type</i>");
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Formal Parameters
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Formal Parameter List";
        c.klass = FormalParameterList.class;
        c.summary = "A formal-parameter-list is a list of formal-parameters.";
        c.addSyntax(0, "( <i>$formal$<sub>1</sub></i> , ... , <i>$formal$<sub>n</sub></i> )");
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Formal Parameter";
        c.klass = FormalParameter.class;
        c.summary = "A formal-parameter is an explicitly typed variable declaration.";
        c.addSyntax(0, "<i>$variable$</i> : <i>$type$</i>");
        variables(c, "variable");
        usetype(c, "<i>type</i>");
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Other Components
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Name";
        c.klass = Name.class;
        c.summary = "A name construct is used to represent a name that does not denote a type.";
        c.addSyntax(0, "<i>name</i>");
        c.addDetail(0, "A <i>name</i> is a sequence of letters, digits, underscores, and/or dollar signs.");
        c.addDetail(0, "A <i>name</i> cannot start with a digit.");
        c.addDetail(0, "A <i>name</i> is case-sensitive.");
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Namespace";
        c.klass = Namespace.class;
        c.summary = "A namespace construct is used to represent a package.";
        c.addSyntax(0, "<i></i>");
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Type Specifier";
        c.klass = TypeSpecifier.class;
        c.summary = "A type-specifier specifies a type by its name and dimensions.";
        c.addSyntax(0, "");
        c.addDetail(0, "If the number of dimensions is non-zero, then an array-type is specified.");
        c.addDetail(0, "One cannot specify the null-type using a type-specifier.");
        //
        c.addDetail(0, "Automatically Imported Types:");
        //
        usetype(c, "<i>type</i>");
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Documentation Comments
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Doc Comment";
        c.klass = FormalParameter.class; // TODO
        c.summary = "A doc-comment is a comment that can be processed by documentation generators.";
        c.addSyntax(0, "<i>$DocLine$</i>");
        c.addSyntax(0, "<i>$DocLine$</i>");
        c.addSyntax(0, "<i>$DocLine$</i>");
        Index.add(c);

        c = Construct.newInstance();
        c.name = "Doc Comment Line";
        c.klass = FormalParameter.class; // TODO
        c.summary = "A doc-comment-line that is a single line in a doc-comment.";
        c.addSyntax(0, "/// <i>text</i>");
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Regular Comments
        ////////////////////////////////////////////////////////////////////////////////////////////

        c = Construct.newInstance();
        c.name = "Comments";
        c.klass = TypeSpecifier.class; // TODO
        c.summary = "Comments allow programmers to leave notes inside of code.";
        c.addSyntax(0, "<b>//&nbsp;&nbsp;&nbsp;</b><i>body of single-line comment</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>#&nbsp;&nbsp;&nbsp;</b><i>body of single-line comment</i>");
        c.addSyntaxHR();
        c.addSyntax(0, "<b>/*&nbsp;&nbsp;&nbsp;</b><i>body of multi-line comment</i><b>&nbsp;&nbsp;&nbsp;*/</b>");
        c.addDetail(0, "Comments are simply ignored by the compiler.");
        Index.add(c);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Pages
        ////////////////////////////////////////////////////////////////////////////////////////////

        Index.indent = 0;

        Index.add("<b>Type System</b>");
        ++Index.indent;

        p = Page.newInstance();
        p.name = "Type Structure";
        p.markdown = new File("Type Structure.md");
        Index.add(p);

        p = Page.newInstance();
        p.name = "Type Conversions and Assignability";
        p.markdown = new File("Type Conversions.md");
        Index.add(p);

        p = Page.newInstance();
        p.name = "Special Names";
        p.markdown = new File("Names.md");
        Index.add(p);

        p = Page.newInstance();
        p.name = "Accessibility";
        p.markdown = new File("Accessibility.md");
        Index.add(p);

        p = Page.newInstance();
        p.name = "Resolution";
        p.markdown = new File("Resolution.md");
        Index.add(p);

        Index.add("Examples of Type Errors", "TypeCheckingExamplesIndexPage.html");

        --Index.indent;

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Hidden Pages
        ////////////////////////////////////////////////////////////////////////////////////////////

        p = Page.newInstance();
        p.name = "Generator Functions";
        p.markdown = new File("functions/Generator Functions.md");

        p = Page.newInstance();
        p.name = "Infer Functions";
        p.markdown = new File("functions/Infer Functions.md");

        p = Page.newInstance();
        p.name = "Memoized Functions";
        p.markdown = new File("functions/Memoized Functions.md");

        p = Page.newInstance();
        p.name = "Start Functions";
        p.markdown = new File("functions/Start Functions.md");

        p = Page.newInstance();
        p.name = "Setup Functions";
        p.markdown = new File("functions/Setup Functions.md");

        p = Page.newInstance();
        p.name = "Sync Functions";
        p.markdown = new File("functions/Sync Functions.md");

        p = Page.newInstance();
        p.name = "Test Functions";
        p.markdown = new File("functions/Test Functions.md");

        p = Page.newInstance();
        p.name = "Module Member";
        p.markdown = new File("syntax/Module Member.md");

        p = Page.newInstance();
        p.name = "Expression";
        p.markdown = new File("syntax/Expression.md");

        p = Page.newInstance();
        p.name = "Statement";
        p.markdown = new File("syntax/Statement.md");
    }

    private static void expression(final Construct c,
                                   final String expression,
                                   final boolean singular)
    {
        String message = "The type of $XX$ <i>$YY$</i> must be either a primitive-type or a reference-type.";

        message = message.replace("$XX$", singular ? "the" : "each");

        message = message.replace("$YY$", expression);

        c.addCheck(ErrorCode.VALUE_REQUIRED, message);
    }

    private static void variables(final Construct c,
                                  final String alias)
    {
        // TODO: discuss scoping better
        //       A variable is really only in scope after its initialization site.

        c.addDetail(0, "The scope of the <i>$variable$</i> is anywhere in the enclosing function.".replace("$variable$", alias));
        c.addDetail(0, "The <i>$variable$</i> is alive precisely during an activation of the enclosing function.".replace("$variable$", alias));
        c.addCheck(ErrorCode.DUPLICATE_VARIABLE, "The <i>$variable$</i> cannot share its name with another variable declared in the same scope.".replace("$variable$", alias));
    }

    private static void annotationList(Construct c)
    {
        c.addSyntax(0, "@<i>annotation<sub>1</sub></i>");
        c.addSyntax(0, "@<i>annotation<sub>2</sub></i>");
        c.addSyntax(0, "@<i>annotation<sub>n</sub></i>");

        c.addCheck(ErrorCode.DUPLICATE_ANNOTATION, "Each annotation in an annotation-list must be uniquely typed.");
    }

    private static void greedyOperands(final Construct c)
    {
        c.addDetail(0, "Both operands are greedily evaluated.");
        c.addDetail(1, "The left-operand is evaluated first.");
        c.addDetail(1, "The right-operand is evaluated second.");
    }

    private static void binaryoperator(final Construct c)
    {
        greedyOperands(c);

        c.addCheck(ErrorCode.NO_SUCH_BINARY_OPERATOR, "None of the overloads will accept the left-operand due to its type.");
        c.addCheck(ErrorCode.NO_SUCH_BINARY_OPERATOR, "None of the overloads will accept the right-operand due to its type.");
    }

    private static void arithmeticOverloads(final Construct c,
                                            final String operation)
    {
        final String op = operation;

        c.addDetail(0, "Predefined Overloads:");
        c.addDetail(1, "(char + char) &$8614; char".replace("+", op));
        c.addDetail(1, "(byte + byte) &$8614; byte".replace("+", op));
        c.addDetail(1, "(short + short) &$8614; short".replace("+", op));
        c.addDetail(1, "(int + int) &$8614; int".replace("+", op));
        c.addDetail(1, "(long + long) &$8614; long".replace("+", op));
        c.addDetail(1, "(float + float) &$8614; float".replace("+", op));
        c.addDetail(1, "(double + double) &$8614; double".replace("+", op));
        c.addDetail(1, "($JavaMathBigInteger$ + $JavaMathBigInteger$) &$8614; $JavaMathBigInteger$".replace("+", op));
        c.addDetail(1, "($JavaMathBigDecimal$ + $JavaMathBigDecimal$) &$8614; $JavaMathBigDecimal$".replace("+", op));

        c.addDetail(0, "The overload that best matches the operands will be selected.");
        c.addDetail(1, "Unboxing will be performed, if necessary.");
        c.addDetail(1, "Coercion will be performed, if necessary.");
    }

    private static void equalityOverloads(final Construct c,
                                          final String operation)
    {
        c.addDetail(0, "Predefined Overloads:");
        c.addDetail(1, "(boolean + boolean) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(char + char) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(byte + byte) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(short + short) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(int + int) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(long + long) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(float + float) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(double + double) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "($JavaMathBigInteger$ + $JavaMathBigInteger$) &$8614; $JavaMathBigInteger$".replace("+", operation));
        c.addDetail(1, "($JavaMathBigDecimal$ + $JavaMathBigDecimal$) &$8614; $JavaMathBigDecimal$".replace("+", operation));
        c.addDetail(1, "($JavaLangObject$ + $JavaLangObject$) &$8614; boolean".replace("+", operation));

        c.addDetail(0, "The overload that best matches the operands will be selected.");
        c.addDetail(1, "Boxing will be performed, if necessary.");
        c.addDetail(1, "Unboxing will be performed, if necessary.");
        c.addDetail(1, "Coercion will be performed, if necessary.");
    }

    private static void comparisonOverloads(final Construct c,
                                            final String operation)
    {
        c.addDetail(0, "Predefined Overloads:");
        c.addDetail(1, "(boolean + boolean) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(char + char) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(byte + byte) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(short + short) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(int + int) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(long + long) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(float + float) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "(double + double) &$8614; boolean".replace("+", operation));
        c.addDetail(1, "($JavaLangComparable$ + $JavaLangComparable$) &$8614; boolean".replace("+", operation));

        c.addDetail(0, "The overload that best matches the operands will be selected.");
        c.addDetail(1, "Boxing will be performed, if necessary.");
        c.addDetail(1, "Unboxing will be performed, if necessary.");
        c.addDetail(1, "Coercion will be performed, if necessary.");
    }

    private static void logicalOverloads(final Construct c,
                                         final String operation)
    {
        c.addDetail(0, "Predefined Overload:");
        c.addDetail(1, "(boolean + boolean) &$8614; boolean".replace("+", operation));

        c.addDetail(0, "Unboxing will be performed, if necessary.");
    }

    private static void condition(final Construct c)
    {
        unbox(c, "condition");

        expectType(c, ErrorCode.EXPECTED_CONDITION, "<i>condition</i>", boolean.class);
    }

    private static void condition(final Construct c,
                                  final int example)
    {
        unbox(c, "condition");

        expectType(c, ErrorCode.EXPECTED_CONDITION, "<i>condition</i>", boolean.class, example);
    }

    private static void conditions(final Construct c)
    {
        c.addCheck(ErrorCode.EXPECTED_CONDITION, "The type of each condition must be assignable to primitive-type boolean.");
    }

    private static void usetype(final Construct c,
                                final String part)
    {
        c.addCheck(ErrorCode.NO_SUCH_TYPE, " The type specified by <i>" + part + "</i> must exist.");
        c.addCheck(ErrorCode.INACCESSIBLE_TYPE, "The type specified by <i>" + part + "</i> must be accessible.");
    }

    private static void expectType(final Construct c,
                                   final ErrorCode code,
                                   final String part,
                                   final Class type)
    {
        c.addCheck(code, "The type of <i>" + part + "</i> must be assignable to type " + type.getName() + ".");
    }

    private static void expectType(final Construct c,
                                   final ErrorCode code,
                                   final String part,
                                   final Class type,
                                   final int example)
    {
        c.addCheck(code, "The type of <i>" + part + "</i> must be assignable to type " + type.getName() + ".", example);
    }

    private static void loop(final Construct c)
    {
        c.addDetail(0, "The <i>body</i> of a loop can contain break-statements.");
        c.addDetail(0, "The <i>body</i> of a loop can contain continue-statements.");
        c.addDetail(0, "The <i>body</i> of a loop can contain redo-statements.");
    }

    private static void returns(final Construct c,
                                final String return_type,
                                final String return_value)
    {
        c.addDetail(0, "Return Type: " + return_type);
        c.addDetail(0, "Return " + return_value + '.');
    }

    private static void returns(final Construct c,
                                final Class return_type,
                                final String return_value)
    {
        c.addDetail(0, "Return Type: " + JSONBuilder.link(return_type));
        c.addDetail(0, "Return " + return_value + '.');
    }

    private static void typedec(final Construct c)
    {
        c.addCheck(ErrorCode.DUPLICATE_TYPE, "No two types can share the same descriptor.");
    }

    private static void conversions(final Construct c)
    {
        int indent = 0;

        c.addDetail(indent, "Predefined Conversions");
        ++indent;

        c.addDetail(indent, "Boxing Conversions");
        ++indent;
        c.addDetail(indent, "boolean => Boolean");
        c.addDetail(indent, "char => Character");
        c.addDetail(indent, "byte => Byte");
        c.addDetail(indent, "short => Short");
        c.addDetail(indent, "int => Integer");
        c.addDetail(indent, "long => Long");
        c.addDetail(indent, "float => Float");
        c.addDetail(indent, "double => Double");

        c.addDetail(indent, "boolean => Object");
        c.addDetail(indent, "char => Object");
        c.addDetail(indent, "byte => Object");
        c.addDetail(indent, "short => Object");
        c.addDetail(indent, "int => Object");
        c.addDetail(indent, "long => Object");
        c.addDetail(indent, "float => Object");
        c.addDetail(indent, "double => Object");
        --indent;

        c.addDetail(indent, "Unboxing Conversions");
        ++indent;
        c.addDetail(indent, "Boolean => boolean");
        c.addDetail(indent, "Character => char");
        c.addDetail(indent, "Byte => byte");
        c.addDetail(indent, "Short => short");
        c.addDetail(indent, "Integer => int");
        c.addDetail(indent, "Long => long");
        c.addDetail(indent, "Float => float");
        c.addDetail(indent, "Double => double");
        --indent;

        c.addDetail(indent, "To String Conversions");
        ++indent;
        c.addDetail(indent, "boolean => String");
        c.addDetail(indent, "char => String");
        c.addDetail(indent, "byte => String");
        c.addDetail(indent, "short => String");
        c.addDetail(indent, "int => String");
        c.addDetail(indent, "long => String");
        c.addDetail(indent, "float => String");
        c.addDetail(indent, "double => String");
        c.addDetail(indent, "Object => String");
        --indent;

        c.addDetail(indent, "Primitive To Primitive Conversions");
        ++indent;
        c.addDetail(indent, "boolean => boolean");
        c.addDetail(indent, "boolean => char");
        c.addDetail(indent, "boolean => byte");
        c.addDetail(indent, "boolean => short");
        c.addDetail(indent, "boolean => int");
        c.addDetail(indent, "boolean => long");
        c.addDetail(indent, "boolean => float");
        c.addDetail(indent, "boolean => double");
        c.addDetail(indent, "char => boolean");
        c.addDetail(indent, "char => char");
        c.addDetail(indent, "char => byte");
        c.addDetail(indent, "char => short");
        c.addDetail(indent, "char => int");
        c.addDetail(indent, "char => long");
        c.addDetail(indent, "char => float");
        c.addDetail(indent, "char => double");
        c.addDetail(indent, "byte => boolean");
        c.addDetail(indent, "byte => char");
        c.addDetail(indent, "byte => byte");
        c.addDetail(indent, "byte => short");
        c.addDetail(indent, "byte => int");
        c.addDetail(indent, "byte => long");
        c.addDetail(indent, "byte => float");
        c.addDetail(indent, "byte => double");
        c.addDetail(indent, "short => boolean");
        c.addDetail(indent, "short => char");
        c.addDetail(indent, "short => byte");
        c.addDetail(indent, "short => short");
        c.addDetail(indent, "short => int");
        c.addDetail(indent, "short => long");
        c.addDetail(indent, "short => float");
        c.addDetail(indent, "short => double");
        c.addDetail(indent, "int => boolean");
        c.addDetail(indent, "int => char");
        c.addDetail(indent, "int => byte");
        c.addDetail(indent, "int => short");
        c.addDetail(indent, "int => int");
        c.addDetail(indent, "int => long");
        c.addDetail(indent, "int => float");
        c.addDetail(indent, "int => double");
        c.addDetail(indent, "long => boolean");
        c.addDetail(indent, "long => char");
        c.addDetail(indent, "long => byte");
        c.addDetail(indent, "long => short");
        c.addDetail(indent, "long => int");
        c.addDetail(indent, "long => long");
        c.addDetail(indent, "long => float");
        c.addDetail(indent, "long => double");
        c.addDetail(indent, "float => boolean");
        c.addDetail(indent, "float => char");
        c.addDetail(indent, "float => byte");
        c.addDetail(indent, "float => short");
        c.addDetail(indent, "float => int");
        c.addDetail(indent, "float => long");
        c.addDetail(indent, "float => float");
        c.addDetail(indent, "float => double");
        c.addDetail(indent, "double => boolean");
        c.addDetail(indent, "double => char");
        c.addDetail(indent, "double => byte");
        c.addDetail(indent, "double => short");
        c.addDetail(indent, "double => int");
        c.addDetail(indent, "double => long");
        c.addDetail(indent, "double => float");
        c.addDetail(indent, "double => double");
        --indent;


        c.addDetail(0, "Resolution of a Predefined Conversion (X => T):");
        c.addDetail(1, "Sort the predefined conversions from most specific to most abstract.");
        c.addDetail(1, "Remove the conversions where the output is not type T.");
        c.addDetail(1, "For each remaining conversion C:");
        c.addDetail(2, "If X is a subtype of the input type of C, then C is the conversion to select.");
        c.addDetail(1, "No predefined conversion was found, so the conversion is a cast.");


        c.addDetail(0, "A cast (X => T) is impossible when both:");
        c.addDetail(1, "X is not a supertype of T");
        c.addDetail(1, "X is not a subtype of T");


        c.addCheck(ErrorCode.IMPOSSIBLE_CONVERSION, "The conversion must be either predefined or a valid cast.");
    }

    private static void predefinedImports(final Construct c)
            throws ClassNotFoundException
    {
        final Importer importer = new Importer(null);

        c.addDetail(0, "<b>Predefined Imports:</b>");

        for (String type : importer.imported())
        {
            final Class klass = Class.forName(type);

            c.addDetail(1, JSONBuilder.link(klass));
        }
    }

    private static void unbox(final Construct c,
                              final String part)
    {
        c.addDetail(0, "The <i>part</i> will be unboxed, if necessary.".replace("part", part));
    }

    private static void typesOfLoops(final Construct c)
    {
        c.addDetail(0, "Types of Loops:");
        c.addDetail(1, "$Forever Statement$s");
        c.addDetail(1, "$While Statement$s");
        c.addDetail(1, "$Until Statement$s");
        c.addDetail(1, "$Do-While Statement$s");
        c.addDetail(1, "$Do-Until Statement$s");
        c.addDetail(1, "$For Statement$s");
        c.addDetail(1, "$Foreach Statement$s");
    }

    private static void relatedBoxedType(final Construct c,
                                         final Class type)
    {
        c.addDetail(0, "Related Boxed Type: " + type.getName());
    }

    private static void specialAnnotation(final Construct c,
                                          final Class annotation)
    {
        c.addDetail(0, "The " + JSONBuilder.link(annotation) + " annotation is automatically applied.");
    }

    /**
     * This method appends links to inherited methods onto
     * the list of details regarding a construct.
     *
     * @param c is the construct.
     * @param indent is the indentation level to use for each detail.
     * @param klass is the most-specific type that declares the methods.
     */
    private static void inheritMethods(final Construct c,
                                       final int indent,
                                       final Class klass)
    {
        /**
         * Using a TreeMap allows the methods to be sorted.
         */
        final Map<String, String> methods = Maps.newTreeMap();

        /**
         * Find the inherited methods.
         */
        for (Method method : klass.getMethods())
        {
            final String value = JSONBuilder.link(method);

            final String key = method.getName() + ":::" + value;

            methods.put(key, value);
        }

        /**
         * Add the the methods to the list of details.
         */
        for (String method : methods.values())
        {
            c.addDetail(indent, method);
        }
    }
}
