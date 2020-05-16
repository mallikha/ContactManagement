(function () {
    'use strict';
    //IIFE


    function ContactCreationController($scope, contactService) {
        var ctrl = this;

        ctrl.sexes = contactNgUtil.getSexes();

        // dynamic properties for the common template
        ctrl.title = 'Contact Creation';
        ctrl.btnFormValidationLbl = 'Create';
        ctrl.btnFormRevertLbl = 'Clear';
        // end dynamic properties for the common template

        this.$onInit = function () {
            ctrl.contact = ctrl.createEmptyContact();      
        }

        ctrl.submitAction = function () {

            console.log('Saving New Contact', ctrl.contact);

            contactService.createContact(ctrl.contact)
                .then(
                    function (newId) {
                        ctrl.contact.id = newId;
                        ctrl.onContactAdded({
                            createdContact: ctrl.contact
                        });
                        ctrl.resetAction();
                    },
                    function (errResponse) {
                        ctrl.onError();
                    }
                );
        }
        
        ctrl.createEmptyContact = function () {
            var contact = {
                id: null,
                firstName: '',
                lastName: '',
                birthday: null,
                email: '',
                phone: '',
                sex: '',
                address: {
                    addressLine: '',
                    city: '',
                    zip: '',
                    country: ''
                }
            }
            return contact;
        }        

        ctrl.resetAction = function () {
            ctrl.contact = ctrl.createEmptyContact();
            $scope.myForm.$setPristine(); // reset Form            
        }


    };


    angular.module('myContactApp')
        .component('contactCreation', {
            bindings: {
                onContactAdded: '&',
                onError: '&'
            },
            templateUrl: 'components/common/contactCreationOrEdit.html?' + new Date(),
            controller: ContactCreationController
        })


    //END IIFE
})();