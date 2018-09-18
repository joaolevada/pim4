/**
 * Alias do Jquery
 */

const doc = jQuery.noConflict(true);

/**
*  Appdoc é um atalho para adicionar eventos aos elementos do container #app
*  O container que as views são injetadas .
* @example 
*   Appdoc.on(event, element , function); 
*/
const Appdoc = $('#app');

export {
    doc,
    Appdoc
}
