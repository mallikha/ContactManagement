(function () {
    'use strict';
    //IIFE


    function ContactListController($scope, contactService) {

        var ctrl = this;
        ctrl.contacts = [];
        ctrl.addressMode = 'LONG';

        this.$onInit = function () {
            ctrl.fetchAllContacts();
  		  	ctrl.api = {};
  		  	ctrl.api.refreshExistingContact = ctrl.refreshExistingContact;
        };

        ctrl.fetchAllContacts = function () {
            contactService.fetchAllContacts()
                .then(
                    function (contacts) {
                        ctrl.contacts = contacts;
                    },
                    function (errResponse) {
                        ctrl.onError();
                    }
                );
        }
        
        ctrl.refreshExistingContact = function(contact){
        	console.log("refresh contact");
        	
	      	 for(var i = 0; i < ctrl.contacts.length; i++){
	      		 var currentContact = ctrl.contacts[i];
	      		 
	      		   if (contact.id == currentContact.id){
	      			   for(var j in contact) currentContact[j] = contact[j];
	      		   }
	         }		
        }                
        

        /**
              user action
          */
        ctrl.toggleAddressMode = function () {
            if (ctrl.addressMode == 'LONG') {
                ctrl.addressMode = 'SHORT';
            } else {
                ctrl.addressMode = 'LONG';
            }
        }

        ctrl.isShortAddressMode = function () {
            if (ctrl.addressMode == 'SHORT') {
                return true;
            }
            return false;
        }

        ctrl.isFullAddressMode = function () {
            return !ctrl.isShortAddressMode();
        }
        
        ctrl.deleteAction = function (contact) {
            var index = ctrl.contacts.indexOf(contact);

            if (index == -1) {
                return;
            }

            contactService.deleteContact(contact.id)
                .then(
                    function () {
                        ctrl.contacts.splice(index, 1);
                        ctrl.onContactDeleted({
                            "contactId": contact.id
                        });
                    },
                    function (errResponse) {
                        ctrl.onError();
                    }
                );

        }
        
        ctrl.editAction = function (contact) {
           var copyContact = angular.copy(contact);
           ctrl.onEditRequest({"contact" : copyContact});
        }        
    }



    angular.module('myContactApp').component('contactList', {

        templateUrl: 'components/manageContacts/contactList.html?' + new Date(),
        controller: ContactListController,
        bindings: {
            onError: '&',
            onEditRequest : '&',
            onContactDeleted : "&",
            api : '='
        }
    });



    //END IIFE
})();