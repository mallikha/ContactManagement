(function () {
    'use strict';
    //IIFE

    function ContactsManagementViewController(baseParentComponentController, $mdSidenav) {
        var ctrl = this;
        angular.extend(ContactsManagementViewController.prototype, baseParentComponentController);
        		
		ctrl.registerApiContactEdit = function (api) {
			ctrl.apiContactEdit = api; 
		}
		
		ctrl.showContactEdit = function () {		    
			$mdSidenav('rightComponent')
			  .open()
			  .then(function () {
			    console.debug("showContactEdit done");
			  });   
		}
		
		ctrl.hideContactEdit =  function () {
			$mdSidenav('rightComponent').close()
			  .then(function () {
			  	console.debug("hideContactEdit done");
			  });
		}
		
		ctrl.contactAdded = function (createdContact) {
            ctrl.displaySuccessMsg("Contact with id " + createdContact.id + " added.");
        }
		
		ctrl.contactDeleted = function (contactId) {
		    ctrl.displaySuccessMsg("Contact with id " + contactId + " deleted.");
		}        
		
		ctrl.handleEditRequest = function(contact){
			console.debug("handleEditRequest with id "+ contact);
			ctrl.showContactEdit();
			ctrl.apiContactEdit.edit(contact);
		}
		
		ctrl.contactEditCanceled = function (){
			ctrl.hideContactEdit();
		}
		
		ctrl.contactUpdated = function (contact){
			ctrl.hideContactEdit();
			ctrl.apiContactList.refreshExistingContact(contact);
		}

    }


    angular.module('myContactApp').component('contactsManagementView', {
        templateUrl: 'components/manageContacts/contactsManagementView.html?' + new Date(),
        controller: ContactsManagementViewController
    });

    //END IIFE
})();