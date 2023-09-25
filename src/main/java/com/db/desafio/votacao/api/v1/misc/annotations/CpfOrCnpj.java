/**
 * Filename:    CpfOrCnpj.java
 *
 * Description: Implementation of the CpfOrCnpj class.
 *
 * Revision:    1.0
 *
 * Changed by:  gd
 *
 * Author:      Gabriel Dullius
 * Email:       dulliusgabriel@gmail.com
 *
 * Copyright of the computer program(s) contained herein
 * is of intellectual property of the studying for the voting challenge by DB.
 * The program(s) may be used and/or copied with permission.
 * Challenge: https://github.com/dbserver/desafio-votacao
 *
 */
package com.db.desafio.votacao.api.v1.misc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.db.desafio.votacao.api.v1.misc.CpfOrCnpjValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint( validatedBy = CpfOrCnpjValidator.class )
@Target({ ElementType.FIELD })
@Retention( RetentionPolicy.RUNTIME )
public @interface CpfOrCnpj 
{
    String message() default "CPF ou CNPJ inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
