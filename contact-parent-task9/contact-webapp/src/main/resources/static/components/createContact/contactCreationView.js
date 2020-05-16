(function () {
    'use strict';
    //IIFE

    function ContactCreationViewController(baseParentComponentController) {
        var ctrl = this;
        angular.extend(ContactCreationViewController.prototype, baseParentComponentController);
        
        ctrl.contactAdded = function (createdContact) {
        	ctrl.displaySuccessMsg("Contact with id " + createdContact.id + " added.");
        }
    }

    angular.module('myContactApp').component('contactCreationView', {
        templateUrl: 'components/createContact/contactCreationView.html?' + new Date(),
        controller: ContactCreationViewController
    });


    //END IIFE
})();