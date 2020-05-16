 var contactNgUtil = (function () {
     // 'use strict';
     // IIFE

     var sexes = [{
             value: 'MALE'
            },
         {
             value: 'FEMALE'
            },
         {
             value: 'UNKNOWN'
            }];


     return {

         getSexes: function () {
             return sexes;
         }

     }
     // END IIFE
 })();