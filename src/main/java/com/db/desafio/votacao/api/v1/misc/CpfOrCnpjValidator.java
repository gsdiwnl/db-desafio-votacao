/**
 * Filename:    CpfOrCnpjValidator.java
 *
 * Description: Implementation of the CpfOrCnpjValidator class.
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
package com.db.desafio.votacao.api.v1.misc;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import com.db.desafio.votacao.api.v1.misc.annotations.CpfOrCnpj;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator
    implements
        ConstraintValidator<CpfOrCnpj, String> 
{
    /**
     * isValid
     * 
     * @param value String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
	@Override
	public boolean isValid( String value, ConstraintValidatorContext context ) 
    {
		if( value == null ) 
        {
			return true;
		}

        if( value.length() == 11 )
        {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.initialize( null );
    
            return cpfValidator.isValid( value, context );
        }
        else if( value.length() == 14 )
        {
            CNPJValidator cnpjValidator = new CNPJValidator();
    
            cnpjValidator.initialize( null );
    
            return cnpjValidator.isValid( value, context );
        }

        return false;
	}
}