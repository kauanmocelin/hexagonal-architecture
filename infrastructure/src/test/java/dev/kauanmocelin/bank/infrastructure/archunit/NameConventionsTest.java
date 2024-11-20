package dev.kauanmocelin.bank.infrastructure.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "dev.kauanmocelin.bank", importOptions = ImportOption.DoNotIncludeTests.class)
public class NameConventionsTest {

    @ArchTest
    static final ArchRule springBootApplicationStartupShouldHaveSpecificName = classes()
        .that()
        .areAnnotatedWith(SpringBootApplication.class)
        .should().haveSimpleName("SpringAppConfig");

    @ArchTest
    static final ArchRule controllerShouldBeSuffixedWithController = classes()
        .that()
        .resideInAPackage("..controller..")
        .or().areAnnotatedWith(RestController.class)
        .should().haveSimpleNameEndingWith("Controller")
        .allowEmptyShould(false);

    @ArchTest
    static final ArchRule inputPortsShouldBeSuffixedWithUseCaseOrQuery = classes().that()
        .resideInAPackage("..application.port.input..")
        .should().haveSimpleNameEndingWith ("UseCase")
        .orShould().haveSimpleNameEndingWith("Query")
        .allowEmptyShould(false);
}
