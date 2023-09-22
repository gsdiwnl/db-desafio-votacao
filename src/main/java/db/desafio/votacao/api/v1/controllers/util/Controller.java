/**
 * Filename:    Controller.java
 *
 * Description: Implementation of the Controller class.
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
package db.desafio.votacao.api.v1.controllers.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Controller
{
    protected static final String VERSION = "v1/";
    
    /** 
     * ok
     * 
     * @return ResponseEntity<T>
     */
    protected <T> ResponseEntity<T> ok() 
    {
        return new ResponseEntity<>( HttpStatus.OK );
    }
   
    /** 
     * ok
     * 
     * @param body T
     * @return ResponseEntity<T>
     */
    protected <T> ResponseEntity<T> ok( T body )
    {
        return new ResponseEntity<>( body, HttpStatus.OK );
    }

    /** 
     * created
     * 
     * @return ResponseEntity<T>
     */
    protected <T> ResponseEntity<T> created() 
    {
        return new ResponseEntity<>( HttpStatus.CREATED );
    }
    
    /** 
     * created
     * 
     * @param body T
     * @return ResponseEntity<T>
     */
    protected <T> ResponseEntity<T> created( T body )
    {
        return new ResponseEntity<>( body, HttpStatus.CREATED );
    }
    
    /** 
     * noContent
     * 
     * @return ResponseEntity<T>
     */
    protected <T> ResponseEntity<T> noContent() 
    {
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}