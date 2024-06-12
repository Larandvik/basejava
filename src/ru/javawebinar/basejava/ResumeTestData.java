package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;

public class ResumeTestData {
    private static Resume resumeTest;

    public static Resume getResumeTest(String uuid, String fullName) {
        resumeTest = new Resume(uuid, fullName);
        resumeTest.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resumeTest.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resumeTest.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
//        resumeTest.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin/");
//        resumeTest.addContact(ContactType.GITHUB, "https://github.com/gkislin");
//        resumeTest.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
//        resumeTest.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
//        resumeTest.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
//                "по Java Web и Enterprise технологиям"));
//        resumeTest.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
//                "креативность, инициативность. Пурист кода и архитектуры."));
//        resumeTest.addSection(SectionType.ACHIEVEMENT, new ListSection("""
//                Организация команды и успешная реализация Java проектов для
//                сторонних заказчиков: приложения автопарк на стеке Spring Cloud/
//                микросервисы, система мониторинга показателей спортсменов на
//                Spring Boot, участие в проекте МЭШ на Play-2, многомодульный
//                Spring Boot + Vaadin проект для комплексных DIY смет
//                """, """
//                С 2013 года: разработка проектов "Разработка Web
//                приложения","Java Enterprise", "Многомодульный maven.
//                Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP).
//                Удаленное взаимодействие (JMS/AKKA)". Организация онлайн
//                стажировок и ведение проектов. Более 3500 выпускников.
//                """, """
//                Реализация двухфакторной аутентификации для онлайн платформы
//                управления проектами Wrike. Интеграция с Twilio, DuoSecurity,
//                Google Authenticator, Jira, Zendesk.
//                """, """
//                Налаживание процесса разработки и непрерывной интеграции ERP
//                системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP.
//                Разработка приложения управления окружением на стеке:
//                Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и
//                авторизации различных ERP модулей, интеграция CIFS/SMB java
//                сервера.
//                """, """
//                Реализация c нуля Rich Internet Application приложения на стеке
//                технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet,
//                HTML5, Highstock для алгоритмического трейдинга.
//                """, """
//                Создание JavaEE фреймворка для отказоустойчивого
//                взаимодействия слабо-связанных сервисов (SOA-base архитектура,
//                JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о
//                состоянии через систему мониторинга Nagios. Реализация онлайн
//                клиента для администрирования и мониторинга системы по JMX
//                (Jython/ Django).
//                """, """
//                Реализация протоколов по приему платежей всех основных
//                платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк),
//                Белоруcсии(Erip, Osmp) и Никарагуа."""
//        ));
//        resumeTest.addSection(SectionType.QUALIFICATIONS, new ListSection(
//                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
//                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
//                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2,",
//                "Oracle, MySQL, SQLite, MS SQL, HSQLDB",
//                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
//                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts", """
//                Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor,
//                MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate,
//                EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports,
//                Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).""",
//                "Python: Django.",
//                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
//                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka", """
//                Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB,
//                StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX,
//                Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.""",
//                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix", """
//                администрирование Hudson/Jenkins, Ant + custom task, SoapUI,
//                JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer""",
//                """
//                        Отличное знание и опыт применения концепций ООП, SOA,
//                        шаблонов проектрирования, архитектурных шаблонов, UML,
//                        функционального программирования
//                        """,
//                "Родной русский, английский \"upper intermediate\""
//        ));
//        Period periodAlcatel = new Period(2001, Month.SEPTEMBER, 2005, Month.JANUARY,
//                "Инженер по аппаратному и программному тестированию",
//                "Тестирование, отладка, внедрение ПО цифровой телефонной станции " +
//                        "Alcatel 1000 S12 (CHILL, ASM).");
//
//        Company Alcatel = new Company("Alcatel", "http://www.alcatel.ru/", periodAlcatel);
//        CompanySection experience = new CompanySection();
//        experience.addCompanies(Alcatel);
//        resumeTest.addSection(SectionType.EXPERIENCE, experience);
//
//        Period periodItmo1 = new Period(1987, Month.SEPTEMBER, 1993, Month.JUNE,
//                "Инженер (программист Fortran, C)", null);
//        Period periodItmo2 = new Period(1993, Month.SEPTEMBER, 1996, Month.JULY,
//                "Аспирантура (программист С, С++)", null);
//        Company itmo = new Company("Санкт-Петербургский национальный исследовательский университет " +
//                "информационных технологий, механики и оптики", "https://itmo.ru/",
//                periodItmo1);
//        itmo.addPeriod(periodItmo2);
//
//        CompanySection education = new CompanySection();
//        education.addCompanies(itmo);
//        resumeTest.addSection(SectionType.EDUCATION, education);
        return resumeTest;
    }

    public static void main(String[] args) {
        printInfo(resumeTest);
    }

    private static void printInfo(Resume resumeTest) {
        System.out.println(resumeTest.getFullName());
        System.out.println(resumeTest.getContact(ContactType.PHONE));
        System.out.println(resumeTest.getContact(ContactType.SKYPE));
        System.out.println(resumeTest.getContact(ContactType.EMAIL));
        System.out.println(resumeTest.getContact(ContactType.LINKEDIN));
        System.out.println(resumeTest.getContact(ContactType.GITHUB));
        System.out.println(resumeTest.getContact(ContactType.STACKOVERFLOW));
        System.out.println(resumeTest.getContact(ContactType.HOMEPAGE));
        System.out.println(SectionType.OBJECTIVE + ": \n" + resumeTest.getSections(SectionType.OBJECTIVE));
        System.out.println(SectionType.PERSONAL + ": \n" + resumeTest.getSections(SectionType.PERSONAL));
        System.out.println(SectionType.ACHIEVEMENT + ": \n" + resumeTest.getSections(SectionType.ACHIEVEMENT));
        System.out.println(SectionType.QUALIFICATIONS + ": \n" + resumeTest.getSections(SectionType.QUALIFICATIONS));
        System.out.println(SectionType.EXPERIENCE + ": \n" + resumeTest.getSections(SectionType.EXPERIENCE));
        System.out.println(SectionType.EDUCATION + ": \n" + resumeTest.getSections(SectionType.EDUCATION));
    }
}
