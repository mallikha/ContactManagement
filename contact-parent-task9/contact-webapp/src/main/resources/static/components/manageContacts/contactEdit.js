(function () {
    'use strict';
    //IIFE


    function ContactEditController($scope, contactService) {
        var ctrl = this;
        ctrl.sexes = contactNgUtil.getSexes();
        
        // dynamic properties for the common template
        ctrl.title = 'Contact Edit';
        ctrl.btnFormValidationLbl = 'Update';
        ctrl.btnFormRevertLbl = 'Cancel';
        // end dynamic properties for the common template
        
        this.$onInit = function () {
            ctrl.api = {};
            ctrl.api.edit = ctrl.editAction;
            ctrl.onInit({api: ctrl.api});            
        }

        ctrl.submitAction = function () {

            console.log('Updating Contact', ctrl.contact);
        
            contactService.updateContact(ctrl.contact, ctrl.contact.id)

            .then(
                function () {                    
                    ctrl.onUpdate({"contact": ctrl.contact});                    
                },
                function (errResponse) {
                    ctrl.onError();
                }
            );
        }

        ctrl.editAction = function (contact) {
            ctrl.contact = contact;
        };

        ctrl.resetAction = function () {                    
            ctrl.onCancel();
        }


    };


    angular.module('myContactApp')
        .component('contactEdit', {
            bindings: {
                onError: '&',                
                onCancel: '&',
                onUpdate: '&',                
                onInit : '&'
            },
            templateUrl: 'components/common/contactCreationOrEdit.html?' + new Date(),
            controller: ContactEditController
        })


    //END IIFE
})();