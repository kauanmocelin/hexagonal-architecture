package dev.kauanmocelin.bank.infrastructure.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "dev.kauanmocelin.bank", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    @ArchTest
    static final ArchRule enforceLayeredArchitecture = layeredArchitecture()
        .consideringOnlyDependenciesInLayers()
        .layer("domain").definedBy("dev.kauanmocelin.bank.domain..")
        .layer("application").definedBy("dev.kauanmocelin.bank.application..")
        .layer("adapter").definedBy("dev.kauanmocelin.bank.adapter..")
        .layer("infrastructure").definedBy("dev.kauanmocelin.bank.infrastructure..")
        .whereLayer("domain").mayNotAccessAnyLayer()
        .whereLayer("domain").mayOnlyBeAccessedByLayers("application", "adapter", "infrastructure")
        .whereLayer("application").mayOnlyBeAccessedByLayers("adapter", "infrastructure")
        .whereLayer("adapter").mayOnlyBeAccessedByLayers("infrastructure");
}
