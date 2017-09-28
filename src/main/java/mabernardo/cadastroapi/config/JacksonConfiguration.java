package mabernardo.cadastroapi.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

@Configuration
public class JacksonConfiguration {

    /*
     * Suporte para tipos Hibernate no Jackson.
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /*
     * M&oacute;dulo Jackson Afterburner para aumentar a velocidade de serializa&ccedil;&atilde;o/desserializa&ccedil;&atilde;o.
     */
    @Bean
    public AfterburnerModule afterburnerModule() {
        return new AfterburnerModule();
    }

    /*
     * M&oacute;dulo para serializa&ccedil;&atilde;o/desserializa&ccedil;&atilde;o do problema RFC7807.
     */
    @Bean
    ProblemModule problemModule() {
        return new ProblemModule();
    }

    /*
     * M&oacute;dulo para serializa&ccedil;&atilde;o/desserializa&ccedil;&atilde;o do ConstraintViolationProblem.
     */
    @Bean
    ConstraintViolationProblemModule constraintViolationProblemModule() {
        return new ConstraintViolationProblemModule();
    }

}
