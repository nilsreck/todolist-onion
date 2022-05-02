package todolist.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import todolist.TodolistSpringApplication;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packagesOf = TodolistSpringApplication.class)
public class ArchitectureTest {

    @ArchTest
    ArchRule onionTest = onionArchitecture()
        .domainModels("todolist.domain..")
        .domainServices("todolist.domain..")
        .applicationServices("todolist.appservices..")
        .adapter("web", "todolist.web..")
        .adapter("persistence", "todolist.persistence..")
        .adapter("infrastructure", "todolist..");
}
